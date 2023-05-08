package com.example.usos.StudentMethods;

import java.io.Serializable;

public class Grade implements Serializable {
    private double value;
    private double weight;

    public Grade(double value, double weight) {
        this.value = value;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "  " +
                value + ", ";
    }

    public double getValue() {
        return value;
    }

    public double getWeight() {
        return weight;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
