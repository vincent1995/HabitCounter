package huang.bling.habitcounter.UI.Fragment;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.IntentFilter;
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

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import haung.bling.habitcounter.Count.Counter;
import haung.bling.habitcounter.Count.Util;
import huang.bling.habitcounter.R;
import huang.bling.habitcounter.UI.MyViewModel;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;


/**
 *  Chart Fragment
 *
 *  In this Fragment, we show some statistic information using some chart.
 *
 *  Required Charts:
 *  1. Bar Chart
 *      The bar chart show how many times habit happened in every 1 day, 7 days, 1 month, 1 year.
 *      Should also have a button to change the time window between 1 day, 7 days, 1 month and 1 year.
 *
 *  TODO 1. Draw Chart complete, calculate the data in Counter.
 *  TODO 2. Add button to change time window.
 *
 */
public class ChartFragment extends Fragment {
    Counter counter;
    ColumnChartView periodColChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        container = (ViewGroup) inflater.inflate(R.layout.fragment_chart, container, false);
        periodColChart = container.findViewById(R.id.ChartF_period_colChart);
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
        initChartFragment();
    }

    private void initChartFragment(){
        //ArrayList<Integer> counts = counter.counterNumberInEachPeriod(30);
        ArrayList<Integer> counts = new ArrayList<>();
        counts.add(1);
        counts.add(11);
        counts.add(2);
        counts.add(22);
        counts.add(15);
        counts.add(8);
        load_periodColChart_Data(counts);
    }


    /** Load a list of data into period column chart */
    private void load_periodColChart_Data(ArrayList<Integer> counts) {
        ColumnChartData data;
        List<Column> columns = new ArrayList<Column>();
        ArrayList<SubcolumnValue> subColumns;

        // create columns
        for (int i = 0; i < counts.size(); i++) {
            int count = counts.get(i);

            // sub column
            subColumns = new ArrayList<>();
            subColumns.add(new SubcolumnValue(count));

            // column
            Column column = new Column(subColumns);
            column.setHasLabelsOnlyForSelected(true);

            // add column
            columns.add(column);
        }

        // load columns into chart
        data = new ColumnChartData(columns);

        //
        Axis axisX = new Axis();
        Axis axisY = new Axis().setHasLines(true);
        axisX.setName("Axis X");
        axisY.setName("Axis Y");
        data.setAxisXBottom(axisX);
        data.setAxisYLeft(axisY);

        periodColChart.setColumnChartData(data);
    }
}
