package com.gen.tool.actUpdate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShowInfo {

@SerializedName("time")
@Expose
private String time;
@SerializedName("location")
@Expose
private String location;
@SerializedName("locationName")
@Expose
private String locationName;
@SerializedName("onSales")
@Expose
private String onSales;
@SerializedName("price")
@Expose
private String price;
@SerializedName("latitude")
@Expose
private Double latitude;
@SerializedName("longitude")
@Expose
private Double longitude;
@SerializedName("endTime")
@Expose
private String endTime;

public String getTime() {
return time;
}

public void setTime(String time) {
this.time = time;
}

public String getLocation() {
return location;
}

public void setLocation(String location) {
this.location = location;
}

public String getLocationName() {
return locationName;
}

public void setLocationName(String locationName) {
this.locationName = locationName;
}

public String getOnSales() {
return onSales;
}

public void setOnSales(String onSales) {
this.onSales = onSales;
}

public String getPrice() {
return price;
}

public void setPrice(String price) {
this.price = price;
}

public Double getLatitude() {
return latitude;
}

public void setLatitude(Double latitude) {
this.latitude = latitude;
}

public Double getLongitude() {
return longitude;
}

public void setLongitude(Double longitude) {
this.longitude = longitude;
}

public String getEndTime() {
return endTime;
}

public void setEndTime(String endTime) {
this.endTime = endTime;
}

}