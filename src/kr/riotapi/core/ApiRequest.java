package kr.riotapi.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kenneth
 * Date: 24.12.13
 * Time: 14:45
 * To change this template use File | Settings | File Templates.
 */
public class ApiRequest {

    protected final String template;
    protected final ApiService service;

    ApiRequest(String template, ApiService service) {
        this.template = template;
        this.service = service;
    }

    public ApiService getService() {
        return service;
    }

    public ApiPreCall parameter(ApiParameter.Type type, String key, Object value) {
        return new ApiPreCall(new ApiParameter(type, key, value), this);
    }

    public ApiPreCall pathParameter(String key, Object value) {
        return new ApiPreCall(new ApiParameter(ApiParameter.Type.PATH, key, value), this);
    }

    public ApiPreCall queryParameter(String key, Object value) {
        return new ApiPreCall(new ApiParameter(ApiParameter.Type.QUERY, key, value), this);
    }

    public ApiCall forRegion(String region) {
        return new ApiCall(region, new ArrayList<ApiParameter>(), this);
    }

    String format(List<ApiParameter> parameters) {
        String temp = template;
        for(ApiParameter parameter : parameters) {
            if(parameter.getType() != ApiParameter.Type.PATH) continue;
            temp = temp.replace("{" + parameter.getKey() + "}", parameter.getValue().toString());
        }
        return temp;
    }
}
