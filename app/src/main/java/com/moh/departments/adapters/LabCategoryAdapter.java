package com.moh.departments.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moh.departments.R;
import com.moh.departments.models.LabCategoryDataModel;

import java.util.ArrayList;

public class LabCategoryAdapter extends RecyclerView.Adapter<LabCategoryAdapter.MyViewHolder> {
    LinearLayoutManager mLinearLayoutManager;
    private ArrayList<LabCategoryDataModel> dataSet;
    private Context context;

    public LabCategoryAdapter(ArrayList<LabCategoryDataModel> dataSet, Context context) {
        this.dataSet = dataSet;
        this.context = context;
        Log.e("CAT_TEST", "dataSet size" + dataSet.size());

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lab_res_cat_row, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        final LabCategoryDataModel Carddata = dataSet.get(listPosition);
        holder.txtcatname.setText(Carddata.getCATEGORY_NAME());
        holder.txtcatid.setText(String.valueOf(Carddata.getCATEGORY_ID()));
        holder.txtcatstatus.setText(Carddata.getCATSTATUS());
        holder.txtcatstatid.setText(String.valueOf(Carddata.getCATSTATUSID()));
        holder.txtgroupname.setText(Carddata.getCATEGORY_NAME());
        holder.txtgroupid.setText(Carddata.getCATGROUPID());
        if (Carddata.getCATSTATUSID() == 3) {
            LinearLayout catlayout = (LinearLayout) holder.itemView.findViewById(R.id.catlayout);
            catlayout.setBackgroundColor(Color.YELLOW);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LinearLayout testlayout = (LinearLayout) holder.itemView.findViewById(R.id.testlayout);
                    Integer visiblity = testlayout.getVisibility();
                    if (visiblity == 8) {
                        testlayout.setVisibility(View.VISIBLE);
                        Log.e("ayat1", " vis" + visiblity + " " + Carddata.getCATEGORY_NAME() + " " + Carddata.getCATEGORY_NAME() + " LIST_TEST_SIZE=" + Carddata.getmListTest().size());
                        //  Toast.makeText(holder.itemView.getContext(),"tag"+testlayout.getTag(), Toast.LENGTH_SHORT).show();
                        // Log.e("ayat", " pos" + listPosition + " " + Carddata.getCATEGORY_NAME() + " " + Carddata.getCATEGORY_NAME() + " LIST_TEST_SIZE=" + Carddata.getmListTest().size());
                        LabTestAdapter mLabTestAdapter = new LabTestAdapter(Carddata.getmListTest());
                        mLinearLayoutManager = new LinearLayoutManager(holder.itemView.getContext());
                        holder.innertestRecyclerView.setLayoutManager(mLinearLayoutManager);
                        holder.innertestRecyclerView.setAdapter(mLabTestAdapter);
                    } else {
                        testlayout.setVisibility(View.GONE);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtcatname, txtcatstatus, txtcatid, txtcatstatid, txtgroupname, txtgroupid;
        RecyclerView innertestRecyclerView;
        LinearLayout test_layout;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.txtcatname = itemView.findViewById(R.id.txtcatname);
            this.txtcatid = itemView.findViewById(R.id.txtcatid);
            this.txtcatstatus = itemView.findViewById(R.id.txtcatstatus);
            this.txtcatstatid = itemView.findViewById(R.id.txtcatstatid);
            this.txtgroupname = itemView.findViewById(R.id.txtgroupname);
            this.txtgroupid = itemView.findViewById(R.id.txtgroupid);
            this.innertestRecyclerView = itemView.findViewById(R.id.innertestRecyclerView);

        }
    }

}