package haung.bling.habitcounter.Count;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class Util {
    public static void makeToast(String msg,Context context){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
    public static int getIntervalDay(Date d1,Date d2){
        int MS_IN_ONE_DAY = 24*60*60*1000;
        return (int) (Math.abs(d1.getTime()-d2.getTime()) / MS_IN_ONE_DAY);
    }
    public static ArrayList<Date> createFakeLog(){
        ArrayList<Date> habit_log = new ArrayList<>();
        return habit_log;
    }
}
