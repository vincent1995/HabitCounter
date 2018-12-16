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
    MyViewModel viewModel;
    TextView max_val;
    TextView cur_val;
    Button button_add;


    /** Init Fragment */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        container =  (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);
        max_val =  container.findViewById(R.id.MainF_max_val);
        cur_val =  container.findViewById(R.id.MainF_cur_val);
        button_add =  container.findViewById(R.id.MainF_button_add);
        return container;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(MyViewModel.class);
        Util.makeToast(viewModel.getCounter().s,getContext());
    }

    /** Define onClick Event */

    public void onClickAddButton(View view){
        viewModel.getCounter().s = "changed string";
    }


}
