package kr.riotapi.ext;

import com.google.gson.JsonElement;
import kr.riotapi.core.ApiRequest;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: kenneth
 * Date: 26.12.13
 * Time: 10:18
 * To change this template use File | Settings | File Templates.
 */
public class StatsService extends ExtApiService {

    protected final ApiRequest ranked;
    protected final ApiRequest summary;

    public StatsService(RiotApi api) {
        super("stats", "v1.2", api);
        this.ranked = request("by-summoner/{id}/ranked");
        this.summary = request("by-summoner/{id}/summary");
    }

    public JsonElement ranked(RegionEnum region, int summonerId) throws IOException {
        return executeAndParse(ranked.pathParameter("id", summonerId).forRegion(region.abbr));
    }

    public JsonElement ranked(int summonerId) throws IOException {
        return ranked(defaultRegion(), summonerId);
    }

    public JsonElement ranked(RegionEnum region, int summonerId, String seasonString) throws IOException {
        return executeAndParse(ranked.pathParameter("id", summonerId).queryParameter("season", seasonString).forRegion(region.abbr));
    }

    public JsonElement ranked(int summonerId, String seasonString) throws IOException {
        return ranked(defaultRegion(), summonerId, seasonString);
    }

    public JsonElement summary(RegionEnum region, int summonerId) throws IOException {
        return executeAndParse(summary.pathParameter("id", summonerId).forRegion(region.abbr));
    }

    public JsonElement summary(int summonerId) throws IOException {
        return summary(defaultRegion(), summonerId);
    }

    public JsonElement summary(RegionEnum region, int summonerId, String seasonString) throws IOException {
        return executeAndParse(summary.pathParameter("id", summonerId).queryParameter("season", seasonString).forRegion(region.abbr));
    }

    public JsonElement summary(int summonerId, String seasonString) throws IOException {
        return summary(defaultRegion(), summonerId, seasonString);
    }
}
