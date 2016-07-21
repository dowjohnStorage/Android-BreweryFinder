package com.epicodus.androidindependentproject.models;

import org.parceler.Parcel;

import java.util.Date;

/**
 * Created by john on 7/15/16.
 */

@Parcel
public class Review {
    private String reviewer;
    private String reviewContent;
    private String dateSubmitted;
    private String rating;
    private String breweryID;
    private String pushId;

    public Review() {};

    public Review(String reviewer, String reviewContent, Date dateSubmitted, String rating, String breweryID) {
        this.reviewer = reviewer;
        this.reviewContent = reviewContent;
        this.dateSubmitted = dateSubmitted.toString();
        this.rating = rating;
        this.breweryID = breweryID;
    }

    public String getReviewer() {
        return reviewer;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public String getDateSubmitted() {
        return dateSubmitted;
    }

    public String getRating() { return rating; }

    public String getBreweryID() {
        return breweryID;
    }

    public String getPushId() {
        return pushId;
    }
}
