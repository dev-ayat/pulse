package com.moh.departments.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.moh.departments.models.LabCategoryDataModel;
import com.moh.departments.models.LabTestDataModel;
import com.moh.departments.models.LaborderCardviewDataModel;
import com.moh.departments.models.Labtestcultnotemodel;
import com.moh.departments.models.Labtestcultureantimodel;
import com.moh.departments.models.Labtestculturemodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LaborderCardviewAdapter extends RecyclerView.Adapter<LaborderCardviewAdapter.MyViewHolder> {
    Context con;
    TextView ststus;
    LinearLayoutManager mLinearLayoutManager;
    ArrayList<LabCategoryDataModel> mListCat;
    LabCategoryDataModel mLabCategoryDataModel;
    ArrayList<Object> mListTest;
    LabTestDataModel mLabTestDataModel;
    Labtestculturemodel mLabtestculturemodel;
    Labtestcultureantimodel mLabtestcultureantimodel;
    Labtestcultnotemodel mLabtestcultnotemodel;
    ArrayList<Object> mantiList;
    LabCategoryAdapter labcat;
    CulturantiAdapter anticult;
    Context context;
    private ArrayList<LaborderCardviewDataModel> dataSet;

    public LaborderCardviewAdapter(ArrayList<LaborderCardviewDataModel> dataSet) {
        this.dataSet = dataSet;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lab_order_cardview, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        final LaborderCardviewDataModel Carddata = dataSet.get(listPosition);

        holder.txtorderdate.setText(Carddata.getLaborderdate());
        holder.txtorderid.setText(String.valueOf(Carddata.getLaborderid()));
        holder.txtlaborderyear.setText(String.valueOf(Carddata.getLaborderyear()));
        holder.txtpatriceID.setText(String.valueOf(Carddata.getPatpatricID()));
        holder.txthosno.setText(String.valueOf(Carddata.getHos_no()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout card_container = (LinearLayout) holder.itemView.findViewById(R.id.card_container);
                Integer visiblity = card_container.getVisibility();
                if (visiblity == 8) {
                    card_container.setVisibility(View.VISIBLE);
                    if (Carddata.getmListCat().size() == 0) {
                        Map<String, String> map = new HashMap<>();
                        map.put("ORDER_CD", String.valueOf(Carddata.getLaborderid()));
                        map.put("ORDER_YEAR", String.valueOf(Carddata.getLaborderyear()));
                        map.put("PATRIC_CD", String.valueOf(Carddata.getPatpatricID()));
                        map.put("ORDER_DATE", Carddata.getLaborderdate());
                        map.put("HOS_NO", String.valueOf(Carddata.getHos_no()));
                        Log.e("test", map.toString());
                        //       Toast.makeText(holder.itemView.getContext(), "Request Cat", Toast.LENGTH_SHORT).show();
                        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.LAB_RESULT_URL, map, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {

                                try {
                                    Log.e("json:", jsonObject.toString());
                                    JSONArray cat_arr = (JSONArray) jsonObject.getJSONArray("CAT");
                                    if (cat_arr.length() > 0) {

                                        for (int i = 0; i < cat_arr.length(); i++) {
                                            JSONObject cat_obj = cat_arr.getJSONObject(i);
                                            Log.e("json2:", cat_obj.toString());
                                            Log.e("jsongroup:", cat_obj.toString());
                                            mLabCategoryDataModel = new LabCategoryDataModel(cat_obj.getString("CATEGORY_NAME"), cat_obj.getString("ORDER_STATUS_NAME_EN"), Integer.parseInt(cat_obj.getString("D_ORDER_STATUS")),
                                                    Integer.parseInt(cat_obj.getString("CATEGORY_ID")), cat_obj.getString("GROUP_NAME_EN"), cat_obj.getString("GROUP_CD"));
                                            Carddata.getmListCat().add(mLabCategoryDataModel);
                                            JSONObject tests = cat_arr.getJSONObject(i);
                                            JSONArray test_arr = (JSONArray) tests.getJSONArray("TESTS");
                                            if (test_arr.length() > 0) {
                                                Log.e("json3:", test_arr.toString());

                                                for (int y = 0; y < test_arr.length(); y++) {
                                                    JSONObject test_obj = test_arr.getJSONObject(y);
                                                    if (!cat_obj.getString("GROUP_CD").equals("7")) {
                                                        Log.e("MYERRROR", "mLabTestDataModel=" + y);
                                                        mLabTestDataModel = new LabTestDataModel(
                                                                test_obj.getString("TEST_ITEMS_NAME"),
                                                                test_obj.getString("TEST_UNIT"),
                                                                test_obj.getString("RESULT_VALUE").equals("null") ? " " : test_obj.getString("RESULT_VALUE"),
                                                                test_obj.getString("REFERANCE_NORMAL_VALUE").equals("null") ? " " : test_obj.getString("REFERANCE_NORMAL_VALUE"),
                                                                test_obj.getString("REFERANCE_CRITICAL_VALUE").equals("null") ? " " : test_obj.getString("REFERANCE_CRITICAL_VALUE")
                                                        );
                                                        mLabCategoryDataModel.getmListTest().add(mLabTestDataModel);

                                                    } else {
                                                        if (cat_obj.getString("CATEGORY_ID").equals("125") || cat_obj.getString("CATEGORY_ID").equals("126") || cat_obj.getString("CATEGORY_ID").equals("256") || cat_obj.getString("CATEGORY_ID").equals("15")) {
                                                            Log.e("MYERRROR", "mLabtestcultnotemodel" + y);
                                                            mLabtestcultnotemodel = new Labtestcultnotemodel(
                                                                    test_obj.getString("GRAMSTAIN").equals("null") ? " " : test_obj.getString("GRAMSTAIN"),
                                                                    test_obj.getString("ACID_FAST_STAIN").equals("null") ? " " : test_obj.getString("ACID_FAST_STAIN"),
                                                                    test_obj.getString("KOH").equals("null") ? " " : test_obj.getString("KOH"),
                                                                    test_obj.getString("FUNGI").equals("null") ? " " : test_obj.getString("FUNGI"),
                                                                    test_obj.getString("RESULT_NOTE").equals("null") ? " " : test_obj.getString("RESULT_NOTE")

                                                            );
                                                            mLabCategoryDataModel.getmListTest().add(mLabtestcultnotemodel);

                                                        } else {

                                                            Log.e("MYERRROR", "mLabtestculturemodel" + y);
                                                            mLabtestculturemodel = new Labtestculturemodel(
                                                                    test_obj.getString("CATEGORY_NAME_AR"),
                                                                    test_obj.getString("STATE_CULTURE_NAME"),
                                                                    test_obj.getString("ORGANISM_NAME_A"),
                                                                    test_obj.getString("ORGANISM_COUNT_A"),
                                                                    test_obj.getString("ORGANISM_NAME_B"),
                                                                    test_obj.getString("ORGANISM_COUNT_B"),
                                                                    test_obj.getString("ORGANISM_NAME_C"),
                                                                    test_obj.getString("ORGANISM_COUNT_C"),
                                                                    test_obj.getString("STATE_CULTURE_CODE")

                                                            );
                                                            mLabCategoryDataModel.getmListTest().add(mLabtestculturemodel);
                                                            JSONObject cult_anti = test_arr.getJSONObject(y);
                                                            JSONArray cultanti_arr = (JSONArray) cult_anti.getJSONArray("CULT");
                                                            if (cultanti_arr.length() > 0) {
                                                                Log.e("cult_anti:", cultanti_arr.toString());

                                                                for (int H = 0; H < cultanti_arr.length(); H++) {
                                                                    JSONObject cultanti_obj = cultanti_arr.getJSONObject(H);
                                                                    Log.e("MYanti", "mLabtestcultureantimodel" + H);
                                                                    mLabtestcultureantimodel = new Labtestcultureantimodel(
                                                                            cultanti_obj.getString("TEST_NAME_EN"),
                                                                            cultanti_obj.getString("CULTURE_A_ABBR").equals("null") ? " " : cultanti_obj.getString("CULTURE_A_ABBR"),
                                                                            cultanti_obj.getString("CULTURE_B_ABBR").equals("null") ? " " : cultanti_obj.getString("CULTURE_B_ABBR"),
                                                                            cultanti_obj.getString("CULTURE_C_ABBR").equals("null") ? " " : cultanti_obj.getString("CULTURE_C_ABBR")
                                                                    );
                                                                    mLabtestculturemodel.getMantiList().add(mLabtestcultureantimodel);
                                                                }
                                                                Log.e("cult_antifor:", cultanti_arr.toString());

                                                            }
                                                        }

                                                    }

                                                }
                                            }
                                        }
                                        notifyDataSetChanged();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Log.e("json", "ERROR" + e.getMessage());
                                    Toast.makeText(holder.itemView.getContext(), "No orders", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(final VolleyError volleyError) {
                                android.os.Handler mHandler = new android.os.Handler();
                                if (volleyError instanceof NetworkError) {
                                } else if (volleyError instanceof ServerError) {
                                } else if (volleyError instanceof AuthFailureError) {
                                } else if (volleyError instanceof ParseError) {
                                } else if (volleyError instanceof NoConnectionError) {
                                } else if (volleyError instanceof TimeoutError) {
                                    Log.e("error", volleyError.getClass().getName());

                                }
                            }
                        });

                        Controller.getInstance().addToRequestQueue(jsObjRequest);

                    }
                } else {
                    card_container.setVisibility(View.GONE);
                }
            }
        });
        labcat = new LabCategoryAdapter(Carddata.getmListCat(), context);
        mLinearLayoutManager = new LinearLayoutManager(holder.itemView.getContext());
        holder.catRecyclerview.setLayoutManager(mLinearLayoutManager);
        holder.catRecyclerview.setAdapter(labcat);

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtorderdate, txtorderid, txtlaborderyear, txthosno, txtpatriceID;
        RecyclerView catRecyclerview;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.txtorderdate = (TextView) itemView.findViewById(R.id.txtorderdate);
            this.txtorderid = (TextView) itemView.findViewById(R.id.txtorderid);
            this.txtlaborderyear = (TextView) itemView.findViewById(R.id.txtlaborderyear);
            this.txtpatriceID = (TextView) itemView.findViewById(R.id.txtpatriceID);
            this.txthosno = (TextView) itemView.findViewById(R.id.txthosno);
            catRecyclerview = (RecyclerView) itemView.findViewById(R.id.innerCatRecyclerView);
            //RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(null);
            //catRecyclerview.setLayoutManager(mLayoutManager);

        }
    }

}