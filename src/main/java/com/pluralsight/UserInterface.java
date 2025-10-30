package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    private Dealership dealership;
    private final Scanner scan = new Scanner(System.in);


    public UserInterface() {
        init();
    }

    private void init() {

        dealership = new DealershipFileManager().getDealership();


    }

    public void display() {
        Scanner scan = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("""
                    MENU
                    1) Find vehicles within a price range
                    2) Find vehicles by make / model
                    3) Find vehicles by year range
                    4) Find vehicles by color
                    5) Find vehicles by mileage range
                    6) Find vehicles by type (car, truck, SUV, van)
                    7) List ALL vehicles
                    8) Add a vehicle
                    9) Remove a vehicle
                    10) Sale of a vehicle
                    11) Lease of a vehicle
                    99) Quit
                    """);
            System.out.print("enter your choice: ");
            String command = scan.nextLine();
            switch (command) {
                case "1" -> processGetByPriceRequest();
                case "2" -> processGetByMakeModelRequest();
                case "3" -> processGetByYearRequest();
                case "4" -> processGetByColorRequest();
                case "5" -> processGetByMileageRequest();
                case "6" -> processGetByVehicleTypeRequest();
                case "7" -> processGetAllVehicles();
                case "8" -> addVehicleRequest();
                case "9" -> removeVehicleRequest();
                case "10" -> sellVehicle();
                case "11" -> leaseVehicle();
                case "99" -> running = false;
                default -> System.out.println("invalid choice please try again");
            }
        }
    }

    public void displayVehicles(ArrayList<Vehicle> vehicles) {
        for (Vehicle v : vehicles) {
            System.out.println(v);
        }
    }

    public void processGetByPriceRequest() {

        System.out.print("enter minimum price: ");
        double min = scan.nextDouble();
        System.out.print("enter maximum price: ");
        double max = scan.nextDouble();
        scan.nextLine();

        displayVehicles(dealership.getVehiclesByPrice(min, max));
    }

    public void processGetByMakeModelRequest() {
        System.out.print("enter make: ");
        String make = scan.nextLine();
        System.out.print("enter model:");
        String model = scan.nextLine();

        displayVehicles(dealership.getVehiclesByMakeModel(make, model));

    }

    public void processGetByYearRequest() {
        System.out.print("enter minimum year: ");
        int min = scan.nextInt();
        System.out.print("enter maximum year: ");
        int max = scan.nextInt();
        scan.nextLine();

        displayVehicles(dealership.getVehiclesByYear(min, max));

    }

    public void processGetByColorRequest() {
        System.out.print("enter a color: ");
        String color = scan.nextLine();

        displayVehicles(dealership.getVehiclesByColor(color));
    }

    public void processGetByMileageRequest() {
        System.out.print("enter minimum mileage: ");
        int min = scan.nextInt();
        System.out.print("enter maximum mileage: ");
        int max = scan.nextInt();
        scan.nextLine();

        displayVehicles(dealership.getVehiclesByMileage(min, max));
    }

    public void processGetByVehicleTypeRequest() {
        System.out.print("enter vehicle type: ");
        String vehicleType = scan.nextLine();

        displayVehicles(dealership.getVehiclesByType(vehicleType));
    }

    public void processGetAllVehicles() {
        displayVehicles(dealership.getAllVehicles());
    }

    public Vehicle processGetByVinNumber(){
        Scanner scan =new Scanner(System.in);
        System.out.print("Enter the vin number: ");
        int vinNumber=scan.nextInt();
        scan.nextLine();
        return dealership.getVehicleByVinNumber(vinNumber);
    }

    public void addVehicleRequest() {
        System.out.print("enter vin number: ");
        int vin = scan.nextInt();
        System.out.print("enter year: ");
        int year = scan.nextInt();
        scan.nextLine();
        System.out.print("enter make: ");
        String make = scan.nextLine();
        System.out.print("enter model: ");
        String model = scan.nextLine();
        System.out.print("enter vehicle type: ");
        String type = scan.nextLine();
        System.out.print("enter color: ");
        String color = scan.nextLine();
        System.out.print("enter mileage: ");
        int odometer = scan.nextInt();
        System.out.print("enter price: ");
        double price = scan.nextDouble();
        scan.nextLine();

        dealership.addVehicle(new Vehicle(vin, year, make, model, type, color, odometer, price));
        new DealershipFileManager().saveDealership(dealership);
    }

    public void removeVehicleRequest() {
        ArrayList<Vehicle> listToFilter = dealership.getAllVehicles();

        System.out.print("enter vin number (optional): ");
        String vin = scan.nextLine();
        if (!vin.isEmpty()) {
            int vinNum = Integer.parseInt(vin);
            Vehicle vehicle = listToFilter.stream()
                    .filter(v -> v.getVin() == vinNum)
                    .findFirst()
                    .orElse(null);
            if (vehicle != null) {
                dealership.removeVehicle(vehicle);
                new DealershipFileManager().saveDealership(dealership);
            } else {
                System.out.println("invalid vin number please try again");
                return;
            }
        }
        System.out.print("enter year (optional): ");
        String year = scan.nextLine();
        if (!year.isEmpty()) {
            listToFilter = new ArrayList<>(listToFilter.stream()
                    .filter(v -> v.getYear() == Integer.parseInt(year))
                    .toList()
            );
        }

        System.out.print("enter make (optional): ");
        String make = scan.nextLine();
        if (!make.isEmpty()) {
            listToFilter = new ArrayList<>(listToFilter.stream()
                    .filter(v -> v.getMake().equalsIgnoreCase(make))
                    .toList()
            );
        }
        System.out.print("enter model (optional): ");
        String model = scan.nextLine();
        if (!model.isEmpty()) {
            listToFilter = new ArrayList<>(listToFilter.stream()
                    .filter(v -> v.getModel().equalsIgnoreCase(model))
                    .toList()
            );
        }
        System.out.print("enter vehicle type (optional): ");
        String type = scan.nextLine();
        if (!type.isEmpty()) {
            listToFilter = new ArrayList<>(listToFilter.stream()
                    .filter(v -> v.getVehicleType().equalsIgnoreCase(type))
                    .toList()
            );
        }
        System.out.print("enter color (optional): ");
        String color = scan.nextLine();
        if (!color.isEmpty()) {
            listToFilter = new ArrayList<>(listToFilter.stream()
                    .filter(v -> v.getColor().equalsIgnoreCase(color))
                    .toList()
            );
        }

        if (listToFilter.size() == 1) {
            dealership.removeVehicle(listToFilter.get(0));
            new DealershipFileManager().saveDealership(dealership);

        } else {
            displayVehicles(listToFilter);
            System.out.println("more than 1 car found, please try again");
        }


    }

    public void sellVehicle(){
        Vehicle vehicleSold=processGetByVinNumber();
        if(vehicleSold==null){
            System.out.println("vehicle could not found. Please try again.");
            return;
        }
        System.out.print("date of contract: ");
        String date=scan.nextLine();
        System.out.print("customer name: ");
        String customerName=scan.nextLine();
        System.out.print("customer email: ");
        String customerEmail=scan.nextLine();
        System.out.print("do you want finance(yes/no): ");
        boolean financed=scan.nextLine().equalsIgnoreCase("yes");

        SalesContract salesContract=new SalesContract(vehicleSold,date,customerName,customerEmail,financed);

        ContractFileManager contractFileManager=new ContractFileManager();
        contractFileManager.saveContract(salesContract);


    }

    public void leaseVehicle(){

        Vehicle vehicleSold=processGetByVinNumber();
        if(vehicleSold==null){
            System.out.println("vehicle could not found. Please try again.");
            return;
        }
        System.out.print("date of contract: ");
        String date=scan.nextLine();
        System.out.print("customer name: ");
        String customerName=scan.nextLine();
        System.out.print("customer email: ");
        String customerEmail=scan.nextLine();

        LeaseContract leaseContract=new LeaseContract(vehicleSold,date,customerName,customerEmail);

        ContractFileManager contractFileManager=new ContractFileManager();
        contractFileManager.saveContract(leaseContract);

    }



}
