package huang.bling.habitcounter.UI.Fragment;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import haung.bling.habitcounter.Count.Counter;
import haung.bling.habitcounter.Count.Util;
import huang.bling.habitcounter.R;
import huang.bling.habitcounter.UI.MainActivity;
import huang.bling.habitcounter.UI.MyViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
//TODO show static data below the add button
public class MainFragment extends Fragment {
    Counter counter;
    TextView max_duration;
    TextView cur_duration;
    TextView last7d_count;
    TextView last30d_count;
    TextView last1y_count;
    TextView total_count;
    Button addButton;


    /** Init Fragment */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        container =  (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);
        max_duration =  container.findViewById(R.id.MainF_max_val);
        cur_duration =  container.findViewById(R.id.MainF_cur_val);
        last7d_count = container.findViewById(R.id.MainF_last7d_value);
        last30d_count = container.findViewById(R.id.MainF_last30d_value);
        last1y_count = container.findViewById(R.id.MainF_last1y_value);
        total_count = container.findViewById(R.id.MainF_total_value);
        addButton = container.findViewById(R.id.MainF_button_add);
        addButtonHandler();
        return container;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        counter = ViewModelProviders.of(getActivity()).get(MyViewModel.class).getCounter();
    }

    @Override
    public void onStart() {
        super.onStart();
        updateUI();
    }

    /** Handle event */
    public void addButtonHandler(){
        // Add button
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                counter.addRecord();
                updateUI();
            }
        });
    }

    public void updateUI(){
        max_duration.setText(String.valueOf(counter.getMax()));
        cur_duration.setText(String.valueOf(counter.getCur()));
        last7d_count.setText(String.valueOf(counter.getRecentCount(7)));
        last30d_count.setText(String.valueOf(counter.getRecentCount(30)));
        last1y_count.setText(String.valueOf(counter.getRecentCount(365)));
        total_count.setText(String.valueOf(counter.getTotalCount()));
    }
}
