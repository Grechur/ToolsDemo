package com.grechur.common.util;

/**
 * 常量字符串
 */
public class ConstantUtils {

    /**
     * 用户模块
     */
    public static final String USERNAME = "userName";//用户名
    public static final String ISREALNAME = "isRealname";//用户名
    public static final String INFOCERTIFIED = "infoCertified";//用户名
    public static final String USERID = "userID";//用户id
    public static final String TOKENID = "token";//tokenid
    public static final String BASEH5URL = "cdnName";//tokenid
    public static final String CDNTIME = "cdnTime";//tokenid
    public static final String AFTERSALE = "afterSale";//售后平台介绍
    public static final String NEEDVERIFY = "verMsg";//是否验证验证码
    public static final String CART = "new_cart";//购物车
    public static final String COLLECTION = "collection";
    public static final String INDEX = "index";//mainactivity的序号
    public static final String DEFAULTADDRESS = "defaultAddress";//默认地址
    public static final String PAYSTAE = "payState";//支付种类
    public static final String PHONE = "mobile";//手机号
    public static final String PASSWORD = "password";//密码
    public static final String NEWPASS = "newPass";//新密码
    public static final String TYPE = "type";//类型
    public static final String VERCODE = "verCode";//验证码
    public static final String IMGVERCODE = "authCodeName";//图像验证码
    public static final String TITLE = "title";//标题
    public static final String LOGINNAME = "userName";//登录名
    public static final String LOGINPASSWORD = "userPass";//登录密码
    public static final String NEWPHONENUM = "newPhoneNum";//新手机号
    public static final String BANKNAME = "bankName";//银行名称
    public static final String BANKCODE = "bankCode";//银行代码
    public static final String CARDLIST = "cardList";//银行列表
    public static final String ISEMPTYPAYPWD = "isEmptyPayPwd";//是否设置了支付密码
    public static final String ISAUTHORIZATION = "isAuthorization";//是否已经授权过
    public static final String ISREALNAMEAPPROVE = "isRealnameApprove";//是否已经实名
    public static final String ISLEARNINGAUTH = "isLearningAuth";//是否已经学信网授权
    public static final String ISCREDITCARDAUTH = "isCreditCardAuth";//是否已经信用卡授权
    public static final String ISCARRIEROPERATORAUTH = "isCarrieroperatorAuth";//是否已经运营商授权
    public static final String ISCREDITAUTH = "isCreditAuth";//是否已经征信授权
    public static final String CHANNEL = "registerType";//渠道
    public static final String IMEI = "imei";//imei
    public static final String ANDROID_ID = "androidId";//androidId
    public static final String REG_PHONE = "^[1][0-9]{10}$";//验证手机号的正则
    public static final String REG_VERC = "^[0-9]{6}$";//验证验证码的正则
    public static final String REG_IMG_VERC = "^[0-9]{6}$";//图形验证验证码的正则
    public static final String REG_ID = "^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X|x)$";//验证身份证的正则
    public static final String REG_CARDID = "^(\\d{16}|\\d{19})$";//验证银行卡号的正则

    /**
     * 修改密码
     */
    public static final String MODIFY_LOGIN_PASSWORD = "modify_login_password";//修改登陆密码
    public static final String MODIFY_PAY_PASSWORD = "modify_pay_password";//修改支付密码
    public static final String FORGET_LOGIN_PASSWORD = "forget_login_password";//忘记登陆密码
    public static final String FORGET_PAY_PASSWORD = "forget_pay_password";//忘记支付密码
    public static final String SET_PAY_PASSWORD = "set_pay_password";//设置支付密码

    /**
     * 金额确认
     */
    public static final String CASHIER_DESK = "cashier_desk";//还款
    public static final String PETTY_CASH = "petty_cash";//定金
    public static final String PAYDOWN_PAYAMOUNT = "paydown_payamount";//定金

    /**
     * WebView
     */
    public static final String URL = "url";//跳转到WebView传的url
    public static final String IMGURL = "imgUrl";//详情页分享的商品图
    public static final String REBATEFRAGMENT = "rebateFragment";//从超级返跳转到实名认证页
    public static final String NEED_USERINFO = "needUserInfo";//跳到webview是否需要参数
    public static final String CONFIRM_PROTOCOL_BROADCAST = "confirmpPotocolBroadcast";//同意超级返协议后返回页面刷新
    public static final String GUID_BROADCAST = "guidBroadcast";//正向引导

