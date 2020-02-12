package com.javastudy.coworkings.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CoworkingFilter {
    private String city;
    private List<String> filters;
    private List<String> price;
    private Map<String, String> rating;
    private List<String> equipment;
}
