import java.util.Map;

public class Main {
    public static void main(String[] args) {
        //create new Automata
        Automata myAutomata = new Automata("menu.txt");
        System.out.println("New automata created");
        System.out.println("State " + myAutomata.getState());
        System.out.println();

        //switch on
        myAutomata.on();
        System.out.println("Testing on method");
        System.out.println("State " + myAutomata.getState());
        System.out.println();

        //put 20 "coins"
        myAutomata.coin(20);
        System.out.println("Testing coin method");
        System.out.println("The automata got 20 coins");
        System.out.println("State " + myAutomata.getState());
        System.out.println("Cash " + myAutomata.getCash());
        System.out.println();

        //put 5 additional "coins"
        myAutomata.coin(5);
        System.out.println("The automata got 5 additional coins");
        System.out.println("State " + myAutomata.getState());
        System.out.println("Cash " + myAutomata.getCash());
        System.out.println();

        //print menu
        System.out.println("Testing getMenu method");
        for (Map.Entry<String, Integer> pair : myAutomata.getMenu().entrySet()) {
            String key = pair.getKey();
            Integer value = pair.getValue();
            System.out.println(key + " = " + value);
        }
        System.out.println();

        //choose some product not from the menu
        System.out.println("Teating choice method");
        System.out.println("We choose some product not from the menu");
        Pair myPair = myAutomata.choice("123");
        System.out.println(myPair.getMessage());
        System.out.println("State " + myAutomata.getState());
        System.out.println("Cash " + myAutomata.getCash());
        System.out.println("Beverage " + myAutomata.getBeverage());
        System.out.println();

        //choose some product with lack of money
        System.out.println("Testing choice method");
        System.out.println("We choose some product with lack of money");
        myPair = myAutomata.choice("latte");
        System.out.println( myPair.getMessage());
        System.out.println("Result sum = " + myPair.getSum());
        System.out.println("State " + myAutomata.getState());
        System.out.println("Cash " + myAutomata.getCash());
        System.out.println("Beverage " + myAutomata.getBeverage());
        System.out.println();

        //put 30 additional "coins"
        myAutomata.coin(30);
        System.out.println("The automata got 30 additional coins");
        System.out.println("Cash " + myAutomata.getCash());
        System.out.println();

        //choose some product with excess of money
        System.out.println("Testing choice method");
        System.out.println("We choose product with excess of money");
        myPair = myAutomata.choice("latte");
        System.out.println(myPair.getMessage());
        System.out.println("Result sum = " + myPair.getSum());
        System.out.println("State " + myAutomata.getState());
        System.out.println();


        //return cash
        System.out.println("Testing returnCash method");
        myAutomata.coin(20);
        System.out.println("The automata got 20 coins");
        System.out.println("Take your " + myAutomata.returnCash() + " coins");
        System.out.println("State " + myAutomata.getState());
        System.out.println("Cash " + myAutomata.getCash());
        System.out.println();

        //checking method off
        System.out.println("Testing method off");
        myAutomata.off();
        System.out.println("State " + myAutomata.getState());
        System.out.println("Cash " + myAutomata.getCash());
        System.out.println("Beverage " + myAutomata.getBeverage());
        System.out.println("Message in result field " + myAutomata.getResult().getMessage());
        System.out.println("Sum in result field " + myAutomata.getResult().getSum());

        //myAutomata.printMenu();
    }



}
