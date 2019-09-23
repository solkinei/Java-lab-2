import java.util.HashMap;
import java.util.Map;

public class Automata {

    enum STATES {OFF, WAIT, ACCEPT, CHECK, COOK}

    ;

    private STATES states;
    private int cash = 0;
    private int price = 0;
    private Map<String, Integer> menu = new HashMap<String, Integer>();

    Automata() {
        this.states = STATES.OFF;
        this.cash = cash;
    }

    public void on() {
        if (states != STATES.OFF) {
            return;
        }
        states = STATES.WAIT;
    }

    public void off() {
        if (states != STATES.WAIT) {
            return;
        }
        states = STATES.OFF;
    }

    public void coin(int coins) {
        if (states == STATES.WAIT || states == STATES.ACCEPT) {
            this.cash += coins;
            states = STATES.ACCEPT;
        }
    }

    public void setMenu(String name, int price) {
        menu.put(name, price);
    }

    public Map<String, Integer> getMenu() {
        return menu;
    }

    STATES getState() {
        return states;
    }

    public int choice(String name) {
        if (states == STATES.ACCEPT) {
            price = menu.get(name);
            try {
                if (check()) {
                    states = STATES.CHECK;
                } else {
                    throw new Exception("Need more money!");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
        return cash;
    }

    private boolean check(){
        return cash>price;
    }

    public void cancel(){
        if(states==STATES.CHECK||states==STATES.ACCEPT){
            states=STATES.WAIT;
            cash=0;
        }
    }

    public void cook(String name){
        if(states==STATES.CHECK){
            states=STATES.COOK;
            cash-=menu.get(name);
        }
    }

    public void finish(){
        if(states==STATES.COOK){
            states=STATES.WAIT;
        }
    }

    public int getCash(){
        return cash;
    }
}

