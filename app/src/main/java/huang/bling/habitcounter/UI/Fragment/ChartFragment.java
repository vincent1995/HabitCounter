package huang.bling.habitcounter.UI.Fragment;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.resources.TextAppearance;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import huang.bling.habitcounter.R;
import huang.bling.habitcounter.UI.MyViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
// TODO add chart
public class ChartFragment extends Fragment {
    TableLayout table;
    MyViewModel viewModel;

    public ChartFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        container = (ViewGroup) inflater.inflate(R.layout.fragment_chart, container, false);

        table =  container.findViewById(R.id.ChartF_table);
        int size = viewModel.getCounter().getValue().getDataSize();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        for(int i = size-1;i>=0;i--){
            String date = ft.format(viewModel.getCounter().getValue().getData(i));

            TableRow row = new TableRow(getContext());
            row = (TableRow) inflater.inflate(R.layout.table_item,row);
            TextView new_textView = row.findViewById(R.id.table_item);
            new_textView.setText(date);

            table.addView(row);
        }
        return container;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(MyViewModel.class);
    }
}
