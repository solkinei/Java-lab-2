import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Automata {
    enum STATES {OFF, WAIT, ACCEPT, CHECK, COOK}
    private STATES States;
    private int cash;
    private int cost;
    private Map <String,Integer> Menu = new HashMap<>();

    Automata (){
        cash = 0;
        States = STATES.OFF;
        setMenu();
    }

    private void setMenu(){
        String str = "";
        try {
            FileReader reader = new FileReader("menu.txt");
            int c;
            while((c=reader.read())!=-1){
                str = str + (char)c;
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        String[] buf = str.split(",");
        for (int i = 0; i < buf.length; i++){
            String[] bufX = buf[i].split("-");
            String x = bufX [0];
            int y = Integer.parseInt(bufX [1]);
            Menu.put (x,y);
        }
    }

    Map getMenu(){
        return Menu;
    }

    void on_off(){
        if (States == STATES.OFF) {
            States = STATES.WAIT;
        }
        else if (States == STATES.WAIT) {
            States = STATES.OFF;
        }
    }

    int coin (int cash){
        if ((States == STATES.WAIT)||(States == STATES.ACCEPT)) {
            States = STATES.ACCEPT;
            this.cash += cash;
        }
        return this.cash;
    }

    void choice(String drink){
        if (States == STATES.ACCEPT) {
            cost = Menu.get(drink);
            if (check()) {
                States = STATES.CHECK;
            }
        }
    }

    private Boolean check (){
        return (cash >= cost);
    }

    void cook (){
        if (States == STATES.CHECK) {
            States = STATES.COOK;
            cash-=cost;
        }
    }

    void cancel (){
        if ((States == STATES.CHECK)||(States == STATES.ACCEPT)||(States == STATES.COOK)) {//COOK
            States = STATES.WAIT;
            cash = 0;
        }
    }

    STATES getStates(){
        return States;
    }

    int getCash(){
        return cash;
    }
}
