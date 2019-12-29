package com.moh.departments.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.moh.departments.R;
import com.moh.departments.adapters.LaborderfavtestAdapter;
import com.moh.departments.constants.Controller;
import com.moh.departments.constants.CustomRequest;
import com.moh.departments.models.Labordertestmodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class favtestFragment extends Fragment implements LaborderfavtestAdapter.myfavInterface {
    private static ArrayList<Labordertestmodel> Favlist;
    RecyclerView recyclerViewfavlist;
    LaborderfavtestAdapter laborderfavtestAdapter;
    ArrayList<Labordertestmodel> MyOutput;
    ArrayList<Labordertestmodel> MyFavList;


    public favtestFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favtest, container, false);
        recyclerViewfavlist = (RecyclerView) view.findViewById(R.id.laborder_fav_test_recyclerv);
        Favlist = new ArrayList<>();
        MyOutput = new ArrayList<>();
        laborderfavtestAdapter = new LaborderfavtestAdapter(Favlist, this);
        RecyclerView.LayoutManager mfavLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewfavlist.setLayoutManager(mfavLayoutManager);
        recyclerViewfavlist.setAdapter(laborderfavtestAdapter);
        preparefavlistData();
        return view;
    }

    private void preparefavlistData() {
        Map<String, String> map = new HashMap<>();
        map.put("DOC_ID", String.valueOf(Controller.pref.getInt("USER_CODE", -1)));
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.GET_FAV_LAB_TEST_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    Log.e("json:", jsonObject.toString());
                    JSONArray fav_arr = (JSONArray) jsonObject.getJSONArray("FAV_LAB_TEST");
                    Log.e("json", jsonObject.toString());


                    Log.e("json", "" + fav_arr.length());
                    if (fav_arr.length() > 0) {
                        Log.e("json", "" + fav_arr.getJSONObject(0).getString("F_LAB_TEST_CD") + " " + fav_arr.getJSONObject(0).getString(("F_TEST_NAME")));

                        for (int i = 0; i < fav_arr.length(); i++) {
                            JSONObject fav_obj = (JSONObject) fav_arr.get(i);
                            if (fav_obj != null) {
                                if (fav_obj.getString("F_LAB_TEST_CD") != null) {

                                    Labordertestmodel mfavlaborderModel = new Labordertestmodel(fav_obj.getString("F_TEST_NAME"), fav_obj.getString("F_LAB_TEST_CD"));
                                    Favlist.add(mfavlaborderModel);


                                }
                            }
                        }
                    } else {

                    }
                    recyclerViewfavlist.setAdapter(laborderfavtestAdapter);
                    laborderfavtestAdapter.notifyDataSetChanged();

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

    public void myfavClick(Labordertestmodel mLabordertestmodel) {
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
    public void mRemoveFavClick(Labordertestmodel mLabordertestmodel) {

        Log.e("REMOVfav", mLabordertestmodel.getTEST_CD() + " " + mLabordertestmodel.getTEST_NAME());
        MyFavList = new ArrayList<>();
        MyFavList.add(mLabordertestmodel);
        Log.e("REMOVfav", "MyFavList.size()=" + MyFavList.size());
        List<String> mFavList = new ArrayList<>();
        for (int i = 0; i < MyFavList.size(); i++) {
            Log.e("REMOVfav", "MyFavList.get(i).getTEST_NAME()=" + MyFavList.get(i).getTEST_NAME());
            mFavList.add(MyFavList.get(i).getTEST_NAME());
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                    .setTitle("تأكيد الحذف من المفضلة")
                    .setIcon(R.drawable.testicon)
                    .setItems(mFavList.toArray(new CharSequence[mFavList.size()]), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    })
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Controller.LOADER_DIALOG.showDialog("جاري الحذف من المفضلة");
                            DelFavListData();
                        }
                    })
                    .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //  Toast.makeText(getContext(),"Cancel",Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                    });
            builder.create();
            builder.show();

        }
    }

    private void DelFavListData() {

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
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.DELETE_FAV_LAB_TEST_URL, map, new Response.Listener<JSONObject>() {
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
                                Controller.Msg_DIALOG.showDialog("تم الحذف بنجاح");
                                Favlist.clear();
                                preparefavlistData();
                            }
                        }, 2000);
                        Controller.Msg_DIALOG.hideDialog();
                    } else {
                        Handler aHandler = new Handler();
                        aHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Controller.LOADER_DIALOG.hideDialog();
                                Controller.Msg_DIALOG.showDialog("لم يتم الحذف");
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

    public interface OnfavCatClickListener {
        void onFavCatChecked(View v, int position);

        void onRemoveFavClicked(View v, int position);

    }

}
