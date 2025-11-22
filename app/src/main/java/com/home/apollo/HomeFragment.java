package com.home.apollo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    private TextView glucoseLevelText;
    private TextView statusText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        glucoseLevelText = view.findViewById(R.id.glucose_level);
        statusText = view.findViewById(R.id.status_text);

        // TODO: Update this with actual glucose level from your data source
        updateGlucoseLevel(95); // Example value

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
}