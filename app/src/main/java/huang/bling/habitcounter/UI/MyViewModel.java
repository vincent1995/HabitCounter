package huang.bling.habitcounter.UI;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import haung.bling.habitcounter.Count.Counter;

public class MyViewModel extends ViewModel {
    //TODO viewmodel has problem.
    private MutableLiveData<Counter> counter = new MutableLiveData<>();
    MyViewModel(){
        counter.setValue(new Counter());
    }
    public Counter getCounter(){
        return counter.getValue();
    }
}
