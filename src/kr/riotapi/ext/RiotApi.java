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

/**
 * The entry point for doing Riot api calls via the extension package provided by this library.
 * This Class handles the api key, a default region and if wanted translators.
 * ApiCalls are executed and parsed by this class but are triggered by using a method in one of the
 * several service classes. The services of this api can be accessed via the public fields of this class.
 * This class is not programmed to be thread safe.
 * @author Kenneth Radunz
 */
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

    /**
     * <p>creates a new RiotApi which will use the specified api key for api calls.
     * The default region is set to <code>RegionEnum.NORTH_AMERICA</code>.</p>
     * @param apiKey - the api key to use by the created instance of this class, should a valid key
     */
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

    /**
     * <p>creates a new RiotApi which will use the specified api key for api calls
     * and will use the passed region as default region.</p>
     * @param apiKey - the api key to use by the created instance of this class, should be a valid key
     * @param defaultRegion - the passed region will be used as default region, <code>RegionEnum.NORTH_AMERICE</code>
     *                      will be used if <code>null</code>
     */
    public RiotApi(String apiKey, RegionEnum defaultRegion) {
        this(apiKey);
        this.defaultRegion = defaultRegion == null ? RegionEnum.NORTH_AMERICA : defaultRegion;
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

    /**
     * <p>return the default region used by this instance.</p>
     * @return the default region of this instance
     */
    public RegionEnum getDefaultRegion() {
        return defaultRegion;
    }

    /**
     * <p>sets the default region of this instance to the passed argument.
     * All future calls will use the passed argument as default region if needed.</p>
     * @param defaultRegion - the new default region. <code>RegionEnum.NORTH_AMERICA</code> if <code>null</code> is passed
     */
    public void setDefaultRegion(RegionEnum defaultRegion) {
        this.defaultRegion = defaultRegion == null? RegionEnum.NORTH_AMERICA : defaultRegion;
    }

    /**
     * <p>registers a new translator for a given class. ApiCalls can now be translated to the type specified by <code>key</code>
     * using the service fields of the instance where the translator is registered.</p>
     * @param key - the key under which the translator is stored
     * @param translator - the translator to call
     * @param <V> - the type the translator translates to
     */
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
