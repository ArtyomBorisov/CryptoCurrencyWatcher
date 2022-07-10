package watcher.controllers.advice;

public class ResponseError {
    private String header;
    private String message;

    public ResponseError(String statusCode, String message) {
        this.header = statusCode;
        this.message = message;
    }

    public String getHeader() {
        return header;
    }

    public String getMessage() {
        return message;
    }
}
