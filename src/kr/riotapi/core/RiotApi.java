package kr.riotapi.core;

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
}
