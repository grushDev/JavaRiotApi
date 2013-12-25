package kr.riotapi.ext;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import kr.riotapi.core.ApiCall;
import kr.riotapi.core.ApiDomain;
import kr.riotapi.core.ApiRequest;

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
    protected final Gson gson;

    public RiotApi(String apiKey) {
        super(apiKey);
        this.summoner = new SummonerService(this);
        gson = new Gson();
    }

    JsonElement executeAndParse(ApiCall call) throws IOException {
        String jsonSource = execute(call);
        return gson.fromJson(jsonSource, JsonElement.class);
    }
}
