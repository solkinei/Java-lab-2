import java.util.ArrayList;

enum STATES {OFF, WAIT, ACCEPT, CHECK, COOK}

class Automata {
    private int cash;
    private STATES state;
    private ArrayList<String> menu; // = new ArrayList<String>();
    private ArrayList<Integer> prices; // = new ArrayList<Integer>();

    public Automata() {
        this.cash = 0;
        this.state = STATES.OFF;
        this.menu = new ArrayList<String>();
        menu.add("tea");
        menu.add("coffee");
        menu.add("water");
        menu.add("milk");
        this.prices = new ArrayList<Integer>();
        prices.add(30);
        prices.add(40);
        prices.add(10);
        prices.add(25);
    }

    public void on(){
        if(this.state == STATES.OFF){
            this.state = STATES.WAIT;
        }
    }

    public void off(){
        if (this.state == STATES.WAIT){
            this.state = STATES.OFF;
        }
    }

    public void coin(int coins){
        if(this.state == STATES.WAIT || this.state == STATES.ACCEPT){
            this.state = STATES.ACCEPT;
            this.cash+=coins;
        }
    }


    public ArrayList[] getMenu() {
        ArrayList[] itemAndPrice = {menu, prices};
        return itemAndPrice;
    }

    public STATES getState(){
        return this.state;
    }

    public int choice(String str){
        if(this.state == STATES.ACCEPT ){
            this.state = STATES.CHECK;
            int ch = menu.indexOf(str) ;
            return ch;
        }
        else
            return -1;
    }

    public boolean check(int index){
        if(this.state == STATES.CHECK && this.cash>=prices.get(index)) {
            return true;
        }
        else {
            return false;
        }
    }

    public void cancel(){
        if(this.state == STATES.ACCEPT || this.state == STATES.CHECK){
            this.state = STATES.WAIT;
        }
    }

    public boolean cook(int index){
        if(this.state == STATES.CHECK && check(index)) {
            this.state = STATES.COOK;
            return true;
        }
        else
            return false;
    }

    public void finish(boolean bool){
        if(this.state == STATES.COOK && bool){
            this.state = STATES.WAIT;
        }
    }

    public int getCash(){
        return this.cash;
    }
}

public class Lab2 {
    public static void main(String [] args){
        Automata automata = new Automata();
        int money = 40;
        String drink = "coffee";
        automata.on();
        automata.coin(money);
        automata.getMenu();
        int indexOfDrink = automata.choice(drink);
        automata.check(indexOfDrink);
        boolean cookIsDone = automata.cook(indexOfDrink);
        automata.finish(cookIsDone);
        automata.off();
    }

}