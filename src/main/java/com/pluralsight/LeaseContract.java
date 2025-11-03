package com.pluralsight;

public class LeaseContract extends Contract {

    private double expectedEndingValue;
    private double leaseFee;

    public LeaseContract(Vehicle vehicleSold, String date, String customerName, String customerEmail) {

        super(vehicleSold, date, customerName, customerEmail);

        this.expectedEndingValue=vehicleSold.getPrice()/2;
        this.leaseFee=vehicleSold.getPrice()*0.07;
    }

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    @Override
    public double getTotalPrice() {
        return super.getVehicleSold().getPrice()-expectedEndingValue;
    }

    @Override
    public double getMonthlyPayment() {

        return (getTotalPrice()*1.04)/36;
    }

    @Override
    public String toString() {
        String contractHeader = String.format("\n%-14s %-11s %-14s %-6s \n"+
                        "──────────────────────────────────────────────────────────────────────────────────\n",
                "CONTRACT TYPE", "DATE", "CUSTOMER NAME", "CUSTOMER EMAIL");
        String contractData=String.format("%-14s %-11s %-14s %-6s \n",
                "LEASE",super.getDate(),super.getCustomerName(),super.getCustomerEmail());

        String leaseHeader = String.format("\n%-24s %-9s %-11s %-15s\n"+
                        "──────────────────────────────────────────────────────────────────────────────────\n",
                "EXPECTED ENDING VALUE", "LEASE FEE", "TOTAL PRICE","MONTHLY PAYMENT");
        String leaseData=String.format("%-24.2f %-9.2f %-11.2f %-15.2f  \n",
                expectedEndingValue,leaseFee,getTotalPrice(),getMonthlyPayment());


        return contractHeader+contractData+super.getVehicleSold()+leaseHeader+leaseData;
    }
}
