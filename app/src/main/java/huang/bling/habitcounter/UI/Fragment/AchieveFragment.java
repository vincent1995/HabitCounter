package huang.bling.habitcounter.UI.Fragment;


import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import haung.bling.habitcounter.Count.Achievement;
import huang.bling.habitcounter.R;
import huang.bling.habitcounter.UI.MyViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class AchieveFragment extends Fragment {
    MyViewModel viewModel;
    Achievement myAchievement;

    public AchieveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        LinearLayout v=(LinearLayout)inflater.inflate(R.layout.fragment_achieve,container,false);
        v.setBackgroundColor(Color.GREEN);
        for(int i=0;i<myAchievement.getSize();i++){
            LinearLayout list=(LinearLayout)v.findViewById(R.id.achieve_list);
            CardView cv=new CardView(super.getActivity());
            TextView tv=new TextView(super.getContext());
            tv.setText(myAchievement.getDescription(i));
            if(myAchievement.calculateAchievement(i)){
                cv.setCardBackgroundColor(Color.WHITE);
            }
            else{
                cv.setCardBackgroundColor(Color.DKGRAY);
            }
            cv.addView(tv);
            list.addView(cv);
        }


        return v;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        viewModel = ViewModelProviders.of(getActivity()).get(MyViewModel.class);
        myAchievement=new Achievement(viewModel.getCounter());



    }

}
