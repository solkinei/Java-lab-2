import java.util.HashMap;
import java.util.Map;

class Automata {
    enum State {
        WAIT, OFF, ACCEPT, CHECK, COOK
    }

    private static float cash;
    private static Map<String, Float> menu;
    private static State state;

    public Automata(int cash) {
        this.cash = cash;
        this.state = State.OFF;
        this.menu = new HashMap<String, Float>();
    }

    public State getState() {
        return state;
    }

    public float getCash() {
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

    public boolean coin(int cash) {
        if (state == State.WAIT || state == State.ACCEPT) {
            this.cash += cash;
            state = State.ACCEPT;
            return true;
        }
        return false;
    }

    public void addToMenu(String name, float price) {
        menu.put(name, price);
    }

    public Map<String, Float> printMenu() {
        return menu;
    }


    public State printState() {
        return state;
    }

    public float choice(String drink) {
        if (state == State.ACCEPT) {
            state = State.CHECK;
            try {
                float cost = menu.get(drink);
                return menu.get(drink);
            } catch (Exception e) {
                return -1;
            }
        } else {
            return 0;
        }
    }

    public boolean check(float cash) {
        if (state == State.CHECK) {
            return this.cash > cash;
        } else {
            return false;
        }
    }

    public void cancel() {
        if (state == State.ACCEPT || state == State.CHECK) {
            state = State.WAIT;
        }
    }

    public void cook(String drink) {
        if (state == State.CHECK) {
            cash -= menu.get(drink);
            state = State.COOK;
        }
    }

    public void finish() {
        if (state == State.COOK) {
            state = State.WAIT;
        }
    }
}


