package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    private DealershipFileManager fileManager;
    private static Scanner scanner = new Scanner(System.in);

    public UserInterface() {
        fileManager = new DealershipFileManager();
        dealership = fileManager.getDealership();
        scanner = new Scanner(System.in);
    }

    public void display() {
        boolean running = true;


        while (running) {
            displayMenu();
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1 -> processGetByPriceRequest();
                case 2 -> processGetByMakeModelRequest();
                case 3 -> processGetByYearRequest();
                case 4 -> processGetByColorRequest();
                case 5 -> processGetByMileageRequest();
                case 6 -> processGetByVehicleTypeRequest();
                case 7 -> displayVehicles(dealership.getAllVehicles());
                case 8 -> processAddVehicleRequest();
                case 9 -> processRemoveVehicleRequest();
                case 10 -> processSaleOrLeaseVehicle();
                case 99 -> running = false;
                default -> System.out.println("Invalid choice. Try again.");
            }
        }

        System.out.println("Goodbye!");
    }

    private void displayMenu() {
        System.out.println("\n" + dealership.getName());
        System.out.println(dealership.getAddress() + " | " + dealership.getPhone());
        System.out.println("1 - Find vehicles within a price range");
        System.out.println("2 - Find vehicles by make / model");
        System.out.println("3 - Find vehicles by year range");
        System.out.println("4 - Find vehicles by color");
        System.out.println("5 - Find vehicles by mileage range");
        System.out.println("6 - Find vehicles by type");
        System.out.println("7 - List ALL vehicles");
        System.out.println("8 - Add a vehicle");
        System.out.println("9 - Remove a vehicle");
        System.out.println("10 - Sell or Lease a Vehicle");
        System.out.println("99 - Quit");
    }

    private void processGetByPriceRequest() {
        double min = readDouble("Minimum price: ");
        double max = readDouble("Maximum price: ");
        displayVehicles(dealership.getVehiclesByPrice(min, max));
    }

    private void processGetByMakeModelRequest() {
        System.out.print("Make, or leave blank for any: ");
        String make = scanner.nextLine();
        System.out.print("Model, or leave blank for any: ");
        String model = scanner.nextLine();
        displayVehicles(dealership.getVehiclesByMakeModel(make, model));
    }

    private void processGetByYearRequest() {
        int min = readInt("Minimum year: ");
        int max = readInt("Maximum year: ");
        displayVehicles(dealership.getVehiclesByYear(min, max));
    }

    private void processGetByColorRequest() {
        System.out.print("Color: ");
        String color = scanner.nextLine();
        displayVehicles(dealership.getVehiclesByColor(color));
    }

    private void processGetByMileageRequest() {
        int min = readInt("Minimum mileage: ");
        int max = readInt("Maximum mileage: ");
        displayVehicles(dealership.getVehiclesByMileage(min, max));
    }

    private void processGetByVehicleTypeRequest() {
        System.out.print("Type, such as car, truck, SUV, or van: ");
        String type = scanner.nextLine();
        displayVehicles(dealership.getVehiclesByType(type));
    }

    private void processAddVehicleRequest() {
        int vin = readInt("VIN: ");
        int year = readInt("Year: ");
        System.out.print("Make: ");
        String make = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Type: ");
        String type = scanner.nextLine();
        System.out.print("Color: ");
        String color = scanner.nextLine();
        int odometer = readInt("Odometer: ");
        double price = readDouble("Price: ");

        Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
        dealership.addVehicle(vehicle);
        fileManager.saveDealership(dealership);
        System.out.println("Vehicle added and inventory file updated.");
    }

    private void processRemoveVehicleRequest() {
        int vin = readInt("Enter VIN to remove: ");
        boolean removed = dealership.removeVehicleByVin(vin);

        if (removed) {
            fileManager.saveDealership(dealership);
            System.out.println("Vehicle removed and inventory file updated.");
        } else {
            System.out.println("No vehicle found with that VIN.");
        }
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }

        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
    }

    private void processSaleOrLeaseVehicle() {
        System.out.print("""
                1. Sell
                2. Lease
                Please choose your option: 
                """);
        int contractType = Integer.parseInt(scanner.nextLine());
        switch (contractType) {
            case 1 -> processSellVehicle();
            case 2 -> processLeaseVehicle();
            default -> {
                System.out.println("Invalid choice.");
                return;
            }

        }
    }
    
    private void processSellVehicle(){
        System.out.println("Enter VIN:");
        int vin = scanner.nextInt();
        scanner.nextLine();
        Vehicle vehicle = dealership.getVehicleByVin(vin);
        if(vehicle == null){
            System.out.println("Vehicle is not found.");
            return;
        }
        System.out.println("Enter customer name:");
        String customerName = scanner.nextLine();
        System.out.println("Enter customer email:");
        String customerEmail = scanner.nextLine();
        System.out.println("Enter contract date(YYYY-MM-DD):");
        String date = scanner.nextLine();
        Contract contract;

        System.out.println("Do they want to finance?(yes/no)");
        String financeChoice = scanner.nextLine();
        boolean finance = financeChoice.equalsIgnoreCase("yes");
        contract = new SaleContract(date, customerName, customerEmail, vehicle, finance);
        
    }
    private void processLeaseVehicle(){
        System.out.println("Enter VIN:");
        int vin = scanner.nextInt();
        scanner.nextLine();
        Vehicle vehicle = dealership.getVehicleByVin(vin);
        if(vehicle == null){
            System.out.println("Vehicle is not found.");
            return;
        }
        System.out.println("Enter customer name:");
        String customerName = scanner.nextLine();
        System.out.println("Enter customer email:");
        String customerEmail = scanner.nextLine();
        System.out.println("Enter contract date(YYYY-MM-DD):");
        String date = scanner.nextLine();
        Contract contract;
        contract = new LeaseContract(date, customerName, customerEmail, vehicle);

    }
    
    private int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
