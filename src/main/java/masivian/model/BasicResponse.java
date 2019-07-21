package masivian.model;

/**
 * Envelop response, from the controller to the spark response
 */
public class BasicResponse {

    private int status;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BasicResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

}
