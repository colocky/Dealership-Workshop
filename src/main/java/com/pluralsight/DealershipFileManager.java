package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DealershipFileManager {
    private static final String fileName = "inventory.csv";

    public Dealership getDealership() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String firstLine = reader.readLine();
            if (firstLine == null) {
                throw new RuntimeException("Inventory file is empty.");
            }

            String[] dealershipData = firstLine.split("\\|");
            Dealership dealership = new Dealership(dealershipData[0], dealershipData[1], dealershipData[2]);

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;

                String[] parts = line.split("\\|");
                int vin = Integer.parseInt(parts[0]);
                int year = Integer.parseInt(parts[1]);
                String make = parts[2];
                String model = parts[3];
                String type = parts[4];
                String color = parts[5];
                int odometer = Integer.parseInt(parts[6]);
                double price = Double.parseDouble(parts[7]);

                dealership.addVehicle(new Vehicle(vin, year, make, model, type, color, odometer, price));
            }

            return dealership;
        } catch (IOException e) {
            throw new RuntimeException("Could not read inventory file: " + fileName, e);
        }
    }

    public void saveDealership(Dealership dealership) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.printf("%s|%s|%s%n", dealership.getName(), dealership.getAddress(), dealership.getPhone());

            for (Vehicle vehicle : dealership.getAllVehicles()) {
                writer.printf("%d|%d|%s|%s|%s|%s|%d|%.2f%n",
                        vehicle.getVin(),
                        vehicle.getYear(),
                        vehicle.getMake(),
                        vehicle.getModel(),
                        vehicle.getVehicleType(),
                        vehicle.getColor(),
                        vehicle.getOdometer(),
                        vehicle.getPrice());
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not save inventory file: " + fileName, e);
        }
    }
}
