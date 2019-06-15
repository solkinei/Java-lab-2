import java.util.ArrayList;
import java.io.*;


enum STATES{
    OFF,WAIT,ACCEPT,CHECK,COOK;
        }

class Automata {
    private int cash; //- для хранения текущей суммы;
    private ArrayList<String> drinks = new ArrayList<String>(); //- строки с названиями напитков (может подгружаться из файла);
    private ArrayList<Integer> prices = new ArrayList<Integer>(); // - цены напитков (соответствует массиву с напитками);
    private String[] menu;
    private STATES state; //- текущее состояние автомата;
    private PrintStream ps;

    public Automata(String fileName, PrintStream ps) {
        this.state = STATES.OFF;
        this.cash = 0;
        this.ps = ps;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String buf[] = line.split(" ");
                this.drinks.add(buf[0]);
                this.prices.add(Integer.parseInt(buf[1]));
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void on() {
        if (state == STATES.OFF)
            state = STATES.WAIT;
    }

    public void off() {
        if (state == STATES.WAIT)
            state = STATES.OFF;
    }

    public void coin(int money) {
        if (state == STATES.WAIT || state == STATES.ACCEPT) {
            state = STATES.ACCEPT;
            cash += money;
        }

    }

    public String[] getMenu() {
        if (state != STATES.OFF) {
            menu = new String[2 * drinks.size()];
            for (int i = 0; i < drinks.size(); i++) {
                menu[2 * i] = drinks.get(i);
                menu[2 * i + 1] = Integer.toString(prices.get(i));
            }
            return menu;
        }
        return null;
    }

    public STATES getState() {
        return state;
    }

    public int getCash() {
        return cash;
    }

    public int cancel() {
        if (state == STATES.ACCEPT) {
            int returnMoney = getCash();
            cash = 0;
            state = STATES.WAIT;
            return returnMoney;
        }
        return -1;
    }

    public int choice(String nameOfDrink) {
        if (state == STATES.ACCEPT) {
            if (check(nameOfDrink)) {
                cash -= prices.get(drinks.indexOf(nameOfDrink));
                return cook(nameOfDrink);
            }
            return cancel();
        }
        return -5;
    }

    private boolean check(String nameOfDrink) {
        if (cash >= prices.get(drinks.indexOf(nameOfDrink))) {
            state = STATES.CHECK;
            return true;
        }
        return false;
    }

    private int cook(String nameOfDrink) {
        if (state == STATES.CHECK) {
            state = STATES.COOK;
            return finish(nameOfDrink);
        }
        return -10;
    }

    private int finish(String nameOfDrink) {
        if (state == STATES.COOK) {
            int change = getCash();
            cash = 0;
            int timeToWait = 10; //second
            ps.print("Cooking your drink " + nameOfDrink);
            try {
                for (int i = 0; i < timeToWait; i++) {
                    Thread.sleep(1000);
                    ps.print(".");
                }
                ps.println();
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            state = STATES.WAIT;
            return change;
        }
        return -20;
    }
}

