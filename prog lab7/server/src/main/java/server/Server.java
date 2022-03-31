package server;

import connection.ConnectionListener;
import connection.request.RequestHandler;
import connection.request.RequestReader;
import exception.AuthException;
import exception.CommandExecutionException;
import exception.CommandNotFoundException;
import exception.PersistentException;
import log.Log;
import response.ResponseSender;
import transferobjects.Request;
import transferobjects.RequestOpsState;
import transferobjects.Response;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

import static java.nio.channels.SelectionKey.OP_READ;
import static java.nio.channels.SelectionKey.OP_WRITE;

public class Server implements Runnable{
    private final ExecutorService readRequests = Executors.newFixedThreadPool(10);
    private final ExecutorService sendResponses = new ForkJoinPool(10);
    private final ConnectionListener connectionListener;
    private final RequestReader requestReader;
    private final RequestHandler requestHandler;
    private final ResponseSender responseSender;
    private final Selector selector;
    private final List<RequestOpsState> changeRequests = new ArrayList<>();
    private final Map<SocketChannel, Response> responseMap = new HashMap<>();

    public Server(ConnectionListener listener, RequestReader reader, RequestHandler handler, ResponseSender sender) throws IOException {
        connectionListener = listener;
        requestReader = reader;
        requestHandler = handler;
        responseSender = sender;
        selector = connectionListener.openConnection();
    }

    @Override
    public void run() {
        while(true) {
            try {
                synchronized (changeRequests) {
                    for (RequestOpsState requestOpsState : changeRequests) {
                        if (requestOpsState.getType() == RequestOpsState.CHANGEOPS) {
                            SelectionKey key = requestOpsState.getSocketChannel().keyFor(selector);
                            key.interestOps(requestOpsState.getOps());
                        } else if (requestOpsState.getType() == RequestOpsState.DEREGISTER) {
                            SelectionKey key = requestOpsState.getSocketChannel().keyFor(selector);
                            key.cancel();
                            requestOpsState.getSocketChannel().close();
                        }
                    }
                    changeRequests.clear();
                }

                selector.select();

                Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
                while (selectedKeys.hasNext()) {
                    SelectionKey key = selectedKeys.next();
                    selectedKeys.remove();

                    if (!key.isValid()) {
                        continue;
                    }

                    if (key.isAcceptable()) {
                        connectionListener.accept(key);
                    } else if (key.isReadable()) {
                        read(key);
                    } else if (key.isWritable()) {
                        sendResponse(key);
                        key.interestOps(0);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void read(SelectionKey selectionKey) {
        Runnable readingRequestRunnable = () -> {
            try {
                Request request = requestReader.getRequest(selectionKey);
                System.out.println(request.getUserString());
                handleRequest(request);
            } catch (IOException | ClassNotFoundException e) {
                Log.getLogger().error(e);
                Log.getLogger().error(e.getStackTrace());
            }
        };
        readRequests.submit(readingRequestRunnable);
    }

    private void handleRequest(Request request) {
        Runnable handlingRequestRunnable = () -> {
            try {
                Response response = requestHandler.handleRequest(request);
                response.setSocketChannel(request.getSocketChannel());
                prepareToSend(response);
            } catch (CommandNotFoundException | CommandExecutionException | AuthException e) {
                Log.getLogger().error(e);
                Log.getLogger().error(e.getStackTrace());
                Response response = new Response(e.getMessage(), false, false);
                response.setSocketChannel(request.getSocketChannel());
                prepareToSend(response);
            } catch (PersistentException e) {
                Log.getLogger().error(e.getDbErrorMessage());
                Log.getLogger().error(e);
                Response response = new Response(e.getMessage(), false, false);
                response.setSocketChannel(request.getSocketChannel());
                prepareToSend(response);
            }
        };
        new Thread(handlingRequestRunnable).start();
    }

    private void prepareToSend(Response response) {
        synchronized (changeRequests) {
            changeRequests.add(new RequestOpsState(response.getSocketChannel(), RequestOpsState.CHANGEOPS, OP_WRITE));
            synchronized (responseMap) {
                responseMap.put(response.getSocketChannel(), response);
            }
        }
        this.selector.wakeup();
    }

    private void sendResponse(SelectionKey selectionKey) {
        Runnable sendingResponseRunnable = () -> {
            try {
                synchronized (responseMap) {
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    responseSender.sendResponse(channel, responseMap.get(selectionKey.channel()));
                    synchronized (changeRequests) {
                        changeRequests.add(new RequestOpsState((SocketChannel) selectionKey.channel(), RequestOpsState.CHANGEOPS, OP_READ));
                    }
                }
                selector.wakeup();
            } catch (IOException | ClassNotFoundException e) {
                Log.getLogger().error(e);
                Log.getLogger().error(e.getStackTrace());
            }
        };
        sendResponses.submit(sendingResponseRunnable);
    }

    public void shutdownExecutorServices() {
        readRequests.shutdownNow();
        sendResponses.shutdown();
    }

}
