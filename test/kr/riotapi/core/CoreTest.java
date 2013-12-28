package kr.riotapi.core;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: kenneth
 * Date: 28.12.13
 * Time: 19:48
 * To change this template use File | Settings | File Templates.
 */
public class CoreTest {

    @Test
    public void testChampionRequest(){
        String expected = "http://prod.api.pvp.net/api/lol/euw/v1.1/champion?api_key=MY_KEY";
        ApiDomain domain = new ApiDomain("MY_KEY");
        ApiService service = domain.service("champion", "v1.1");
        ApiRequest request = service.request("");
        ApiCall call = request.forRegion("euw");
        String actual = call.toUrlString();
        assertEquals(expected, actual);
    }

    @Test
    public void testChampionFreeToPlay() {
        String expected = "http://prod.api.pvp.net/api/lol/euw/v1.1/champion?freeToPlay=true&api_key=MY_KEY";
        ApiDomain domain = new ApiDomain("MY_KEY");
        ApiService service = domain.service("champion", "v1.1");
        ApiRequest request = service.request("");
        ApiPreCall preCall = request.queryParameter("freeToPlay", true);
        ApiCall call = preCall.forRegion("euw");
        String actual = call.toUrlString();
        assertEquals(expected, actual);
    }
}
