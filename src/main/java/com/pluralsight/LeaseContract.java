package com.pluralsight;

public class LeaseContract extends Contract{

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
    }

    public double getExpectedEndingValue(){
        return getVehicleSold().getPrice() * 0.50;
    }

    public double getLeaseFee(){
        return getVehicleSold().getPrice() * 0.07;
    }

    @Override
    public double getTotalPrice() {
        return getExpectedEndingValue() + getLeaseFee();
    }

    @Override
    public double getMonthlyPayment() {
        double principal = getTotalPrice();
        double annualRate = 0.04;
        int months = 36;
        double monthlyRate = annualRate /12;

        return principal * monthlyRate * Math.pow(1+monthlyRate,months)/(Math.pow(1+monthlyRate,months)-1);



    }
}
