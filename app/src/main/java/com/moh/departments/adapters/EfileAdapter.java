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
import com.moh.departments.dialog.DialogLoding;
import com.moh.departments.models.EfileModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EfileAdapter extends RecyclerView.Adapter<EfileAdapter.MyViewHolder> {
    private ArrayList<EfileModel> dataSet;
    private Context context;
    private DialogLoding mDialogLoding;

    public EfileAdapter(ArrayList<EfileModel> dataSet, Context context) {
        this.dataSet = dataSet;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.patient_file_row, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        mDialogLoding = new DialogLoding(context);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        EfileModel Carddata = dataSet.get(listPosition);
        holder.txtvisitdate.setText(Carddata.getVISTE_DATE());
        holder.txthossid.setText(Carddata.getHOSSID());
        holder.txtmrpid.setText(Carddata.getIDNUMBER());
        final Map<String, String> map = new HashMap<>();
        map.put("VISIT_DATE", Carddata.getVISTE_DATE());
        map.put("MRP_ID", Carddata.getIDNUMBER());
        Log.e("test", map.toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialogLoding.showDialog("جاري جلب الملفات");
                Log.e("pdf", "جاري جلب الملفات");
                EfileModel Carddata = dataSet.get(listPosition);
                String my_MRP_ID = Carddata.getIDNUMBER();
                String my_VISIT_DATE = Carddata.getVISTE_DATE();
                //String mypdf ="http://apps.moh.gov.ps/newwebarch/index.php/WebArchCi/meregepdf2/"+my_MRP_ID+"/"+ my_VISIT_DATE;
                String mypdf = "http://apps.moh.gov.ps/newwebarch/index.php/Androidhos_api/GET_FILES_BY_VISIT_PDF/" + my_MRP_ID + "/" + my_VISIT_DATE;
//                final String query = Uri.encode(my_MRP_ID+"/"+ my_VISIT_DATE, "utf-8");
//                String urldecoded = mypdf + query;
                Log.e("urlpdf", mypdf);
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mypdf));
                context.startActivity(browserIntent);
                mDialogLoding.hideDialog();

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtvisitdate, txthossid, txtmrpid;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.txtvisitdate = (TextView) itemView.findViewById(R.id.txtvisitdate);
            this.txthossid = (TextView) itemView.findViewById(R.id.txthossid);
            this.txtmrpid = (TextView) itemView.findViewById(R.id.txtmrpid);
        }
    }


}