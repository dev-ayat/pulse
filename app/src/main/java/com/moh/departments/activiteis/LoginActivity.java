package com.moh.departments.activiteis;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.moh.departments.R;
import com.moh.departments.constants.Controller;
import com.moh.departments.constants.CustomRequest;
import com.moh.departments.dialog.DialogLoding;
import com.moh.departments.dialog.DialogMsg;
import com.moh.departments.models.PrivMenu;
import com.moh.departments.models.Screen;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {
    public static final String TAG = Controller.class.getSimpleName();
    Thread splashTread;
    ArrayList<HashMap<String, String>> permissions;
    SharedPreferences pref;
    TextView msg;
    Button btnlogin;
    ImageView gif;
    //private LoadingDialog loader_dialog;
    ArrayList<HashMap<String, String>> USER_LOCATION = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>> USER_SCREEN = new ArrayList<HashMap<String, String>>();
    EditText username, password;
    CheckBox svpasschBox;
    Animation animation, animation2;
    TextView result;
    String user, pass;
    private Dialog pDialog;
    private TextView txtResponse;
    private Controller controller;

    // temporary string to show the parsed response
    private String jsonResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        Controller.LOADER_DIALOG = new DialogLoding(this);
        Controller.Msg_DIALOG = new DialogMsg(this);
        username = findViewById(R.id.txtusername);
        password = findViewById(R.id.txtpassword);
        btnlogin = findViewById(R.id.btnlogin);
        svpasschBox = findViewById(R.id.svpasschBox);
        username.setText("900764135");
        password.setText("900764135");
        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        Controller.editor.putString("SAVE_LOGIN", "0");
        Log.e("TEST", Controller.pref.getString("username", ""));
        if (Controller.pref.getString("SAVE_LOGIN", "0").equals("0")) {
            Log.e("save", Controller.pref.getString("SAVE_LOGIN", "0"));
            username.setText(Controller.pref.getString("username", ""));
            password.setText(Controller.pref.getString("password", ""));
            svpasschBox.setChecked(true);
        }


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = username.getText().toString().trim();
                pass = password.getText().toString().trim();


                if (svpasschBox.isChecked()) {
                    Controller.editor.putBoolean("saveLogin", true);
                    Controller.editor.putString("username", user);
                    Controller.editor.putString("password", pass);
                    Controller.editor.commit();
                } else {
                    Controller.editor.putString("SAVE_LOGIN", "");
                    Controller.editor.putString("username", "");
                    Controller.editor.putString("password", "");
                    Controller.editor.commit();
                }

                if (user.length() < 1 | pass.length() < 1) {
                    Toast.makeText(LoginActivity.this, "user name or password is incorrect", Toast.LENGTH_LONG).show();
                } else {
                    doLogin(user, pass);
                }

            }
        });
    }


    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


    public void doLogin(String username, String password) {

        if (Controller.IsConnected()) {
            Controller.LOADER_DIALOG.showDialog("جاري التحقق من اسم المستخدم و كلمة المرور");
            Map<String, String> map = new HashMap<>();
            map.put("username", username);
            map.put("password", password);
            Log.e("url", Controller.AUTH_URL + " " + map.toString());
            CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.AUTH_URL, map, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject jsonObject) {

                    Log.e("json:", jsonObject.toString());

                    try {
                        JSONObject check_auth_obj = (JSONObject) jsonObject.getJSONObject("check_auth");
                        JSONArray auth_arr = (JSONArray) check_auth_obj.getJSONArray("auth");
                        JSONObject status_obj = auth_arr.getJSONObject(0);
                        Log.e("st auth:", status_obj.getString("status"));
                        int status = Integer.parseInt(status_obj.getString("status"));
                        if (status == 0) {
                            Log.e("ayat87", "ayat87");
                            android.os.Handler aHandler = new android.os.Handler();
                            aHandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Controller.LOADER_DIALOG.hideDialog();
                                    Controller.Msg_DIALOG.showDialog("خطأ في اسم المستخدم أو كلمة المرور");
                                }
                            }, 3000);
                            Controller.Msg_DIALOG.hideDialog();
                        } else {
                            android.os.Handler aHandler = new android.os.Handler();
                            aHandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Controller.LOADER_DIALOG.hideDialog();
                                    Controller.Msg_DIALOG.showDialog("تم تسجيل الدخول بنجاح");
                                }
                            }, 3000);
                            JSONArray users_arr = (JSONArray) jsonObject.getJSONArray("USERS");
                            JSONArray priv_arr = (JSONArray) jsonObject.getJSONArray("PRIVS");
                            JSONObject dept_obj = (JSONObject) jsonObject.getJSONObject("DEPARTMENT");


                            if (dept_obj != null) {
                                JSONArray Spc_dept_arr = (JSONArray) dept_obj.getJSONArray("SPC_DEPARTMENTS");
                                if (Spc_dept_arr.length() > 0) {
                                    Log.e("ayat1", "" + Spc_dept_arr.length());
                                    JSONObject spc_obj = Spc_dept_arr.getJSONObject(0);
                                    Log.e("ayat2", "" + spc_obj.getString("DEPT_NAME_AR"));
                                    Controller.editor.putString("DEPT_CODE", spc_obj.getString("DEPT_CODE"));
                                    Controller.editor.putString("DEPT_NAME_AR", spc_obj.getString("DEPT_NAME_AR"));
                                    Controller.editor.putString("DEPT_CD_SELEC", spc_obj.getString("DEPT_CODE"));
                                    Controller.editor.commit();
                                }
                            }

                            if (users_arr != null) {

                                if (users_arr.length() > 0) {
                                    JSONObject obj = users_arr.getJSONObject(0);

                                    if (obj != null) {


                                        if (obj.getString("USER_CODE") != null) {

                                            Controller.editor.putInt("USER_CODE", Integer.parseInt(obj.getString("USER_CODE")));
                                            Controller.editor.putString("USER_NAME", obj.getString("USER_FULL_NAME"));
                                            Controller.editor.putString("USER_ID", obj.getString("USER_ID"));
                                            Controller.editor.putString("USER_MOBILE", obj.getString("USER_MOBILE"));
                                            Controller.editor.putString("USER_EMAIL", obj.getString("USER_EMAIL"));
                                            Controller.editor.putString("LOGIN_MODE", "1");
                                            Controller.editor.commit();


                                            if (priv_arr != null) {

                                                if (priv_arr.length() > 0) {
                                                    JSONObject menus = priv_arr.getJSONObject(0);

                                                    Controller.editor.putInt("LOC_TYPE", Integer.parseInt(menus.getString("LOC_TYPE")));
                                                    Controller.editor.putInt("LOC_CD", Integer.parseInt(menus.getString("LOC_CD")));
                                                    Controller.editor.commit();

                                                    JSONArray menu_arr = (JSONArray) menus.getJSONArray("menu");
                                                    for (int x = 0; x < menu_arr.length(); x++) {
                                                        JSONObject menu_obj = menu_arr.getJSONObject(x);
                                                        PrivMenu privMenu = new PrivMenu();
                                                        privMenu.setId(Integer.parseInt(menu_obj.getString("MENU_CODE")));
                                                        privMenu.setName(menu_obj.getString("MENU_NAME"));

                                                        JSONObject screens = menu_arr.getJSONObject(x);
                                                        JSONArray screen_arr = (JSONArray) screens.getJSONArray("screen");
                                                        for (int y = 0; y < screen_arr.length(); y++) {
                                                            JSONObject screen_obj = screen_arr.getJSONObject(y);
                                                            Screen screen = new Screen();
                                                            screen.setId(Integer.parseInt(screen_obj.getString("SCREEN_CODE")));
                                                            screen.setName(screen_obj.getString("SCREEN_NAME"));
                                                            privMenu.getScreens().add(screen);
                                                            //  Controller.SCREENS.add(screen);
                                                        }
                                                        Controller.PrivMENUS.add(privMenu);
                                                    }
                                                }
                                            }
                                            android.os.Handler mHandler = new android.os.Handler();
                                            mHandler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Controller.LOADER_DIALOG.hideDialog();
                                                    Log.e("splash", Controller.pref.getString("LOGIN_MODE", "0"));

                                                    ////////
                                                    // Checking for first time launch for boarding screen
                                                    controller = new Controller();
                                                    if (!controller.isFirstTimeLaunch()) {
                                                        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                                                        startActivity(i);
                                                        finish();
                                                    } else {
                                                        Intent i = new Intent(LoginActivity.this, boardingActivity.class);
                                                        controller.setFirstTimeLaunch(false);
                                                        startActivity(i);
                                                        finish();
                                                    }

                                                    ///////

                                                }
                                            }, 3000);

                                        } else {


                                        }
                                    }
                                }
                            }
                        }
                    } catch (JSONException e) {

                        e.printStackTrace();
                        Log.e("json", "ERROR");

                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(final VolleyError volleyError) {
                    android.os.Handler mHandler = new android.os.Handler();
                    if (volleyError instanceof NetworkError) {
                        Log.e("login", "NetworkError");
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Controller.Msg_DIALOG.showDialog("يرجى التحقق من الإتصال بالإنترنت");
                            }
                        }, 7000);
                    } else if (volleyError instanceof ServerError) {
                        Log.e("login", "ServerError");
                    } else if (volleyError instanceof AuthFailureError) {
                        Log.e("login", "AuthFailureError");
                    } else if (volleyError instanceof ParseError) {
                        Log.e("login", "ParseError");
                    } else if (volleyError instanceof NoConnectionError) {
                        Log.e("login", "NoConnectionError");
                    } else if (volleyError instanceof TimeoutError) {
                        Log.e("login", "TimeoutError");
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Controller.Msg_DIALOG.showDialog("يرجى المحاولة فيما بعد");
                            }
                        }, 7000);

                    }
                }
            });

            Controller.getInstance().addToRequestQueue(jsObjRequest);

        } else {
            Controller.Msg_DIALOG.showDialog("يرجى التحقق من الاتصال بالإنترنت");
            android.os.Handler mHandler = new android.os.Handler();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Controller.Msg_DIALOG.hideDialog();
                }
            }, 3000);
        }
    }


    public void showicd10(View view) {
        startActivity(new Intent(this, icd10Activity.class));

    }
}
