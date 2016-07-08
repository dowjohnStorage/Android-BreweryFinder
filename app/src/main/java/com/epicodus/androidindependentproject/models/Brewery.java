package com.epicodus.androidindependentproject.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
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
    private String mImgURL;

    public Brewery() {}

    public Brewery(String breweryID, String name, String phone, String address, String locationType, String description, String website, String locality, String region, String imgURL) {
        this.mBreweryID = breweryID;
        this.mName = name;
        this.mPhone = phone;
        this.mAddress = address;
        this.mLocationType = locationType;
        this.mDescription = description;
        this.mWebsite = website;
        this.mLocality = locality;
        this.mRegion = region;
        this.mImgURL = imgURL;
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

    public String getImgURL() {
        return mImgURL;
    }

    public String getSearchableAddress(){
        String modifiedString = (mAddress + ", " + mLocality + "%2C " + mRegion);
        String finalString = modifiedString.replaceAll(" ", "+");
        return finalString;
    }
}
