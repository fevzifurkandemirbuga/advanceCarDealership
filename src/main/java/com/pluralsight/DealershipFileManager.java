package com.pluralsight;

import java.io.*;

public class DealershipFileManager {

    public Dealership getDealership() {
        Dealership dealership = new Dealership("Fevzi", "Atlanta", "999-999-9999");

        try {
            FileReader fileReader = new FileReader("inventory.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] datas = line.split("\\|");
                int vin = Integer.parseInt(datas[0]);
                int year = Integer.parseInt(datas[1]);
                int odometer = Integer.parseInt(datas[6]);
                double price = Double.parseDouble(datas[7]);

                Vehicle vehicle = new Vehicle(vin, year, datas[2], datas[3], datas[4], datas[5], odometer, price);
                dealership.addVehicle(vehicle);

            }
            bufferedReader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return dealership;
    }

    public void saveDealership(Dealership dealership) {
        try {
            FileWriter fileWriter = new FileWriter("inventory.csv");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("vin|year|make|model|vehicleType|color|odometer|price");

            for (Vehicle v : dealership.getAllVehicles()) {
                String text = String.format("%d|%d|%s|%s|%s|%s|%d|%.2f",
                        v.getVin(), v.getYear(), v.getMake(), v.getModel(),
                        v.getVehicleType(), v.getColor(), v.getOdometer(), v.getPrice());
                bufferedWriter.write(text + "\n");

            }
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
