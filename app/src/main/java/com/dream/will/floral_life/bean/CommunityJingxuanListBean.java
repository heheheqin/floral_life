package com.dream.will.floral_life.bean;

import java.util.List;

/**
 * Author：Will on 2016/12/17 10:39
 * Mail：heheheqin.will@gmail.com
 */

public class CommunityJingxuanListBean {


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
        private int appoint;
        private String attachment;
        private String attachmentSnap;
        private int audit;
        private CircleTypeBean circleType;
        private int comment;
        private String content;
        private String createDate;
        private CustomerBean customer;
        private boolean hasAppoint;
        private String id;
        private boolean jian;
        private int jianXuhao;
        private String jingIcon;
        private String keywords;
        private String lastAdminName;
        private String lastOperTime;
        private boolean newest;
        private int newestXuhao;
        private int read;
        private int release;
        private int share;
        private String sharePageUrl;
        private String title;

        public int getAppoint() {
            return appoint;
        }

        public void setAppoint(int appoint) {
            this.appoint = appoint;
        }

        public String getAttachment() {
            return attachment;
        }

        public void setAttachment(String attachment) {
            this.attachment = attachment;
        }

        public String getAttachmentSnap() {
            return attachmentSnap;
        }

        public void setAttachmentSnap(String attachmentSnap) {
            this.attachmentSnap = attachmentSnap;
        }

        public int getAudit() {
            return audit;
        }

        public void setAudit(int audit) {
            this.audit = audit;
        }

        public CircleTypeBean getCircleType() {
            return circleType;
        }

        public void setCircleType(CircleTypeBean circleType) {
            this.circleType = circleType;
        }

        public int getComment() {
            return comment;
        }

        public void setComment(int comment) {
            this.comment = comment;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public CustomerBean getCustomer() {
            return customer;
        }

        public void setCustomer(CustomerBean customer) {
            this.customer = customer;
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

        public boolean isJian() {
            return jian;
        }

        public void setJian(boolean jian) {
            this.jian = jian;
        }

        public int getJianXuhao() {
            return jianXuhao;
        }

        public void setJianXuhao(int jianXuhao) {
            this.jianXuhao = jianXuhao;
        }

        public String getJingIcon() {
            return jingIcon;
        }

        public void setJingIcon(String jingIcon) {
            this.jingIcon = jingIcon;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getLastAdminName() {
            return lastAdminName;
        }

        public void setLastAdminName(String lastAdminName) {
            this.lastAdminName = lastAdminName;
        }

        public String getLastOperTime() {
            return lastOperTime;
        }

        public void setLastOperTime(String lastOperTime) {
            this.lastOperTime = lastOperTime;
        }

        public boolean isNewest() {
            return newest;
        }

        public void setNewest(boolean newest) {
            this.newest = newest;
        }

        public int getNewestXuhao() {
            return newestXuhao;
        }

        public void setNewestXuhao(int newestXuhao) {
            this.newestXuhao = newestXuhao;
        }

        public int getRead() {
            return read;
        }

        public void setRead(int read) {
            this.read = read;
        }

        public int getRelease() {
            return release;
        }

        public void setRelease(int release) {
            this.release = release;
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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public static class CircleTypeBean {
            private String createDate;
            private int sort;
            private String type;
            private String typeId;

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getTypeId() {
                return typeId;
            }

            public void setTypeId(String typeId) {
                this.typeId = typeId;
            }
        }

        public static class CustomerBean {
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
    }
}
