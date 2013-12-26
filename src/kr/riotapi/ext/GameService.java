package kr.riotapi.ext;

import com.google.gson.JsonElement;
import kr.riotapi.core.ApiRequest;

import java.io.IOException;
import java.util.List;

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

    public JsonElement recent(RegionEnum region, int summonerId) throws IOException, ApiException {
        return executeAndParse(recent.pathParameter("id", summonerId).forRegion(region.abbr));
    }

    public <V> List<V> recent(RegionEnum region, int summonerId, Class<V> returnType) throws IOException, ApiException {
        JsonElement element = recent(region, summonerId);
        return translateList(returnType, element);
    }

    public JsonElement recent(int summonerId) throws IOException, ApiException {
        return recent(defaultRegion(), summonerId);
    }

    public <V> List<V> recent(int summonerId, Class<V> returnType) throws IOException, ApiException {
        JsonElement element = recent(summonerId);
        return translateList(returnType, element);
    }
}
