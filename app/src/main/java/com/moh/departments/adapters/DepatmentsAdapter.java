package com.moh.departments.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.moh.departments.R;
import com.moh.departments.constants.Controller;
import com.moh.departments.models.Departments;

import java.util.List;

public class DepatmentsAdapter extends RecyclerView.Adapter<DepatmentsAdapter.MyViewHolder> {

    public String deptcdselected;
    private List<Departments> deptsList;
    private int lastSelectedPosition = -1;


    public DepatmentsAdapter(List<Departments> deptsList) {
        this.deptsList = deptsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dept_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Departments dept = deptsList.get(position);
        holder.deptnameradio.setText(dept.getDeptname());
        holder.deptcd.setText(dept.getDeptcd());
        holder.deptnameradio.setChecked(lastSelectedPosition == position);

    }

    @Override
    public int getItemCount() {
        return deptsList.size();
    }

    public void updateDeptsdata(List<Departments> newdeptsList) {
        deptsList.clear();
        deptsList.addAll(newdeptsList);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public RadioButton deptnameradio;
        public TextView deptcd;
        public Button btndeptlist;

        public MyViewHolder(View view) {
            super(view);
            deptnameradio = (RadioButton) view.findViewById(R.id.deptnameradio);
            deptcd = (TextView) view.findViewById(R.id.txtdeptcd);
            btndeptlist = (Button) view.findViewById(R.id.btndeptlist);
            deptnameradio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lastSelectedPosition = getAdapterPosition();
                    notifyDataSetChanged();
                    Log.d("API123", "selected :" + deptcd.getText());
                    deptcdselected = (String) deptcd.getText();
                    Log.d("var", "id:" + deptcdselected);
                    Controller.editor.putString("DEPT_CD_SELEC", deptcdselected);
                    Controller.editor.commit();
                    Log.d("ayat2", "shared :" + Controller.pref.getString("DEPT_CD_SELEC", ""));


                }
            });
        }
    }


}