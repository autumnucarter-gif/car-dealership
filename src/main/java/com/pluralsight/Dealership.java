package com.pluralsight;

import java.math.BigDecimal;
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
        this.inventory = inventory;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Vehicle> getAllVehicles() {
        return inventory;
    }
    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }
    public void removeVehicle(Vehicle vehicle) {
        inventory.remove(vehicle);
    }
    public List<Vehicle> getVehiclesByPrice(BigDecimal min, BigDecimal max) {
        List<Vehicle> matches = new ArrayList<Vehicle>();
        for (Vehicle v : this.inventory) {
            if (v.getPrice().compareTo(min) >= 0 && v.getPrice().compareTo(max) <= 0) {
                matches.add(v); }
        }
        return matches;

    }
    public List<Vehicle> getVehiclesByMakeModel(String make, String model)
    { List<Vehicle> matches = new ArrayList<Vehicle>();
        for (Vehicle v : this.inventory) {
            if (v.getMake().equalsIgnoreCase(make) && v.getModel().equalsIgnoreCase(model)) {
                matches.add(v); }
        }
        return matches;

    }
    public List<Vehicle> getVehiclesByYear(int min, int max) {
        List<Vehicle> matches = new ArrayList<Vehicle>();
        for (Vehicle v : this.inventory) {
            if (v.getYear() >= min && v.getYear() <= max) {
                matches.add(v);
            }
        }
        return matches;

    }
    public List<Vehicle> getVehiclesByColor(String color)
    { List<Vehicle> matches = new ArrayList<Vehicle>();
        for (Vehicle v : this.inventory) {
            if (v.getColor().equalsIgnoreCase(color)) {
                matches.add(v);
            }
        }
        return matches;
    }
    public List<Vehicle> getVehiclesByMileage(int min, int max) {
        List<Vehicle> matches = new ArrayList<Vehicle>();
        for (Vehicle v : this.inventory) {
            if (v.getOdometer() >= min && v.getOdometer() <= max) {
                matches.add(v);
            }
        }
        return matches;
    }
    public List<Vehicle> getVehiclesByType(String vehicleType) {
        List<Vehicle> matches = new ArrayList<Vehicle>();
        for (Vehicle v : this.inventory) {
            if (v.getVehicleType().equalsIgnoreCase(vehicleType)) {
                matches.add(v);
            }
        }
        return matches;
    }
}
