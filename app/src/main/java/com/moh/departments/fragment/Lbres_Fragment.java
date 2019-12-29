package com.moh.departments.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.moh.departments.R;
import com.moh.departments.activiteis.HomeActivity;
import com.moh.departments.adapters.LaborderCardviewAdapter;
import com.moh.departments.constants.Controller;
import com.moh.departments.constants.CustomRequest;
import com.moh.departments.models.LaborderCardviewDataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Lbres_Fragment extends Fragment {
    private static RecyclerView.Adapter laborderCardviewAdapter;
    private static RecyclerView recyclerView;
    private static ArrayList<LaborderCardviewDataModel> Carddata;
    TextView txtscnane, txtpatid, txtpatName;
    String patname, patid, indate;
    ImageView card_arrow;
    private RecyclerView.LayoutManager layoutManager;

    public Lbres_Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_labres, container, false);
        patname = getArguments().getString("patname");
        patid = getArguments().getString("patid");
        indate = getArguments().getString("indate");
        Log.d("patid", patid);
        Log.d("patname", patname);

        recyclerView = (RecyclerView) view.findViewById(R.id.laborder_recycler_view);
        txtpatName = view.findViewById(R.id.txtpatName);
        txtpatid = view.findViewById(R.id.txtpatid);
        txtpatName.setText(patname);
        txtpatid.setText(patid);
        Carddata = new ArrayList<>();
        laborderCardviewAdapter = new LaborderCardviewAdapter(Carddata);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(laborderCardviewAdapter);
        preparedeptData();
        card_arrow = (ImageView) view.findViewById(R.id.card_arrow);
        setHasOptionsMenu(true);/// to disable icon from menu
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtscnane = view.findViewById(R.id.txtscnane);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.addToBackStack(null);

    }

    private void preparedeptData() {

        Map<String, String> map = new HashMap<>();
        map.put("patid", patid);
        map.put("indate", indate);
        Log.e("test", map.toString());
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.LAB_ORDERS_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {


                try {
                    Log.e("json:", jsonObject.toString());
                    JSONArray orders_arr = (JSONArray) jsonObject.getJSONArray("LAB_ORDERS");
                    Log.e("json", jsonObject.toString());


                    Log.e("json", "" + orders_arr.length());
                    if (orders_arr.length() > 0) {
                        Log.e("json", "" + orders_arr.getJSONObject(0).getString("ORDER_CD") + " " + orders_arr.getJSONObject(0).getInt(("ORDER_PATREC_CODE")));

                        for (int i = 0; i < orders_arr.length(); i++) {
                            JSONObject obj = orders_arr.getJSONObject(i);

                            if (obj != null) {

                                if (obj.getString("ORDER_CD") != null) {

                                    LaborderCardviewDataModel Card = new LaborderCardviewDataModel((obj.getString("ORDER_CREATED_ON")), Integer.parseInt(obj.getString("ORDER_CD")),
                                            Integer.parseInt(obj.getString("ORDER_YEAR")), Integer.parseInt(obj.getString("HOS_NO")), Integer.parseInt(obj.getString("ORDER_PATREC_CODE")));
                                    Carddata.add(Card);


                                }
                            }
                        }
                    } else {
                        // final View view = null;
                        LinearLayout emptyreslab = (LinearLayout) getView().findViewById(R.id.emptyreslab_layout);
                        emptyreslab.setVisibility(View.VISIBLE);

                    }
                    Log.e("json", "" + Carddata.size());
                    recyclerView.setAdapter(laborderCardviewAdapter);
                    laborderCardviewAdapter.notifyDataSetChanged();

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

    public void onResume() {
        super.onResume();
        // Set title bar
        ((HomeActivity) getActivity())
                .setActionBarTitle("نتائج الفحوصات");
    }
}
