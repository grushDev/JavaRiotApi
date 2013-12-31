package kr.riotapi.ext;

import com.google.gson.JsonElement;
import org.junit.Test;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: kenneth
 * Date: 31.12.13
 * Time: 15:01
 * To change this template use File | Settings | File Templates.
 */
public class ExtTest {

    RiotApi api = new RiotApi("MY_KEY", RegionEnum.EUROPE_WEST);

    @Test
    public void setUp() throws IOException, ApiException {
        JsonElement element = api.summoner.byName("grushnakk");
    }
}
