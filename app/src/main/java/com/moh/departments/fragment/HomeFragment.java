package com.moh.departments.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.moh.departments.R;
import com.moh.departments.constants.Controller;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    TextView txth_username, txth_ID, texth_email, txtdept;
    ImageView personalimg;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txth_username = view.findViewById(R.id.txth_username);
        txth_ID = view.findViewById(R.id.txth_ID);
        txtdept = view.findViewById(R.id.txtdept);
        personalimg = (ImageView) view.findViewById(R.id.personalimg);
        texth_email = view.findViewById(R.id.texth_email);
        txth_username.setText(Controller.pref.getString("USER_NAME", ""));
        txth_ID.setText(Controller.pref.getString("USER_ID", ""));
        txtdept.setText(Controller.pref.getString("DEPT_NAME_AR", ""));
        texth_email.setText(Controller.pref.getString("USER_EMAIL", ""));
        Log.e("img_url", Controller.PERSONAL_IMG + Controller.pref.getString("USER_ID", ""));
        Picasso.with(getContext())
                .load(Controller.PERSONAL_IMG + Controller.pref.getString("USER_ID", ""))
                .into(personalimg);
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
//    @Override
//    public void onBackPressed() {
//        new AlertDialog.Builder(getContext())
//                .setTitle("Really Exit?")
//                .setMessage("Are you sure you want to exit?")
//                .setNegativeButton(android.R.string.no, null)
//                .setPositiveButton(android.R.string.yes, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent=new Intent(getContext(),LoginActivity.class);
//                        startActivity(intent);
//                        LoginActivity.super.onBackPressed();
//
//                    }
//
//                    public void onClick(DialogInterface arg0, int arg1) {
//                        LoginActivity.super.onBackPressed();
//                    }
//                }).create().show();
//    }

//    Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
//
//      if (currentFragment instanceof ClothOptionFragment) {
//
//    }

}
