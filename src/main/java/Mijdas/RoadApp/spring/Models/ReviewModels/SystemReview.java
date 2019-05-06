
package Mijdas.RoadApp.spring.Models.ReviewModels;

public class SystemReview
{
    private String review;
    //Date should be set to the local system time upon inserting.
    private String date;

    public SystemReview(String review)
    {
        this.review = review;
    }
    public SystemReview()
    {
        this.review = review;
    }

    public void setText(String text)
    {
        this.review = text;
    }

    public String getValue()
    {
        return review;
    }

    public String getDate()
    {
        return date;
    }


}
