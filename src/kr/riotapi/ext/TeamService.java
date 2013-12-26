package kr.riotapi.ext;

import com.google.gson.JsonElement;
import kr.riotapi.core.ApiRequest;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: kenneth
 * Date: 26.12.13
 * Time: 10:26
 * To change this template use File | Settings | File Templates.
 */
public class TeamService extends ExtApiService {

    protected final ApiRequest bySummoner;

    public TeamService(RiotApi api) {
        super("team", "v2.2", api);
        this.bySummoner = request("by-summoner/{id}");
    }

    public JsonElement bySummoner(RegionEnum region, int summonerId) throws IOException, ApiException {
        return executeAndParse(bySummoner.pathParameter("id", summonerId).forRegion(region.abbr));
    }

    public JsonElement bySummoner(int summonerId) throws IOException, ApiException {
        return bySummoner(defaultRegion(), summonerId);
    }
}
