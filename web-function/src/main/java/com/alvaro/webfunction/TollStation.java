package com.alvaro.webfunction;

public class TollStation {
    private String stationId;
    private Float mileMarker;
    private Integer stallCount;

    public TollStation(String stationId, Float mileMarker, Integer stallCount) {
        this.stationId = stationId;
        this.mileMarker = mileMarker;
        this.stallCount = stallCount;
    }

    public String getStationId() {
        return stationId;
    }

    public Float getMileMarker() {
        return mileMarker;
    }

    public Integer getStallCount() {
        return stallCount;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public void setMileMarker(Float mileMarker) {
        this.mileMarker = mileMarker;
    }

    public void setStallCount(Integer stallCount) {
        this.stallCount = stallCount;
    }
}
