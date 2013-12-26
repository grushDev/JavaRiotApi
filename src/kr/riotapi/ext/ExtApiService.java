package kr.riotapi.ext;

import com.google.gson.JsonElement;
import kr.riotapi.core.ApiCall;
import kr.riotapi.core.ApiService;

import java.io.IOException;

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

    protected JsonElement executeAndParse(ApiCall call) throws IOException {
        return ((RiotApi)api).executeAndParse(call);
    }

    public RegionEnum defaultRegion() {
        return ((RiotApi)api).getDefaultRegion();
    }
}
