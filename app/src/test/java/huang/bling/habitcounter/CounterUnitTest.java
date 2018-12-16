package huang.bling.habitcounter;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

import haung.bling.habitcounter.Count.Counter;
import haung.bling.habitcounter.Count.Util;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CounterUnitTest {

    public Counter createFakeCounter(){
        Counter counter = new Counter();

        ArrayList<Date> habit_log = Util.createFakeLog();
        habit_log.add(new Date(118,1,1));
        habit_log.add(new Date(118,2,1));
        habit_log.add(new Date(118,3,1));
        habit_log.add(new Date(118,4,1));
        habit_log.add(new Date(118,5,1));
        habit_log.add(new Date(118,6,1));
        habit_log.add(new Date(118,7,1));
        habit_log.add(new Date(118,8,1));
        habit_log.add(new Date(118,11,14));
        habit_log.add(new Date(118,11,14));
        habit_log.add(new Date(118,11,14));
        habit_log.add(new Date(118,11,14));
        habit_log.add(new Date(118,11,14));

        counter.loadArrayList(habit_log);
        return counter;
    }

    @Test
    public void addRecord(){
        Counter counter = createFakeCounter();
        assertEquals(13,counter.getTotalCount());
        counter.addRecord();
        assertEquals(14,counter.getTotalCount());
    }

    @Test
    public void calStatic(){
        Counter counter = createFakeCounter();
        assertEquals(5,counter.getRecentCount(7));
    }
}

