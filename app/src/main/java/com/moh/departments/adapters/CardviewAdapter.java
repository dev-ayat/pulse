package com.moh.departments.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.moh.departments.R;
import com.moh.departments.models.CardviewDataModel;

import java.util.ArrayList;

public class CardviewAdapter extends RecyclerView.Adapter<CardviewAdapter.MyViewHolder> {
    private ArrayList<CardviewDataModel> dataSet;

    public CardviewAdapter(ArrayList<CardviewDataModel> dataSet) {
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

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtpatName, txtpatid, txtdate, txtdoc, txtroom, txtbed, txtmrpid;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.txtpatName = (TextView) itemView.findViewById(R.id.txtpatName);
            this.txtpatid = (TextView) itemView.findViewById(R.id.txtpatid);
            this.txtdate = (TextView) itemView.findViewById(R.id.txtdate);
            this.txtdoc = (TextView) itemView.findViewById(R.id.txtdoc);
            this.txtroom = (TextView) itemView.findViewById(R.id.txtroom);
            this.txtbed = (TextView) itemView.findViewById(R.id.txtbed);
            this.txtmrpid = (TextView) itemView.findViewById(R.id.txtmrpid);
        }
    }

}