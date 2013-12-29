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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import kr.riotapi.core.ApiCall;
import kr.riotapi.core.ApiDomain;
import kr.riotapi.core.StatusCodeException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RiotApi extends ApiDomain {

    public final SummonerService summoner;
    public final ChampionService champion;
    public final LeagueService league;
    public final GameService game;
    public final StatsService stats;
    public final TeamService team;

    protected final Gson gson;
    protected RegionEnum defaultRegion = RegionEnum.NORTH_AMERICA;
    protected Map<Class, Translator> translatorMap;

    public RiotApi(String apiKey) {
        super(apiKey);
        this.summoner = new SummonerService(this);
        this.champion = new ChampionService(this);
        this.game = new GameService(this);
        this.league = new LeagueService(this);
        this.stats = new StatsService(this);
        this.team = new TeamService(this);
        this.gson = new Gson();
        this.translatorMap = new HashMap<>();
    }

    public RiotApi(String apiKey, RegionEnum defaultRegion) {
        this(apiKey);
        this.defaultRegion = defaultRegion;
    }

    JsonElement executeAndParse(ApiCall call) throws IOException, ApiException {
        String jsonSource = null;
        try {
            jsonSource = execute(call);
        } catch(StatusCodeException ex) {
            throw new ApiException(ApiExceptionCause.byCode(ex.code), ex.code);
        }
        return gson.fromJson(jsonSource, JsonElement.class);
    }

    public RegionEnum getDefaultRegion() {
        return defaultRegion;
    }

    public void setDefaultRegion(RegionEnum defaultRegion) {
        this.defaultRegion = defaultRegion;
    }

    public <V> void registerTranslator(Class<V> key, Translator<V> translator) {
        translatorMap.put(key, translator);
    }

    public <V> void deregisterTranslator(Class<V> key) {
        translatorMap.remove(key);
    }

    <V> V translate(Class<V> key, JsonElement data) {
        try {
            return (V) translatorMap.get(key).translate(data);
        } catch(Exception ex) {
            throw new TranslateException("could not translate data", ex);
        }
    }

    <V> List<V> translateList(Class<V> key, JsonElement data) {
        ArrayList<V> list = new ArrayList<>();
        JsonArray array = data.getAsJsonArray();
        for(JsonElement element : array) {
            list.add(translate(key, element));
        }
        return list;
    }

    public boolean isRegistered(Class key) {
        return translatorMap.containsKey(key);
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
