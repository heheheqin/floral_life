package com.dream.will.floral_life.bean;

import java.util.List;

/**
 * Author：Will on 2016/12/15 15:47
 * Mail：heheheqin.will@gmail.com
 */

public class StoreThemeGoods {

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
        private String themeId;
        private String themeName;
        private List<ThemeGoodsViewsBean> themeGoodsViews;

        public String getThemeId() {
            return themeId;
        }

        public void setThemeId(String themeId) {
            this.themeId = themeId;
        }

        public String getThemeName() {
            return themeName;
        }

        public void setThemeName(String themeName) {
            this.themeName = themeName;
        }

        public List<ThemeGoodsViewsBean> getThemeGoodsViews() {
            return themeGoodsViews;
        }

        public void setThemeGoodsViews(List<ThemeGoodsViewsBean> themeGoodsViews) {
            this.themeGoodsViews = themeGoodsViews;
        }

        public static class ThemeGoodsViewsBean {
            private String goodsId;
            private String goodsName;
            private String goodsEnglisName;
            private String goodsImg;
            private String goodsBigImg;
            private double price;
            private double marketPrice;
            private boolean outOfSku;

            public String getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(String goodsId) {
                this.goodsId = goodsId;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public String getGoodsEnglisName() {
                return goodsEnglisName;
            }

            public void setGoodsEnglisName(String goodsEnglisName) {
                this.goodsEnglisName = goodsEnglisName;
            }

            public String getGoodsImg() {
                return goodsImg;
            }

            public void setGoodsImg(String goodsImg) {
                this.goodsImg = goodsImg;
            }

            public String getGoodsBigImg() {
                return goodsBigImg;
            }

            public void setGoodsBigImg(String goodsBigImg) {
                this.goodsBigImg = goodsBigImg;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public double getMarketPrice() {
                return marketPrice;
            }

            public void setMarketPrice(double marketPrice) {
                this.marketPrice = marketPrice;
            }

            public boolean isOutOfSku() {
                return outOfSku;
            }

            public void setOutOfSku(boolean outOfSku) {
                this.outOfSku = outOfSku;
            }
        }
    }
}
