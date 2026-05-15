package com.pluralsight;

import java.io.*;


public class ContractFileManager {

    public void saveContract(Contract contract, Vehicle vehicle) {
        try {
            FileWriter fileWriter = new FileWriter("contract.csv");
            PrintWriter writer = new PrintWriter(fileWriter);
            vehicle = contract.getVehicleSold();

            if (contract instanceof SaleContract saleContract) {
                writer.printf("SALE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f|%s|%.2f", contract.getDate(), contract.getCustomerName(), contract.getCustomerEmail(), vehicle.getVin(), vehicle.getYear(), vehicle.getMake(), vehicle.getModel(), vehicle.getVehicleType(), vehicle.getColor(), vehicle.getOdometer(), vehicle.getPrice(), saleContract.getSaleTaxAmount(), saleContract.getRecordingFee(), saleContract.getProcessingFee(), saleContract.getTotalPrice(), saleContract.isFinanced(), saleContract.getMonthlyPayment());
            } else if (contract instanceof LeaseContract leaseContract) {
                writer.printf("LEASE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f", contract.getDate(), contract.getCustomerName(), contract.getCustomerEmail(), vehicle.getVin(), vehicle.getYear(), vehicle.getMake(), vehicle.getModel(), vehicle.getVehicleType(), vehicle.getColor(), vehicle.getOdometer(), vehicle.getPrice(), leaseContract.getExpectedEndingValue(), leaseContract.getLeaseFee(), leaseContract.getTotalPrice(), leaseContract.getMonthlyPayment());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
