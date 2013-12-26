package kr.riotapi.core;

import org.apache.http.HttpStatus;
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
public class ApiDomain { //TODO find better name???

    protected final String apiKey;
    protected final String baseUrl = "http://prod.api.pvp.net/api/lol";

    public ApiDomain(String apiKey) {
        this.apiKey = apiKey;
    }

    public ApiService service(String name, String version) {
        return new ApiService(name, version, this);
    }

    public String concatenateURL(List<ApiParameter> parameters, String... parts) {
        StringBuilder builder = new StringBuilder(baseUrl);
        for(String part : parts) {
            if(part == null || part.equals("")) continue;
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

    public String execute(ApiCall call) throws IOException, StatusCodeException {
        HttpGet get = new HttpGet(call.toUrlString());
        String body = null;
        int statusCode = 0;
        try(CloseableHttpClient client = HttpClients.createDefault()) {
            CloseableHttpResponse response = client.execute(get);
            statusCode = response.getStatusLine().getStatusCode();
            body = EntityUtils.toString(response.getEntity());
            response.close();
        } catch(IOException ex) {
            throw new IOException("There was a problem receiving or processing a server response: " + ex.getMessage(), ex);
        }
        if(statusCode != HttpStatus.SC_OK)
            throw new StatusCodeException("status code was not 200", statusCode);
        return body;
    }
}
