package kr.riotapi.ext;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import kr.riotapi.core.ApiCall;
import kr.riotapi.core.ApiDomain;
import kr.riotapi.core.StatusCodeException;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: kenneth
 * Date: 25.12.13
 * Time: 17:19
 * To change this template use File | Settings | File Templates.
 */
public class RiotApi extends ApiDomain {

    public final SummonerService summoner;
    public final ChampionService champion;
    public final LeagueService league;
    public final GameService game;
    public final StatsService stats;
    public final TeamService team;

    protected final Gson gson;
    protected RegionEnum defaultRegion = RegionEnum.NORTH_AMERICA;

    public RiotApi(String apiKey) {
        super(apiKey);
        this.summoner = new SummonerService(this);
        this.champion = new ChampionService(this);
        this.game = new GameService(this);
        this.league = new LeagueService(this);
        this.stats = new StatsService(this);
        this.team = new TeamService(this);
        gson = new Gson();
    }

    public RiotApi(String apiKey, RegionEnum defaultRegion) {
        this(apiKey);
        this.defaultRegion = defaultRegion;
    }

    JsonElement executeAndParse(ApiCall call) throws IOException, ApiException {
        String jsonSource = null;
        try {
            jsonSource = execute(call);
        } catch(StatusCodeException ex) {
            throw new ApiException(ApiExceptionCause.byCode(ex.code), ex.code);
        }
        return gson.fromJson(jsonSource, JsonElement.class);
    }

    public RegionEnum getDefaultRegion() {
        return defaultRegion;
    }

    public void setDefaultRegion(RegionEnum defaultRegion) {
        this.defaultRegion = defaultRegion;
    }
}
