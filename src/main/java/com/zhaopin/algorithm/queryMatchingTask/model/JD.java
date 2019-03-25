package com.zhaopin.algorithm.queryMatchingTask.model;

import java.io.Serializable;

public class JD implements Serializable {
    private static final long serialVersionUID = 111L;

    private String companyId;
    private String jdId;
    private String jdTitle;
    private String jdDesc;

    public JD(String companyId,
              String jdId,
              String jdTitle,
              String jdDesc) {
        this.companyId = companyId;
        this.jdId = jdId;
        this.jdTitle = jdTitle;
        this.jdDesc = jdDesc;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getJdId() {
        return jdId;
    }

    public void setJdId(String jdId) {
        this.jdId = jdId;
    }

    public String getJdTitle() {
        return jdTitle;
    }

    public void setJdTitle(String jdTitle) {
        this.jdTitle = jdTitle;
    }

    public String getJdDesc() {
        return jdDesc;
    }

    public void setJdDesc(String jdDesc) {
        this.jdDesc = jdDesc;
    }

    @Override
    public String toString() {
        return "companyId='" + companyId +
                ",jdId='" + jdId +
                ",jdTitle='" + jdTitle +
                ",jdDesc='" + jdDesc;
    }
}
