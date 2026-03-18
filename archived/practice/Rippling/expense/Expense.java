package Rippling.expense;


import java.util.ArrayList;
import java.util.List;

public class Expense {
    private String expenseId;
    private String itemId;
    private String expenseType;
    private double amountInUsd;
    private String sellerType;
    private String sellerName;

    public Expense(String expenseId, String itemId, String expenseType, double amountInUsd, String sellerType, String sellerName) {
        this.expenseId = expenseId;
        this.itemId = itemId;
        this.expenseType = expenseType;
        this.amountInUsd = amountInUsd;
        this.sellerType = sellerType;
        this.sellerName = sellerName;
    }

    public String getExpenseId() {
        return expenseId;
    }

    public String getItemId() {
        return itemId;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseId(String expenseId) {
        this.expenseId = expenseId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public void setAmountInUsd(double amountInUsd) {
        this.amountInUsd = amountInUsd;
    }

    public void setSellerType(String sellerType) {
        this.sellerType = sellerType;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public double getAmountInUsd() {
        return amountInUsd;
    }

    public String getSellerType() {
        return sellerType;
    }

    public String getSellerName() {
        return sellerName;
    }
}



