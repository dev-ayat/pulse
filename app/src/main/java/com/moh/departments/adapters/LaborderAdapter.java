package com.moh.departments.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moh.departments.R;
import com.moh.departments.fragment.GrouptestFragment;
import com.moh.departments.models.LaborderModel;
import com.moh.departments.models.Labordertestmodel;

import java.util.ArrayList;

public class LaborderAdapter extends RecyclerView.Adapter<LaborderAdapter.MyViewHolder> {
    LinearLayoutManager mLinearLayoutManager;
    myInterface mmyInterface;
    private ArrayList<LaborderModel> dataSet;
    private Context context;

    public LaborderAdapter(ArrayList<LaborderModel> dataSet, Context context, myInterface mmyInterface) {
        this.dataSet = dataSet;
        this.context = context;
        this.mmyInterface = mmyInterface;
        Log.e("CAT_TEST", "dataSet size" + dataSet.size());

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lab_order_groups_row, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        final LaborderModel Carddata = dataSet.get(listPosition);
        holder.GROUP_NAME.setText(Carddata.getGROUP_NAME());
        holder.GROUP_CD.setText(String.valueOf(Carddata.getGROUPCD()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout catlayout = (LinearLayout) holder.itemView.findViewById(R.id.cat_layout_container);
                Integer visiblity = catlayout.getVisibility();
                if (visiblity == 8) {
                    catlayout.setVisibility(View.VISIBLE);
                    Log.e("ayat1", " vis" + visiblity + " " + " LIST_CAT_SIZE=" + Carddata.getmListCat().size());

                    LabordertestAdapter labordertestAdapter = new LabordertestAdapter(Carddata.getmListCat());
                    mLinearLayoutManager = new LinearLayoutManager(holder.itemView.getContext());
                    holder.innercatRecyclerView.setLayoutManager(mLinearLayoutManager);
                    holder.innercatRecyclerView.setAdapter(labordertestAdapter);
                    labordertestAdapter.setOnCatClickListener(new GrouptestFragment.OnCatClickListener() {
                        @Override
                        public void onCatChecked(View v, int position) {
                            System.out.println("Position " + position);
                            Carddata.getmListCat().get(position).setChecked(((CheckBox) v).isChecked());
                            mmyInterface.myClick(Carddata.getmListCat().get(position));
                            Log.e("checkbox", Carddata.getmListCat().get(position).getTEST_NAME());
                        }

                        @Override
                        public void onCatFavClicked(View v, int position) {
                            System.out.println("Position " + position);

                            Log.e("ERROR", "FLAG1");
                            Log.e("ERROR", "FLAG2");
                            mmyInterface.maddFavClick(Carddata.getmListCat().get(position));
                            Log.e("addfav", Carddata.getmListCat().get(position).getTEST_NAME());

                        }
                    });


                } else {
                    catlayout.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public interface myInterface {
        void myClick(Labordertestmodel mLabordertestmodel);

        void maddFavClick(Labordertestmodel mLabordertestmodel);

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView GROUP_CD, GROUP_NAME;
        RecyclerView innercatRecyclerView;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.GROUP_NAME = itemView.findViewById(R.id.txtgroup_name1);
            this.GROUP_CD = itemView.findViewById(R.id.txtgroup_cd1);
            this.innercatRecyclerView = itemView.findViewById(R.id.innertestorderRV);


        }
    }

}