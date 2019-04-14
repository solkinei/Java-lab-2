public class Automata {
    public enum STATE {
        OFF, WAIT, ACCEPT, CHECK, COOK
    }

    public enum DRINK {
        TEE, COFFEE, NOTHING
    }

    private STATE state = STATE.OFF;
    private int cash = 0;
    private int price = 0;
    private String drink = DRINK.NOTHING.toString();

    public String on() {
        if (state == STATE.OFF)
            state = STATE.WAIT;
        return state.toString();
    }

    public String off() {
        state = STATE.OFF;
        return state.toString();
    }

    public String getState() {
      //  System.out.println("State = " + state);
     //   System.out.println("Cash = " + cash);
      //  System.out.println("Choise = " + drink);
        return state.toString();
    }

    public int getCash() {
        //  System.out.println("State = " + state);
        //   System.out.println("Cash = " + cash);
        //  System.out.println("Choise = " + drink);
        return cash;
    }

    public String getDrink() {
        //  System.out.println("State = " + state);
        //   System.out.println("Cash = " + cash);
        //  System.out.println("Choise = " + drink);
        return drink;
    }

    public int coin(int payment) {
        cash += payment;
       // check();
        return cash;
    }

    public void getMenu() {
        System.out.println(DRINK.TEE + " cost " + 5);
        System.out.println(DRINK.COFFEE + " cost " + 10);
    }

    public String choice() {
        String drink = DRINK.NOTHING.toString();
        choice(drink);
        return drink;
    }

    public String choice(String drink) {
        price = getPriceForChoise(drink);
        this.drink = drink;
        return drink;
    }

    public int getPriceForChoise(String drink) {
        int priceForChoise;
        if (drink == DRINK.TEE.toString()) {
            priceForChoise = 5;
        } else if (drink == DRINK.COFFEE.toString()) {
            priceForChoise = 10;
        } else if (drink == DRINK.NOTHING.toString()) {
            priceForChoise = 0;
        } else
            priceForChoise = 100000000;

        return priceForChoise;
    }

    public boolean check(){
        boolean check = false;
        state = STATE.CHECK;
        if (cash >= getPriceForChoise(drink)) {
            state = STATE.COOK;
            cook();
            check = true;
            cash -= price;
        } else {
            state = STATE.WAIT;
            System.out.println("Need more money");
        }
        return check;
    }

    public String cook() {
        if (state == STATE.COOK) {
            System.out.println("Start cook");
         //   Thread.sleep(5000);
            System.out.println("Finish cook");
            state = STATE.WAIT;
            drink = DRINK.NOTHING.toString();
        }
        return state.toString();
    }

    public String cancel() {
        System.out.println("Money " + cash);
        choice();
        state= STATE.WAIT;
        return state.toString();
    }

    public String finish() {
        state = STATE.OFF;
        return state.toString();
    }

    public static void main(String[] args) throws InterruptedException {

        Automata auto = new Automata();

        auto.on();
        auto.getState();
        auto.choice("TEE");
        auto.getState();
        auto.coin(5);
        auto.getState();

    }
}
