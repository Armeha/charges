package com.osipyan.armen;

public class Subscriber {
    private int id;
    private String lastName;
    private String street;
    private int houseNumber;
    private int apartmentNumber;
    private int accrualType;
    private int previousAccrual;
    private int currentAccrual;
    private double accrual;

    public Subscriber() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public int getAccrualType() {
        return accrualType;
    }

    public void setAccrualType(int accrualType) {
        this.accrualType = accrualType;
    }

    public int getPreviousAccrual() {
        return previousAccrual;
    }

    public void setPreviousAccrual(int previousAccrual) {
        this.previousAccrual = previousAccrual;
    }

    public int getCurrentAccrual() {
        return currentAccrual;
    }

    public void setCurrentAccrual(int currentAccrual) {
        this.currentAccrual = currentAccrual;
    }

    public double getAccrual() {
        return accrual;
    }

    public void setAccrual() {
        if (this.accrualType == 1) {
            this.accrual = 301.26;
        } else {
            this.accrual = (this.currentAccrual - this.previousAccrual) * 1.52;
        }
    }

    public void setAccrual(double accrual) {
        this.accrual = accrual;
    }

    @Override
    public String toString() {
        return
                id +
                        lastName + '\'' +
                        street + '\'' +
                        houseNumber +
                        apartmentNumber +
                        accrualType +
                        previousAccrual +
                        currentAccrual +
                        accrual
                ;
    }
}
