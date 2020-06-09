package com.ipn.escom.neuropsi.record.server.entity.values;

public enum Laterality {
    LEFT_HANDED("LEFT_HANDED"), RIGHT_HANDED("RIGHT_HANDED"), AMBIDEXTROUS("AMBIDEXTROUS");

    String lateralityValue;

    Laterality(String laterality) {
        lateralityValue = laterality;
    }


}
