package com.flipkartTest.stepdefs;

public class PhoneDetails {
    private String modelNameAndStorageSize;
    private String price;
    private String noOfRatings;

    public PhoneDetails(String modelNameAndStorageSize, String price, String noOfRatings) {
        this.modelNameAndStorageSize = modelNameAndStorageSize;
        this.price = price;
        this.noOfRatings = noOfRatings;
    }

    public String getModelNameAndStorageSize() {
        return modelNameAndStorageSize;
    }

    public void setModelNameAndStorageSize(String modelNameAndStorageSize) {
        this.modelNameAndStorageSize = modelNameAndStorageSize;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNoOfRatings() {
        return noOfRatings;
    }

    public void setNoOfRatings(String noOfRatings) {
        this.noOfRatings = noOfRatings;
    }

    public String toString(){
        return modelNameAndStorageSize+",  "+price+" , "+noOfRatings;
    }
}
