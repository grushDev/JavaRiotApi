package kr.riotapi.ext;

import com.google.gson.JsonElement;
import kr.riotapi.core.ApiCall;
import kr.riotapi.core.ApiService;

import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kenneth
 * Date: 25.12.13
 * Time: 17:40
 * To change this template use File | Settings | File Templates.
 */
public class ExtApiService extends ApiService {

    public ExtApiService(String name, String version, RiotApi api) {
        super(name, version, api);
    }

    protected JsonElement executeAndParse(ApiCall call) throws IOException, ApiException {
        return getRiotApi().executeAndParse(call);
    }

    public RegionEnum defaultRegion() {
        return getRiotApi().getDefaultRegion();
    }

    public RiotApi getRiotApi() {
        return (RiotApi)api;
    }

    public <V> V translate(Class<V> key, JsonElement data) {
        return getRiotApi().translate(key, data);
    }

    public <V> List<V> translateList(Class<V> key, JsonElement data) {
        return getRiotApi().translateList(key, data);
    }
}
