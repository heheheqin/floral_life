package com.dream.will.floral_life.bean;

import java.util.List;

/**
 * Author：Will on 2016/12/12 15:46
 * Mail：heheheqin.will@gmail.com
 */

public class Video {

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
        private int appoint;
        private AuthorBean author;
        private CategoryBean category;
        private boolean check;
        private String content;
        private String content2;
        private String content3;
        private String contentTitle1;
        private String contentTitle2;
        private String contentTitle3;
        private String createDate;
        private String desc;
        private String descIcon;
        private String descTitle;
        private int favo;
        private int fnCommentNum;
        private int fnCuringNum;
        private int fnDifficultyNum;
        private String fnGoodsIds;
        private int fnHumidityNum;
        private int fnIsVph;
        private int fnVphReadNum;
        private boolean hasAddFavo;
        private boolean hasAppoint;
        private String id;
        private String keywords;
        private int newAppoint;
        private int newFavo;
        private int newRead;
        private int order;
        private String pageUrl;
        private int pass;
        private String pushTime;
        private int read;
        private int share;
        private String sharePageUrl;
        private String smallIcon;
        private String title;
        private boolean top;
        private boolean video;
        private String videoUrl;
        private List<?> goodsList;

        public int getAppoint() {
            return appoint;
        }

        public void setAppoint(int appoint) {
            this.appoint = appoint;
        }

        public AuthorBean getAuthor() {
            return author;
        }

        public void setAuthor(AuthorBean author) {
            this.author = author;
        }

        public CategoryBean getCategory() {
            return category;
        }

        public void setCategory(CategoryBean category) {
            this.category = category;
        }

        public boolean isCheck() {
            return check;
        }

