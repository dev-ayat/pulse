package com.moh.departments.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.moh.departments.R;
import com.moh.departments.activiteis.HomeActivity;

public class LabFragment extends Fragment {

    TextView lblscname, lbordername;
    CardView labrescard, labordercard;
    String patname, patid, indate, patadmcd;

    public LabFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lab, container, false);
        patname = getArguments().getString("patname");
        patid = getArguments().getString("patid");
        indate = getArguments().getString("indate");
        patadmcd = getArguments().getString("patadm");
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // lblscname = view.findViewById(R.id.lblscname);
        lbordername = view.findViewById(R.id.lbordername);
        labrescard = view.findViewById(R.id.labrescard);
        labordercard = view.findViewById(R.id.labordercard);
        labrescard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Lbres_Fragment labres_Fragment = new Lbres_Fragment();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Bundle args = new Bundle();
                args.putString("patname", patname);
                args.putString("patid", patid);
                args.putString("indate", indate);
                args.putString("patadmcd", patadmcd);
                labres_Fragment.setArguments(args);
                ft.replace(R.id.content_frame, labres_Fragment);
                ft.addToBackStack(null);
                ft.commit();

            }
        });


        labordercard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                laborderFragment laborder = new laborderFragment();
                Bundle args = new Bundle();
                args.putString("patname", patname);
                args.putString("patid", patid);
                args.putString("indate", indate);
                args.putString("patadmcd", patadmcd);
                laborder.setArguments(args);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_frame, laborder);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

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
        ((HomeActivity) getActivity()).setActionBarTitle("المختبر");
    }
}
