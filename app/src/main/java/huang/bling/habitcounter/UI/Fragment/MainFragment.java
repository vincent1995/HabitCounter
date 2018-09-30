package huang.bling.habitcounter.UI.Fragment;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import haung.bling.habitcounter.Count.Counter;
import huang.bling.habitcounter.R;
import huang.bling.habitcounter.UI.MyViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
//TODO show static data below the add button
public class MainFragment extends Fragment {
    private MyViewModel viewModel;
    TextView max_val;
    TextView cur_val;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        container =  (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);
        max_val =  container.findViewById(R.id.MainF_max_val);
        cur_val =  container.findViewById(R.id.MainF_cur_val);
        Button button_add =  container.findViewById(R.id.MainF_button_add);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.addRecord();
                updateUI();
            }
        });
        updateUI();
        return container;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //init viewModel
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(MyViewModel.class);
    }

    void updateUI(){
        max_val.setText(String.valueOf(viewModel.getCounter().getValue().getMaxDay()));
        cur_val.setText(String.valueOf(viewModel.getCounter().getValue().getCurDay()));
    }
}
