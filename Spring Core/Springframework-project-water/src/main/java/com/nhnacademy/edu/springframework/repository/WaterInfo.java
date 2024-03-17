package com.nhnacademy.edu.springframework.repository;

import java.math.BigDecimal;

public class WaterInfo implements Comparable<WaterInfo> {
    private String city;
    private String sector;
    private double minUsage;
    private double maxUsage;
    private int unitPrice;
    private BigDecimal billTotal;

    public WaterInfo(String city, String sector, double minUsage, double maxUsage, int unitPrice) {
        this.city = city;
        this.sector = sector;
        this.minUsage = minUsage;
        this.maxUsage = maxUsage;
        this.unitPrice = unitPrice;
    }

    public String getCity() {
        return city;
    }

    public String getSector() {
        return sector;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public double getMinUsage() {
        return minUsage;
    }

    public double getMaxUsage() {
        return maxUsage;
    }

    public BigDecimal getBillTotal() {
        return billTotal;
    }

    public boolean isInUsageRange(double waterUsage) {
        return (this.minUsage <= waterUsage) && (this.maxUsage >= waterUsage);
    }

    public void calculateBillTotal(double waterUsage) {
        this.billTotal = BigDecimal.valueOf(waterUsage).multiply(BigDecimal.valueOf(this.unitPrice));
    }

    @Override
    public String toString() {
        return "WaterBill {" +
                "city='" + city + '\'' +
                ", sector='" + sector + '\'' +
                ", unitPrice=" + unitPrice +
                ", billTotal=" + billTotal.intValue() +
                '}';
    }

    @Override
    public int compareTo(WaterInfo o) {
        return this.billTotal.compareTo(o.billTotal);
    }
}
