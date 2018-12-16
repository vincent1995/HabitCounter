package haung.bling.habitcounter.Count;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

public class Util {
    public static void makeToast(String msg,Context context){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
