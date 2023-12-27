package com.ll.medium_mission.global.Security;

import lombok.Getter;

@Getter
public enum MemberRole {


    ADMIN("ROLE_ADMIN"),
    MEMBER("ROLE_MEMBER");


    MemberRole(String value) {
        this.value = value;
    }

    private String value;

}