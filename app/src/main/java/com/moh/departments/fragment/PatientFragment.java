package com.moh.departments.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.moh.departments.R;
import com.moh.departments.activiteis.HomeActivity;
import com.moh.departments.adapters.CardviewAdapter;
import com.moh.departments.constants.Controller;
import com.moh.departments.constants.CustomRequest;
import com.moh.departments.constants.RecyclerTouchListener;
import com.moh.departments.models.CardviewDataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//import android.support.v7.widget.SearchView.OnQueryTextListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class PatientFragment extends Fragment {//implements SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener{

    static View.OnClickListener myOnClickListener;
    private static CardviewAdapter cardviewadapter;
    private static RecyclerView recyclerView;
    private static ArrayList<CardviewDataModel> Carddata;
    private static ArrayList<Integer> removedItems;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerTouchListener touchListener;


    public PatientFragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_patient, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.patient_recycler_view);
        SearchView emptyTextView = view.findViewById(android.R.id.empty);
        Carddata = new ArrayList<>();
        cardviewadapter = new CardviewAdapter(Carddata);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        // for swipe
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cardviewadapter);
        preparedeptData();
        Touchlistner();


        emptyTextView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s == null || s.trim().isEmpty()) {
                    resetSearch();
                    return false;
                }

                ArrayList<CardviewDataModel> filteredValues = new ArrayList<CardviewDataModel>(Carddata);
                for (CardviewDataModel value : Carddata) {
                    if (!value.getPatname().toLowerCase().contains(s.toLowerCase())) {
                        filteredValues.remove(value);
                    }
                }

                cardviewadapter = new CardviewAdapter(filteredValues);
                recyclerView.setAdapter(cardviewadapter);
                cardviewadapter.notifyDataSetChanged();

                return false;
            }
        });
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
        item.setVisible(true);
        super.onPrepareOptionsMenu(menu);

    }

    private void preparedeptData() {
        String DEPT_CODE = Controller.pref.getString("DEPT_CD_SELEC", "");
        Log.e("test", DEPT_CODE);
        Map<String, String> map = new HashMap<>();
        map.put("DEPT_CODE", DEPT_CODE);
        Log.e("test", map.toString());
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.DEPT_PAT_URL, map, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    Log.e("json:", jsonObject.toString());
                    JSONArray patient_arr = (JSONArray) jsonObject.getJSONArray("PATIENTS");
                    Log.e("json", jsonObject.toString());
                    Log.e("json", "" + patient_arr.length());
                    if (patient_arr.length() > 0) {
                        Log.e("jsonmrp", "" + patient_arr.getJSONObject(0).getString("MRP_ID"));

                        for (int i = 0; i < patient_arr.length(); i++) {
                            JSONObject obj = patient_arr.getJSONObject(i);

                            if (obj != null) {

                                if (obj.getString("INPATIENT_CODE") != null) {

                                    CardviewDataModel Card = new CardviewDataModel(obj.getString("FULL_NAME_AR")
                                            , obj.getString("ADMISSION_ENTER_DOC_NAME")
                                            , obj.getString("ADMISSION_ROOM_OUT_NAME").equals("null") ? " " : obj.getString("ADMISSION_ROOM_OUT_NAME")
                                            , obj.getString("ADMISSION_BED_OUT_NAME").equals("null") ? " " : obj.getString("ADMISSION_BED_OUT_NAME")
                                            , obj.getString("MRP_ID")
                                            , Integer.parseInt(obj.getString("MRP_PATREC_CODE"))
                                            , Integer.parseInt(obj.getString("INPATIENT_ADMISSION_CD"))
                                            , obj.getString("ADMISSION_ENTER_DATE"));
                                    Carddata.add(Card);


                                }
                            }
                        }
                    }
                    Log.e("json", "" + Carddata.size());
                    recyclerView.setAdapter(cardviewadapter);
                    cardviewadapter.notifyDataSetChanged();

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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //  inflater.inflate(R.menu.search_menu, menu);
        //MenuItem searchItem = menu.findItem(R.id.action_search);
//        SearchView searchView = (SearchView) searchItem.getActionView();
//        searchView.setOnQueryTextListener(this);
        //  searchView.setQueryHint("Search");

        super.onCreateOptionsMenu(menu, inflater);

        //  super.onCreateOptionsMenu(menu, inflater);
    }

    public void resetSearch() {
        cardviewadapter = new CardviewAdapter(Carddata);

        recyclerView.setAdapter(cardviewadapter);
    }

    public void Touchlistner() {
        touchListener = new RecyclerTouchListener((Activity) getContext(), recyclerView);
        touchListener
                .setClickable(new RecyclerTouchListener.OnRowClickListener() {
                    @Override
                    public void onRowClicked(int position) {
                        Toast.makeText(getContext(), " Not Available", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onIndependentViewClicked(int independentViewID, int position) {
                    }
                })
                .setSwipeOptionViews(R.id.delete_task, R.id.edit_task, R.id.rad_task)
                .setSwipeable(R.id.rowFG, R.id.rowBG, new RecyclerTouchListener.OnSwipeOptionsClickListener() {
                    @Override
                    public void onSwipeOptionClicked(int viewID, int position) {

                        String patname = cardviewadapter.getDataSet().get(position).getPatname().toString();
                        String patmrpid = cardviewadapter.getDataSet().get(position).getPtmrpid().toString();
                        String indate = Carddata.get(position).getIndate().toString();
                        int patidint = cardviewadapter.getDataSet().get(position).getPatid();
                        int patadmcd = cardviewadapter.getDataSet().get(position).getAdmcd();

                        String patid = String.valueOf(patidint);
                        String patmrp = String.valueOf(patmrpid);
                        String patadm = String.valueOf(patadmcd);
                        Bundle args = new Bundle();
                        args.putString("patname", patname);
                        args.putString("patid", patid);
                        args.putString("patmrp", patmrp);
                        args.putString("indate", indate);
                        args.putString("patadm", patadm);
                        switch (viewID) {
                            case R.id.delete_task:
                                //  Toast.makeText(getContext(),Carddata.get(position).getPatname(),Toast.LENGTH_SHORT).show();
                                LabFragment labFragment = new LabFragment();
                                FragmentManager fm = getFragmentManager();
                                FragmentTransaction ft = getFragmentManager().beginTransaction();
                                labFragment.setArguments(args);
                                Log.d("ayat", patid);
                                Log.d("ayat2", patname);

                                ft.replace(R.id.content_frame, labFragment);
                                ft.addToBackStack(null);
                                ft.commit();
                                break;
                            case R.id.edit_task:
                                //Toast.makeText(getContext(),"Rad Not Available", Toast.LENGTH_SHORT).show();
                                Efile_Fragment efile_Fragment = new Efile_Fragment();
                                FragmentManager efilefm = getFragmentManager();
                                FragmentTransaction efileft = getFragmentManager().beginTransaction();
                                efile_Fragment.setArguments(args);
                                Log.d("ayat", patid);
                                Log.d("ayat2", patname);
                                efileft.replace(R.id.content_frame, efile_Fragment);
                                efileft.addToBackStack(null);
                                efileft.commit();
                                break;
                            case R.id.rad_task:
                                Toast.makeText(getContext(), "pharm Not Available", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.radiology_task:
                                Toast.makeText(getContext(), "rad Not Available", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerView.addOnItemTouchListener(touchListener);
        // Set title bar
        ((HomeActivity) getActivity())
                .setActionBarTitle("الأقسام الداخلية");
    }


}
