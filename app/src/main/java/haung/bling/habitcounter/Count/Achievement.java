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
        return false;
    }
    public String getDescription(int x){
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
        return "This is description";
    }
}
