
package com.moh.departments.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moh.departments.R;
import com.moh.departments.models.LabTestDataModel;
import com.moh.departments.models.Labtestcultnotemodel;
import com.moh.departments.models.Labtestculturemodel;

import java.util.ArrayList;

public class LabTestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public final int VIEW_LAB_TEST = 1;
    public final int VIEW_LAB_CULT = 2;
    public final int VIEW_LAB_CULT_NOTES = 3;
    LinearLayoutManager mLinearLayoutManager;
    private ArrayList<Object> dataSet;

    public LabTestAdapter(ArrayList<Object> dataSet) {
        this.dataSet = dataSet;
        Log.e("LAB_TEST", "size=" + dataSet.size());

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        RecyclerView.ViewHolder myHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        switch (viewType) {
            case VIEW_LAB_TEST:
                view = inflater.inflate(R.layout.lab_res_test_row, viewGroup, false);
                myHolder = new MyViewHolder(view);
                break;
            case VIEW_LAB_CULT:

                view = inflater.inflate(R.layout.culture_result, viewGroup, false);
                myHolder = new MyViewHolder2(view);
                break;
            case VIEW_LAB_CULT_NOTES:

                view = inflater.inflate(R.layout.culture_res_notes, viewGroup, false);
                myHolder = new MyViewHolder3(view);
                break;
        }
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int listPosition) {
        switch (viewHolder.getItemViewType()) {
            case VIEW_LAB_TEST:
                LabTestDataModel Carddata = (LabTestDataModel) dataSet.get(listPosition);
                ((MyViewHolder) viewHolder).txttestname.setText(Carddata.getTxttestname());
                ((MyViewHolder) viewHolder).lbtestunit.setText(Carddata.getLbtestunit());
                ((MyViewHolder) viewHolder).txttestvalue.setText(Carddata.getTxttestvalue());
                ((MyViewHolder) viewHolder).lbtestminref.setText(Carddata.getLbtestminref());
                ((MyViewHolder) viewHolder).lbtestmaxref.setText(Carddata.getLbtestmaxref());
                break;
            case VIEW_LAB_CULT:
                //    LinearLayout testlayoutheader = (LinearLayout)((MyViewHolder2) viewHolder).itemView.findViewById(R.id.testlayout_header);
                //   testlayoutheader.setVisibility(View.GONE);
                Labtestculturemodel cultdata = (Labtestculturemodel) dataSet.get(listPosition);
                ((MyViewHolder2) viewHolder).culturname.setText(cultdata.getCulturname());
                ((MyViewHolder2) viewHolder).cultureres.setText(cultdata.getCultureres());
                ((MyViewHolder2) viewHolder).orgA.setText(cultdata.getOrgA());
                ((MyViewHolder2) viewHolder).orgAcount.setText(cultdata.getOrgAcount());
                ((MyViewHolder2) viewHolder).orgB.setText(cultdata.getOrgB());
                ((MyViewHolder2) viewHolder).orgBcount.setText(cultdata.getOrgBcount());
                ((MyViewHolder2) viewHolder).orgC.setText(cultdata.getOrgC());
                ((MyViewHolder2) viewHolder).orgCcount.setText(cultdata.getOrgCcount());
                ((MyViewHolder2) viewHolder).statcultcd.setText(cultdata.getStatcultcd());
                if (cultdata.getStatcultcd().equals("4")) {
                    LinearLayout cutantilayout = (LinearLayout) ((MyViewHolder2) viewHolder).itemView.findViewById(R.id.antibiotic_layout);
                    cutantilayout.setVisibility(View.VISIBLE);
                    com.moh.departments.adapters.CulturantiAdapter mCulturantiAdapter = new CulturantiAdapter(cultdata.getMantiList());
                    mLinearLayoutManager = new LinearLayoutManager(((MyViewHolder2) viewHolder).itemView.getContext());
                    ((MyViewHolder2) viewHolder).innercultRecyclerView.setLayoutManager(mLinearLayoutManager);
                    ((MyViewHolder2) viewHolder).innercultRecyclerView.setAdapter(mCulturantiAdapter);
                }

                break;
            case VIEW_LAB_CULT_NOTES:
                Labtestcultnotemodel cultnotedata = (Labtestcultnotemodel) dataSet.get(listPosition);
                ((MyViewHolder3) viewHolder).gramstain.setText(cultnotedata.getGramstain());
                ((MyViewHolder3) viewHolder).acidfaststain.setText(cultnotedata.getAcidfaststain());
                ((MyViewHolder3) viewHolder).KOH.setText(cultnotedata.getKOH());
                ((MyViewHolder3) viewHolder).Fungi.setText(cultnotedata.getFungi());
                ((MyViewHolder3) viewHolder).notes.setText(cultnotedata.getNotes());
                break;

        }
//            LabTestDataModel Carddata = (LabTestDataModel) dataSet.get(listPosition);
//            Log.e("LAB_TEST_min", Carddata.getLbtestminref() + " pos=" + listPosition);
//            Log.e("LAB_TEST_max", Carddata.getLbtestmaxref() + " pos=" + listPosition);
//
//        }
//        else if(dataSet.get(listPosition) instanceof Labtestculturemodel) {
//        }
    }

    @Override
    public int getItemViewType(int position) {
        if (dataSet.get(position) instanceof LabTestDataModel)
            return VIEW_LAB_TEST;
        else if (dataSet.get(position) instanceof Labtestculturemodel) {
            return VIEW_LAB_CULT;
        } else if (dataSet.get(position) instanceof Labtestcultnotemodel) {
            return VIEW_LAB_CULT_NOTES;
        }
        return -1;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txttestname, lbtestunit, txttestvalue, lbtestminref, lbtestmaxref;
        View divider;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.txttestname = (TextView) itemView.findViewById(R.id.txttestname);
            this.lbtestunit = (TextView) itemView.findViewById(R.id.lbtestunit);
            this.txttestvalue = (TextView) itemView.findViewById(R.id.txttestvalue);
            this.lbtestminref = (TextView) itemView.findViewById(R.id.lbtestminref);
            this.lbtestmaxref = (TextView) itemView.findViewById(R.id.lbtestmaxref);
        }
    }

    public static class MyViewHolder2 extends RecyclerView.ViewHolder {

        TextView culturname, cultureres, orgA, orgAcount, orgB, orgBcount, orgC, orgCcount, statcultcd;
        RecyclerView innercultRecyclerView;

        public MyViewHolder2(View itemView) {
            super(itemView);
            this.culturname = (TextView) itemView.findViewById(R.id.txt_culturname);
            this.cultureres = (TextView) itemView.findViewById(R.id.txt_cultureres);
            this.orgA = (TextView) itemView.findViewById(R.id.txt_orgA);
            this.orgAcount = (TextView) itemView.findViewById(R.id.txt_orgAcount);
            this.orgB = (TextView) itemView.findViewById(R.id.txt_orgB);
            this.orgBcount = (TextView) itemView.findViewById(R.id.txt_orgBcount);
            this.orgC = (TextView) itemView.findViewById(R.id.txt_orgC);
            this.orgCcount = (TextView) itemView.findViewById(R.id.txt_orgCcount);
            this.statcultcd = (TextView) itemView.findViewById(R.id.txt_cultureresid);
            this.innercultRecyclerView = itemView.findViewById(R.id.innercultRecyclerView);

        }
    }

    public static class MyViewHolder3 extends RecyclerView.ViewHolder {

        TextView gramstain, acidfaststain, KOH, Fungi, notes;

        public MyViewHolder3(View itemView) {
            super(itemView);
            this.gramstain = (TextView) itemView.findViewById(R.id.txt_gramstain);
            this.acidfaststain = (TextView) itemView.findViewById(R.id.txt_acidfaststain);
            this.KOH = (TextView) itemView.findViewById(R.id.txt_KOH);
            this.Fungi = (TextView) itemView.findViewById(R.id.txt_Fungi);
            this.notes = (TextView) itemView.findViewById(R.id.txt_notes);

        }
    }
}
