package com.moh.departments.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.moh.departments.R;
import com.moh.departments.activiteis.HomeActivity;
import com.moh.departments.adapters.RadPhotoAdapter;
import com.moh.departments.constants.Controller;
import com.moh.departments.constants.CustomRequest;
import com.moh.departments.models.RadphotoModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class radresFragment extends Fragment {

    private static RecyclerView.Adapter radPhotoAdapter;
    private static RecyclerView radrecyclerView;
    private static ArrayList<RadphotoModel> Carddata;
    TextView txtscnane, txtpatid, txtpatName;
    String patname, patid, indate;
    LinearLayout radheader;
    private RecyclerView.LayoutManager layoutManager;

    public radresFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_radres, container, false);
        patname = getArguments().getString("patname");
        patid = getArguments().getString("patid");
        indate = getArguments().getString("indate");
        radrecyclerView = (RecyclerView) view.findViewById(R.id.radorder_recycler_view);
        radheader = view.findViewById(R.id.radheader);
        txtpatName = view.findViewById(R.id.txtpatName);
        txtpatid = view.findViewById(R.id.txtpatid);
        txtpatName.setText(patname);
        txtpatid.setText(patid);
        Carddata = new ArrayList<>();
        radPhotoAdapter = new RadPhotoAdapter(Carddata, getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        radrecyclerView.setLayoutManager(mLayoutManager);
        radrecyclerView.setAdapter(radPhotoAdapter);
        prepareRadPhotoData();
        setHasOptionsMenu(true);/// to disable icon from menu
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_dept);
        item.setVisible(false);
        super.onPrepareOptionsMenu(menu);

    }

    private void prepareRadPhotoData() {

        Map<String, String> map = new HashMap<>();
        map.put("PATREC_CODE", patid);
        Log.e("PATREC_CODE", patid);
        // map.put("PATREC_CODE", "298933");
        map.put("P_FROM_DATE", indate);
        //  map.put("P_FROM_DATE", "04/04/2012");
        Log.e("test", map.toString());
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.GET_GET_PHOTO_RAD_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    Log.e("jsonrad:", jsonObject.toString());
                    JSONArray RAD_ARR = (JSONArray) jsonObject.getJSONArray("RAD_REPORT");
                    Log.e("json", jsonObject.toString());


                    Log.e("json", "" + RAD_ARR.length());
                    if (RAD_ARR.length() > 0) {

                        for (int i = 0; i < RAD_ARR.length(); i++) {
                            JSONObject obj = RAD_ARR.getJSONObject(i);
                            if (obj != null) {
                                if (obj.getString("ORDER_CODE") != null) {
                                    RadphotoModel Card = new RadphotoModel(
                                            obj.getString("ORDER_DATE"),
                                            obj.getString("SERVICE_NAME_AR"),
                                            obj.getString("ORGAN_SERVICE_CD"),
                                            obj.getString("ORGAN_NAME_AR"),
                                            obj.getString("ORGAN_CODE"),
                                            obj.getString("MRP_ID")
                                    );
                                    Carddata.add(Card);
                                }
                            }
                        }
                    } else {
                        LinearLayout emptyEfile = (LinearLayout) getView().findViewById(R.id.emptyEfile_layout);
                        radheader.setVisibility(View.GONE);
                        emptyEfile.setVisibility(View.VISIBLE);
                    }
                    Log.e("json", "" + Carddata.size());
                    radrecyclerView.setAdapter(radPhotoAdapter);
                    radPhotoAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("json", "ERROR");
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


    public void onResume() {
        super.onResume();
        // Set title bar
        ((HomeActivity) getActivity()).setActionBarTitle("نتائج الأشعة");
    }

}
