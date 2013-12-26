package kr.riotapi.ext;

import com.google.gson.JsonElement;
import kr.riotapi.core.ApiRequest;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: kenneth
 * Date: 25.12.13
 * Time: 17:49
 * To change this template use File | Settings | File Templates.
 */
public class ChampionService extends ExtApiService {

    protected final ApiRequest champion;

    public ChampionService(RiotApi api) {
        super("champion", "v1.1", api);
        this.champion = request("");
    }

    public JsonElement champions(RegionEnum region) throws IOException, ApiException {
        return champions(region, false);
    }

    public JsonElement champions() throws IOException, ApiException {
        return champions(defaultRegion());
    }

    public JsonElement champions(RegionEnum region, boolean freeToPlay) throws IOException, ApiException {
        return executeAndParse(champion.queryParameter("freeToPlay", freeToPlay).forRegion(region.abbr));
    }

    public JsonElement champions(boolean freeToPlay) throws IOException, ApiException {
        return champions(defaultRegion(), freeToPlay);
    }
}
