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
    protected final ApiRequest byId;
    protected final ApiRequest runes;
    protected final ApiRequest masteries;

    public SummonerService(RiotApi api) {
        super("summoner", "v1.2", api);
        this.byName = request("by-name/{name}");
        this.byId = request("{id}");
        this.runes = request("{id}/runes");
        this.masteries = request("{id}/masteries");
    }

    public JsonElement byName(RegionEnum region, String name) throws IOException {
        return executeAndParse(byName.pathParameter("name", name).forRegion(region.abbr));
    }

    public JsonElement byName(String name) throws IOException {
        return byName(defaultRegion(), name);
    }

    public JsonElement byId(RegionEnum region, int summonerId) throws IOException {
        return executeAndParse(byId.pathParameter("id", summonerId).forRegion(region.abbr));
    }

    public JsonElement byId(int summonerId) throws IOException {
        return byId(defaultRegion(), summonerId);
    }

    public JsonElement runes(RegionEnum region, int summonerId) throws IOException {
        return executeAndParse(runes.pathParameter("id", summonerId).forRegion(region.abbr));
    }

    public JsonElement runes(int summonerId) throws IOException {
        return runes(defaultRegion(), summonerId);
    }

    public JsonElement masteries(RegionEnum region, int summonerId) throws IOException {
        return executeAndParse(masteries.pathParameter("id", summonerId).forRegion(region.abbr));
    }

    public JsonElement masteries(int summonerId) throws IOException {
        return masteries(defaultRegion(), summonerId);
    }
}
