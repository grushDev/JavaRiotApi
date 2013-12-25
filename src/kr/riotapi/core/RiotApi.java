package kr.riotapi.core;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kenneth
 * Date: 24.12.13
 * Time: 14:41
 * To change this template use File | Settings | File Templates.
 */
public class RiotApi {

    protected final String apiKey;
    protected final String baseUrl = "http://prod.api.pvp.net/api/lol";

    public RiotApi(String apiKey) {
        this.apiKey = apiKey;
    }

    public ApiService service(String name, String version) {
        return new ApiService(name, version, this);
    }

    String concatenateURL(List<ApiParameter> parameters, String... parts) {
        StringBuilder builder = new StringBuilder(baseUrl);
        for(String part : parts) {
            builder.append("/").append(part);
        }
        builder.append("?");
        for(ApiParameter parameter : parameters) {
            if(parameter.getType() != ApiParameter.Type.QUERY) continue;
            builder.append(parameter.getKey()).append("=").append(parameter.getValue()).append("&");
        }
        builder.append("api_key=").append(apiKey);
        return builder.toString();
    }

    String execute(ApiCall call) {
        HttpGet get = new HttpGet(call.toUrlString());
        String body = null;
        int statusCode = 0;
        try(CloseableHttpClient client = HttpClients.createDefault()) {
            CloseableHttpResponse response = client.execute(get);
            statusCode = response.getStatusLine().getStatusCode();
            body = EntityUtils.toString(response.getEntity());
            response.close();
        } catch(IOException ex) {
            throw new UnsupportedOperationException("not supported yet."); //TODO throw custom exception
        }
        if(statusCode != 200) //TODO magic number
            throw new UnsupportedOperationException("not supported yet."); //TODO throw custom exception
        return body;
    }
}