        public void setCheck(boolean check) {
            this.check = check;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent2() {
            return content2;
        }

        public void setContent2(String content2) {
            this.content2 = content2;
        }

        public String getContent3() {
            return content3;
        }

        public void setContent3(String content3) {
            this.content3 = content3;
        }

        public String getContentTitle1() {
            return contentTitle1;
        }

        public void setContentTitle1(String contentTitle1) {
            this.contentTitle1 = contentTitle1;
        }

        public String getContentTitle2() {
            return contentTitle2;
        }

        public void setContentTitle2(String contentTitle2) {
            this.contentTitle2 = contentTitle2;
        }

        public String getContentTitle3() {
            return contentTitle3;
        }

        public void setContentTitle3(String contentTitle3) {
            this.contentTitle3 = contentTitle3;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getDescIcon() {
            return descIcon;
        }

        public void setDescIcon(String descIcon) {
            this.descIcon = descIcon;
        }

        public String getDescTitle() {
            return descTitle;
        }

        public void setDescTitle(String descTitle) {
            this.descTitle = descTitle;
        }

        public int getFavo() {
            return favo;
        }

        public void setFavo(int favo) {
            this.favo = favo;
        }

        public int getFnCommentNum() {
            return fnCommentNum;
        }

        public void setFnCommentNum(int fnCommentNum) {
            this.fnCommentNum = fnCommentNum;
        }

        public int getFnCuringNum() {
            return fnCuringNum;
        }

        public void setFnCuringNum(int fnCuringNum) {
            this.fnCuringNum = fnCuringNum;
        }

        public int getFnDifficultyNum() {
            return fnDifficultyNum;
        }

        public void setFnDifficultyNum(int fnDifficultyNum) {
            this.fnDifficultyNum = fnDifficultyNum;
        }

        public String getFnGoodsIds() {
            return fnGoodsIds;
        }

        public void setFnGoodsIds(String fnGoodsIds) {
            this.fnGoodsIds = fnGoodsIds;
        }

        public int getFnHumidityNum() {
            return fnHumidityNum;
        }

        public void setFnHumidityNum(int fnHumidityNum) {
            this.fnHumidityNum = fnHumidityNum;
        }

        public int getFnIsVph() {
            return fnIsVph;
        }

        public void setFnIsVph(int fnIsVph) {
            this.fnIsVph = fnIsVph;
        }

        public int getFnVphReadNum() {
            return fnVphReadNum;
        }

        public void setFnVphReadNum(int fnVphReadNum) {
            this.fnVphReadNum = fnVphReadNum;
        }

        public boolean isHasAddFavo() {
            return hasAddFavo;
        }

        public void setHasAddFavo(boolean hasAddFavo) {
            this.hasAddFavo = hasAddFavo;
        }

        public boolean isHasAppoint() {
            return hasAppoint;
        }

        public void setHasAppoint(boolean hasAppoint) {
            this.hasAppoint = hasAppoint;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public int getNewAppoint() {
            return newAppoint;
        }

        public void setNewAppoint(int newAppoint) {
            this.newAppoint = newAppoint;
        }

        public int getNewFavo() {
            return newFavo;
        }

        public void setNewFavo(int newFavo) {
            this.newFavo = newFavo;
        }

        public int getNewRead() {
            return newRead;
        }

        public void setNewRead(int newRead) {
            this.newRead = newRead;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public String getPageUrl() {
            return pageUrl;
        }

        public void setPageUrl(String pageUrl) {
            this.pageUrl = pageUrl;
        }

        public int getPass() {
            return pass;
        }

        public void setPass(int pass) {
            this.pass = pass;
        }

        public String getPushTime() {
            return pushTime;
        }

        public void setPushTime(String pushTime) {
            this.pushTime = pushTime;
        }

        public int getRead() {
            return read;
        }

        public void setRead(int read) {
            this.read = read;
        }

        public int getShare() {
            return share;
        }

        public void setShare(int share) {
            this.share = share;
        }

        public String getSharePageUrl() {
            return sharePageUrl;
        }

        public void setSharePageUrl(String sharePageUrl) {
            this.sharePageUrl = sharePageUrl;
        }

        public String getSmallIcon() {
            return smallIcon;
        }

        public void setSmallIcon(String smallIcon) {
            this.smallIcon = smallIcon;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isTop() {
            return top;
        }

        public void setTop(boolean top) {
            this.top = top;
        }

        public boolean isVideo() {
            return video;
        }

        public void setVideo(boolean video) {
            this.video = video;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public List<?> getGoodsList() {
            return goodsList;
        }

        public void setGoodsList(List<?> goodsList) {
            this.goodsList = goodsList;
        }

        public static class AuthorBean {
            private int articleCount;
            private int attentionCount;
            private boolean attentioned;
            private String auth;
            private int bbsCount;
            private String birthday;
            private boolean championM;
            private boolean championW;
            private boolean championY;
            private String city;
            private String content;
            private String countryCode;
            private String createDate;
            private boolean dingYue;
            private String email;
            private int experience;
            private int fansCount;
            private boolean gag;
            private String gagBeginDate;
            private String gagEndDate;
            private String headImg;
            private String id;
            private String identity;
            private String imQQ;
            private String imWeibo;
            private String imWeixin;
            private int initSubscibeNum;
            private int integral;
            private int isAddress;
            private String j_PUSH_CODE;
            private boolean jian;
            private int level;
            private String loginDate;
            private String market;
            private int messageCount;
            private String mobile;
            private int mySubscibeNum;
            private String newAuth;
            private String newPassword;
            private int occSelected;
            private String occupation;
            private String password;
            private String realName;
            private String sex;
            private String speciality;
            private String state;
            private int subscibeNum;
            private String terminal;
            private String token;
            private String uplevelPercent;
            private String userName;
            private String validDate_M;
            private String validDate_W;
            private String validDate_Y;
            private List<?> listContent;

            public int getArticleCount() {
                return articleCount;
            }

            public void setArticleCount(int articleCount) {
                this.articleCount = articleCount;
            }

            public int getAttentionCount() {
                return attentionCount;
            }

            public void setAttentionCount(int attentionCount) {
                this.attentionCount = attentionCount;
            }

            public boolean isAttentioned() {
                return attentioned;
            }

            public void setAttentioned(boolean attentioned) {
                this.attentioned = attentioned;
            }

            public String getAuth() {
                return auth;
            }

            public void setAuth(String auth) {
                this.auth = auth;
            }

            public int getBbsCount() {
                return bbsCount;
            }

            public void setBbsCount(int bbsCount) {
                this.bbsCount = bbsCount;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public boolean isChampionM() {
                return championM;
            }

            public void setChampionM(boolean championM) {
                this.championM = championM;
            }

            public boolean isChampionW() {
                return championW;
            }

            public void setChampionW(boolean championW) {
                this.championW = championW;
            }

            public boolean isChampionY() {
                return championY;
            }

            public void setChampionY(boolean championY) {
                this.championY = championY;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCountryCode() {
                return countryCode;
            }

            public void setCountryCode(String countryCode) {
                this.countryCode = countryCode;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public boolean isDingYue() {
                return dingYue;
            }

            public void setDingYue(boolean dingYue) {
                this.dingYue = dingYue;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public int getExperience() {
                return experience;
            }

            public void setExperience(int experience) {
                this.experience = experience;
            }

            public int getFansCount() {
                return fansCount;
            }

            public void setFansCount(int fansCount) {
                this.fansCount = fansCount;
            }

            public boolean isGag() {
                return gag;
            }

            public void setGag(boolean gag) {
                this.gag = gag;
            }

            public String getGagBeginDate() {
                return gagBeginDate;
            }

            public void setGagBeginDate(String gagBeginDate) {
                this.gagBeginDate = gagBeginDate;
            }

            public String getGagEndDate() {
                return gagEndDate;
            }

            public void setGagEndDate(String gagEndDate) {
                this.gagEndDate = gagEndDate;
            }

            public String getHeadImg() {
                return headImg;
            }

            public void setHeadImg(String headImg) {
                this.headImg = headImg;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getIdentity() {
                return identity;
            }

            public void setIdentity(String identity) {
                this.identity = identity;
            }

            public String getImQQ() {
                return imQQ;
            }

            public void setImQQ(String imQQ) {
                this.imQQ = imQQ;
            }

            public String getImWeibo() {
                return imWeibo;
            }

            public void setImWeibo(String imWeibo) {
                this.imWeibo = imWeibo;
            }

            public String getImWeixin() {
                return imWeixin;
            }

            public void setImWeixin(String imWeixin) {
                this.imWeixin = imWeixin;
            }

            public int getInitSubscibeNum() {
                return initSubscibeNum;
            }

            public void setInitSubscibeNum(int initSubscibeNum) {
                this.initSubscibeNum = initSubscibeNum;
            }

            public int getIntegral() {
                return integral;
            }

            public void setIntegral(int integral) {
                this.integral = integral;
            }

            public int getIsAddress() {
                return isAddress;
            }

            public void setIsAddress(int isAddress) {
                this.isAddress = isAddress;
            }

            public String getJ_PUSH_CODE() {
                return j_PUSH_CODE;
            }

            public void setJ_PUSH_CODE(String j_PUSH_CODE) {
                this.j_PUSH_CODE = j_PUSH_CODE;
            }

            public boolean isJian() {
                return jian;
            }

            public void setJian(boolean jian) {
                this.jian = jian;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public String getLoginDate() {
                return loginDate;
            }

            public void setLoginDate(String loginDate) {
                this.loginDate = loginDate;
            }

            public String getMarket() {
                return market;
            }

            public void setMarket(String market) {
                this.market = market;
            }

            public int getMessageCount() {
                return messageCount;
            }

            public void setMessageCount(int messageCount) {
                this.messageCount = messageCount;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public int getMySubscibeNum() {
                return mySubscibeNum;
            }

            public void setMySubscibeNum(int mySubscibeNum) {
                this.mySubscibeNum = mySubscibeNum;
            }

            public String getNewAuth() {
                return newAuth;
            }

            public void setNewAuth(String newAuth) {
                this.newAuth = newAuth;
            }

            public String getNewPassword() {
                return newPassword;
            }

            public void setNewPassword(String newPassword) {
                this.newPassword = newPassword;
            }

            public int getOccSelected() {
                return occSelected;
            }

            public void setOccSelected(int occSelected) {
                this.occSelected = occSelected;
            }

            public String getOccupation() {
                return occupation;
            }

            public void setOccupation(String occupation) {
                this.occupation = occupation;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getSpeciality() {
                return speciality;
            }

            public void setSpeciality(String speciality) {
                this.speciality = speciality;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public int getSubscibeNum() {
                return subscibeNum;
            }

            public void setSubscibeNum(int subscibeNum) {
                this.subscibeNum = subscibeNum;
            }

            public String getTerminal() {
                return terminal;
            }

            public void setTerminal(String terminal) {
                this.terminal = terminal;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public String getUplevelPercent() {
                return uplevelPercent;
            }

            public void setUplevelPercent(String uplevelPercent) {
                this.uplevelPercent = uplevelPercent;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getValidDate_M() {
                return validDate_M;
            }

            public void setValidDate_M(String validDate_M) {
                this.validDate_M = validDate_M;
            }

            public String getValidDate_W() {
                return validDate_W;
            }

            public void setValidDate_W(String validDate_W) {
                this.validDate_W = validDate_W;
            }

            public String getValidDate_Y() {
                return validDate_Y;
            }

            public void setValidDate_Y(String validDate_Y) {
                this.validDate_Y = validDate_Y;
            }

            public List<?> getListContent() {
                return listContent;
            }

            public void setListContent(List<?> listContent) {
                this.listContent = listContent;
            }
        }

        public static class CategoryBean {
            private String createDate;
            private String enName;
            private String id;
            private String img;
            private String name;
            private int order;

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getEnName() {
                return enName;
            }

            public void setEnName(String enName) {
                this.enName = enName;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getOrder() {
                return order;
            }

            public void setOrder(int order) {
                this.order = order;
            }
        }
    }
}
