
package com.bipul.nrsingdidristict.modelForComplainFechAllGET;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("employee_id")
    @Expose
    private Integer employeeId;
    @SerializedName("subject_id")
    @Expose
    private Integer subjectId;
    @SerializedName("defendant_name")
    @Expose
    private String defendantName;
    @SerializedName("mobile_no")
    @Expose
    private String mobileNo;
    @SerializedName("complain")
    @Expose
    private String complain;
    @SerializedName("complainant_name")
    @Expose
    private String complainantName;
    @SerializedName("complainant_address")
    @Expose
    private String complainantAddress;
    @SerializedName("defendant_address")
    @Expose
    private String defendantAddress;
    @SerializedName("picture")
    @Expose
    private Object picture;
    @SerializedName("updated_by")
    @Expose
    private Object updatedBy;
    @SerializedName("created_by_ip")
    @Expose
    private String createdByIp;
    @SerializedName("updated_by_ip")
    @Expose
    private Object updatedByIp;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getDefendantName() {
        return defendantName;
    }

    public void setDefendantName(String defendantName) {
        this.defendantName = defendantName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getComplain() {
        return complain;
    }

    public void setComplain(String complain) {
        this.complain = complain;
    }

    public String getComplainantName() {
        return complainantName;
    }

    public void setComplainantName(String complainantName) {
        this.complainantName = complainantName;
    }

    public String getComplainantAddress() {
        return complainantAddress;
    }

    public void setComplainantAddress(String complainantAddress) {
        this.complainantAddress = complainantAddress;
    }

    public String getDefendantAddress() {
        return defendantAddress;
    }

    public void setDefendantAddress(String defendantAddress) {
        this.defendantAddress = defendantAddress;
    }

    public Object getPicture() {
        return picture;
    }

    public void setPicture(Object picture) {
        this.picture = picture;
    }

    public Object getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Object updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getCreatedByIp() {
        return createdByIp;
    }

    public void setCreatedByIp(String createdByIp) {
        this.createdByIp = createdByIp;
    }

    public Object getUpdatedByIp() {
        return updatedByIp;
    }

    public void setUpdatedByIp(Object updatedByIp) {
        this.updatedByIp = updatedByIp;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

}
