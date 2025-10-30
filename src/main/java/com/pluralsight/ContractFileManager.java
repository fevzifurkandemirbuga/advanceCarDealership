package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {

    public void saveContract(Contract contract){
        try{
            FileWriter fileWriter=new FileWriter("contract.csv",true);
            BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);

            String textFirstPart,contractField;
            String contractType=(contract instanceof SalesContract ? "SALE":"LEASE");
            Vehicle vehicle=contract.getVehicleSold();

            textFirstPart=String.format("%s|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|",
                    contractType,contract.getDate(),contract.getCustomerName(),contract.getCustomerEmail(),
                    vehicle.getVin(),vehicle.getYear(),vehicle.getMake(),vehicle.getModel(),vehicle.getVehicleType(),
                    vehicle.getColor(), vehicle.getOdometer(),vehicle.getPrice());

            if(contract instanceof SalesContract){
                contractField=String.format("%.2f|%.2f|%.2f|%.2f|%s|%.2f",
                        ((SalesContract) contract).getSalesTax(),((SalesContract) contract).getRecordingFee(),
                        ((SalesContract) contract).getProcessingFee(),((SalesContract) contract).getTotalPrice(),
                        ((SalesContract) contract).isFinance(),((SalesContract) contract).getMonthlyPayment());
            }
            else{
                contractField=String.format("%.2f|%.2f|%.2f|%.2f",
                        ((LeaseContract) contract).getExpectedEndingValue(),((LeaseContract) contract).getLeaseFee(),
                        ((LeaseContract) contract).getTotalPrice(),((LeaseContract) contract).getMonthlyPayment());
            }

            bufferedWriter.write(textFirstPart+contractField+"\n");

            bufferedWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }



}
