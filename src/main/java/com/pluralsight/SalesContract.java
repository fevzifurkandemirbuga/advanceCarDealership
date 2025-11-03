package com.pluralsight;

public class SalesContract extends Contract{


    private double salesTax;
    private double recordingFee;
    private double processingFee;
    private boolean financed;

    public SalesContract(Vehicle vehicleSold,String date, String customerName, String customerEmail,boolean financed) {



        super(vehicleSold, date, customerName, customerEmail);
        this.salesTax=vehicleSold.getPrice()*0.05;
        this.recordingFee=100;
        this.financed = financed;
        this.processingFee=(vehicleSold.getPrice()<10000 ? 295 : 495);
    }

    public boolean isFinance() {
        return financed;
    }

    public void setFinance(boolean finance) {
        this.financed = finance;
    }



    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public double getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(double salesTax) {
        this.salesTax = salesTax;
    }

    @Override
    public double getTotalPrice() {
        if(financed){
            if(super.getVehicleSold().getPrice()>10000){
                return super.getVehicleSold().getPrice()*1.0425;
            }
            return super.getVehicleSold().getPrice()*1.0525;
        }
        return super.getVehicleSold().getPrice();
    }

    @Override
    public double getMonthlyPayment() {
        if(financed){
            if(super.getVehicleSold().getPrice()>10000){
                return getTotalPrice()/48;
            }
            return getTotalPrice()/24;
        }

        return 0;
    }

    @Override
    public String toString() {

        String contractHeader = String.format("\n%-14s %-11s %-14s %-6s \n"+
                        "──────────────────────────────────────────────────────────────────────────────────\n",
                "CONTRACT TYPE", "DATE", "CUSTOMER NAME", "CUSTOMER EMAIL");
        String contractData=String.format("%-14s %-11s %-14s %-6s \n",
                "SALE",super.getDate(),super.getCustomerName(),super.getCustomerEmail());

        String saleHeader = String.format("\n%-11s %-15s %-16s %-12s %-17s %-17s \n"+
                        "──────────────────────────────────────────────────────────────────────────────────\n",
                "SALES TAX", "RECORDING FEE", "PROCESSING FEE", "TOTAL PRICE","FINANCE OPTION0","MONTHLY PAYMENT");
        String saleData=String.format("%-11.2f %-15.2f %-16.2f %-12.2f %-17s %-17.2f  \n",
                salesTax,recordingFee,processingFee,getTotalPrice(),financed,getMonthlyPayment());


        return contractHeader+contractData+super.getVehicleSold()+saleHeader+saleData;

    }
}
