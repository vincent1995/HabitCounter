package haung.bling.habitcounter.Count;

public class Achievement {
    Counter myCounter;
    final int size=5;
    public Achievement(Counter c){
        myCounter=c;
    }

    public int getSize(){
        return size;
    }
    public boolean calculateAchievement(int x){
        switch(x){
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
        if(x%2==0) return true;
        return false;
    }
    public String getDescription(int x){
        String result="";
        switch(x){
            case 0:
                result = "Diet";
                break;
            case 1:
                result = "Gym";
                break;
            case 2:
                result = "Study";
                break;
            case 3:
                result = "Endurance";
                break;
            case 4:
                result = "Alcholic";
                break;
        }
        return result;
    }
}
