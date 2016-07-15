package com.epicodus.androidindependentproject.models;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Brewery {
    private String breweryID;
    private String name;
    private String phone;
    private String address;
    private String locationType;
    private String description;
    private String website;
    private String locality;
    private String region;
    private String imgURL;

    public Brewery() {}

    public Brewery(String breweryID, String name, String phone, String address, String locationType, String description, String website, String locality, String region, String imgURL) {
        this.breweryID = breweryID;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.locationType = locationType;
        this.description = description;
        this.website = website;
        this.locality = locality;
        this.region = region;
        this.imgURL = imgURL;
    }

    public String getBreweryID() {
        return breweryID;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getLocationType() {
        return locationType;
    }

    public String getDescription() {
        return description;
    }

    public String getWebsite() {
        return website;
    }

    public String getLocality() {
        return locality;
    }

    public String getRegion() {
        return region;
    }

    public String getImgURL() {
        return imgURL;
    }

    public String getSearchableAddress(){
        String modifiedString = (address + ", " + locality + "%2C " + region);
        String finalString = modifiedString.replaceAll(" ", "+");
        return finalString;
    }
}
