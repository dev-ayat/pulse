package com.moh.departments.activiteis;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.moh.departments.R;
import com.moh.departments.adapters.ExpandableListAdapter;
import com.moh.departments.constants.Controller;
import com.moh.departments.dialog.DialogLoding;
import com.moh.departments.dialog.DialogMsg;
import com.moh.departments.fragment.DepartmentsFragment;
import com.moh.departments.fragment.HomeFragment;
import com.moh.departments.fragment.PatientFragment;
import com.moh.departments.fragment.PieChartFragment;
import com.moh.departments.models.MenuModel;
import com.moh.departments.models.PrivMenu;
import com.moh.departments.models.Screen;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView txth_username, txtdept;
    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    List<MenuModel> headerList = new ArrayList<>();
    HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Controller.LOADER_DIALOG = new DialogLoding(this);
        Controller.Msg_DIALOG = new DialogMsg(this);

        expandableListView = findViewById(R.id.expandableListView);
        prepareMenuData();
        populateExpandableList();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        txth_username = headerView.findViewById(R.id.txth_username);
        txtdept = headerView.findViewById(R.id.txtdept);
        txth_username.setText(Controller.pref.getString("USER_NAME", ""));
        txtdept.setText(Controller.pref.getString("DEPT_NAME_AR", ""));
        ImageView pernavimg = (ImageView) headerView.findViewById(R.id.pernavimg);
        Picasso.with(this)
                .load(Controller.PERSONAL_IMG + Controller.pref.getString("USER_ID", ""))
                .into(pernavimg);
        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, homeFragment);
        HomeActivity homeActivity = new HomeActivity();
        ft.commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                finish();
            } else
                super.onBackPressed();
        }


    }

    public void close_app() {
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setMessage("هل تريد تسجيل الخروج ؟");
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "لا", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                alertDialog.dismiss();
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "نعم", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                Controller.editor.putString("LOGIN_MODE", "0");
                Controller.editor.commit();

            }
        });
        alertDialog.show();
    }

    public void open_icd10() {
        startActivity(new Intent(this, icd10Activity.class));
    }

    public void show_onboarding() {
        startActivity(new Intent(this, boardingActivity.class));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_dept);
        item.setVisible(false);
        super.onPrepareOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_dept) {
            DepartmentsFragment departmentsFragment = new DepartmentsFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            departmentsFragment.show(ft, "tag");
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //make this method blank
        return true;
    }


    private void prepareMenuData() {

        MenuModel menuModel;
//        menuModel = new MenuModel("الرئيسية", true, false, 0); //PrivMenu of Android Tutorial. No sub menus
//        headerList.add(menuModel);
        for (int i = 0; i < Controller.PrivMENUS.size(); i++) {

            String name = Controller.PrivMENUS.get(i).getName();
            int menuid = Controller.PrivMENUS.get(i).getId();
            menuModel = new MenuModel(name, true, true, menuid); //PrivMenu of Java Tutorials
            headerList.add(menuModel);

            if (!menuModel.hasChildren) {
                childList.put(menuModel, null);
            }

            List<MenuModel> childModelsList = new ArrayList<>();
            for (int j = 0; j < Controller.PrivMENUS.get(i).getScreens().size(); j++) {

                String screen = Controller.PrivMENUS.get(i).getScreens().get(j).getName();
                int screenid = Controller.PrivMENUS.get(i).getScreens().get(j).getId();

                MenuModel childModel = new MenuModel(screen, false, false, screenid);
                childModelsList.add(childModel);
            }

            if (menuModel.hasChildren) {
                Log.d("API123", "here");
                childList.put(menuModel, childModelsList);
            }

        }
        menuModel = new MenuModel("الاستعلام عن icd10", true, false, 1); //PrivMenu of Android Tutorial. No sub menus
        headerList.add(menuModel);
        menuModel = new MenuModel("حول التطبيق", true, false, 2); //PrivMenu of Android Tutorial. No sub menus
        headerList.add(menuModel);
        menuModel = new MenuModel("تسجيل الخروج", true, false, 3); //PrivMenu of Android Tutorial. No sub menus
        headerList.add(menuModel);


    }

    private void populateExpandableList() {

        expandableListAdapter = new ExpandableListAdapter(this, headerList, childList);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {


                if (headerList.get(groupPosition).isGroup) {
                    if (!headerList.get(groupPosition).hasChildren) {
                        FrameLayout fragment = findViewById(R.id.content_frame);
                        int menuid = headerList.get(groupPosition).getMenuid();

                        Fragment myfragment = null;
                        Fragment homefragment = new HomeFragment();
                        switch (menuid) {
                            case 1:
                                open_icd10();
                                break;
                            case 2:
                                show_onboarding();
                                break;
                            case 3:
                                close_app();
                                break;
                        }

                        if (myfragment != null) {
                            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                            ft.replace(R.id.content_frame, homefragment);
                            ft.addToBackStack(null);
                            ft.commit();
                        }
                        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                        drawer.closeDrawer(GravityCompat.START);
                        onBackPressed();
                    }
                }

                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                if (childList.get(headerList.get(groupPosition)) != null) {

                    PrivMenu menu = Controller.PrivMENUS.get(groupPosition);
                    Screen screen = menu.getScreens().get(childPosition);
                    // Toast.makeText(HomeActivity.this, "screen id:"+screen.getId(), Toast.LENGTH_SHORT).show();
                    // Log.e( "onChildClick: ","screen id"+screen.getId() );
                    FrameLayout fragment = findViewById(R.id.content_frame);
                    int screenid = screen.getId();
                    Fragment myfragment = null;
                    switch (screenid) {

                        case 537:

                            ///spc_dept
                            ///end spc dept

                            myfragment = new PatientFragment();
                            break;
                        case 765:

                            myfragment = new HomeFragment();

                            break;
                        case 770:

                            myfragment = new PieChartFragment();
                            break;
                    }

                    if (myfragment != null) {
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_frame, myfragment);
                        ft.addToBackStack(null);
                        ft.commit();
                    }
                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);
                    onBackPressed();
                }
                return false;
            }
        });
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    protected void onPause() {

        super.onPause();
    }
}
