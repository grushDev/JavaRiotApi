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

import java.util.ArrayList;
import java.util.List;

public class ApiRequest {

    protected final String template;
    protected final ApiService service;

    public ApiRequest(String template, ApiService service) {
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
