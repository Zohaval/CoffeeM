package org.example;

public enum espresso {
    WATER (80),
    MILK (20),
    COFFEE(50);

    private final int measure;

    espresso(int measure) {
        this.measure = measure;
    }

    public int getMeasure() {
        return measure;
    }
}
