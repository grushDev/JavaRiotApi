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

public enum ApiExceptionCause {
    UNKNOWN(-1),
    SUMMONER_NOT_FOUND(404),
    BAD_REQUEST(400),
    INTERNAL_SERVER_ERROR(500),
    UNAUTHORIZED(401);

    public final int code;

    ApiExceptionCause(int code) {
        this.code = code;
    }

    public static ApiExceptionCause byCode(int code) {
        for(ApiExceptionCause error : values()) {
            if(error.code == code)
                return error;
        }
        return UNKNOWN;
    }
}
