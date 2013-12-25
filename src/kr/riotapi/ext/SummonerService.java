package kr.riotapi.ext;

import com.google.gson.JsonElement;
import kr.riotapi.core.ApiRequest;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: kenneth
 * Date: 25.12.13
 * Time: 17:20
 * To change this template use File | Settings | File Templates.
 */
public class SummonerService extends ExtApiService {

    protected final ApiRequest byName;

    public SummonerService(RiotApi api) {
        super("summoner", "v1.2", api);
        byName = this.request("by-name/{name}");
    }

    public JsonElement byName(RegionEnum region, String name) throws IOException {
        return executeAndParse(byName.pathParameter("name", name).forRegion(region.abbr));
    }
}
