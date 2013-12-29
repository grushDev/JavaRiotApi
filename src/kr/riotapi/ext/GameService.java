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
import kr.riotapi.core.ApiRequest;

import java.io.IOException;
import java.util.List;

public class GameService extends ExtApiService {

    protected final ApiRequest recent;

    public GameService(RiotApi api) {
        super("game", "v1.2", api);
        this.recent = request("by-summoner/{id}/recent");
    }

    public JsonElement recent(RegionEnum region, int summonerId) throws IOException, ApiException {
        return executeAndParse(recent.pathParameter("id", summonerId).forRegion(region.abbr));
    }

    public <V> List<V> recent(RegionEnum region, int summonerId, Class<V> returnType) throws IOException, ApiException {
        JsonElement element = recent(region, summonerId);
        return translateList(returnType, element);
    }

    public JsonElement recent(int summonerId) throws IOException, ApiException {
        return recent(defaultRegion(), summonerId);
    }

    public <V> List<V> recent(int summonerId, Class<V> returnType) throws IOException, ApiException {
        JsonElement element = recent(summonerId);
        return translateList(returnType, element);
    }
}
