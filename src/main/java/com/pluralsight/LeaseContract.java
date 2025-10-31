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
        String contractHeader = String.format("\n%-8s %-6s %-6s %-6s \n"+
                        "──────────────────────────────────────────────────────────────────────────────────\n",
                "CONTRACT TYPE", "DATE", "CUSTOMER NAME", "CUSTOMER EMAIL");
        String contractData=String.format("%-4s %-6s %-6s %-6s \n",
                "LEASE",super.getDate(),super.getCustomerName(),super.getCustomerEmail());

        String leaseHeader = String.format("\n%-6s %-6s %-6s %-6s %-6s %-6s \n"+
                        "──────────────────────────────────────────────────────────────────────────────────\n",
                "EXPECTED ENDING VALUE", "LEASE FEE", "TOTAL PRICE","MONTHLY PAYMENT");
        String leaseData=String.format("%-6.2f %-6.2f %-6.2f %-6.2f %-6s %-6.2f  \n",
                expectedEndingValue,leaseFee,getTotalPrice(),getMonthlyPayment());


        return "***********************************************************************************\n"+
                contractData+contractData+super.getVehicleSold()+leaseHeader+leaseData+
                "***********************************************************************************\n";
    }
}
