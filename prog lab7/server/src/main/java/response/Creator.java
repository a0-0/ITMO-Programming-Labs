package response;

import transferobjects.Response;

public interface Creator {
    Response createResponse(String message, boolean success, boolean productRequired);

    Response createResponse();

    void addToMsg(String msg);

    void setObject(Object o);
}
