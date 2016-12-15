package com.dream.will.floral_life.bean;

import java.util.List;

/**
 * Author：Will on 2016/12/14 15:56
 * Mail：heheheqin.will@gmail.com
 */

public class WallpaperList {

    private String msg;
    private List<ResultBean> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        private int dowloadNum;
        private String fnCreateDate;
        private String fnId;
        private int fnIdentity;
        private String fnUpdateDate;
        private int isUse;
        private String lastAdmin;
        private String name;
        private int sequence;
        private List<WallpapersBean> wallpapers;

        public int getDowloadNum() {
            return dowloadNum;
        }

        public void setDowloadNum(int dowloadNum) {
            this.dowloadNum = dowloadNum;
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

        public String getFnUpdateDate() {
            return fnUpdateDate;
        }

        public void setFnUpdateDate(String fnUpdateDate) {
            this.fnUpdateDate = fnUpdateDate;
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

        public List<WallpapersBean> getWallpapers() {
            return wallpapers;
        }

        public void setWallpapers(List<WallpapersBean> wallpapers) {
            this.wallpapers = wallpapers;
        }

        public static class WallpapersBean {
            private String bigImg;
            private int fnAPPCollectionNum;
            private int fnAPPDownloadNum;
            private int fnAPPShareNum;
            private int fnAppReadNum;
            private String fnAuthor;
            private int fnCharge;
            private int fnCollectionNum;
            private String fnCreateDate;
            private int fnDownloadNum;
            private String fnId;
            private int fnIdentity;
            private String fnImg;
            private int fnPrice;
            private int fnReadNum;
            private int fnSequence;
            private int fnShareNum;
            private int fnStatus;
            private String fnTitle;
            private String fnTypeId;
            private String fnUpdateDate;
            private String groupId;
            private int guoupSequence;
            private String keyWord;
            private String releaseDate;
            private String snapImg;
            private Object wallpaperCircle;
            private Object wallpaperGroup;
            private List<WallpaperCirclesBean> wallpaperCircles;

            public String getBigImg() {
                return bigImg;
            }

            public void setBigImg(String bigImg) {
                this.bigImg = bigImg;
            }

            public int getFnAPPCollectionNum() {
                return fnAPPCollectionNum;
            }

            public void setFnAPPCollectionNum(int fnAPPCollectionNum) {
                this.fnAPPCollectionNum = fnAPPCollectionNum;
            }

            public int getFnAPPDownloadNum() {
                return fnAPPDownloadNum;
            }

            public void setFnAPPDownloadNum(int fnAPPDownloadNum) {
                this.fnAPPDownloadNum = fnAPPDownloadNum;
            }

            public int getFnAPPShareNum() {
                return fnAPPShareNum;
            }

            public void setFnAPPShareNum(int fnAPPShareNum) {
                this.fnAPPShareNum = fnAPPShareNum;
            }

            public int getFnAppReadNum() {
                return fnAppReadNum;
            }

            public void setFnAppReadNum(int fnAppReadNum) {
                this.fnAppReadNum = fnAppReadNum;
            }

            public String getFnAuthor() {
                return fnAuthor;
            }

            public void setFnAuthor(String fnAuthor) {
                this.fnAuthor = fnAuthor;
            }

            public int getFnCharge() {
                return fnCharge;
            }

            public void setFnCharge(int fnCharge) {
                this.fnCharge = fnCharge;
            }

            public int getFnCollectionNum() {
                return fnCollectionNum;
            }

            public void setFnCollectionNum(int fnCollectionNum) {
                this.fnCollectionNum = fnCollectionNum;
            }

            public String getFnCreateDate() {
                return fnCreateDate;
            }

            public void setFnCreateDate(String fnCreateDate) {
                this.fnCreateDate = fnCreateDate;
            }

            public int getFnDownloadNum() {
                return fnDownloadNum;
            }

            public void setFnDownloadNum(int fnDownloadNum) {
                this.fnDownloadNum = fnDownloadNum;
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

            public String getFnImg() {
                return fnImg;
            }

            public void setFnImg(String fnImg) {
                this.fnImg = fnImg;
            }

            public int getFnPrice() {
                return fnPrice;
            }

            public void setFnPrice(int fnPrice) {
                this.fnPrice = fnPrice;
            }

            public int getFnReadNum() {
                return fnReadNum;
            }

            public void setFnReadNum(int fnReadNum) {
                this.fnReadNum = fnReadNum;
            }

            public int getFnSequence() {
                return fnSequence;
            }

            public void setFnSequence(int fnSequence) {
                this.fnSequence = fnSequence;
            }

            public int getFnShareNum() {
                return fnShareNum;
            }

            public void setFnShareNum(int fnShareNum) {
                this.fnShareNum = fnShareNum;
            }

            public int getFnStatus() {
                return fnStatus;
            }

            public void setFnStatus(int fnStatus) {
                this.fnStatus = fnStatus;
            }

            public String getFnTitle() {
                return fnTitle;
            }

            public void setFnTitle(String fnTitle) {
                this.fnTitle = fnTitle;
            }

            public String getFnTypeId() {
                return fnTypeId;
            }

            public void setFnTypeId(String fnTypeId) {
                this.fnTypeId = fnTypeId;
            }

            public String getFnUpdateDate() {
                return fnUpdateDate;
            }

            public void setFnUpdateDate(String fnUpdateDate) {
                this.fnUpdateDate = fnUpdateDate;
            }

            public String getGroupId() {
                return groupId;
            }

            public void setGroupId(String groupId) {
                this.groupId = groupId;
            }

            public int getGuoupSequence() {
                return guoupSequence;
            }

            public void setGuoupSequence(int guoupSequence) {
                this.guoupSequence = guoupSequence;
            }

            public String getKeyWord() {
                return keyWord;
            }

            public void setKeyWord(String keyWord) {
                this.keyWord = keyWord;
            }

            public String getReleaseDate() {
                return releaseDate;
            }

            public void setReleaseDate(String releaseDate) {
                this.releaseDate = releaseDate;
            }

            public String getSnapImg() {
                return snapImg;
            }

            public void setSnapImg(String snapImg) {
                this.snapImg = snapImg;
            }

            public Object getWallpaperCircle() {
                return wallpaperCircle;
            }

            public void setWallpaperCircle(Object wallpaperCircle) {
                this.wallpaperCircle = wallpaperCircle;
            }

            public Object getWallpaperGroup() {
                return wallpaperGroup;
            }

            public void setWallpaperGroup(Object wallpaperGroup) {
                this.wallpaperGroup = wallpaperGroup;
            }

            public List<WallpaperCirclesBean> getWallpaperCircles() {
                return wallpaperCircles;
            }

            public void setWallpaperCircles(List<WallpaperCirclesBean> wallpaperCircles) {
                this.wallpaperCircles = wallpaperCircles;
            }

            public static class WallpaperCirclesBean {
                private String fnCreateDate;
                private String fnId;
                private int fnIdentity;
                private int isUse;
                private String lastAdmin;
                private String name;
                private int sequence;
                private String updateDate;

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

                public String getUpdateDate() {
                    return updateDate;
                }

                public void setUpdateDate(String updateDate) {
                    this.updateDate = updateDate;
                }
            }
        }
    }
}
