package kr.riotapi.core;

/**
 * Created with IntelliJ IDEA.
 * User: kenneth
 * Date: 24.12.13
 * Time: 14:53
 * To change this template use File | Settings | File Templates.
 */
public class ApiParameter {
    public static enum Type {
        PATH, QUERY;
    }

    protected final Type type;
    protected final String key;
    protected final Object value;

    public ApiParameter(Type type, String key, Object value) {
        this.type = type;
        this.key = key;
        this.value = value;
    }


    public Type getType() {
        return type;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }
}
