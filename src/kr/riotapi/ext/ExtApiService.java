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

package kr.riotapi.ext;

import com.google.gson.JsonElement;
import kr.riotapi.core.ApiCall;
import kr.riotapi.core.ApiService;

import java.io.IOException;
import java.util.List;

public class ExtApiService extends ApiService {

    public ExtApiService(String name, String version, RiotApi api) {
        super(name, version, api);
    }

    protected JsonElement executeAndParse(ApiCall call) throws IOException, ApiException {
        return getRiotApi().executeAndParse(call);
    }

    public RegionEnum defaultRegion() {
        return getRiotApi().getDefaultRegion();
    }

    public RiotApi getRiotApi() {
        return (RiotApi)api;
    }

    public <V> V translate(Class<V> key, JsonElement data) {
        return getRiotApi().translate(key, data);
    }

    public <V> List<V> translateList(Class<V> key, JsonElement data) {
        return getRiotApi().translateList(key, data);
    }
}
