package com.dream.will.floral_life.bean;

import org.json.JSONObject;

/**
 * Author：Will on 2016/12/16 17:14
 * Mail：heheheqin.will@gmail.com
 */

public class CityBean {
    //城市首字母
    private String letter;
    //城市分组id

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    private long typeId;
    private String center_x;
    private String center_y;
    private String cityalias;
    private String cityid;
    private String cityname;
    private String citypinyin;
    private String comparename;
    private String esfalias;
    private String lat;
    private String lng;
    private String mobiletype;
    private int xiaomarent;
    private String xiaomaurl;
    private int xiaomazhuangxiu;

    public CityBean(JSONObject json , long typeId, String letter) {
        this.typeId = typeId;
        this.letter = letter;
        //json
        cityid = json.optString("cityid");
        center_x = json.optString("center_x");
        center_y = json.optString("center_y");
        cityalias = json.optString("cityalias");
        cityname = json.optString("cityname");
        citypinyin = json.optString("citypinyin");
        comparename = json.optString("comparename");
        esfalias = json.optString("esfalias");
        lat = json.optString("lat");
        lng = json.optString("lng");
        mobiletype = json.optString("mobiletype");
        xiaomarent = json.optInt("xiaomarent");
        xiaomaurl = json.optString("xiaomaurl");
        xiaomazhuangxiu = json.optInt("xiaomazhuangxiu");
    }

    public String getCenter_x() {
        return center_x;
    }

    public void setCenter_x(String center_x) {
        this.center_x = center_x;
    }

    public String getCenter_y() {
        return center_y;
    }

    public void setCenter_y(String center_y) {
        this.center_y = center_y;
    }

    public String getCityalias() {
        return cityalias;
    }

    public void setCityalias(String cityalias) {
        this.cityalias = cityalias;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getCitypinyin() {
        return citypinyin;
    }

    public void setCitypinyin(String citypinyin) {
        this.citypinyin = citypinyin;
    }

    public String getComparename() {
        return comparename;
    }

    public void setComparename(String comparename) {
        this.comparename = comparename;
    }

    public String getEsfalias() {
        return esfalias;
    }

    public void setEsfalias(String esfalias) {
        this.esfalias = esfalias;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getMobiletype() {
        return mobiletype;
    }

    public void setMobiletype(String mobiletype) {
        this.mobiletype = mobiletype;
    }

    public int getXiaomarent() {
        return xiaomarent;
    }

    public void setXiaomarent(int xiaomarent) {
        this.xiaomarent = xiaomarent;
    }

    public String getXiaomaurl() {
        return xiaomaurl;
    }

    public void setXiaomaurl(String xiaomaurl) {
        this.xiaomaurl = xiaomaurl;
    }

    public int getXiaomazhuangxiu() {
        return xiaomazhuangxiu;
    }

    public void setXiaomazhuangxiu(int xiaomazhuangxiu) {
        this.xiaomazhuangxiu = xiaomazhuangxiu;
    }

    public CityBean() {
    }


}
