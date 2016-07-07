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

    public String getBreweryID() {
        return mBreweryID;
    }

    public String getName() {
        return mName;
    }

    public String getPhone() {
        return mPhone;
    }

    public String getAddress() {
        return mAddress;
    }

    public String getLocationType() {
        return mLocationType;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getWebsite() {
        return mWebsite;
    }

    public String getLocality() {
        return mLocality;
    }

    public String getRegion() {
        return mRegion;
    }
}
