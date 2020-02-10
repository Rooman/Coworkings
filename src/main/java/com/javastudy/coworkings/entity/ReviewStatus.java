package com.javastudy.coworkings.entity;

public enum ReviewStatus {
    NEW("new"),
    APPROVED("approved"),
    REJECTED("rejected");

    private String name;

    ReviewStatus(String name) {
        this.name = name;
    }

    public static ReviewStatus getByName(String name) {
        for (ReviewStatus reviewStatus : values()) {
            if (reviewStatus.name.equalsIgnoreCase(name)) {
                return reviewStatus;
            }
        }
        throw new IllegalArgumentException("No ReviewStatus with such name: " + name);
    }
}
