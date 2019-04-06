import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.util.Map;

public class Automata {
    private int cash;
    private HashMap<String, Integer> menu;
    private States state;
    private String beverage; //for chosen beverage

    //constructor for creating the new Automata
    public Automata(String nameOfFile) {
        this.cash = 0; //initial amount of cash for the new Automata
        this.state = States.OFF; //initial state for the new Automata
        this.menu = new HashMap<String, Integer>();

        //creating menu
        BufferedReader breader = null; //buffered stream for reading strings of menu from the file
        String tmpString; //temporary string for reading from the file in format "beverage=price"
        try {
            breader = new BufferedReader(new FileReader(nameOfFile));
            while ((tmpString = breader.readLine()) != null) {
                String[] dividedStrings = tmpString.split("=", 2);
                this.menu.put(dividedStrings[0],(Integer.parseInt(dividedStrings[1]))); //put string to the menu
            }
            breader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.beverage = null;
    }

    //getters and setters
    public int getCash() {
        return cash;
    }

    //current state for user
    public States getState() {
        return state;
    }

    public HashMap<String, Integer> getMenu() {
        return menu;
    }

    public String getBeverage() {
        return beverage;
    }

    private void setCash(int cash) {
        if ((States.COOK == getState()) || (States.ACCEPT == getState())) {
            if (cash >= 0) {
                this.cash = cash;
            }
        }
    }

    private void setState(States state) {
        this.state = state;
    }


    private void setBeverage(String beverage) {
        if ((States.CHECK == getState()) || (States.COOK == getState())){
            this.beverage = beverage;
        }
    }

    //switching-on
    public void on() {
        //checking the current state
        if (States.OFF == getState()) {
            setState(States.WAIT);
        }
    }

    //switching off
    public void off() {
        //checking the current state
        if (States.WAIT == getState()) {
            setState(States.OFF);
        }
    }

    //getting coins
    public int coin(int cash) {
       if ((States.WAIT == getState()) || (States.ACCEPT == getState())) {
           setState(States.ACCEPT);
           setCash(getCash() + cash);
       }
        return getCash();
    }

    //cancelling the current session and returning money
    public int returnCash() {
        if (States.ACCEPT == getState()) {
            int cash = getCash();
            setCash(0);
            setState(States.WAIT);
            return cash;
        }
        else {
            return 0;
        }
    }

    //user's choice of beverage
    public void choice(String beverage) {
        if (States.ACCEPT != getState()) {
            return;
        }
        //checking if menu contains this beverage
        if (!getMenu().containsKey(beverage)) {
            return;
        }
        setState(States.CHECK);
        if (!check(beverage)) {
            lackOfCash();
        }
    }

    private void lackOfCash(){
        setState(States.ACCEPT);
    }

    //checking the amount of money
    private boolean check(String beverage) {
        if (getCash() >= getMenu().get(beverage)) {
            setBeverage(beverage);
            return true;
        }
        return false;
    }

    //delay as imitation of cooking process, returning the name of product and excess cash
    public Pair cook() {
        if (States.CHECK != getState()) {
            return null;
        }
        setState(States.COOK);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Integer cashToReturn = getCash() - getMenu().get(beverage);
        Pair result = new Pair(cashToReturn, beverage);
        finish();
        return result;
    }

        //finishing
    private void finish() {
        if (States.COOK == getState()) {
            setBeverage(null);
            setCash(0);
            setState(States.WAIT);
            return;
        }
    }
/*
    public void printMenu(){
        for (Map.Entry<String, Integer> pair : getMenu().entrySet()) {
            String key = pair.getKey();
            Integer value = pair.getValue();
            System.out.println(key + " = " + value);
        }
    }
    */

}


