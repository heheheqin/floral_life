package com.dream.will.floral_life.bean;

import java.util.List;

/**
 * Author：Will on 2016/12/14 23:07
 * Mail：heheheqin.will@gmail.com
 */

public class StoreRecommendBanner {

    private String code;
    private String text;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String modelId;
        private String modelName;
        private List<ViewBean> view;

        public String getModelId() {
            return modelId;
        }

        public void setModelId(String modelId) {
            this.modelId = modelId;
        }

        public String getModelName() {
            return modelName;
        }

        public void setModelName(String modelName) {
            this.modelName = modelName;
        }

        public List<ViewBean> getView() {
            return view;
        }

        public void setView(List<ViewBean> view) {
            this.view = view;
        }

        public static class ViewBean {
            private String title;
            private String imageUrl;
            private String url;
            private String type;
            private String jumpId;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getJumpId() {
                return jumpId;
            }

            public void setJumpId(String jumpId) {
                this.jumpId = jumpId;
            }
        }
    }
}
