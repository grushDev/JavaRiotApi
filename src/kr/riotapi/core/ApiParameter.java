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

public class ApiParameter {
    public static enum Type {
        PATH, QUERY;
    }

    protected final Type type;
    protected final String key;
    protected final Object value;

    public ApiParameter(Type type, String key, Object value) {
        this.type = type;
        this.key = key;
        this.value = value;
    }


    public Type getType() {
        return type;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }
}
