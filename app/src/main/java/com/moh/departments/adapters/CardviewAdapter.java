package com.moh.departments.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.moh.departments.R;
import com.moh.departments.fragment.Efile_Fragment;
import com.moh.departments.fragment.LabFragment;
import com.moh.departments.fragment.RadFragment;
import com.moh.departments.models.CardviewDataModel;

import java.util.ArrayList;

public class CardviewAdapter extends RecyclerView.Adapter<CardviewAdapter.MyViewHolder> {
    private ArrayList<CardviewDataModel> dataSet;
    private Context context;

    public CardviewAdapter(Context context, ArrayList<CardviewDataModel> dataSet) {
        this.dataSet = dataSet;

    }

    public ArrayList<CardviewDataModel> getDataSet() {
        return dataSet;
    }

    public void setDataSet(ArrayList<CardviewDataModel> dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_layout, parent, false);

        //  view.setOnClickListener(PatientFragment.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        CardviewDataModel Carddata = dataSet.get(listPosition);
        holder.txtpatName.setText(Carddata.getPatname());
        holder.txtpatid.setText(String.valueOf(Carddata.getPatid()));
        holder.txtdate.setText(Carddata.getIndate());
        holder.txtdoc.setText(Carddata.getDocname());
        holder.txtroom.setText(Carddata.getRoomname());
        holder.txtbed.setText(Carddata.getBedname());
        holder.txtmrpid.setText(Carddata.getPtmrpid());

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) holder.itemView.getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        ViewGroup.LayoutParams layoutParams = holder.rowFG.getLayoutParams();
        layoutParams.width = width;
        holder.rowFG.setLayoutParams(layoutParams);
        holder.hscroll.postDelayed(new Runnable() {
            public void run() {
                //holder.s.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
                int x, y;
                x = holder.rowFG.getLeft();
                y = holder.rowFG.getTop();
                holder.hscroll.scrollTo(x, y);
            }
        }, 1);

        final String patname = Carddata.getPatname();
        String indate = Carddata.getIndate();
        final String patid = String.valueOf(Carddata.getPatid());
        String patmrp = String.valueOf(Carddata.getPtmrpid());
        String patadm = String.valueOf(Carddata.getAdmcd());

        final Bundle args = new Bundle();
        args.putString("patname", patname);
        args.putString("patid", patid);
        args.putString("patmrp", patmrp);
        args.putString("indate", indate);
        args.putString("patadm", patadm);
        holder.img_lab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LabFragment labFragment = new LabFragment();
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
                labFragment.setArguments(args);
                ft.replace(R.id.content_frame, labFragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        holder.img_files.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Efile_Fragment efile_Fragment = new Efile_Fragment();
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
                efile_Fragment.setArguments(args);
                ft.replace(R.id.content_frame, efile_Fragment);
                ft.addToBackStack(null);
                ft.commit();

            }
        });

        holder.img_rad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadFragment radFragment = new RadFragment();
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
                radFragment.setArguments(args);
                ft.replace(R.id.content_frame, radFragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtpatName, txtpatid, txtdate, txtdoc, txtroom, txtbed, txtmrpid;
        LinearLayout rowFG;
        HorizontalScrollView hscroll;
        ImageView img_lab, img_files, img_rad, img_pharm;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.rowFG = itemView.findViewById(R.id.rowFG);
            this.txtpatName = itemView.findViewById(R.id.txtpatName);
            this.txtpatid = itemView.findViewById(R.id.txtpatid);
            this.txtdate = itemView.findViewById(R.id.txtdate);
            this.txtdoc = itemView.findViewById(R.id.txtdoc);
            this.txtroom = itemView.findViewById(R.id.txtroom);
            this.txtbed = itemView.findViewById(R.id.txtbed);
            this.txtmrpid = itemView.findViewById(R.id.txtmrpid);
            this.hscroll = itemView.findViewById(R.id.hscroll);
            this.img_lab = itemView.findViewById(R.id.img_lab);
            this.img_files = itemView.findViewById(R.id.img_files);
            this.img_rad = itemView.findViewById(R.id.img_rad);
            this.img_pharm = itemView.findViewById(R.id.img_pharm);

        }
    }

}