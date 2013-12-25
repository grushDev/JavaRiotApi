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

    public JsonElement champions(RegionEnum region) throws IOException{
        return executeAndParse(champion.forRegion(region.abbr));
    }
}
