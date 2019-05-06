
package Mijdas.RoadApp.spring.Models.ReviewModels;

import Mijdas.RoadApp.spring.Models.TransactionModels.TransactionHistory;

public class MechanicReview
{
    private int mechanicID;
    private String review;
    private int rating;
    private TransactionHistory transactionHistory;

    public MechanicReview(int mechanicID, String review, int rating)
    {
        this.mechanicID = mechanicID;
        this.review = review;
        this.rating = rating;
        transactionHistory = new TransactionHistory();
    }

    public int getRating()
    {
        return rating;
    }

    public String getReview()
    {
        return review;
    }

    public int getMechanicID()
    {
        return mechanicID;
    }
}
