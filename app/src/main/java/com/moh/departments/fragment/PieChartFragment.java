package com.moh.departments.fragment;


import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.moh.departments.R;
import com.moh.departments.activiteis.HomeActivity;

import java.util.ArrayList;

//import com.github.mikephil.charting.utils.Highlight;
//import com.github.mikephil.charting.utils.PercentFormatter;

public class PieChartFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pie_chart, container, false);

        PieChart pieChart = (PieChart) view.findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);

        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.
        ArrayList<PieEntry> yvalues = new ArrayList<PieEntry>();
        yvalues.add(new PieEntry(8f, "January"));
        yvalues.add(new PieEntry(15f, "February"));
        yvalues.add(new PieEntry(12f, "March"));
        yvalues.add(new PieEntry(25f, "April"));
        yvalues.add(new PieEntry(23f, "May"));
        yvalues.add(new PieEntry(17f, "June"));

        PieDataSet dataSet = new PieDataSet(yvalues, "Election Results");
        dataSet.setValueTextSize(100f);
        PieData data = new PieData(dataSet);
        // In Percentage term
        data.setValueFormatter(new PercentFormatter());
        // Default value
        //data.setValueFormatter(new DefaultValueFormatter(0));
        pieChart.setData(data);
        pieChart.getDescription().setText("Description of my chart");
        pieChart.getDescription().setTextSize(16f); //sets the size of the label text in density pixels min = 6f, max = 24f, default is 10f, font size will be in dp
        pieChart.getDescription().setTextAlign(Paint.Align.RIGHT); //Sets the text alignment of the description text. Default RIGHT
        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(25f);
        pieChart.setHoleRadius(25f);

        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.DKGRAY);
        pieChart.animateXY(1400, 1400);

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {

            }

            @Override
            public void onNothingSelected() {

            }
        });

        return view;
    }

    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).setActionBarTitle("الإحصائيات الإدارية");
    }

}
