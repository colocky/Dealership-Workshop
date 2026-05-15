package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public List<Vehicle> getAllVehicles() {
        return new ArrayList<>(inventory);
    }

    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    public Vehicle getVehicleByVin(int vin){
        for (Vehicle vehicle: inventory) {
            if (vehicle.getVin() == vin){
                return vehicle;
            }
        }
        return null;
    }

    public boolean removeVehicleByVin(int vin) {
        return inventory.removeIf(vehicle -> vehicle.getVin() == vin);
    }

    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        ArrayList<Vehicle> results = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getPrice() >= min && vehicle.getPrice() <= max) {
                results.add(vehicle);
            }
        }
        return results;
    }
    public void removeVehicle(Vehicle vehicle){
        inventory.remove(vehicle);
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        ArrayList<Vehicle> results = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            boolean makeMatches = make.isBlank() || vehicle.getMake().equalsIgnoreCase(make);
            boolean modelMatches = model.isBlank() || vehicle.getModel().equalsIgnoreCase(model);
            if (makeMatches && modelMatches) {
                results.add(vehicle);
            }
        }
        return results;
    }

    public List<Vehicle> getVehiclesByYear(int min, int max) {
        ArrayList<Vehicle> results = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getYear() >= min && vehicle.getYear() <= max) {
                results.add(vehicle);
            }
        }
        return results;
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        ArrayList<Vehicle> results = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getColor().equalsIgnoreCase(color)) {
                results.add(vehicle);
            }
        }
        return results;
    }

    public List<Vehicle> getVehiclesByMileage(int min, int max) {
        ArrayList<Vehicle> results = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getOdometer() >= min && vehicle.getOdometer() <= max) {
                results.add(vehicle);
            }
        }
        return results;
    }

    public List<Vehicle> getVehiclesByType(String type) {
        ArrayList<Vehicle> results = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getVehicleType().equalsIgnoreCase(type)) {
                results.add(vehicle);
            }
        }
        return results;
    }
}
