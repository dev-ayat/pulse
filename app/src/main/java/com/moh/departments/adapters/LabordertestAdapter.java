package com.moh.departments.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.moh.departments.R;
import com.moh.departments.fragment.GrouptestFragment;
import com.moh.departments.models.Labordertestmodel;

import java.util.ArrayList;

public class LabordertestAdapter extends RecyclerView.Adapter<LabordertestAdapter.MyViewHolder> {
    public static MyListenerCat mMyListenerCat;
    GrouptestFragment.OnCatClickListener onCatClickListener;
    private ArrayList<Labordertestmodel> dataSet;

    public LabordertestAdapter(ArrayList<Labordertestmodel> dataSet) {
        this.dataSet = dataSet;
        Log.e("CAT_", "dataSet size" + dataSet.size());

    }

    public static MyListenerCat getmMyListenerCat() {
        return mMyListenerCat;
    }

    public static void setmMyListenerCat(MyListenerCat mMyListenerCat) {
        LabordertestAdapter.mMyListenerCat = mMyListenerCat;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lab_order_test_row, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        final Labordertestmodel Carddata = dataSet.get(listPosition);
        holder.TEST_NAME.setText(Carddata.getTEST_NAME());
        holder.TEST_CD.setText(String.valueOf(Carddata.getTEST_CD()));
        holder.chx_catcd.setChecked(Carddata.isChecked());
        holder.chx_catcd.setTag(Carddata);
        holder.addtofav.setTag(Carddata);
        holder.chx_catcd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (onCatClickListener != null) {
                    onCatClickListener.onCatChecked(view, listPosition);
                }
                mMyListenerCat.onClickCat(Carddata);

            }
        });
        holder.addtofav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (onCatClickListener != null) {
                    onCatClickListener.onCatFavClicked(view, listPosition);
                }

            }
        });
    }

    void setOnCatClickListener(GrouptestFragment.OnCatClickListener onCatClickListener) {
        this.onCatClickListener = onCatClickListener;
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public interface MyListenerCat {
        void onClickCat(Labordertestmodel mLabordertestmodel);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView TEST_NAME, TEST_CD;
        CheckBox chx_catcd;
        ImageView addtofav;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.TEST_NAME = itemView.findViewById(R.id.order_testname);
            this.TEST_CD = itemView.findViewById(R.id.order_testcd);
            this.chx_catcd = itemView.findViewById(R.id.chx_catcd);
            this.addtofav = itemView.findViewById(R.id.addtofav);

        }

        @Override
        public void onClick(View view) {
            onCatClickListener.onCatChecked(view, (Integer) view.getTag());
            onCatClickListener.onCatFavClicked(view, (Integer) view.getTag());

        }

    }

}