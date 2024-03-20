package com.petpals.caregivers.persistence.entities;

public enum CaregiverTypes {
    VET(0),
    TRAINER(1),
    GROOMER(2);

    private final int caregiverType;
    CaregiverTypes(int caregiverType) {
        this.caregiverType = caregiverType;
    }

    public int getCaregiverType() {
        return caregiverType;
    }

    @Override
    public String toString() {
        return "CaregiverTypes{" +
                "caregiverType=" + caregiverType +
                '}';
    }
}
