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

public class SummonerService extends ExtApiService {

    protected final ApiRequest byName;
    protected final ApiRequest byId;
    protected final ApiRequest runes;
    protected final ApiRequest masteries;

    public SummonerService(RiotApi api) {
        super("summoner", "v1.2", api);
        this.byName = request("by-name/{name}");
        this.byId = request("{id}");
        this.runes = request("{id}/runes");
        this.masteries = request("{id}/masteries");
    }

    public JsonElement byName(RegionEnum region, String name) throws IOException, ApiException {
        return executeAndParse(byName.pathParameter("name", name).forRegion(region.abbr));
    }

    public <V> V byName(RegionEnum region, String name, Class<V> returnType) throws IOException, ApiException {
        return translate(returnType, byName(region, name));
    }

    public JsonElement byName(String name) throws IOException, ApiException {
        return byName(defaultRegion(), name);
    }

    public <V> V byName(String name, Class<V> returnType) throws IOException, ApiException {
        return translate(returnType, byName(name));
    }

    public JsonElement byId(RegionEnum region, int summonerId) throws IOException, ApiException {
        return executeAndParse(byId.pathParameter("id", summonerId).forRegion(region.abbr));
    }

    public <V> V byId(RegionEnum region, int summonerId, Class<V> returnType) throws IOException, ApiException {
        return translate(returnType, byId(region, summonerId));
    }

    public JsonElement byId(int summonerId) throws IOException, ApiException {
        return byId(defaultRegion(), summonerId);
    }

    public <V> V byId(int summonerId, Class<V> returnType) throws IOException, ApiException {
        return translate(returnType, byId(summonerId));
    }

    public JsonElement runes(RegionEnum region, int summonerId) throws IOException, ApiException {
        return executeAndParse(runes.pathParameter("id", summonerId).forRegion(region.abbr));
    }

    public <V> V runes(RegionEnum region, int summonerId, Class<V> returnType) throws IOException, ApiException {
        return translate(returnType, runes(region, summonerId));
    }

    public JsonElement runes(int summonerId) throws IOException, ApiException {
        return runes(defaultRegion(), summonerId);
    }

    public <V> V runes(int summonerId, Class<V> returnType) throws IOException, ApiException {
        return translate(returnType, runes(summonerId));
    }

    public JsonElement masteries(RegionEnum region, int summonerId) throws IOException, ApiException {
        return executeAndParse(masteries.pathParameter("id", summonerId).forRegion(region.abbr));
    }

    public <V> V masteries(RegionEnum region, int summonerId, Class<V> returnType) throws IOException, ApiException {
        return translate(returnType, masteries(region, summonerId));
    }

    public JsonElement masteries(int summonerId) throws IOException, ApiException {
        return masteries(defaultRegion(), summonerId);
    }

    public <V> V masteries(int summonerId, Class<V> returnType) throws IOException, ApiException {
        return translate(returnType, masteries(summonerId));
    }
}
