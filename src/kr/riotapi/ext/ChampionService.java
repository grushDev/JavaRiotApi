package kr.riotapi.ext;

import com.google.gson.JsonElement;
import kr.riotapi.core.ApiRequest;

import java.io.IOException;
import java.util.List;

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

    public <V> List<V> champions(RegionEnum region, Class<V> returnType) throws IOException, ApiException {
        return translateList(returnType, champions(region));
    }

    public JsonElement champions() throws IOException, ApiException {
        return champions(defaultRegion());
    }

    public <V> List<V> champions(Class<V> returnType) throws IOException, ApiException {
        return translateList(returnType, champions());
    }

    public JsonElement champions(RegionEnum region, boolean freeToPlay) throws IOException, ApiException {
        return executeAndParse(champion.queryParameter("freeToPlay", freeToPlay).forRegion(region.abbr));
    }

    public <V> List<V> champions(RegionEnum region, boolean freeToPlay, Class<V> returnType) throws IOException, ApiException {
        return translateList(returnType, champions(region, freeToPlay));
    }

    public JsonElement champions(boolean freeToPlay) throws IOException, ApiException {
        return champions(defaultRegion(), freeToPlay);
    }

    public <V> List<V> champions(boolean freeToPlay, Class<V> returnType) throws IOException, ApiException {
        return translateList(returnType, champions(freeToPlay));
    }
}
