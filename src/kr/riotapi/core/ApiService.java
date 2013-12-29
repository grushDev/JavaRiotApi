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

public class ApiService {

    protected final String name;
    protected final String version;
    protected final ApiDomain api;

    public ApiService(String name, String version, ApiDomain api) {
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

    public ApiDomain getApi() {
        return api;
    }

    public ApiRequest request(String template) {
        return new ApiRequest(template, this);
    }
}
