package com.javastudy.coworkings.entity;

public enum RatingOrder {
    HIGH_TO_LOW("highToLow"),
    LOW_TO_HIGH("lowToHigh");

    private String name;

    RatingOrder(String name) {
        this.name = name;
    }

    public static RatingOrder getByName(String name) {
        for (RatingOrder ratingOrder : values()) {
            if (ratingOrder.name.equalsIgnoreCase(name)) {
                return ratingOrder;
            }
        }
        throw new IllegalArgumentException("No RatingOrder with such name: " + name);
    }
}
