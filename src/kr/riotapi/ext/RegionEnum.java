package kr.riotapi.ext;

/**
 * Created with IntelliJ IDEA.
 * User: kenneth
 * Date: 25.12.13
 * Time: 17:24
 * To change this template use File | Settings | File Templates.
 */
public enum RegionEnum {
    EUROPE_WEST("euw"),
    EUROPE_NORTH_EAST("eune"),
    NORTH_AMERICA("na"),
    BRASIL("br"),
    TURKEY("tr");

    public final String abbr;

    RegionEnum(String abbr) {
        this.abbr = abbr;
    }
}
