package kr.riotapi;

import com.google.gson.JsonElement;
import kr.riotapi.core.*;
import kr.riotapi.ext.ApiException;
import kr.riotapi.ext.RegionEnum;
import kr.riotapi.ext.RiotApi;
import kr.riotapi.ext.Translator;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: kenneth
 * Date: 28.12.13
 * Time: 20:00
 * To change this template use File | Settings | File Templates.
 */
public class Example {

    public static void main(String[] args) throws IOException, StatusCodeException, ApiException {

        //getting a summoner from name only using the core classes
        ApiDomain domain = new ApiDomain("MY_KEY");
        ApiService summonerService = domain.service("summoner", "v1.1");
        ApiRequest summonerByNameRequest = summonerService.request("by-name/{summonerName}");
        ApiCall call = summonerByNameRequest.pathParameter("summonerName", "grushnakk").forRegion("euw");
        String body = call.execute(); //IOException, StatusCodeException
        //concatenate statements
        String body2 = new ApiDomain("MY_KEY")
                .service("summoner", "v1.1")
                .request("by-name/{summonerName}")
                .pathParameter("summonerName", "grushnakk")
                .forRegion("euw").execute();
        //Using the Extension
        RiotApi api = new RiotApi("MY_KEY");
        JsonElement element = api.summoner.byName(RegionEnum.EUROPE_WEST, "grushnakk"); //IOException, ApiException
        //default region
        api = new RiotApi("MY_KEY", RegionEnum.EUROPE_WEST);
        element = api.summoner.byName("grushnakk");
        //using translators
        api.registerTranslator(Integer.class, new Translator<Integer>() {
            @Override
            public Integer translate(JsonElement element) {
                return 5; //this example is kinda stupid
            }
        });
        int five = api.summoner.byName("grushnakk", Integer.class);
        System.out.println(five);
    }
}
