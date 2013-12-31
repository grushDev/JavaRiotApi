/*
 * Copyright (C) 2013  Kenneth Radunz kenneth.r.dev@gmail.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package kr.riotapi.core;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

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
            throw new IOException("There was a problem receiving or processing a server response", ex);
        }
        if(statusCode != HttpStatus.SC_OK)
            throw new StatusCodeException("status code was not 200", statusCode);
        return body;
    }
}
