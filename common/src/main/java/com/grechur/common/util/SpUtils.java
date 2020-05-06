package com.grechur.common.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * @ClassName :SpUtils
 * @Description:从sp存取的工具类
 * @Author :lkl
 * @Date :2016-1-18 下午2:57:11
 * @Version :V1.0
 */
public class SpUtils {
    public static final String SP_NAME = "bsd";
    private static SharedPreferences sp;
    private static String[] split;
    private static String baseH5;

    /**
     * 保存字符串
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveString(Context context, String key, String value) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0);
        sp.edit().putString(key, value).apply();
    }

    /**
     * 保存float
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveFloat(Context context, String key, float value) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0);
        sp.edit().putFloat(key, value).apply();
    }

    /**
     * 保存布尔
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveBoolean(Context context, String key, boolean value) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0);
        sp.edit().putBoolean(key, value).apply();
    }

    /**
     * 保存long
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveLong(Context context, String key, long value) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0);
        sp.edit().putLong(key, value).apply();
    }

    public static void saveInt(Context context, String key, int value) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0);
        sp.edit().putInt(key, value).apply();
    }

    public static int getInt(Context context, String key, int defValue) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0);
        return sp.getInt(key, defValue);
    }

    /**
     * 返回字符串
     *
     * @param context  上下文
     * @param key      key
     * @param defValue 默认值
     * @return
     */
    public static String getString(Context context, String key, String defValue) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0);
        return sp.getString(key, defValue);
    }

    public static boolean getBoolean(Context context, String key,
                                     boolean defValue) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0);
        return sp.getBoolean(key, defValue);
    }

    public static float getFloat(Context context, String key,
                                 float defValue) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0);
        return sp.getFloat(key, defValue);
    }

    public static long getLong(Context context, String key,
                               Long defValue) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0);
        return sp.getLong(key, defValue);
    }



//    /**
//     * 把数据集合里面的数据转换成字符串
//     *
//     * @param mDatas
//     * @return
//     */
//    public static String dataToString(List<CartGoods> mDatas) {
//    /*    StringBuilder spCart = new StringBuilder();
////        ListIterator<CartGoods> listIterator = mDatas.listIterator();
////        while (listIterator.hasNext()) {
//        for (int i = 0; i < mDatas.size(); i++) {
//            CartGoods commodity =mDatas.get(i);
//            spCart.append(commodity.goodsName).append(":");
//            spCart.append(commodity.goodsNum).append(":");
//            spCart.append(commodity.goodsCode).append(":");
//            if(TextUtils.isEmpty(commodity.mSkuCode)){
//                spCart.append(commodity.originalPrice).append(",");
//            }else {
//                spCart.append(commodity.originalPrice).append(":");
//                // 增加SKUCODE
//                spCart.append(commodity.mSkuCode).append(",");
//            }
//        }
//        return spCart.toString();*/
//        Gson gson=new Gson();
//        String jsonObject = gson.toJson(mDatas);
//        return jsonObject;
//    }


//    /**
//     * 把sp文件里面的数据转换成对象列表
//     *
//     * @param context
//     * @return
//     */
//    public static List<CartGoods> spToData(Context context) {
//       /* String spCollection = SpUtils.getString(context, ConstantUtils.CART, "");
//        List<CartGoods> mDatas = new ArrayList<>();
//        if (!TextUtils.isEmpty(spCollection)) {
//            String[] split = spCollection.split(",");
//            for (int i = 0; i < split.length; i++) {
//                CartGoods goodsBean = new CartGoods();
//                String[] split1 = split[i].split(":");
//                if (split1.length == 4) {
//                    goodsBean.goodsName = split1[0];
//                    goodsBean.goodsNum = Integer.parseInt(split1[1]);
//                    goodsBean.goodsCode = split1[2];
//                    goodsBean.originalPrice = Integer.parseInt(split1[3]);
//                    mDatas.add(goodsBean);
//                }else if (split1.length == 5) {
//                    goodsBean.goodsName = split1[0];
//                    goodsBean.goodsNum = Integer.parseInt(split1[1]);
//                    goodsBean.goodsCode = split1[2];
//                    goodsBean.originalPrice = Integer.parseInt(split1[3]);
//                    goodsBean.mSkuCode = split1[4];
//                    mDatas.add(goodsBean);
//                }
//            }
//
//        }
//        return mDatas;*/
//        String spCollection = SpUtils.getString(context, ConstantUtils.CART, "");
//        Gson gson = new Gson();
//        List<CartGoods> mDatas = gson.fromJson(spCollection, new TypeToken<List<CartGoods>>(){}.getType());
//        if(mDatas==null){
//            mDatas=new ArrayList<CartGoods>();
//        }
//        return mDatas;
//    }

//    /**
//     * 获得购物车中的商品数量
//     *
//     * @param context
//     * @return
//     */
//    public static int getCartNum(Context context) {
//        int cartNum = 0;
//        List<CartGoods> goodsBeans = spToData(context);
//        ListIterator<CartGoods> goodsBeanListIterator = goodsBeans.listIterator();
//        while (goodsBeanListIterator.hasNext()) {
//            CartGoods goodsBean = goodsBeanListIterator.next();
//            cartNum += goodsBean.goodsNum;
//        }
//        return cartNum;
//    }




    /**
     * 获取token值
     *
     * @return
     * @param: context
     */
    public static String getToken(Context context) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0);
        return sp.getString(ConstantUtils.TOKENID, "");
    }

    /**
     * 保存手机号
     *
     * @return
     * @param: context
     */
    public static void saveMobilePhone(Context context, String phone) {
        saveString(context, ConstantUtils.PHONE, phone);
    }

    /**
     * 获取手机号
     *
     * @return
     * @param: context
     */
    public static String getMobilePhone(Context context) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0);
        return sp.getString(ConstantUtils.PHONE, "");
    }

//    /**
//     * 获取UserId值
//     *
//     * @return
//     * @param: context
//     */
//    public static String getUserId(Context context) {
////        if (sp == null)
////            sp = context.getSharedPreferences(SP_NAME, 0);
//        String uid=com.basestonedata.instalment.manager.UserManager.getInstance().getUserId();
//        return uid;
//    }

    /**
     * 获取h5地址
     *
     * @return
     * @param: context
     */
    public static String getBaseH5AppUrl(final Context context) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, 0);
        baseH5 = "";
        baseH5 = sp.getString(ConstantUtils.BASEH5URL, "");
        if (TextUtils.isEmpty(baseH5)) {
            baseH5 = "http://cdn.xiaoxiangyoupin.com/";
        }
        return baseH5;
    }

//    /**
//     * 获取售后平台介绍
//     *
//     * @return
//     * @param: context
//     */
//    public static List<PlatformInfo> getAfterSaleInfo(Context context) {
//        if (sp == null)
//            sp = context.getSharedPreferences(SP_NAME, 0);
//        String string = sp.getString(ConstantUtils.AFTERSALE, "");
//        UiUtils.logD("qude" + string);
//        try {
//            List<PlatformInfo> PlatformInfos = GsonUtils.changeGsonToList(string, new TypeToken<List<PlatformInfo>>() {
//            }.getType());
//            if (null != PlatformInfos && PlatformInfos.size() > 0) {
//                return PlatformInfos;
//            }
//        } catch (Exception e) {
//
//        }
//
//        return null;
//    }
}
