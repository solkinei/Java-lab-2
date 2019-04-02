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

    private Map setMenu(){
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
        return Menu;
    }

    void getMenu(){
        for(Map.Entry<String, Integer> entry: Menu.entrySet())
            System.out.println(entry.getKey() + " - " + entry.getValue());
    }

    void on_off(){
        if (States == STATES.OFF) {
            States = STATES.WAIT;
            System.out.println("Hello!");
        }
        else if (States == STATES.WAIT) {
            States = STATES.OFF;
            System.out.println("Goodbye!");
        }
        else System.out.println("Error!");
    }

    int coin (int cash){
        if ((States == STATES.WAIT)||(States == STATES.ACCEPT)) {
            States = STATES.ACCEPT;
            this.cash += cash;
            System.out.println("Money in your account is " +this.cash + " rubles.");
        }
        else System.out.println("Error!");
        return this.cash;
    }

    void choice(String drink){
        if (States == STATES.ACCEPT) {
            cost = Menu.get(drink);
            if (check()) {
                States = STATES.CHECK;
                System.out.println("You choose " + drink + ", cost " + cost + " rubles. Are you sure?");
            } else System.out.println("Not enough money");
        }
        else System.out.println("Error!");
    }

    private Boolean check (){
        return (cash >= cost);
    }

    void cook (){
        if (States == STATES.CHECK) {
            States = STATES.COOK;
            cash-=cost;
            System.out.println("Take your drink!");
        }
        else System.out.println("Error!");
    }

    void cancel (){
        if ((States == STATES.CHECK)||(States == STATES.ACCEPT)||(States == STATES.COOK)) {//COOK
            States = STATES.WAIT;
            System.out.println("Take change " + cash + " rubles.");
            cash = 0;
        }
        else System.out.println("Error!");
    }

    STATES getStates(){
        return States;
    }

    int getCash(){
        return cash;
    }
}
