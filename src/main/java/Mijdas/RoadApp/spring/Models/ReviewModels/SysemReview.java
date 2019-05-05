
package Mijdas.RoadApp.spring.Models.ReviewModels;

public class SysemReview
{
    private String review;
    //Date should be set to the local system time upon inserting.
    private String date;
    
    public SysemReview(String review)
    {
        this.review = review;
    }
    public SysemReview()
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
