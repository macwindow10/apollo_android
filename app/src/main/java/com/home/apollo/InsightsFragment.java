package com.home.apollo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InsightsFragment extends Fragment {
    private TextView insightText;
    private List<String> insights;
    private Random random;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_insights, container, false);

        insightText = view.findViewById(R.id.insight_text);
        random = new Random();

        // Initialize insights list
        insights = new ArrayList<>();
        insights.add("Take a 15-minute walk after meals to help lower blood sugar levels.");
        insights.add("Drink more water throughout the day to stay hydrated.");
        insights.add("Consider having a small protein-rich snack before bed to prevent nighttime lows.");
        insights.add("Your blood sugar tends to spike after breakfast. Try a lower carb option.");
        insights.add("Your blood sugar is most stable between 80-120 mg/dL. Keep up the good work!");

        // Display a random insight
        showRandomInsight();

        // Set up refresh button
        view.findViewById(R.id.refresh_button).setOnClickListener(v -> showRandomInsight());

        return view;
    }

    private void showRandomInsight() {
        if (!insights.isEmpty()) {
            String randomInsight = insights.get(random.nextInt(insights.size()));
            insightText.setText(randomInsight);
        }
    }
}