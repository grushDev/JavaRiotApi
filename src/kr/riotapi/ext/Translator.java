package kr.riotapi.ext;

import com.google.gson.JsonElement;

/**
 * Created with IntelliJ IDEA.
 * User: kenneth
 * Date: 26.12.13
 * Time: 11:33
 * To change this template use File | Settings | File Templates.
 */
public interface Translator<V> {

    public V translate(JsonElement element);
}
