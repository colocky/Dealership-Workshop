package com.pluralsight;

import java.io.*;

public class ContractFileManager {

    private static final String CONTRACT_FILE = "contracts.csv";

    public static void saveContract(Contract contract) {
        try (FileWriter fileWriter = new FileWriter(CONTRACT_FILE, true); PrintWriter writer = new PrintWriter(fileWriter)) {
            Vehicle vehicle = contract.getVehicleSold();

            if (contract instanceof SaleContract saleContract) {
                writer.printf("SALE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f|%s|%.2f%n", contract.getDate(), contract.getCustomerName(), contract.getCustomerEmail(), vehicle.getVin(), vehicle.getYear(), vehicle.getMake(), vehicle.getModel(), vehicle.getVehicleType(), vehicle.getColor(), vehicle.getOdometer(), vehicle.getPrice(), saleContract.getSaleTaxAmount(), saleContract.getRecordingFee(), saleContract.getProcessingFee(), saleContract.getTotalPrice(), saleContract.isFinanced() ? "finance" : "not finance", saleContract.getMonthlyPayment());
            } else if (contract instanceof LeaseContract leaseContract) {
                writer.printf("LEASE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f%n", contract.getDate(), contract.getCustomerName(), contract.getCustomerEmail(), vehicle.getVin(), vehicle.getYear(), vehicle.getMake(), vehicle.getModel(), vehicle.getVehicleType(), vehicle.getColor(), vehicle.getOdometer(), vehicle.getPrice(), leaseContract.getExpectedEndingValue(), leaseContract.getLeaseFee(), leaseContract.getTotalPrice(), leaseContract.getMonthlyPayment());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}