
package Mijdas.RoadApp.spring.Models.TransactionModels;

import Mijdas.RoadApp.spring.Models.UserModels.User;

public class Transaction
{
    private float amountPayed;
    private boolean isComplete;
    private User payee;
    private User payer;

    public Transaction(float amountPayed, boolean isComplete, User payee, User payer)
    {
        this.amountPayed = amountPayed;
        this.isComplete = isComplete;
        this.payee = payee;
        this.payer = payer;
    }

   /**************************GETTERS**********************/
    public float getAmountPayed()
    {
        return amountPayed;
    }

    public boolean isIsComplete()
    {
        return isComplete;
    }

    public User getPayee()
    {
        return payee;
    }

    public User getPayer()
    {
        return payer;
    }

     /**************************SETTERS**********************/
    public void setAmountPayed(float amountPayed)
    {
        this.amountPayed = amountPayed;
    }

    public void setIsComplete(boolean isComplete)
    {
        this.isComplete = isComplete;
    }

    public void setPayee(User payee)
    {
        this.payee = payee;
    }

    public void setPayer(User payer)
    {
        this.payer = payer;
    }

    @Override
    public String toString()
    {
        return "Transaction{" + "amountPayed=" + amountPayed + ", isComplete=" + isComplete + ", payee=" + payee + ", payer=" + payer + '}';
    }
}
