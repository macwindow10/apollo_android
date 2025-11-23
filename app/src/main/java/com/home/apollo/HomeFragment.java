package com.home.apollo;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
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
import java.util.Random;

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
        Random random = new Random();
        int value = random.nextInt(41) + 80;  // 80 to 120
        updateGlucoseLevel(value); // Example value

        // Setup chart with dummy data
        setupGlucoseChart(value);

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

    private void setupGlucoseChart(int value) {

        Random random = new Random();
        // Dummy data - replace with your actual data
        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, value));  // 9 AM
        entries.add(new Entry(1, random.nextInt(41) + 80)); // 10 AM
        entries.add(new Entry(2, random.nextInt(41) + 80)); // 11 AM
        entries.add(new Entry(3, random.nextInt(41) + 80)); // 12 PM
        entries.add(new Entry(4, random.nextInt(41) + 80));  // 1 PM
        entries.add(new Entry(5, random.nextInt(41) + 80));  // 2 PM

        // Create a data set with the entries
        LineDataSet dataSet = new LineDataSet(entries, "Glucose Level");
        int myColor = ContextCompat.getColor(getContext(), R.color.darker_blue);
        dataSet.setColor(myColor);
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
        xAxis.setTextSize(14f);
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
        leftAxis.setTextSize(14f);
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
        glucoseChart.setBackgroundColor(Color.TRANSPARENT);
        glucoseChart.getXAxis().setTextColor(Color.BLACK);
        glucoseChart.getAxisLeft().setTextColor(Color.BLACK);
        glucoseChart.setExtraBottomOffset(10f);

        // Refresh chart
        glucoseChart.invalidate();
    }
}