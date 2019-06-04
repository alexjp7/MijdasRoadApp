
package Mijdas.RoadApp.spring.Models.ReviewModels;

import Mijdas.RoadApp.spring.Models.TransactionModels.TransactionHistory;

public class MechanicReview
{
    private int reviewID, starRating;
    private String mechanicUsername, messageText;

    public MechanicReview(int reviewID, String mechanicUsername, String messageText, int starRating)
    {
        this.reviewID = reviewID;
        this.mechanicUsername = mechanicUsername;
        this.messageText = messageText;
        this.starRating = starRating;
    }

    //Getters + Setters
    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }

    public void setMechanicUsername(String mechanicUsername) {
        this.mechanicUsername = mechanicUsername;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public int getReviewID() {
        return reviewID;
    }

    public int getStarRating() {
        return starRating;
    }

    public String getMechanicUsername() {
        return mechanicUsername;
    }

    public String getMessageText() {
        return messageText;
    }

}
