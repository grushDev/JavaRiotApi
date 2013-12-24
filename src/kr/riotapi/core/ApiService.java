package kr.riotapi.core;

/**
 * Created with IntelliJ IDEA.
 * User: kenneth
 * Date: 24.12.13
 * Time: 14:43
 * To change this template use File | Settings | File Templates.
 */
public class ApiService {

    protected final String name;
    protected final String version;
    protected final RiotApi api;

    ApiService(String name, String version, RiotApi api) {
        this.name = name;
        this.version = version;
        this.api = api;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public RiotApi getApi() {
        return api;
    }

    public ApiRequest request(String template) {
        return new ApiRequest(template, this);
    }
}
