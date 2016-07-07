package com.epicodus.androidindependentproject;

public class Brewery {
    private String mBreweryID;
    private String mName;
    private String mPhone;
    private String mAddress;
    private String mLocationType;
    private String mDescription;
    private String mWebsite;
    private String mLocality;
    private String mRegion;

    public Brewery(String breweryID, String name, String phone, String address, String locationType, String description, String website, String locality, String region) {
        this.mBreweryID = breweryID;
        this.mName = name;
        this.mPhone = phone;
        this.mAddress = address;
        this.mLocationType = locationType;
        this.mDescription = description;
        this.mWebsite = website;
        this.mLocality = locality;
        this.mRegion = region;
    }

    public String getmName() {
        return mName;
    }

    public String getmPhone() {
        return mPhone;
    }

    public String getmAddress() {
        return mAddress;
    }

    public String getmLocationType() {
        return mLocationType;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmWebsite() {
        return mWebsite;
    }

    public String getmLocality() {
        return mLocality;
    }

    public String getmRegion() {
        return mRegion;
    }
}
