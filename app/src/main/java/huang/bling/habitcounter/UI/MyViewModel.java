package huang.bling.habitcounter.UI;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import haung.bling.habitcounter.Count.Counter;

public class MyViewModel extends ViewModel {
    //TODO viewmodel has problem.
    public MutableLiveData<Counter> counter;
    MyViewModel(){
        counter = new MutableLiveData<>();
        counter.setValue(new Counter());
    }
    public MutableLiveData<Counter> getCounter(){
        return counter;
    }
    public void addRecord(){
        Counter temp = counter.getValue();
        temp.addRecord();
        counter.setValue(temp);
    }
}
