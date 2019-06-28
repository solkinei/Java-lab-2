package sample;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public class Automata {
    enum State {
        WAIT, OFF, ACCEPT, CHECK, COOK
    }

    public Integer cash;
    private static Map<String, Integer> menu;
    public State state;

    static {
        menu = new HashMap<String, Integer>();
    }

    public Automata(Integer cash) {
        this.cash = cash;
        this.state = State.OFF;
    }

    public State getState() {
        return state;
    }

    public Integer getCash() {
        return cash;
    }

    public void on() {
        if (state == State.OFF) {
            state = State.WAIT;
        }
    }

    public void off() {
        if (state == State.WAIT) {
            state = State.OFF;
        }
    }

    public boolean coin(Integer cash) {
        if (state == State.WAIT || state == State.ACCEPT) {
            this.cash += cash;
            state = State.ACCEPT;
            return true;
        }
        return false;
    }

    public void addToMenu(String name, Integer price) {
        menu.put(name, price);
    }

    public Map<String, Integer> printMenu() {
        return menu;
    }


    public State printState() {
        return state;
    }

    public Integer choice(String drink) {
        if (state == State.ACCEPT||state==State.WAIT) {
            state = State.CHECK;
            try {
                Integer cost = menu.get(drink);
                if (!check(cost)) {
                    cancel();
                    return 0;
                } else {
                    cook(drink);
                    finish();
                    return cash;
                }
            } catch (Exception e) {
                state=State.WAIT;
                return -1; //choice error
            }
        } else {
            state=State.WAIT;
            return -2; // state error
        }
    }

    public boolean check(Integer cash) {
        if (state == State.CHECK) {
            return this.cash >= cash;
        } else {
            return false;
        }
    }

    public void cancel() {
        if (state == State.ACCEPT || state == State.CHECK) {
            state = State.WAIT;
        }
    }

    private void cook(String drink) {
        if (state == State.CHECK) {
            cash -= menu.get(drink);
            state = State.COOK;
        }
    }

    private void finish() {
        if (state == State.COOK) {
            state = State.WAIT;
        }
    }
}
