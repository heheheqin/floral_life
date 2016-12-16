package com.dream.will.floral_life.apiall;

/**
 * Author：Will on 2016/12/12 15:37
 * Mail：heheheqin.will@gmail.com
 */

public class ApiManger {

//   POST 主机
public static final String HOST_POST = "http://app.htxq.net";

//   GET 主机
public static final String HOST_GET = "http://api.htxq.net";

//            首页
//
//    专题
//            文章
public static final String ARTICLE = "/servlet/SysArticleServlet";
//    /servlet/SysArticleServlet
//    post  action=mainList_NewVersion&isVideo=false&currentPageIndex=0&cateId=


//            视屏
    public static final String VIDEO = "/servlet/SysArticleServlet";
//                    /servlet/SysArticleServlet
//    post  action=mainList_NewVersion&isVideo=true&currentPageIndex=0&cateId=

//            下拉刷新  直接请求第一页




//    专题 加载更多
//    /servlet/SysArticleServlet
//            action=mainList_NewVersion     &currentPageIndex=3      &cateId=
public static final String MENU = "/servlet/SysCategoryServlet";

    /*
    本周精选
	/servlet/SysArticleServlet
	post  pageSize=10&action=topContents&currentPageIndex=0
     */

    public static final String JINGXUAN = "/servlet/SysArticleServlet";

    /*
 花艺学堂
	/servlet/SysArticleServlet
	post  action=mainList_NewVersion&currentPageIndex=0&cateId=8dba5958-7da0-4ce9-b1e9-5b92343519a7
 */
    public static final String MENU_DETAIL = "/servlet/SysArticleServlet";


    /*  壁纸页面
    listview
	/servlet/SysWallpaperServlet
	type=click&action=wallpaperLog
	action=getList&phoneType=android&imgScale=1.0&pageIndex=0

     */

    public static final String WALLPAPER = "/servlet/SysWallpaperServlet";


    /**
     *   商城 banner
     */
    public static final String STORE_BANNER = "/cactus/index/getBannerList";

    /**
     *   商城 FirtItemList
     */
    public static final String STORE_FIRTITEMLIST = "/cactus/index/getFirtItemList";
    /**
     *   商城 ThemeBanner
     */
    public static final String STORE_THEMEBANNER = "/cactus/index/getThemeBanner ";

    /**
     *  商城 RecommendBanner
     */
    /*
    本周推荐   横向滑动  srollview
	/cactus/index/getRecommendBanner


		跳转  webview  直接显示
		/shop/PGoodsAction/goodsDetail.do  ?goodsId=3e9f0f8c-39c1-48af-9797-c19013f0e174
     */
    public static final String STORE_RECOMMERD_BANNER = "/cactus/index/getRecommendBanner";


    /**
     * 商城 listview 数据
     */
    public static final String STORE_LISTVIEW = "/cactus/index/getThemeGoods";

/**
 *   社区   精选
 banner
 /servlet/SysAdvertisingServlet
 action=getAdList
 */

public static final String COMMUNITY_JINGXUAN_BANNER = "/servlet/SysAdvertisingServlet";




}
