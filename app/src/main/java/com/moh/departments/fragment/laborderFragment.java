package com.moh.departments.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.material.tabs.TabLayout;
import com.moh.departments.R;
import com.moh.departments.activiteis.HomeActivity;
import com.moh.departments.adapters.LaborderAdapter;
import com.moh.departments.adapters.LaborderfavtestAdapter;
import com.moh.departments.adapters.LabordertestAdapter;
import com.moh.departments.adapters.TabPagerAdapter;
import com.moh.departments.constants.Controller;
import com.moh.departments.constants.CustomRequest;
import com.moh.departments.models.LaborderModel;
import com.moh.departments.models.Labordertestmodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class laborderFragment extends Fragment implements LaborderfavtestAdapter.MyListenerFav
        , LabordertestAdapter.MyListenerCat {

    private static ArrayList<LaborderModel> Carddata;
    private static ArrayList<Labordertestmodel> Favlist;
    LaborderAdapter laborderAdapter;
    LaborderfavtestAdapter laborderfavtestAdapter;
    String patname, patid, indate, patadmcd;
    String userid;
    TextView txtpatid, txtpatName;
    ImageView card_arrow;
    RecyclerView recyclerView, recyclerViewfavlist;
    Button btn_addlaborder;

    ArrayList<Labordertestmodel> MyOutput;
    ArrayList<Labordertestmodel> MyFavList;
    LaborderModel mlaborderModel;
    TabLayout tabLayout;
    ViewPager viewPager;
    TextView badge;

    public laborderFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
//savedInstanceState.getSerializable("object");
        //MyOutput = (ArrayList<Labordertestmodel>)savedInstanceState.getSerializable("object");
        //Log.e("bundle1",""+MyOutput.size());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_laborder, container, false);
        patname = getArguments().getString("patname");
        patid = getArguments().getString("patid");
        indate = getArguments().getString("indate");
        patadmcd = getArguments().getString("patadmcd");
        badge = view.findViewById(R.id.badge);
        userid = Controller.pref.getString("USER_ID", "");
        txtpatName = view.findViewById(R.id.txtpatName);
        txtpatid = view.findViewById(R.id.txtpatid);
        card_arrow = (ImageView) view.findViewById(R.id.card_arrow);
        txtpatName.setText(patname);
        txtpatid.setText(patid);
        setHasOptionsMenu(true);/// to disable icon from menu
        MyOutput = new ArrayList<>();
        btn_addlaborder = view.findViewById(R.id.btn_add_laborder);
        btn_addlaborder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addlaborder();
            }
        });


        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        viewPager = (ViewPager) view.findViewById(R.id.pager);
        configureTabLayout();
        setHasOptionsMenu(true);/// to disable icon from menu

        LaborderfavtestAdapter.setmMyListenerFav(this);
        LabordertestAdapter.setmMyListenerCat(this);
        return view;
    }


    private void configureTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText("المجموعات الرئيسية"));
        tabLayout.addTab(tabLayout.newTab().setText("المفضلة"));
        final PagerAdapter adapter = new TabPagerAdapter(getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (!(adapter == null)) {
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });

    }


    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_dept);
        item.setVisible(false);
        super.onPrepareOptionsMenu(menu);

    }


    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).setActionBarTitle("طلب فحوصات مخبرية");
    }


    public void onClickFav(Labordertestmodel mLabordertestmodel) {
        Log.e("MYERROR", "Fav test name=" + mLabordertestmodel.getTEST_NAME());
        int pos = searchMyoutput(mLabordertestmodel.getTEST_CD());
        if (pos == -1)
            MyOutput.add(mLabordertestmodel);
        else
            MyOutput.remove(pos);

        String badgesize = String.valueOf(MyOutput.size());
        badge.setText(" +" + badgesize + " ");
    }

    @Override
    public void onClickCat(Labordertestmodel mLabordertestmodel) {

        Log.e("MYERROR", "Cat test name=" + mLabordertestmodel.getTEST_NAME());
        int pos = searchMyoutput(mLabordertestmodel.getTEST_CD());
        if (pos == -1)
            MyOutput.add(mLabordertestmodel);
        else
            MyOutput.remove(pos);
        String badgesize = String.valueOf(MyOutput.size());
        badge.setText(" +" + badgesize + " ");
    }

    public int searchMyoutput(String TestCD) {
        int pos = -1;
        for (int i = 0; i < MyOutput.size(); i++)
            if (MyOutput.get(i).getTEST_CD().equals(TestCD))
                pos = i;
        return pos;
    }

    public void addlaborder() {
        Log.e("ERRORsize", "MyOutput.size()=" + MyOutput.size());
        List<String> mListTest = new ArrayList<>();
        for (int i = 0; i < MyOutput.size(); i++) {
            Log.e("ERROR", "MyOutput.get(i).getTEST_NAME()=" + MyOutput.get(i).getTEST_NAME());
            mListTest.add(MyOutput.get(i).getTEST_NAME());
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setTitle("تأكيد إضافة الطلب للمختبر")
                .setIcon(R.drawable.testicon)
                .setItems(mListTest.toArray(new CharSequence[mListTest.size()]), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })

                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Controller.LOADER_DIALOG.showDialog("جاري إضافة الطلب للمختبر");
                        sendorderData();

                    }


                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Toast.makeText(getContext(),"Cancel",Toast.LENGTH_LONG).show();
                        dialog.dismiss();
//                        MyOutput=new ArrayList<>();
//                        badge.setText(" +0 ");
                    }
                });
        builder.create();
        builder.show();


    }

    private void sendorderData() {
        String Testcd = null;
        JSONArray CatArray = new JSONArray();

        for (int i = 0; i < MyOutput.size(); i++) {
            Testcd = MyOutput.get(i).getTEST_CD();
            JSONObject Testobj = new JSONObject();
            try {
                Testobj.put("Testcd", String.valueOf(Testcd));
                CatArray.put(Testobj);
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("ayat", e.getMessage());
            }

        }

        Map<String, String> map = new HashMap<>();
        map.put("ORDER_DOC_NOTES", "");
        map.put("DOC_ID", userid);
        map.put("ORDER_PATREC_CD", patid);
        map.put("ORDER_ADMISSION_CD", patadmcd);
        map.put("CAT_LIST", CatArray.toString());
        map.put("HOS_NO", "");
        Log.e("map", map.toString());
        Log.e("ayat", "map");
        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Controller.INSERT_LAB_ORDER_URL, map, new Response.Listener<JSONObject>() {
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
                                Controller.Msg_DIALOG.showDialog("تم إضافة الطلب بنجاح");
                            }
                        }, 2000);
                        Controller.Msg_DIALOG.hideDialog();
                    } else {
                        Handler aHandler = new Handler();
                        aHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Controller.LOADER_DIALOG.hideDialog();
                                Controller.Msg_DIALOG.showDialog("لم تتم إضافة الطلب");
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
        MyOutput = new ArrayList<>();
        badge.setText(" +0 ");

    }

}
