package com.stahocorp.etlprocess.items;

import java.util.Date;
import java.util.Map;

public class InfoItem {
    private long id;
    private Date fetchDate;
    private String name;
    private String manufacturer;
    private double price;
    private double priceDiscount;
    private boolean discount;
    private String url;
    private Map<String, Map<String, String>> attributes;
    private String filename;
    private Date processingDate;
    private int noOfBoughtUnits;
    private int noOfUnitAvailable;

    public int getNoOfUnitAvailable() {
        return noOfUnitAvailable;
    }

    public void setNoOfUnitAvailable(int noOfUnitAvailable) {
        this.noOfUnitAvailable = noOfUnitAvailable;
    }

    public int getNoOfBoughtUnits() {
        return noOfBoughtUnits;
    }

    public void setNoOfBoughtUnits(int noOfBoughtUnits) {
        this.noOfBoughtUnits = noOfBoughtUnits;
    }

    public Date getProcessingDate() {
        return processingDate;
    }

    public void setProcessingDate(Date processingDate) {
        this.processingDate = processingDate;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    public Date getFetchDate() {
        return fetchDate;
    }

    public void setFetchDate(Date fetchDate) {
        this.fetchDate = fetchDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPriceDiscount() {
        return priceDiscount;
    }

    public void setPriceDiscount(double priceDiscount) {
        this.priceDiscount = priceDiscount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Map<String, String>> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Map<String, String>> attributes) {
        this.attributes = attributes;
    }
}
