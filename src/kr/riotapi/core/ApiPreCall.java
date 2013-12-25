package kr.riotapi.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kenneth
 * Date: 24.12.13
 * Time: 14:52
 * To change this template use File | Settings | File Templates.
 */
public class ApiPreCall {

    protected List<ApiParameter> parameters;
    protected final ApiRequest request;

    ApiPreCall(ApiParameter parameter, ApiRequest request) {
        this.request = request;
        this.parameters = new ArrayList<ApiParameter>();
        parameters.add(parameter);
    }

    ApiPreCall(List<ApiParameter> parameters, ApiParameter parameter, ApiRequest request) {
        this.parameters = new ArrayList<ApiParameter>(parameters);
        this.parameters.add(parameter);
        this.request = request;
    }

    public ApiPreCall parameter(ApiParameter.Type type, String key, Object value) {
        return new ApiPreCall(parameters, new ApiParameter(type, key, value), request);
    }

    public ApiPreCall pathParameter(String key, Object value) {
        return new ApiPreCall(parameters, new ApiParameter(ApiParameter.Type.PATH, key, value), request);
    }

    public ApiPreCall queryParameter(String key, Object value) {
        return new ApiPreCall(parameters, new ApiParameter(ApiParameter.Type.QUERY, key, value), request);
    }

    public ApiCall forRegion(String region) {
        return new ApiCall(region, parameters, request);
    }


}
