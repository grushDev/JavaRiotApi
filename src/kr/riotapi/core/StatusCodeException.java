package kr.riotapi.core;

/**
 * Created with IntelliJ IDEA.
 * User: kenneth
 * Date: 26.12.13
 * Time: 11:01
 * To change this template use File | Settings | File Templates.
 */
public class StatusCodeException extends Exception {

    public final int code;

    public StatusCodeException(String message, int code) {
        super(message);
        this.code = code;
    }
}
