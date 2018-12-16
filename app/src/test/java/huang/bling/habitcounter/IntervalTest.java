package huang.bling.habitcounter;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Date;

import haung.bling.habitcounter.Count.Interval;

public class IntervalTest {
    @Test
    public void cal_30d_interval(){
        Date startDate = new Date(2018,1,1);
        assertEquals(2018,startDate.getYear());
        assertEquals(1,startDate.getMonth());
        assertEquals(1,startDate.getDate());
        Interval v = new Interval(startDate,30);
        assertEquals(false,v.isEnd);
        while(!v.isEnd){
            System.out.println(v.left);
            System.out.println(v.right);
            System.out.println("-----");
            v.goNext();
        }
    }
}
