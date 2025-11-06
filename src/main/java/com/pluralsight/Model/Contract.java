package com.pluralsight.Model;

public abstract class Contract {

    private String date;
    private String customerName;
    private String customerEmail;
    private double totalPrice;
    private double monthlyPayment;
    private Vehicle vehicleSold;


    public Contract(Vehicle vehicle,String date, String customerName, String customerEmail) {
        this.vehicleSold=vehicle;
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Vehicle getVehicleSold() {
        return vehicleSold;
    }

    public void setVehicleSold(Vehicle vehicleSold) {
        this.vehicleSold = vehicleSold;
    }

    public abstract double getTotalPrice();

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public abstract double getMonthlyPayment();

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    @Override
    public String toString() {
        return
                "\ndate='" + date + '\'' +
                "\ncustomerName='" + customerName + '\'' +
                "\ncustomerEmail='" + customerEmail + '\'' +
                "\ntotalPrice=" + totalPrice +
                "\nmonthlyPayment=" + monthlyPayment +
                "\nvehicleSold=" + vehicleSold;
    }
}
