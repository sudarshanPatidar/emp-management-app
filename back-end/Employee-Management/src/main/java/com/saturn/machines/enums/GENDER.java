//package com.saturn.machines.enums;
//public enum GENDER{MALE,FEMALE}

package com.saturn.machines.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum GENDER {
    MALE("M"),
    FEMALE("F");

    private String code;

    GENDER(String code) {
        this.code = code;
    }

    @JsonValue
    public String getCode() {
        return code;
    }

    @JsonCreator
    public static GENDER fromCode(String code) {
        for (GENDER gender : GENDER.values()) {
            if (gender.code.equalsIgnoreCase(code)) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Unknown code: " + code);
    }
}
