package com.home.apollo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class LogFragment extends Fragment {
    private RecyclerView logRecyclerView;
    private LogAdapter logAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log, container, false);

        logRecyclerView = view.findViewById(R.id.log_recycler_view);
        logRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Sample data - replace with your actual data
        List<LogItem> logItems = new ArrayList<>();
        logItems.add(new LogItem("Exercise", "", "10:30 AM"));
        logItems.add(new LogItem("Sleep", "", "1:00 PM"));
        logItems.add(new LogItem("Mood", "", "8:00 AM"));
        logItems.add(new LogItem("Diet", "", "8:00 AM"));

        logAdapter = new LogAdapter(logItems);
        logRecyclerView.setAdapter(logAdapter);

        return view;
    }
}

class LogItem {
    String type;
    String details;
    String time;

    public LogItem(String type, String details, String time) {
        this.type = type;
        this.details = details;
        this.time = time;
    }
}

class LogAdapter extends RecyclerView.Adapter<LogAdapter.LogViewHolder> {
    private List<LogItem> logItems;

    public LogAdapter(List<LogItem> logItems) {
        this.logItems = logItems;
    }

    @Override
    public LogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_log, parent, false);
        return new LogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LogViewHolder holder, int position) {
        LogItem item = logItems.get(position);
        holder.typeText.setText(item.type);
        holder.detailsText.setText(item.details);
        holder.timeText.setText(item.time);
    }

    @Override
    public int getItemCount() {
        return logItems.size();
    }

    static class LogViewHolder extends RecyclerView.ViewHolder {
        TextView typeText;
        TextView detailsText;
        TextView timeText;

        public LogViewHolder(View itemView) {
            super(itemView);
            typeText = itemView.findViewById(R.id.log_type);
            detailsText = itemView.findViewById(R.id.log_details);
            timeText = itemView.findViewById(R.id.log_time);
        }
    }
}