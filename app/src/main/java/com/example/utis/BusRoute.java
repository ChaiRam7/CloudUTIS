package com.example.utis;

public class BusRoute {
    private String destination;
    private String destinationTime;
    private String route;
    private String source;
    private String sourceTime;

    // Default constructor (required by Firestore)
    public BusRoute() {}

    // Constructor
    public BusRoute(String destination, String destinationTime, String route, String source, String sourceTime) {
        this.destination = destination;
        this.destinationTime = destinationTime;
        this.route = route;
        this.source = source;
        this.sourceTime = sourceTime;
    }

    // Getters and setters
    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestinationTime() {
        return destinationTime;
    }

    public void setDestinationTime(String destinationTime) {
        this.destinationTime = destinationTime;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourceTime() {
        return sourceTime;
    }

    public void setSourceTime(String sourceTime) {
        this.sourceTime = sourceTime;
    }
}

