package com.osipyan.armen;

public class AccrualForHouse {
    private int id;
    private String street;
    private int houseNumber;
    private double accrual;

    public AccrualForHouse(int id, String street, int houseNumber, double accrual) {
        this.id = id;
        this.street = street;
        this.houseNumber = houseNumber;
        this.accrual = accrual;
    }

    public int getId() {
        return id;
    }


    public String getStreet() {
        return street;
    }


    public int getHouseNumber() {
        return houseNumber;
    }


    public double getAccrual() {
        return accrual;
    }


}
