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

    public String s = "test String";
    int MS_IN_ONE_DAY = 24*60*60*1000;
    private ArrayList<Date> countData;
    private IntervalStatic sevenDaySta;
    private IntervalStatic thirtyDaySta;
    private IntervalStatic oneYearSta;
    private IntervalStatic totalSta;
    private int maxDay;
    private int curDay;

    public Counter(){
        countData = new ArrayList<Date>();

        sevenDaySta = new IntervalStatic(7,false);
        thirtyDaySta = new IntervalStatic(30,false);
        oneYearSta = new IntervalStatic(365,false);
        totalSta = new IntervalStatic(0,true);
        maxDay = 0;
        curDay = 0;
    }

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

            Log.d("Counter",countData.size()+"date is saved");

            for(int i = 0;i<countData.size();i++){
                String data = fm.format(countData.get(i));
                outputStream.write(data.getBytes());
            }
        }catch(FileNotFoundException exc){

        }catch(IOException exc){

        }
        return true;
    }

    //get statistic data
    private void whole_updateStatistic(){
        sevenDaySta.update(countData);
        thirtyDaySta.update(countData);
        oneYearSta.update(countData);
        totalSta.update(countData);
        maxDay = countMax();
        curDay = countCur();
    }

    int countMax(){
        long max = 0;
        for(int i = 0;i<countData.size()-1;i++){
            Date left = countData.get(i);
            Date right = countData.get(i+1);
            long gap = ( right.getTime() - left.getTime() )/ MS_IN_ONE_DAY;
            if(gap > max)
                max = gap;
        }

        int gap = countCur();
        if(gap> max){
            max = gap;
        }
        return (int)max;
    }

    int countCur(){
        long gap = (   (new Date()).getTime() - countData.get(countData.size()-1).getTime() ) / MS_IN_ONE_DAY;
        return (int)gap;
    }

    ////////////////////////////////////////
    // interface
    public void addRecord(){
        Log.d("counter","add Record"+"now size: " + countData.size());

        Date today = new Date();
        countData.add(today);
    }

    public int getDataSize(){
        return countData.size();
    }

    public Date getData(int index){
        return countData.get(index);
    }

    public int getMaxDay(){
        return maxDay;
    }

    public int getCurDay(){
        return curDay;
    }

    public StatisInfo getStatisInfo(){
        StatisInfo info = new StatisInfo(sevenDaySta.count,sevenDaySta.avg_gap,
                thirtyDaySta.count,thirtyDaySta.avg_gap,
                oneYearSta.count,oneYearSta.avg_gap,
                totalSta.count,totalSta.avg_gap);
        return info;
    }

}






class StatisInfo{
    int sevenDayCount;
    double sevenDayGap;
    int thirtyDayCount;
    double thirtyDayGap;
    int oneYearCount;
    double oneYearGap;
    int totalCount;
    double totalGap;

    StatisInfo( int sevenDayCount,double sevenDayGap,
                int thirtyDayCount,double thirtyDayGap,
                int oneYearCount,double oneYearGap,
                int totalCount, double totalGap){
        this.sevenDayCount = sevenDayCount;
        this.sevenDayGap = sevenDayGap;
        this.thirtyDayCount = thirtyDayCount;
        this.thirtyDayGap = thirtyDayGap;
        this.oneYearCount = oneYearCount;
        this.oneYearGap = oneYearGap;
        this.totalCount = totalCount;
        this.totalGap = totalGap;
    }
}





class IntervalStatic{
    int interval;
    int count;
    double avg_gap;
    boolean istotal;

    long MS_IN_ONE_DAY = 24*60*60*1000;

    IntervalStatic(int inter,boolean total){
        interval = inter;
        count = 0;
        avg_gap = 0;
        istotal = total;
    }

    void update(ArrayList<Date> data){
        int size = data.size();

        Date today = new Date();
        long today_ms = today.getTime();
        count = 0;
        avg_gap = 0;

        //count
        for(int i = size-1;i>=0;i--){
            Date cur = data.get(i);
            long curday_ms = cur.getTime();
            if(today_ms - curday_ms < 7*MS_IN_ONE_DAY || istotal)
                count++;
        }
        // calculate average gap
        Date recordedFirstDay = data.get(0);
        long firstday_ms = recordedFirstDay.getTime();
        long interval_start_ms = today_ms - 7*MS_IN_ONE_DAY;
        if(istotal)
            interval_start_ms = firstday_ms;

        if(interval_start_ms < firstday_ms){
            interval_start_ms = firstday_ms;
            avg_gap = (double)(today_ms - interval_start_ms) / 7*MS_IN_ONE_DAY;
        }
        else{
            avg_gap = (double)interval / count;
        }

    }
}
