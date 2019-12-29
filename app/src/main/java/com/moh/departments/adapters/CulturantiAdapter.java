package com.moh.departments.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.moh.departments.R;
import com.moh.departments.models.Labtestcultureantimodel;

import java.util.ArrayList;

public class CulturantiAdapter extends RecyclerView.Adapter<CulturantiAdapter.MyViewHolder> {
    private ArrayList<Object> dataSet;

    public CulturantiAdapter(ArrayList<Object> dataSet) {
        this.dataSet = dataSet;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.culture_d_anti, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        Labtestcultureantimodel Carddata = (Labtestcultureantimodel) dataSet.get(listPosition);
        holder.txt_antibiotic.setText(Carddata.getAntibiotic());
        holder.txt_antiA.setText(Carddata.getAntiA());
        holder.txt_antiB.setText(Carddata.getAntiB());
        holder.txt_antiC.setText(Carddata.getAntiC());
        Log.e("TEST", listPosition + " " + Carddata.getAntiA());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt_antibiotic, txt_antiA, txt_antiB, txt_antiC;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.txt_antibiotic = (TextView) itemView.findViewById(R.id.txt_antibiotic);
            this.txt_antiA = (TextView) itemView.findViewById(R.id.txt_antiA);
            this.txt_antiB = (TextView) itemView.findViewById(R.id.txt_antiB);
            this.txt_antiC = (TextView) itemView.findViewById(R.id.txt_antiC);

        }
    }

}