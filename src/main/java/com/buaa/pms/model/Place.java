package com.buaa.pms.model;

public class Place {

    private String placeUid;
    private String placeId;
    private String placeName;
    private String placeOrgUid;
    private String placeOrgName;
    private Integer placeType;
    private Integer placeArea;
    private String placeDesc;

    public String getPlaceUid() {
        return placeUid;
    }

    public void setPlaceUid(String placeUid) {
        this.placeUid = placeUid;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceOrgUid() {
        return placeOrgUid;
    }

    public void setPlaceOrgUid(String placeOrgUid) {
        this.placeOrgUid = placeOrgUid;
    }

    public String getPlaceOrgName() {
        return placeOrgName;
    }

    public void setPlaceOrgName(String placeOrgName) {
        this.placeOrgName = placeOrgName;
    }

    public Integer getPlaceType() {
        return placeType;
    }

    public void setPlaceType(Integer placeType) {
        this.placeType = placeType;
    }

    public Integer getPlaceArea() {
        return placeArea;
    }

    public void setPlaceArea(Integer placeArea) {
        this.placeArea = placeArea;
    }

    public String getPlaceDesc() {
        return placeDesc;
    }

    public void setPlaceDesc(String placeDesc) {
        this.placeDesc = placeDesc;
    }

    @Override
    public String toString() {
        return "Place{" +
                "placeUid='" + placeUid + '\'' +
                ", placeId='" + placeId + '\'' +
                ", placeName='" + placeName + '\'' +
                ", placeOrgUid='" + placeOrgUid + '\'' +
                ", placeOrgName='" + placeOrgName + '\'' +
                ", placeType=" + placeType +
                ", placeArea=" + placeArea +
                ", placeDesc='" + placeDesc + '\'' +
                '}';
    }
}
