package com.moh.departments.fragment;

import android.content.Context;
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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.moh.departments.R;
import com.moh.departments.activiteis.HomeActivity;
import com.moh.departments.adapters.EfileAdapter;
import com.moh.departments.constants.Controller;
import com.moh.departments.constants.CustomRequest;
import com.moh.departments.models.EfileModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Efile_Fragment extends Fragment {
    private static RecyclerView.Adapter efileAdapter;
    private static RecyclerView recyclerView;
    private static ArrayList<EfileModel> Carddata;
    TextView txtscnane, txtpatid, txtpatName;
    String patname, patid, indate, patmrpid;
    ImageView card_arrow;
    Context con;
    private RecyclerView.LayoutManager layoutManager;

    public Efile_Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_efile, container, false);
        patname = getArguments().getString("patname");
        patid = getArguments().getString("patid");
        indate = getArguments().getString("indate");
        patmrpid = getArguments().getString("patmrp");
        Log.d("patid", patid);
        Log.d("patname", patname);
        txtpatName = view.findViewById(R.id.txtpatName);
        txtpatid = view.findViewById(R.id.txtpatid);
        txtpatName.setText(patname);
        txtpatid.setText(patid);
        recyclerView = (RecyclerView) view.findViewById(R.id.efile_recycler_view);
        Carddata = new ArrayList<>();
        efileAdapter = new EfileAdapter(Carddata, getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(efileAdapter);
        preparedeptData();
        setHasOptionsMenu(true);/// to disable icon from menu
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtscnane = view.findViewById(R.id.txtscnane);
        recyclerView = (RecyclerView) view.findViewById(R.id.efile_recycler_view);
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Webview_Fragment webview_Fragment = new Webview_Fragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, webview_Fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });


    }

    private void preparedeptData() {

        Map<String, String> map = new HashMap<>();
        map.put("MRP_ID", patmrpid);//"351989"
        Log.e("test", map.toString());
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.Efile_VISIT_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {


                try {
                    Log.e("jsonfile:", jsonObject.toString());
                    JSONArray documents_arr = (JSONArray) jsonObject.getJSONArray("VISITS");
                    Log.e("json", jsonObject.toString());


                    Log.e("json", "" + documents_arr.length());
                    if (documents_arr.length() > 0) {
                        Log.e("EFILE", "" + documents_arr.getJSONObject(0).getString("VISTE_DATE"));

                        for (int i = 0; i < documents_arr.length(); i++) {
                            JSONObject obj = documents_arr.getJSONObject(i);

                            if (obj != null) {

                                if (obj.getString("VISTE_DATE") != null) {

                                    EfileModel Card = new EfileModel(obj.getString("VISTE_DATE"),
                                            obj.getString("HOSSID"),
                                            obj.getString("IDNUMBER"));
                                    Carddata.add(Card);
                                }
                            }
                        }
                    } else {
                        LinearLayout emptyEfile = (LinearLayout) getView().findViewById(R.id.emptyEfile_layout);
                        emptyEfile.setVisibility(View.VISIBLE);
                    }
                    Log.e("json", "" + Carddata.size());
                    recyclerView.setAdapter(efileAdapter);
                    efileAdapter.notifyDataSetChanged();

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
                .setActionBarTitle("الملف الإلكتروني");
    }


}