    /**
     * 设备信息
     */
    public static final String IP = "registerIp";//ip地址
    public static final String DEVICEID = "imei";//设备imei（idfa）
    public static final String MAKE = "make";//设备制造商
    public static final String MODEL = "model";//设备型号
    public static final String OS = "os";//设备操作系统
    public static final String OSVERSION = "osVersion";//设备操作系统版本号
    public static final String APPVERSION = "appVersion";//app版本号
    public static final String NETWORK = "netWork";//网络类型
    public static final String MOBILEOPERATIONTIME = "operationContent";//操作時間：注册，登陆，下单，认证时间
    public static final String LAT = "lat";//经度
    public static final String LON = "lon";//纬度
    public static final String NET_TYPE_2G = "2G";//网络2G
    public static final String NET_TYPE_3G = "3G";//网络3G
    public static final String NET_TYPE_4G = "4G";//网络4G
    public static final String NET_TYPE_WIFI = "wifi";//网络wifi
    public static final String NET_TYPE_NONE = "none";//没有类型
    public static final String NET_TYPE_UNKNOW = "unknow";//未知类型
    public static final String MODIFY_IP = "modify_ip";//修改优品IP地址
    public static final String MODIFY_INFO_IP = "modify_info_ip";//修改追踪IP地址


    /**
     * 商品模块
     */
    public static final String GOODCODE = "goodCode";//商品号
    public static final String GOODCOUNT = "goodCount";//商品数量
    public static final String GOODS_SOURCE = "goodSource";//商品来源
    public static final String CATEGORYID = "categoryId";//请求专场数据参数
    public static final String ISHOME = "isHome";//请求专场数据参数
    public static final String HOMETITLENAME = "titleName";//专场名称

    public static final int GO_GOODS_DETAIL = 1;//跳转到商品详情
    public static final int GO_GOODS_LIST = 2;//跳转到商品列表

    /**
     * 订单模块
     */
    public static final String ISFIRSTADD = "isFirstAdd";//第一次添加地址
    public static final String ONLYONE = "onlyOne";//只有一条地址
    public static final String ORDERCODE = "orderCode";//订单号
    public static final String ORDERSTATUS = "orderStatus";//订单号
    public static final String ORDERITEMCODE = "orderItemCode";//订单号
    public static final String ORDERITEM = "c";//订单子条目
    public static final String ORDERLIST = "orderList";//订单列表
    public static final String ORDERDETAIL = "orderDetail";//订单详情
    public static final String FQORDERMONEY = "fqOrderMoney";//订单分期价
    public static final String TOTALORDERMONEY = "totalOrderMoney";//订单总价
    public static final String COUPONORDERMONEY = "couponOrderMoney";//订单现金券优惠金额
    public static final String GOODSFREEDAYS = "goodsFreeDays";//免息天数
    public static final String INSTALMENT_COUNT = "instalmentCount";//分期期数
    public static final String SCHEME = "scheme";//分期方案
    public static final String COUPONNAME = "couponName";//优惠券名称
    public static final String COUPONCODE = "couponCode";//优惠券代码
    public static final String COUPONUNIT = "couponUnit";//优惠券单位
    public static final String TEMPPOSITION = "tempPosition";//选择的优惠券序号
    public static final String AMOUNT = "amount";//分期计划金额
    public static final String PAYMONEY = "payMoney";//支付宝支付的金额
    public static final String FREEDAY = "freeDay";//免息天数
    public static final String PERIODS = "periods";//分期期数
    public static final String ISSUE = "issue";//友盟统计分期期数
    public static final String PETTYCASH = "pettyCash";//定金
    public static final String MSKUCODE = "mSkuCodeSUK";//订单总价
    public static final String GENERAL_ORDER_OLD = "30";//普通订单
    public static final String GENERAL_ORDER_NEW = "31";//普通订单
    public static final String JINGDONG_ORDER = "32";//京东订单
    public static final String BORROW_ORDER = "33";//现金贷订单
    public static final String GOODSFREEZUHEPAY = "goodszuhepay";//小象组合付款，自己+ 小象分期
    /**
     * 返回值
     */
    public static final String ISINTENT = "isIntent";//是否是从意向订单过来的
    public static final int PETTYCASH_TO_WEBVIEW = 701;//从定金支付到webview页
    public static final int ALIPAY_TO_WEBVIEW = 702;//从支付宝支付到webview页
    public static final int PAYMENT_TO_WEBVIEW = 703;//从还款到webview页
    public static final int ORDER = 801;//从订单登录成功返回
    public static final int MY = 802;//从我的登录成功返回
    public static final int CAR = 803;//从我的登录成功返回
    public static final int ADDADDRESS = 804;//从新增地址返回
    public static final int UPADDRESS = 805;//从修改地址返回
    public static final int COUPON = 806;//从优惠券返回
    public static final int RESULT_NEED_CLOSE = 1000;
    public static final int REQUEST_REAL_NAME = 1001;
    public static final int REQUEST_CODE_LOGIN_FROM_TAB = 1002;
    public static final int REQUEST_SEL_BANK_TYPE = 1003;
    public static final int REQUEST_SEL_INSTALMENT_COUPON = 1004;
    public static final int REQUEST_REALNAME_AUTH = 1005;
    public static final int REQUEST_RAISE_LIMITS = 1006;
    public static final int REQUEST_SEL_PAYTYPE = 1007;
    public static final int REQUEST_CODE_LOGIN_FROM_XG = 1008;
    public static final int REQUEST_CODE_LOGIN_FROM_WEBVIEW = 1009;//从webview判断需要登录
    public static final int REQUEST_CODE_REALNAME_FROM_WEBVIEW = 1010;//从webview判断需要实名
    public static final int REQUEST_SEL_EMERGENCY_CONTACT = 1011;//选择紧急联系人
    public static final int REQUEST_CODE_START_DETECT = 1012;
    public static final int REQUEST_CODE_SEL_MAP = 1013;
    public static final int REQUEST_CODE_LEANING_AUTH = 1014;
    public static final int REQUEST_CODE_CAMERA = 1015;
    public static final int REQUEST_CODE_EDIT_IDENTIFICATION = 1016;//实名芝麻授权错误返回身份资料页修改
    public static final int REQUEST_CODE_SET_PWD = 1017;//去设置支付密码

