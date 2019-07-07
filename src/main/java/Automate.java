import java.util.ArrayList;

public class Automate {
    static double cash = 0;
    static boolean state = false;
    static ArrayList menu = new ArrayList();
    static ArrayList prices = new ArrayList();

    Automate() {
        this.menu.add("coffe");
        this.menu.add("milk");
        this.menu.add("tea");
        this.prices.add(3);
        this.prices.add(2);
        this.prices.add(1);
    }

    static void on() {
        state = true;
        System.out.println("Automate is ON");
    }

    static void off() {
        state = false;
        System.out.println("Automate is OFF");
    }

    static double coin(double money) {
        cash += money;
        if(money >= 0){
            System.out.println("Add to cash "+money+"$");
        }
        else{
            System.out.println("Remove to cash "+(money*(-1))+"$");
        }
        System.out.println("Balance: " + cash + "$");
        return cash;
    }

    static void getMenu() {
        System.out.println("Menu:");
        for (int i = 0; i < menu.size(); i++) {
            System.out.println("\t"+menu.get(i) + " " + prices.get(i) + "$");
        }
    }

    static boolean getState(){
        return state;
    }

    static void choice(String drink){
        if(getState()){
            if(check(drink)){
                cook(drink);
            }
        }
        else{
            System.out.println("Error: Automate if OFF!");
        }
    }
    static void cook(String drink) {
        if (getState()) {
            System.out.println("Start cooking " + drink);
            double price = Double.parseDouble(prices.get(menu.indexOf(drink)).toString());
            price *= -1;
            coin(price);
            System.out.println(drink + " is ready!");
        } else {
            System.out.println("Error: Automate is OFF");
        }
    }

    static boolean check(String drink) {
        if (Double.parseDouble(prices.get(menu.indexOf(drink)).toString()) <= cash) {
            return true;
        } else {
            System.out.println("Error: not enough money to buy! Add " + (Double.parseDouble(prices.get(menu.indexOf(drink)).toString()) - cash) + "$");
            return false;
        }
    }

    static void finish(){
        System.out.println("return cash "+cash+"$");
        cash=0;
        System.out.println("Service is over.");
    }

    public static void main(String[] args){
        Automate test = new Automate();
        on();
        coin(1);
        getMenu();
        choice("coffe");
        coin(2);
        choice("coffe");
        finish();
    }
}
