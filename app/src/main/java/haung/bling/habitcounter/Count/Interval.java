package haung.bling.habitcounter.Count;

import java.util.Date;

public class Interval {

    int type;
    public Date left;
    public Date right;
    final long MS_IN_ONE_DAY = 1000*60*60*24;
    public boolean isEnd = false;

    public Interval(Date earliestDate, int gap){
        type = gap;
        switch(type){
            case 1:
                left = new Date(earliestDate.getYear(),earliestDate.getMonth(),earliestDate.getDay());
                right = new Date();
                right.setTime(left.getTime()+MS_IN_ONE_DAY);
                break;
            case 30:
                left = new Date(earliestDate.getYear(),earliestDate.getMonth(),1);
                if(left.getMonth()<11)
                    right = new Date(earliestDate.getYear(),earliestDate.getMonth()+1,1);
                else
                    right = new Date(earliestDate.getYear()+1,1,1);
                break;
            case 365:
                left = new Date(earliestDate.getYear(),1,1);
                right = new Date(earliestDate.getYear()+1,1,1);
                break;
        }
        if(left.getTime() > (new Date()).getTime())
            isEnd = true;
    }

    public void goNext(){
        switch(type){
            case 1:
                left.setTime(right.getTime());
                right.setTime(right.getTime()+MS_IN_ONE_DAY);
                break;
            case 30:
                left.setTime(right.getTime());
                if(right.getMonth() < 11){
                    right.setMonth(right.getMonth()+1);
                }else{
                    right.setYear(right.getYear());
                    right.setMonth(0);
                }
                break;
            case 365:
                left.setTime(right.getTime());
                right.setYear(right.getYear()+1);
                break;
        }
        // if not pass current time, return true;
        if(left.getTime() > (new Date()).getTime())
            isEnd = true;
    }
}
