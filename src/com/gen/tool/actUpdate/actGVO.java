package com.gen.tool.actUpdate;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class actGVO {

@SerializedName("version")
@Expose
private String version;
@SerializedName("UID")
@Expose
private String uID;
@SerializedName("title")
@Expose
private String title;
@SerializedName("category")
@Expose
private String category;
@SerializedName("showInfo")
@Expose
private List<ShowInfo> showInfo = null;
@SerializedName("showUnit")
@Expose
private String showUnit;
@SerializedName("discountInfo")
@Expose
private String discountInfo;
@SerializedName("descriptionFilterHtml")
@Expose
private String descriptionFilterHtml;
@SerializedName("imageUrl")
@Expose
private String imageUrl;
@SerializedName("masterUnit")
@Expose
private List<String> masterUnit = null;
@SerializedName("subUnit")
@Expose
private List<String> subUnit = null;
@SerializedName("supportUnit")
@Expose
private List<String> supportUnit = null;
@SerializedName("otherUnit")
@Expose
private List<String> otherUnit = null;
@SerializedName("webSales")
@Expose
private String webSales;
@SerializedName("sourceWebPromote")
@Expose
private String sourceWebPromote;
@SerializedName("comment")
@Expose
private String comment;
@SerializedName("editModifyDate")
@Expose
private String editModifyDate;
@SerializedName("sourceWebName")
@Expose
private String sourceWebName;
@SerializedName("startDate")
@Expose
private String startDate;
@SerializedName("endDate")
@Expose
private String endDate;
@SerializedName("status")
@Expose
private String status;
@SerializedName("total")
@Expose
private String total;
@SerializedName("hitRate")
@Expose
private Integer hitRate;

public String getVersion() {
return version;
}

public void setVersion(String version) {
this.version = version;
}

public String getUID() {
return uID;
}

public void setUID(String uID) {
this.uID = uID;
}

public String getTitle() {
return title;
}

public void setTitle(String title) {
this.title = title;
}

public String getCategory() {
return category;
}

public void setCategory(String category) {
this.category = category;
}

public List<ShowInfo> getShowInfo() {
return showInfo;
}

public void setShowInfo(List<ShowInfo> showInfo) {
this.showInfo = showInfo;
}

public String getShowUnit() {
return showUnit;
}

public void setShowUnit(String showUnit) {
this.showUnit = showUnit;
}

public String getDiscountInfo() {
return discountInfo;
}

public void setDiscountInfo(String discountInfo) {
this.discountInfo = discountInfo;
}

public String getDescriptionFilterHtml() {
return descriptionFilterHtml;
}

public void setDescriptionFilterHtml(String descriptionFilterHtml) {
this.descriptionFilterHtml = descriptionFilterHtml;
}

public String getImageUrl() {
return imageUrl;
}

public void setImageUrl(String imageUrl) {
this.imageUrl = imageUrl;
}

public List<String> getMasterUnit() {
return masterUnit;
}

public void setMasterUnit(List<String> masterUnit) {
this.masterUnit = masterUnit;
}

public List<String> getSubUnit() {
return subUnit;
}

public void setSubUnit(List<String> subUnit) {
this.subUnit = subUnit;
}

public List<String> getSupportUnit() {
return supportUnit;
}

public void setSupportUnit(List<String> supportUnit) {
this.supportUnit = supportUnit;
}

public List<String> getOtherUnit() {
return otherUnit;
}

public void setOtherUnit(List<String> otherUnit) {
this.otherUnit = otherUnit;
}

public String getWebSales() {
return webSales;
}

public void setWebSales(String webSales) {
this.webSales = webSales;
}

public String getSourceWebPromote() {
return sourceWebPromote;
}

public void setSourceWebPromote(String sourceWebPromote) {
this.sourceWebPromote = sourceWebPromote;
}

public String getComment() {
return comment;
}

public void setComment(String comment) {
this.comment = comment;
}

public String getEditModifyDate() {
return editModifyDate;
}

public void setEditModifyDate(String editModifyDate) {
this.editModifyDate = editModifyDate;
}

public String getSourceWebName() {
return sourceWebName;
}

public void setSourceWebName(String sourceWebName) {
this.sourceWebName = sourceWebName;
}

public String getStartDate() {
return startDate;
}

public void setStartDate(String startDate) {
this.startDate = startDate;
}

public String getEndDate() {
return endDate;
}

public void setEndDate(String endDate) {
this.endDate = endDate;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public String getTotal() {
return total;
}

public void setTotal(String total) {
this.total = total;
}

public Integer getHitRate() {
return hitRate;
}

public void setHitRate(Integer hitRate) {
this.hitRate = hitRate;
}

}