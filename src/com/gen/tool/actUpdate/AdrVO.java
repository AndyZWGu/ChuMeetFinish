package com.gen.tool.actUpdate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdrVO {

@SerializedName("adrs")
@Expose
private String adrs;
@SerializedName("new_adrs")
@Expose
private String newAdrs;
@SerializedName("new_adrs2")
@Expose
private String newAdrs2;
@SerializedName("zipcode")
@Expose
private String zipcode;
@SerializedName("dataver")
@Expose
private String dataver;

public String getAdrs() {
return adrs;
}

public void setAdrs(String adrs) {
this.adrs = adrs;
}

public String getNewAdrs() {
return newAdrs;
}

public void setNewAdrs(String newAdrs) {
this.newAdrs = newAdrs;
}

public String getNewAdrs2() {
return newAdrs2;
}

public void setNewAdrs2(String newAdrs2) {
this.newAdrs2 = newAdrs2;
}

public String getZipcode() {
return zipcode;
}

public void setZipcode(String zipcode) {
this.zipcode = zipcode;
}

public String getDataver() {
return dataver;
}

public void setDataver(String dataver) {
this.dataver = dataver;
}

}