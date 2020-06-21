package com.ipn.escom.neuropsi.record.server.entity.values;

public enum Role {
    ADMINISTRATOR("ADMINISTRATOR"), SPECIALIST("SPECIALIST"), PATIENT("PATIENT");

    String roleValue;

    Role(String role) {
        roleValue = role;
    }

}
