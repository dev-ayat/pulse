package com.moh.departments.constants;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.moh.departments.activiteis.DialogActivity;
import com.moh.departments.dialog.DialogLoding;
import com.moh.departments.dialog.DialogMsg;
import com.moh.departments.models.PrivMenu;

import java.util.ArrayList;

public class Controller extends Application {

    public static final String TAG = Controller.class.getSimpleName();
    public static ConnectivityManager connectivityManager;
    public static Activity act_msg_dialog, act_iyad_dialog;
    public static DialogActivity miyadDialog;
    // public static String ROOT = "http://pulse.moh.gov.ps/newehosapi/index.php/Android_api/";
    public static String ROOT = "http://apps.moh.gov.ps/ehos/index.php/Android_api/";
    public static String ROOT_PDF = "http://apps.moh.gov.ps/newwebarch/index.php/Androidhos_api/";
    public static String PERSONAL_IMG = "http://apps.moh.gov.ps/newwebemp/AndroidAPI/getimage.php?no=";
    public static String AUTH_URL = ROOT + "auth_user";
    public static String USER_SPC_DEPT_URL = ROOT + "GET_USER_SPC_DEPARTMENTS";
    public static String USER_DEPT_URL = ROOT + "Get_User_Departments";
    public static String DEPT_PAT_URL = ROOT + "GET_PATIENT_BY_DEPT";
    public static String LAB_ORDERS_URL = ROOT + "GET_LAB_ORDERS";
    public static String LAB_RESULT_URL = ROOT + "GET_LAB_CAT";
    public static String Efile_VISIT_URL = ROOT + "GET_PATEINT_VISIT";
    public static String Efile_URL = ROOT + "GET_FILES_BY_VISIT";
    public static String Efile_PDF_URL = ROOT_PDF + "GET_FILES_BY_VISIT_PDF";
    public static String LAB_ORDER_GROUPS_URL = ROOT + "GET_LAB_GROUPS";
    public static String INSERT_LAB_ORDER_URL = ROOT + "INSERT_LAB_ORDER";
    public static String INSERT_FAV_LAB_TEST_URL = ROOT + "ADD_TOFAVOURITE_TEST";
    public static String DELETE_FAV_LAB_TEST_URL = ROOT + "REMOVEFROM_FAV_TEST";
    public static String GET_FAV_LAB_TEST_URL = ROOT + "GET_ALL_FAVOURITE_TEST";
    public static String GET_GET_PHOTO_RAD_URL = ROOT + "GET_PAINT_REPORT_RAD";
    public static DialogLoding LOADER_DIALOG = null;
    public static DialogMsg Msg_DIALOG = null;
    public static SharedPreferences pref;
    public static SharedPreferences.Editor editor;

    public static ArrayList<PrivMenu> PrivMENUS = new ArrayList();
    //  public static ArrayList<JSONObject> SCREENS = new ArrayList();
    public static Context myContext;
    private static Controller mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    public static synchronized Controller getInstance() {
        return mInstance;
    }

//    public static ArrayList<Labordertestmodel> getLabordertestmodel()
//    {
//        Gson gson ;
//        gson=new Gson();
//        pref = myContext.getSharedPreferences("preferencename", 0);
//        String gsonLabordertestmodel = pref.getString(OrderList,"");
//        Type listType = new TypeToken<ArrayList<Labordertestmodel>>(){}.getType();
//        return gson.fromJson(gsonLabordertestmodel, listType);
//    }
//    public static void setArrayPrefs(ArrayList<Labordertestmodel> array) {
//
//        Gson gson = new Gson();
//        String gsonLabordertestmodel = gson.toJson(array);
//        pref = myContext.getSharedPreferences("preferencename", 0);
//        SharedPreferences.Editor editor = pref.edit();
//        editor.remove(OrderList);
//        editor.commit();
//        editor.putString(OrderList,gsonLabordertestmodel);
//        editor.commit();
//    }

    public static boolean IsConnected() {
        connectivityManager = mInstance.getConnectionManager();

        NetworkInfo activeInfo = connectivityManager.getActiveNetworkInfo();
        if (activeInfo != null && activeInfo.isConnected())
            return true;
        else
            return false;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        this.myContext = getApplicationContext();
        pref = getSharedPreferences("userProfile", MODE_PRIVATE);
        editor = pref.edit();
        initShared();
    }

    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(ConstShared.IS_FIRST_TIME_LAUNCH, true);
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(ConstShared.IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }


    public void initShared() {
        editor.putInt(ConstShared.USER_CODE, -1);
        editor.putString(ConstShared.USER_NAME, "");
        editor.putString(ConstShared.USER_ID, "");
        editor.putString(ConstShared.USER_MOBILE, "");
        editor.putString(ConstShared.USER_EMAIL, "");
        editor.putString(ConstShared.LOGIN_MODE, "0");
        editor.putString(ConstShared.DEPT_CODE, "");
        editor.putString(ConstShared.DEPT_NAME_AR, "");
        editor.putString(ConstShared.DEPT_CD_SELEC, "");
        editor.commit();
    }

    public void initlogininfo() {
        editor.putString(ConstShared.SAVE_LOGIN, "");
        editor.putString(ConstShared.username, "");
        editor.putString(ConstShared.password, "");
        editor.commit();
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public ConnectivityManager getConnectionManager() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager;
    }


}