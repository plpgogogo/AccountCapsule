package com.acapsule.accountcapsule.childfrags;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acapsule.accountcapsule.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;
import lecho.lib.hellocharts.view.PreviewColumnChartView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnalysisFragment extends Fragment {

    @Bind(R.id.pieChart)
    PieChartView pieChart;
    @Bind(R.id.previewColumnChart)
    PreviewColumnChartView previewColumnChart;

    private View mView;


    public AnalysisFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_analysis, container, false);
        ButterKnife.bind(this, mView);

        configPieChart();

        return mView;
    }

    private void configPieChart() {
        generateData();
    }

    private void generateData(){
        int numValues = 6;
        List<SliceValue> values = new ArrayList<>();
        for (int i = 0; i < numValues; ++i) {
            SliceValue sliceValue = new SliceValue((float) Math.random() * 30 + 15, ChartUtils.pickColor());
            values.add(sliceValue);
        }


        PieChartData data = new PieChartData(values);
        pieChart.setPieChartData(data);
        data.setHasLabels(false);
        data.setHasLabelsOnlyForSelected(false);
        data.setHasLabelsOutside(false);
        data.setHasCenterCircle(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
