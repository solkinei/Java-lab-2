import java.util.HashMap;
import java.util.Map;

class Automata {
    enum State {
        WAIT, OFF, ACCEPT, CHECK, COOK
    }

    private float cash;
    private static Map<String, Float> menu;
    private State state;

    static {
        menu = new HashMap<String, Float>();
    }

    public Automata(int cash) {
        this.cash = cash;
        this.state = State.OFF;
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
                if (!check(cost)) {
                    cancel();
                    return 0F;
                } else {
                    cook(drink);
                    finish();
                    return cash;
                }
            } catch (Exception e) {
                return -1F; //choice error
            }
        } else {
            return -2F; // state error
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


