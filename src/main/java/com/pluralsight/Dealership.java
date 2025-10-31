package com.pluralsight;

import java.util.ArrayList;

public class Dealership {

    private final String name;
    private final String address;
    private final String phone;
    private final ArrayList<Vehicle> inventory = new ArrayList<>();


    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public ArrayList<Vehicle> getVehiclesByPrice(double min, double max) {

        return new ArrayList<>(
                inventory.stream()
                        .filter(v -> v.getPrice() >= min && v.getPrice() <= max)
                        .toList()
        );
    }

    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model) {
        return new ArrayList<>(
                inventory.stream()
                        .filter(v -> v.getMake().equalsIgnoreCase(make) && v.getModel().equalsIgnoreCase(model))
                        .toList()
        );
    }

    public ArrayList<Vehicle> getVehiclesByYear(int min, int max) {
        return new ArrayList<>(
                inventory.stream()
                        .filter(v -> v.getYear() >= min && v.getYear() <= max)
                        .toList()
        );
    }

    public ArrayList<Vehicle> getVehiclesByColor(String color) {
        return new ArrayList<>(
                inventory.stream()
                        .filter(v -> v.getColor().equalsIgnoreCase(color))
                        .toList()
        );
    }

    public ArrayList<Vehicle> getVehiclesByMileage(int min, int max) {
        return new ArrayList<>(
                inventory.stream()
                        .filter(v -> v.getOdometer() >= min && v.getOdometer() <= max)
                        .toList()
        );
    }

    public ArrayList<Vehicle> getVehiclesByType(String vehicleType) {
        return new ArrayList<>(
                inventory.stream()
                        .filter(v -> v.getVehicleType().equalsIgnoreCase(vehicleType))
                        .toList()
        );
    }

    public ArrayList<Vehicle> getAllVehicles() {
        return inventory;
    }

    public Vehicle getVehicleByVinNumber(int vinNumber){
        return inventory.stream()
                .filter(v->v.getVin()==vinNumber)
                .findFirst()
                .orElse(null);

    }

    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        inventory.remove(vehicle);

    }

    public void addContract(Contract contract){
        System.out.println("new contract added\n"+contract);
        ContractFileManager contractFileManager=new ContractFileManager();
        contractFileManager.saveContract(contract);
    }



}
