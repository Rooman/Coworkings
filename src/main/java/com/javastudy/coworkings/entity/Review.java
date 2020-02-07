package com.javastudy.coworkings.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Builder
@Data
@Setter(AccessLevel.NONE)
public class Review {
    private long id;
    private String description;
    private ReviewStatus reviewStatus;
    private long coworkingId;
    private long userId;
}
