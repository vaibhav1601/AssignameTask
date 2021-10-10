package com.vaibhav.domain.model;

import java.util.List;

public class ProfileDTO {

    private int page;
    private int perPage;
    private int total;
    private int totalpages;

    private List<UserSuccessDTO> mUserSuccessDTOS;


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalpages() {
        return totalpages;
    }

    public void setTotalpages(int totalpages) {
        this.totalpages = totalpages;
    }

    public List<UserSuccessDTO> getmUserSuccessDTOS() {
        return mUserSuccessDTOS;
    }

    public void setmUserSuccessDTOS(List<UserSuccessDTO> mUserSuccessDTOS) {
        this.mUserSuccessDTOS = mUserSuccessDTOS;
    }
}
