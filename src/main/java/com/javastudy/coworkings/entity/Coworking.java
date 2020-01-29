package com.javastudy.coworkings.entity;

public class Coworking {
    private long id;
    private String coworkingName;
    private String mainImage;
    private String overview;
    private String location;
    private long reviewsCount;
    private String city;
    private double dayPrice;
    private double weekPrice;
    private double monthPrice;
    private double rating;
    private String openingHours;
    private boolean containsDesk;
    private boolean containsOffice;
    private boolean containsMeetingRoom;

    private Coworking(){}

    public static CoworkingBuilder newCoworkingBuilder() {
        return new Coworking().new CoworkingBuilder();
    }

    public class CoworkingBuilder {
        private CoworkingBuilder(){}

        public CoworkingBuilder setId(long id) {
            Coworking.this.id = id;
            return this;
        }

        public CoworkingBuilder setCoworkingName(String coworkingName) {
            Coworking.this.coworkingName = coworkingName;
            return this;
        }

        public CoworkingBuilder setMainImage(String mainImage) {
            Coworking.this.mainImage = mainImage;
            return this;
        }


        public CoworkingBuilder setOverview(String overview) {
            Coworking.this.overview = overview;
            return this;
        }


        public CoworkingBuilder setLocation(String location) {
            Coworking.this.location = location;
            return this;
        }

        public CoworkingBuilder setReviewsCount(long reviewsCount) {
            Coworking.this.reviewsCount = reviewsCount;
            return this;
        }


        public CoworkingBuilder setCity(String city) {
            Coworking.this.city = city;
            return this;
        }

        public CoworkingBuilder setDayPrice(double dayPrice) {
            Coworking.this.dayPrice = dayPrice;
            return this;
        }

        public CoworkingBuilder setWeekPrice(double weekPrice) {
            Coworking.this.weekPrice = weekPrice;
            return this;
        }

        public CoworkingBuilder setMonthPrice(double monthPrice) {
            Coworking.this.monthPrice = monthPrice;
            return this;
        }

        public CoworkingBuilder setRating(double rating) {
            Coworking.this.rating = rating;
            return this;
        }

        public CoworkingBuilder setOpeningHours(String openingHours) {
            Coworking.this.openingHours = openingHours;
            return this;
        }

        public CoworkingBuilder setContainsDesk(boolean containsDesk) {
            Coworking.this.containsDesk = containsDesk;
            return this;
        }

        public CoworkingBuilder setContainsOffice(boolean containsOffice) {
            Coworking.this.containsOffice = containsOffice;
            return this;
        }

        public CoworkingBuilder setContainsMeetingRoom(boolean containsMeetingRoom) {
            Coworking.this.containsMeetingRoom = containsMeetingRoom;
            return this;
        }

        public Coworking buildCoworking() {
            return Coworking.this;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCoworkingName() {
        return coworkingName;
    }

    public void setCoworkingName(String coworkingName) {
        this.coworkingName = coworkingName;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getReviewsCount() {
        return reviewsCount;
    }

    public void setReviewsCount(long reviewsCount) {
        this.reviewsCount = reviewsCount;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getDayPrice() {
        return dayPrice;
    }

    public void setDayPrice(double dayPrice) {
        this.dayPrice = dayPrice;
    }

    public double getWeekPrice() {
        return weekPrice;
    }

    public void setWeekPrice(double weekPrice) {
        this.weekPrice = weekPrice;
    }

    public double getMonthPrice() {
        return monthPrice;
    }

    public void setMonthPrice(double monthPrice) {
        this.monthPrice = monthPrice;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public boolean isContainsDesk() {
        return containsDesk;
    }

    public void setContainsDesk(boolean containsDesk) {
        this.containsDesk = containsDesk;
    }

    public boolean isContainsOffice() {
        return containsOffice;
    }

    public void setContainsOffice(boolean containsOffice) {
        this.containsOffice = containsOffice;
    }

    public boolean isContainsMeetingRoom() {
        return containsMeetingRoom;
    }

    public void setContainsMeetingRoom(boolean containsMeetingRoom) {
        this.containsMeetingRoom = containsMeetingRoom;
    }
}