    /**
     * 网络请求
     */
    public static final String NETTIP = "正在请求数据,请稍候";//分期期数
    public static final String XG_PUSH = "XG_PUSH";//推送开关状态
    public static final String UDID = "udid";//指定账号推送标识
    public static final int HTTP_SUCCESS = 200;//返回成功
    public static final int HTTP_FAILURE = 300;//返回失败
    public static final int HTTP_IMG_VERC_FAILURE = 1002;//图片验证码验证失败


    /**
     * 活体检测
     */
    public static final String OUTPUTTYPE = "outputtype";
    public static final String COMPLEXITY = "comlexity";
    public static final String NOTICE = "notice";
    public static final String DETECTLIST = "detectList";
    public static final String MULTIIMAGE = "multiImg";
    public static final String NORMAL = "normal";
    public static final String DEFAULTDETECTLIST = "BLINK MOUTH NOD YAW";


    /**
     * 实名认证步骤
     */
    public static final String IDENTIFICATION = "6501";
    public static final String ZHIMA = "6502";
    public static final String LIVENESS = "6503";
    public static final String JOB = "6504";
    public static final String BANKCARD = "6505";
    public static final String REALNAMELIST = "realNameList";
    //    public static final String STEPLIST = "stepList";
    public static final String AUTHLIST = "authList";
    public static final String STEPINDEX = "compensate";

    /**
     * hotfix
     */
    public static final String APPID_RELEASE = "70441-1";//hotfix上线的appid
    public static final String APPID_TEST = "73505-1";//hotfix测试用的appid

    /**
     * 读物模块
     */
    public static final int Reader_Style_small = 1;//读物小图风格
    public static final int Reader_Style_big = 2;//读物大图风格
    public static final int Reader_click_link = 1;//读物点击跳转到webview
    public static final int Reader_click_banner = 2;//读物点击跳转到专场

    /**
     * Token 失效处理做法
     */
    public static final String IS_NET_GO_CODE = "is_net_go_code";

    public static final String HOME_TAB_DATA = "home_tab_data";//首页tab数据
    public static final String HOME_RECOMMEND_BG = "home_recommend_bg";//首页推荐位背景图

    public static final String HAITAO_FLAG = "haitao_flag";//海淘标志
    public static final String TAXATION = "taxation";//税费
    public static final String CATEGORY_TWO_ID = "categoryTwoId";//	商品二级分类id

    /*
     * 电影票
     *
     * */
    public static final String LIFE_FILM_CITYID = "life_film_cityid";//cityid
    public static final String LIFE_FILM_LON = "life_film_lon";//精度
    public static final String LIFE_FILM_LAT = "life_film_lat";//纬度

    /*
     * 购物车入口来源key
     * */
    public static final String SHOP_CART_SOURCE = "viewSource";

    /*
     * 购物车入口来源value
     * */
    public static final String SOURCE_COMPARE_PRICE = "wholePrice";//全网比价
    public static final String SOURCE_GOODS_DETAIS = "goodsDetail";//商品详情
    public static final String SOURCE_XX_HOME = "home";//小象首页
    public static final String SOURCE_HT_HOME = "haitao";//海淘首页
    public static final String SOURCE_H5 = "h5";//h5

}
