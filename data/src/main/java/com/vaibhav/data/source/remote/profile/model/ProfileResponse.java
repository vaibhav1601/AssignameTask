package com.vaibhav.data.source.remote.profile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfileResponse {

    @SerializedName("per_page")
    @Expose

    private int per_page;

    @SerializedName("total")
    @Expose

    private int total;

    @SerializedName("data")
    @Expose

    private List<UserDataResponse> data;

    @SerializedName("page")
    @Expose

    private int page;

    @SerializedName("total_pages")
    @Expose

    private int total_pages;


    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<UserDataResponse> getData() {
        return data;
    }

    public void setData(List<UserDataResponse> data) {
        this.data = data;
    }
}
