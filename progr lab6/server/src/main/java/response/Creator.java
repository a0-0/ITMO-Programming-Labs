package response;

import model.Response;

public interface Creator {
    Response createResponse(String message, boolean success, boolean productRequired);

    Response createResponse();

    void addToMsg(String msg);
}
