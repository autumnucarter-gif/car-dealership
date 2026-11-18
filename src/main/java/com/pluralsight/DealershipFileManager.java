package com.pluralsight;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.regex.Pattern;

public class DealershipFileManager {
    private static final String FILE_NAME = "inventory.csv";
    public Dealership getDealership() {
        Dealership dealership = null;
        try { BufferedReader bufReader = new BufferedReader(new FileReader(FILE_NAME));
            // Read first line for Dealership details
            String line = bufReader.readLine();
            if (line != null) {
                String[] dealershipTokens = line.split(Pattern.quote("|"));
                String name = dealershipTokens[0];
                String address = dealershipTokens[1];
                String phone = dealershipTokens[2];
                dealership = new Dealership(name, address, phone);
            }
            // Read remaining lines for Vehicle inventory
            while ((line = bufReader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] tokens = line.split(Pattern.quote("|"));
                int vin = Integer.parseInt(tokens[0]);
                int year = Integer.parseInt(tokens[1]);
                String make = tokens[2];
                String model = tokens[3];
                String vehicleType = tokens[4];
                String color = tokens[5];
                int odometer = Integer.parseInt(tokens[6]);
                BigDecimal price = new BigDecimal(tokens[7]);

                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                if (dealership != null) {
                    dealership.addVehicle(vehicle);
                }
            }
            bufReader.close();
        }
        catch (IOException e)
        { System.out.println("Error reading dealership inventory file: " + e.getMessage());
        }
        return dealership;
    }
    public void saveDealership(Dealership dealership) {
        try { BufferedWriter bufWriter = new BufferedWriter(new FileWriter(FILE_NAME)); // Write first line:
            String header = String.format("%s|%s|%s%n",
                    dealership.getName(), dealership.getAddress(), dealership.getPhone());

            bufWriter.write(header); // Write each Vehicle on its own line
            for (Vehicle v : dealership.getAllVehicles()) {
                String vehicleLine = String.format("%d|%d|%s|%s|%s|%s|%d|%.2f%n",
                        v.getVin(), v.getYear(), v.getMake(),
                        v.getModel(), v.getVehicleType(), v.getColor(),
                        v.getOdometer(), v.getPrice());
                bufWriter.write(vehicleLine);
            }
            bufWriter.close();
        } catch (IOException e)
        { System.out.println("Error saving dealership inventory file: " + e.getMessage());
        }
    }
}


