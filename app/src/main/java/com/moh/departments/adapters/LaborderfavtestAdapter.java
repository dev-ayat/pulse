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
import com.moh.departments.fragment.favtestFragment;
import com.moh.departments.models.Labordertestmodel;

import java.util.ArrayList;

public class LaborderfavtestAdapter extends RecyclerView.Adapter<LaborderfavtestAdapter.MyViewHolder> {
    public static MyListenerFav mMyListenerFav;
    favtestFragment.OnfavCatClickListener onfavCatClickListener;
    myfavInterface mmyfavInterface;
    private ArrayList<Labordertestmodel> dataSet;


    public LaborderfavtestAdapter(ArrayList<Labordertestmodel> dataSet, myfavInterface mmyfavInterface) {
        this.dataSet = dataSet;
        this.mmyfavInterface = mmyfavInterface;
        Log.e("CAT_", "dataSet size" + dataSet.size());

    }

    public static MyListenerFav getmMyListenerFav() {
        return mMyListenerFav;
    }

    public static void setmMyListenerFav(MyListenerFav mMyListenerFav) {
        LaborderfavtestAdapter.mMyListenerFav = mMyListenerFav;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lab_order_favtest_row, parent, false);
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
        holder.chx_catcd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (onfavCatClickListener != null) {
                    onfavCatClickListener.onFavCatChecked(view, listPosition);
                }
                Carddata.setChecked(((CheckBox) view).isChecked());
                Log.e("MYERROR", Carddata.getTEST_NAME());
                mMyListenerFav.onClickFav(Carddata);
                mmyfavInterface.myfavClick(Carddata);
                Log.e("checkbox2", Carddata.toString());
            }
        });
        holder.removeFromfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (onfavCatClickListener != null) {
                    onfavCatClickListener.onRemoveFavClicked(view, listPosition);
                }
                mmyfavInterface.mRemoveFavClick(Carddata);
                Log.e("checkbox3", Carddata.toString());
            }
        });
    }

    void setOnfavCatClickListener(favtestFragment.OnfavCatClickListener onfavCatClickListener) {
        this.onfavCatClickListener = onfavCatClickListener;
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public interface myfavInterface {
        void myfavClick(Labordertestmodel mLabordertestmodel);

        void mRemoveFavClick(Labordertestmodel mLabordertestmodel);

    }


    public interface MyListenerFav {
        void onClickFav(Labordertestmodel mLabordertestmodel);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView TEST_NAME, TEST_CD;
        CheckBox chx_catcd;
        ImageView removeFromfav;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.TEST_NAME = itemView.findViewById(R.id.order_testname);
            this.TEST_CD = itemView.findViewById(R.id.order_testcd);
            this.chx_catcd = itemView.findViewById(R.id.chx_catcd);
            this.removeFromfav = itemView.findViewById(R.id.removefromfav);

        }

        @Override
        public void onClick(View view) {
            onfavCatClickListener.onFavCatChecked(view, (Integer) view.getTag());
            onfavCatClickListener.onRemoveFavClicked(view, (Integer) view.getTag());
        }

    }

}