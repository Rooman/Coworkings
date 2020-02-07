package com.javastudy.coworkings.entity;

import lombok.*;

@Builder
@Data
@Setter(AccessLevel.NONE)
public class Coworking {
    private long id;
    private String name;
    private String mainImage;
    private String overview;
    private String location;
    private long reviewsCount;
    private String city;
//    TODO: What should be price format for coworking? 100.00 or 50 ?
    private double dayPrice;
    private double weekPrice;
    private double monthPrice;
    private double rating;
    private String openingHours;
    private boolean containsDesk;
    private boolean containsOffice;
    private boolean containsMeetingRoom;
}