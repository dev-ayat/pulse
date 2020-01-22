package com.moh.departments.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.moh.departments.R;
import com.moh.departments.constants.Controller;
import com.moh.departments.models.RadphotoModel;

import java.util.ArrayList;


public class RadPhotoAdapter extends RecyclerView.Adapter<RadPhotoAdapter.MyViewHolder> {
    private ArrayList<RadphotoModel> dataSet;
    private Context context;

    public RadPhotoAdapter(ArrayList<RadphotoModel> dataSet, Context context) {
        this.dataSet = dataSet;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rad_photo_row, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        RadphotoModel Carddata = dataSet.get(listPosition);
        holder.txtresdate.setText(Carddata.getORDERD_RESULT_DATE());
        holder.txtorganid.setText(Carddata.getORGAN_CODE());
        holder.txtorganname.setText(Carddata.getORGAN_NAME_AR());
        holder.txtservid.setText(Carddata.getORGAN_SERVICE_CD());
        holder.txtservname.setText(Carddata.getSERVICE_NAME_AR());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Controller.LOADER_DIALOG.showDialog("جاري عرض الصورة ");
                RadphotoModel Carddata = dataSet.get(listPosition);
                String my_MRP_ID = Carddata.getMRP_ID();
                String my_SERVICE_NAME_AR = "";
                Log.e("onClick: ", "" + Carddata.getORGAN_SERVICE_CD());
                if (Carddata.getORGAN_SERVICE_CD().equals("1")) {
                    my_SERVICE_NAME_AR = "CR";
                } else if (Carddata.getORGAN_SERVICE_CD().equals("3")) {
                    my_SERVICE_NAME_AR = "CT";
                } else if (Carddata.getORGAN_SERVICE_CD().equals("6")) {
                    my_SERVICE_NAME_AR = "MRI";
                }
                Log.e("my_SERVICE_NAME_AR", "" + my_SERVICE_NAME_AR);
                // http://10.20.11.4:3000/studylist?id=901521823&modality=CR
                String myphoto = "http://10.20.11.4:3000/studylist?id=" + my_MRP_ID + "&modality=" + my_SERVICE_NAME_AR;
                Log.e("urlpdf", myphoto);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(myphoto));
                context.startActivity(browserIntent);
                Controller.LOADER_DIALOG.hideDialog();

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtresdate, txtorganid, txtorganname, txtservid, txtservname;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.txtresdate = (TextView) itemView.findViewById(R.id.txtresdate);
            this.txtorganid = (TextView) itemView.findViewById(R.id.txtorganid);
            this.txtorganname = (TextView) itemView.findViewById(R.id.txtorganname);
            this.txtservid = (TextView) itemView.findViewById(R.id.txtservid);
            this.txtservname = (TextView) itemView.findViewById(R.id.txtservname);
        }
    }

}