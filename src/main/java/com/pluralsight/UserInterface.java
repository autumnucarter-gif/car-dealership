package com.pluralsight;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    private Scanner scanner;

    public UserInterface(Dealership dealership) {
        this.dealership = dealership;
    }

    private void init() {
        DealershipFileManager fileManager = new DealershipFileManager();
        this.dealership = fileManager.getDealership();
    }

    public void display() {
        init();

        boolean running = true;
        while (running) {
            System.out.println("\n=== " + dealership.getName() + " ===");
            System.out.println("1 - Find vehicles within a price range");
            System.out.println("2 - Find vehicles by make / model");
            System.out.println("3 - Find vehicles by year range");
            System.out.println("4 - Find vehicles by color");
            System.out.println("5 - Find vehicles by mileage range");
            System.out.println("6 - Find vehicles by type (car, truck, SUV, van)");
            System.out.println("7 - List ALL vehicles");
            System.out.println("8 - Add a vehicle");
            System.out.println("9 - Remove a vehicle");
            System.out.println("99 - Quit");
            System.out.print("Enter command: ");
            int command = scanner.nextInt();
            scanner.nextLine();
            // consume leftover CRLF
            switch (command) {
                case 1 -> processGetByPriceRequest();
                case 2 -> processGetByMakeModelRequest();
                case 3 -> processGetByYearRequest();
                case 4 -> processGetByColorRequest();
                case 5 -> processGetByMileageRequest();
                case 6 -> processGetByVehicleTypeRequest();
                case 7 -> processGetAllVehiclesRequest();
                case 8 -> processAddVehicleRequest();
                case 9 -> processRemoveVehicleRequest();
                case 99 -> {
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid command. Please try again.");
            }
        }
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }

        System.out.println("\nVIN | Year | Make | Model | Type | Color | Odometer | Price");
        System.out.println("--------------------------------------------------------------------------------");
        for (Vehicle v : vehicles) {
            System.out.printf("%-6d | %-4d | %-10s | %-10s | %-6s | %-6s | %-8d | $%.2f%n",
                    v.getVin(), v.getYear(), v.getMake(),
                    v.getModel(), v.getVehicleType(), v.getColor(),
                    v.getOdometer(), v.getPrice());
        }
    }
    public void processGetByPriceRequest() {

        System.out.print("Enter minimum price: ");
        BigDecimal min = new BigDecimal(scanner.nextLine().trim());
        System.out.print("Enter maximum price: ");
        BigDecimal max = new BigDecimal(scanner.nextLine().trim());

        List<Vehicle> vehicles = dealership.getVehiclesByPrice(min, max);
        displayVehicles(vehicles);
    }

    public void processGetByMakeModelRequest() {

        System.out.print("Enter make: ");
        String make = scanner.nextLine().trim();
        System.out.print("Enter model: ");
        String model = scanner.nextLine().trim();

        List<Vehicle> vehicles = dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(vehicles);
    }

    public void processGetByYearRequest() {

        System.out.print("Enter minimum year: ");
        int min = scanner.nextInt();
        System.out.print("Enter maximum year: ");
        int max = scanner.nextInt();
        scanner.nextLine(); // consume CRLF

        List<Vehicle> vehicles = dealership.getVehiclesByYear(min, max);
        displayVehicles(vehicles);
    }

    public void processGetByColorRequest() {

        System.out.print("Enter color: ");
        String color = scanner.nextLine().trim();

        List<Vehicle> vehicles = dealership.getVehiclesByColor(color);
        displayVehicles(vehicles);

    }

    public void processGetByMileageRequest() {

        System.out.print("Enter minimum mileage: ");
        int min = scanner.nextInt();
        System.out.print("Enter maximum mileage: ");
        int max = scanner.nextInt();
        scanner.nextLine(); // consume CRLF

        List<Vehicle> vehicles = dealership.getVehiclesByMileage(min, max);
        displayVehicles(vehicles);

    }
    public void processGetByVehicleTypeRequest() {

        System.out.print("Enter vehicle type (car, truck, SUV, van): ");
        String type = scanner.nextLine().trim();

        List<Vehicle> vehicles = dealership.getVehiclesByType(type);
        displayVehicles(vehicles);

    }
    public void processGetAllVehiclesRequest() {


        List<Vehicle> vehicles = dealership.getAllVehicles();
        displayVehicles(vehicles);

    }
    public void processAddVehicleRequest() {

        System.out.print("Enter VIN: ");
        int vin = scanner.nextInt();
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        scanner.nextLine(); // consume CRLF

        System.out.print("Enter make: ");
        String make = scanner.nextLine().trim();
        System.out.print("Enter model: ");
        String model = scanner.nextLine().trim();
        System.out.print("Enter vehicle type (car, truck, SUV, van): ");
        String vehicleType = scanner.nextLine().trim();
        System.out.print("Enter color: ");
        String color = scanner.nextLine().trim();
        System.out.print("Enter odometer reading: ");
        int odometer = scanner.nextInt();
        scanner.nextLine(); // consume CRLF

        System.out.print("Enter price: ");
        BigDecimal price = new BigDecimal(scanner.nextLine().trim());

        Vehicle newVehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        dealership.addVehicle(newVehicle);

        DealershipFileManager fileManager = new DealershipFileManager();
        fileManager.saveDealership(dealership);
        System.out.println("Vehicle added and inventory saved!");

    }
    public void processRemoveVehicleRequest() {

        System.out.print("Enter the VIN of the vehicle to remove: ");
        int vinToRemove = scanner.nextInt();
        scanner.nextLine(); // consume CRLF

        Vehicle vehicleToRemove = null;
        for (Vehicle v : dealership.getAllVehicles()) {
            if (v.getVin() == vinToRemove) {
                vehicleToRemove = v;
                break;
            }
        }

        if (vehicleToRemove != null) {
            dealership.removeVehicle(vehicleToRemove);
            DealershipFileManager fileManager = new DealershipFileManager();
            fileManager.saveDealership(dealership);
            System.out.println("Vehicle removed and inventory saved!");
        } else {
            System.out.println("Vehicle with VIN " + vinToRemove + " not found.");
        }
    }
}

