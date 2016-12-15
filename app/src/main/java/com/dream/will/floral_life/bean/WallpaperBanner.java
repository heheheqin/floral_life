package com.dream.will.floral_life.bean;

import java.util.List;

/**
 * Author：Will on 2016/12/14 15:45
 * Mail：heheheqin.will@gmail.com
 */

public class WallpaperBanner {


    private String msg;
    private boolean status;
    private List<ResultBean> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        private String beginDate;
        private String endDate;
        private String fnCreateDate;
        private String fnId;
        private int fnIdentity;
        private String imgUrl;
        private int isUse;
        private String lastAdmin;
        private String name;
        private int sequence;

        public String getBeginDate() {
            return beginDate;
        }

        public void setBeginDate(String beginDate) {
            this.beginDate = beginDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getFnCreateDate() {
            return fnCreateDate;
        }

        public void setFnCreateDate(String fnCreateDate) {
            this.fnCreateDate = fnCreateDate;
        }

        public String getFnId() {
            return fnId;
        }

        public void setFnId(String fnId) {
            this.fnId = fnId;
        }

        public int getFnIdentity() {
            return fnIdentity;
        }

        public void setFnIdentity(int fnIdentity) {
            this.fnIdentity = fnIdentity;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public int getIsUse() {
            return isUse;
        }

        public void setIsUse(int isUse) {
            this.isUse = isUse;
        }

        public String getLastAdmin() {
            return lastAdmin;
        }

        public void setLastAdmin(String lastAdmin) {
            this.lastAdmin = lastAdmin;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSequence() {
            return sequence;
        }

        public void setSequence(int sequence) {
            this.sequence = sequence;
        }
    }
}
