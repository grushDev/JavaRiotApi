package kr.riotapi.ext;

import com.google.gson.JsonElement;
import kr.riotapi.core.ApiRequest;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: kenneth
 * Date: 25.12.13
 * Time: 21:53
 * To change this template use File | Settings | File Templates.
 */
public class GameService extends ExtApiService {

    protected final ApiRequest recent;

    public GameService(RiotApi api) {
        super("game", "v1.2", api);
        this.recent = request("by-summoner/{id}/recent");
    }

    public JsonElement recent(RegionEnum region, int summonerId) throws IOException {
        return executeAndParse(recent.pathParameter("id", summonerId).forRegion(region.abbr));
    }

    public JsonElement recent(int summonerId) throws IOException {
        return recent(defaultRegion(), summonerId);
    }
}
