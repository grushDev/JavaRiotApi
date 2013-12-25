package kr.riotapi.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kenneth
 * Date: 24.12.13
 * Time: 15:01
 * To change this template use File | Settings | File Templates.
 */
public class ApiCall {

    protected List<ApiParameter> parameters;
    protected final ApiRequest request;
    protected final String region;

    public ApiCall(String region, List<ApiParameter> parameters, ApiRequest request) {
        this.parameters = new ArrayList<ApiParameter>(parameters);
        this.request = request;
        this.region = region;
    }

    public String toUrlString() {
        return request.getService().getApi().concatenateURL(
                parameters,
                region,
                request.getService().getVersion(),
                request.getService().getName(),
                request.format(parameters)
        );
    }

    @Override
    public String toString() {
        return toUrlString();
    }

    public String execute() throws IOException {
        return request.getService().getApi().execute(this);
    }
}
