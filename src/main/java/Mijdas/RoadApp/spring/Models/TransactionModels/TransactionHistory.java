
package Mijdas.RoadApp.spring.Models.TransactionModels;

import java.util.ArrayList;


public class TransactionHistory
{
    private ArrayList<Transaction> transactionHistory;

    public TransactionHistory()
    {
        transactionHistory = new ArrayList<>();
    }
    
    public void addTransaction(Transaction t)
    {
        transactionHistory.add(t);
    }
    
    public void voidTransaction(int id)
    {
        //Check for admin privileges, then only void if valid
    }
    
    public boolean modifyTransaction(int id)
    {
        //Check for admin privileges, then only void if valid
        return false;
    }
    
    
            
    
}
