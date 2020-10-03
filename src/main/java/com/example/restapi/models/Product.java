package com.example.restapi.models;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Product {

    @Id @GeneratedValue
    private long id;

    private String name;

    private String category;

    @ElementCollection
    private List<Double> rates;

    private double avgRate;

    private long ownerId;

    public  Product(){}

    public Product(String name, String category, long ownerId) {
        this.name = name;
        this.category = category;
        this.ownerId = ownerId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Double> getRates() {
        return rates;
    }

    public void setRates(List<Double> rates) {
        this.rates = rates;
    }

    public void addRate(Double rate){
        this.rates.add(rate);
    }

    public double getAvgRate() {
        return avgRate;
    }

    public void setAvgRate(double avgRate) {
        this.avgRate = avgRate;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", rates=" + rates +
                ", avgRate=" + avgRate +
                ", ownerId=" + ownerId +
                '}';
    }
}
