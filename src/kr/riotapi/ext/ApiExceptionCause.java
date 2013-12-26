package kr.riotapi.ext;

/**
 * Created with IntelliJ IDEA.
 * User: kenneth
 * Date: 26.12.13
 * Time: 11:07
 * To change this template use File | Settings | File Templates.
 */
public enum ApiExceptionCause {
    UNKNOWN(-1),
    SUMMONER_NOT_FOUND(404),
    BAD_REQUEST(400),
    INTERNAL_SERVER_ERROR(500),
    UNAUTHORIZED(401);

    public final int code;

    ApiExceptionCause(int code) {
        this.code = code;
    }

    public static ApiExceptionCause byCode(int code) {
        for(ApiExceptionCause error : values()) {
            if(error.code == code)
                return error;
        }
        return UNKNOWN;
    }
}
