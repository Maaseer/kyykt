package RestResult;

public enum ResultCode {
    SUCCESS(200),
    BAD_REQUEST(400),
    FORBIDDEN(403),
    NOT_FOUNT(404),
    INTERNAL_SERVER_ERROR(500)
    ;

    private final int code;
    ResultCode(int code) {this.code = code;}
    public int getCode(){return code;}
}
