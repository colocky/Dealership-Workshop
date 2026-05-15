package com.pluralsight;

public class SaleContract extends Contract {


    public SaleContract(String date, String customerName, String customerEmail, boolean vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
    }

    @Override
    public double getTotalPrice() {
    }

    @Override
    public double getMonthlyPayment() {
        return 0;
    }
}
