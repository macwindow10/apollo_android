package com.home.apollo;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private TextView glucoseLevelText;
    private TextView statusText;
    private LineChart glucoseChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        glucoseLevelText = view.findViewById(R.id.glucose_level);
        statusText = view.findViewById(R.id.status_text);
        glucoseChart = view.findViewById(R.id.glucose_chart);

        // Set title
        if (getActivity() != null) {
            getActivity().setTitle("Glucose");
        }

        // TODO: Update this with actual glucose level from your data source
        updateGlucoseLevel(95); // Example value

        // Setup chart with dummy data
        setupGlucoseChart();

        return view;
    }

    private void updateGlucoseLevel(int glucoseLevel) {
        glucoseLevelText.setText(String.valueOf(glucoseLevel));

        // Set status based on glucose level
        if (glucoseLevel < 70) {
            statusText.setText("Low");
            statusText.setTextColor(getResources().getColor(android.R.color.holo_red_dark, null));
        } else if (glucoseLevel > 140) {
            statusText.setText("High");
            statusText.setTextColor(getResources().getColor(android.R.color.holo_red_dark, null));
        } else {
            statusText.setText("Normal");
            statusText.setTextColor(getResources().getColor(android.R.color.holo_green_dark, null));
        }
    }

    private void setupGlucoseChart() {
        // Dummy data - replace with your actual data
        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, 95f));  // 9 AM
        entries.add(new Entry(1, 102f)); // 10 AM
        entries.add(new Entry(2, 115f)); // 11 AM
        entries.add(new Entry(3, 108f)); // 12 PM
        entries.add(new Entry(4, 98f));  // 1 PM
        entries.add(new Entry(5, 92f));  // 2 PM

        // Create a data set with the entries
        LineDataSet dataSet = new LineDataSet(entries, "Glucose Level");
        dataSet.setColor(Color.BLUE);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(10f);
        dataSet.setLineWidth(2f);
        dataSet.setCircleColor(Color.BLUE);
        dataSet.setCircleRadius(4f);
        dataSet.setDrawValues(false); // Hide values on points
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER); // Make the line smooth

        // Create x-axis labels
        final String[] timeValues = new String[]{"9 AM", "10 AM", "11 AM", "12 PM", "1 PM", "2 PM"};

        // Configure x-axis
        XAxis xAxis = glucoseChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        /*
        xAxis.setValueFormatter((float value, AxisBase axis) -> {
            int index = (int) value;
            if (index >= 0 && index < timeValues.length) {
                return timeValues[index];
            }
            return "";
        });*/
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                int index = (int) value;
                if (index >= 0 && index < timeValues.length) {
                    return timeValues[index];
                }
                return "";
            }
        });

        xAxis.setGranularity(1f);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawGridLines(false);

        // Configure y-axis (left)
        YAxis leftAxis = glucoseChart.getAxisLeft();
        leftAxis.setAxisMinimum(60f); // Minimum glucose level
        leftAxis.setAxisMaximum(200f); // Maximum glucose level
        leftAxis.setAxisLineWidth(2f);
        leftAxis.setAxisLineColor(Color.BLACK);
        leftAxis.setTextColor(Color.BLACK);
        leftAxis.setDrawGridLines(true);
        leftAxis.setGridColor(Color.LTGRAY);

        // Hide right y-axis
        YAxis rightAxis = glucoseChart.getAxisRight();
        rightAxis.setEnabled(false);

        // Configure chart appearance
        glucoseChart.setData(new LineData(dataSet));
        glucoseChart.getDescription().setEnabled(false);
        glucoseChart.getLegend().setEnabled(false);
        glucoseChart.setTouchEnabled(true);
        glucoseChart.setDragEnabled(true);
        glucoseChart.setScaleEnabled(true);
        glucoseChart.setPinchZoom(true);
        glucoseChart.setDrawGridBackground(false);
        glucoseChart.setBackgroundColor(Color.WHITE);
        glucoseChart.getXAxis().setTextColor(Color.BLACK);
        glucoseChart.getAxisLeft().setTextColor(Color.BLACK);
        glucoseChart.setExtraBottomOffset(10f);

        // Refresh chart
        glucoseChart.invalidate();
    }
}