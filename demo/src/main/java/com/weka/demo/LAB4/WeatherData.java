package com.weka.demo.LAB4;

public class WeatherData {
    private String outlook;
    private double temperature;
    private double humidity;
    private String windy;

    public WeatherData(String outlook, double temperature, double humidity, String windy) {
        this.outlook = outlook;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windy = windy;
    }

    public String getOutlook() {
        return outlook;
    }

    public void setOutlook(String outlook) {
        this.outlook = outlook;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public String getWindy() {
        return windy;
    }

    public void setWindy(String windy) {
        this.windy = windy;
    }

    // Gettery i settery
}
