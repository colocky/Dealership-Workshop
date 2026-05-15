package com.pluralsight;

public class SaleContract extends Contract {
    private boolean financed;

    public SaleContract(String date, String customerName, String customerEmail, Vehicle vehicleSold) {
        super(date, customerName, customerEmail, vehicleSold);
    }

    public SaleContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, boolean financed) {
        super(date, customerName, customerEmail, vehicleSold);
        this.financed = financed;
    }

    public boolean isFinanced() {
        return financed;
    }
    public double getSaleTaxAmount() {
        return getVehicleSold().getPrice() * 0.05;
    }

    public double getRecordingFee() {
        return 100.00;
    }

    public double getProcessingFee() {
        if (getVehicleSold().getPrice() < 10000) {
            return 295.00;
        } else {
            return 495.00;
        }
    }


    @Override
    public double getTotalPrice() {
        return getVehicleSold().getPrice() + getSaleTaxAmount() + getRecordingFee() + getProcessingFee();
    }

    @Override
    public double getMonthlyPayment() {
        if (!isFinanced()) {
            return 0;
        }

        double paymentRate;
        int months;
        if (getVehicleSold().getPrice() >= 10000) {
            paymentRate = 0.0425;
            months = 48;
        } else {
            paymentRate = 0.0525;
            months = 24;
        }

        double monthlyRate = paymentRate / 12;

        return getTotalPrice() * monthlyRate * Math.pow(1 + monthlyRate, months) / (Math.pow(1 + monthlyRate, months) - 1);
    }
}
