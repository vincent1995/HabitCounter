package haung.bling.habitcounter.Count;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Counter {

    private ArrayList<Date> habit_log = new ArrayList<>();

    public void loadData(Context context){
        File file = new File("counter_data");
        if(!file.exists())
            return;

        FileInputStream inputStream;
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
        try{
            byte[] b = new byte[20];
            inputStream = context.openFileInput("counter_data");
            inputStream.read(b);
            String s = new String(b);
            Log.d(Counter.class.toString(),s);
            Date date = fm.parse(s);
        }
        catch(FileNotFoundException exc){
            return;
        }
        catch(IOException exc){
            return;
        }
        catch(ParseException exc){
            return;
        }

    }

    public boolean saveData(Context context){
        FileOutputStream outputStream;
        SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
        try{
            outputStream = context.openFileOutput("counter_data",Context.MODE_PRIVATE);

            Log.d("Counter",habit_log.size()+"date is saved");

            for(int i = 0;i<habit_log.size();i++){
                String data = fm.format(habit_log.get(i));
                outputStream.write(data.getBytes());
            }
        }catch(FileNotFoundException exc){

        }catch(IOException exc){

        }
        return true;
    }

    public int getMax(){
        long max = 0;
        for(int i = 0;i<habit_log.size()-1;i++){
            Date left = habit_log.get(i);
            Date right = habit_log.get(i+1);
            long gap = Util.getIntervalDay(right,left);
            if(gap > max)
                max = gap;
        }

        int gap = getCur();
        if(gap> max){
            max = gap;
        }
        return (int)max;
    }

    public int getCur(){
        if(habit_log.size() == 0)
            return 0;
        Date today = new Date();
        Date lastDay = habit_log.get(habit_log.size()-1);
        long gap = Util.getIntervalDay(today,lastDay);
        return (int)gap;
    }

    /**
     * Get the count number in the recent several day.
     * @param interval How many recent days should count.
     * @return
     */
    public int getRecentCount(int interval){
        int count = 0;
        Date today = new Date();
        for(Date date:habit_log){
            if(Util.getIntervalDay(date,today) <= interval){
                count++;
            }
        }
        return count;
    }
    public int getTotalCount(){
        return getRecentCount(Integer.MAX_VALUE);
    }

    public void loadArrayList(ArrayList<Date> list){
        habit_log = list;
    }

    public ArrayList<Date> getLog(){
        return habit_log;
    }

    public void addRecord(){
        Date today = new Date();
        habit_log.add(today);
    }

    public ArrayList<Integer> counterNumberInEachPeriod(int gap){
        ArrayList<Integer> counts = new ArrayList<>();

        return counts;
    }
}

