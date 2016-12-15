package com.dream.will.floral_life.bean;

import java.util.List;

/**
 * Author：Will on 2016/12/14 23:05
 * Mail：heheheqin.will@gmail.com
 */

public class StoreFirtItemList {

    private String code;
    private String text;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String fnName;
        private String fnPic;
        private String itemId;

        public String getFnName() {
            return fnName;
        }

        public void setFnName(String fnName) {
            this.fnName = fnName;
        }

        public String getFnPic() {
            return fnPic;
        }

        public void setFnPic(String fnPic) {
            this.fnPic = fnPic;
        }

        public String getItemId() {
            return itemId;
        }

        public void setItemId(String itemId) {
            this.itemId = itemId;
        }
    }
}
