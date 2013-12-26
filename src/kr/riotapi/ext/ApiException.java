package kr.riotapi.ext;

/**
 * Created with IntelliJ IDEA.
 * User: kenneth
 * Date: 26.12.13
 * Time: 11:09
 * To change this template use File | Settings | File Templates.
 */
public class ApiException extends Exception {

    public final ApiExceptionCause cause;
    public final int code;

    public ApiException(ApiExceptionCause cause, int code) {
        this.cause = cause;
        this.code = code;
    }
}
