package com.javastudy.coworkings.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

public class CoworkingFilter {
    private String city;
    private List<String> filters;
    private List<String> price;

    public RatingOrder getRatingOrder() {
        return ratingOrder;
    }

    public void setRatingOrder(RatingOrder ratingOrder) {
        this.ratingOrder = ratingOrder;
    }

    private RatingOrder ratingOrder;
    //private Map<String, RatingOrder> rating;
    private List<String> equipment;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getFilters() {
        return filters;
    }

    public void setFilters(List<String> filters) {
        this.filters = filters;
    }

    public List<String> getPrice() {
        return price;
    }

    public void setPrice(List<String> price) {
        this.price = price;
    }

    /*public Map<String, RatingOrder> getRating() {
        return rating;
    }

    public void setRating(Map<String, RatingOrder> rating) {
        this.rating = rating;
    }*/

    public List<String> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<String> equipment) {
        this.equipment = equipment;
    }
}


