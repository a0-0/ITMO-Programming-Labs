package client;

public class Main {
    public static void main(String[] args)  {
        if (args.length == 2) {
            Application application = new Application(args[0], Integer.parseInt(args[1]));
            application.start();
        } else {
            System.err.println("usage: program_name address port");
        }
    }
}
