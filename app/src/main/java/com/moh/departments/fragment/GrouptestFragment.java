package com.moh.departments.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.moh.departments.R;
import com.moh.departments.adapters.LaborderAdapter;
import com.moh.departments.constants.Controller;
import com.moh.departments.constants.CustomRequest;
import com.moh.departments.dialog.DialogLoding;
import com.moh.departments.dialog.DialogMsg;
import com.moh.departments.models.LaborderModel;
import com.moh.departments.models.Labordertestmodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GrouptestFragment extends Fragment implements LaborderAdapter.myInterface {
    private static ArrayList<LaborderModel> Carddata;
    private static ArrayList<Labordertestmodel> Favlist;
    RecyclerView recyclerView;
    LaborderAdapter laborderAdapter;
    LaborderModel mlaborderModel;
    ArrayList<Labordertestmodel> MyOutput;
    ArrayList<Labordertestmodel> MyFavList;
    DialogLoding mDialogLoding;
    DialogMsg mDialogMsg;
    favtestFragment mfavtestFragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grouptest, container, false);
        mfavtestFragment = new favtestFragment();
        recyclerView = (RecyclerView) view.findViewById(R.id.laborder_groups_recyclerv);
        Carddata = new ArrayList<>();
        laborderAdapter = new LaborderAdapter(Carddata, getContext(), this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(laborderAdapter);
        MyOutput = new ArrayList<>();
        Favlist = new ArrayList<>();
        preparedeptData();
        return view;
    }

    @Override
    public void myClick(Labordertestmodel mLabordertestmodel) {
        Log.e("ERROR", mLabordertestmodel.getTEST_CD() + " " + mLabordertestmodel.getTEST_NAME());
        int pos = searchMyoutput(mLabordertestmodel.getTEST_CD());
        if (pos == -1)
            MyOutput.add(mLabordertestmodel);
        else
            MyOutput.remove(pos);
        //  Controller.setArrayPrefs(MyOutput);
    }

    public int searchMyoutput(String TestCD) {
        int pos = -1;
        for (int i = 0; i < MyOutput.size(); i++)
            if (MyOutput.get(i).getTEST_CD().equals(TestCD))
                pos = i;
        return pos;
    }

    @Override

    public void maddFavClick(Labordertestmodel mLabordertestmodel) {

        Log.e("fav", mLabordertestmodel.getTEST_CD() + " " + mLabordertestmodel.getTEST_NAME());
        MyFavList = new ArrayList<>();
        MyFavList.add(mLabordertestmodel);
        Log.e("fav", "MyFavList.size()=" + MyFavList.size());
        List<String> mFavList = new ArrayList<>();
        for (int i = 0; i < MyFavList.size(); i++) {
            Log.e("fav", "MyFavList.get(i).getTEST_NAME()=" + MyFavList.get(i).getTEST_NAME());
            mFavList.add(MyFavList.get(i).getTEST_NAME());
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setTitle("تأكيد الإضافة إلى المفضلة")
                .setIcon(R.drawable.testicon)
                .setItems(mFavList.toArray(new CharSequence[mFavList.size()]), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Controller.LOADER_DIALOG.showDialog("جاري الإضافة للمفضلة");
                        //mDialogLoding.showDialog("جاري الإضافة للمفضلة");
                        Toast.makeText(getContext(), "Add fav", Toast.LENGTH_SHORT).show();
                        sendFavListData();
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        builder.create();
        builder.show();


    }

    private void preparedeptData() {
        Map<String, String> map = new HashMap<>();

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.LAB_ORDER_GROUPS_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    Log.e("json:", jsonObject.toString());
                    JSONArray groups_arr = (JSONArray) jsonObject.getJSONArray("GROUP");
                    Log.e("json", jsonObject.toString());


                    Log.e("json", "" + groups_arr.length());
                    if (groups_arr.length() > 0) {
                        Log.e("json", "" + groups_arr.getJSONObject(0).getString("GROUP_CD") + " " + groups_arr.getJSONObject(0).getString(("GROUP_NAME_EN")));

                        for (int i = 0; i < groups_arr.length(); i++) {
                            //   JSONObject group_obj = groups_arr.getJSONObject(i);

                            JSONObject group_obj = (JSONObject) groups_arr.get(i);

                            if (group_obj != null) {

                                if (group_obj.getString("GROUP_CD") != null) {
                                    Log.e("json3", "" + groups_arr.getJSONObject(3).getString("GROUP_CD") + " " + groups_arr.getJSONObject(3).getString(("GROUP_NAME_EN")));

                                    mlaborderModel = new LaborderModel(group_obj.getString("GROUP_NAME_EN"), group_obj.getString("GROUP_CD"));
                                    Carddata.add(mlaborderModel);
                                    JSONArray CATEGORY_arr = (JSONArray) group_obj.getJSONArray("CATEGORY");
                                    if (CATEGORY_arr.length() > 0) {
                                        Log.e("json3:", CATEGORY_arr.toString());

                                        for (int y = 0; y < CATEGORY_arr.length(); y++) {
                                            JSONObject CAT_obj = CATEGORY_arr.getJSONObject(y);
                                            Labordertestmodel labordertestmodel = new Labordertestmodel(
                                                    CAT_obj.getString("CATEGORY_NAME_EN"),
                                                    CAT_obj.getString("CATEGORY_CD")
                                            );
                                            mlaborderModel.getmListCat().add(labordertestmodel);
                                        }
                                    }

                                }
                            }
                        }
                    } else {

                    }
                    Log.e("json", "" + Carddata.size());
                    recyclerView.setAdapter(laborderAdapter);
                    laborderAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("json", "ERROR" + e.getMessage());

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError volleyError) {

                Log.e("json", "ErrorListener");
            }
        });

        Controller.getInstance().addToRequestQueue(jsObjRequest);


    }

    private void sendFavListData() {

        String Testcd = null;
        String Testname = null;
        JSONArray CatArray = new JSONArray();

        for (int i = 0; i < MyFavList.size(); i++) {
            Testcd = MyFavList.get(i).getTEST_CD();
            Testname = MyFavList.get(i).getTEST_NAME();
            JSONObject Testobj = new JSONObject();
            try {
                Testobj.put("Testcd", String.valueOf(Testcd));
                Testobj.put("Testname", String.valueOf(Testname));
                CatArray.put(Testobj);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        Map<String, String> map = new HashMap<>();
        map.put("DOC_ID", String.valueOf(Controller.pref.getInt("USER_CODE", -1)));
        map.put("CAT_LIST", CatArray.toString());
        map.put("HOS_NO", "");
        Log.e("map", map.toString());
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.INSERT_FAV_LAB_TEST_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("response:", response.toString());
                try {
                    int res = response.getInt("RESULT");
                    if (res == 1) {
                        Handler aHandler = new Handler();
                        aHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Controller.LOADER_DIALOG.hideDialog();
                                Controller.Msg_DIALOG.showDialog(" تمت الإضافة بنجاح");
                                Favlist.clear();
                            }
                        }, 2000);
                        Controller.Msg_DIALOG.hideDialog();
                    } else if (res == 3) {
                        Handler aHandler = new Handler();
                        aHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Controller.LOADER_DIALOG.hideDialog();
                                Controller.Msg_DIALOG.showDialog("الفحص مضاف مسبقاً للمفضلة");

                            }
                        }, 2000);
                        Controller.Msg_DIALOG.hideDialog();
                    } else {
                        Handler aHandler = new Handler();
                        aHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Controller.LOADER_DIALOG.hideDialog();
                                Controller.Msg_DIALOG.showDialog("لم تتم الإضافة");
                            }
                        }, 3000);
                        Controller.Msg_DIALOG.hideDialog();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Controller.getInstance().addToRequestQueue(jsObjRequest);
    }

    public interface OnCatClickListener {
        void onCatChecked(View v, int position);

        void onCatFavClicked(View v, int position);

    }

}
