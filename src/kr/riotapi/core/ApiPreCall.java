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
