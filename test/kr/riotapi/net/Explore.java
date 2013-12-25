package kr.riotapi.net;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: kenneth
 * Date: 25.12.13
 * Time: 10:00
 * To change this template use File | Settings | File Templates.
 */
public class Explore {

    public static void main(String[] args) {
        String url = "http://prod.api.pvp.net/api/lol/euw/v1.2/stats/by-summoner/30957481/ranked?season=SEASON3&api_key=f677aa8e-eef1-46dc-9a23-5547b417046a";
        HttpGet getRequest = new HttpGet(url);
        try(CloseableHttpClient client = HttpClients.createDefault()) {
            CloseableHttpResponse response = client.execute(getRequest);
            int statusCode = response.getStatusLine().getStatusCode();
            String json  = EntityUtils.toString(response.getEntity());
            System.out.println(json);
            response.close();
        } catch(IOException ioEx) {
            ioEx.printStackTrace();
        }
    }
}
