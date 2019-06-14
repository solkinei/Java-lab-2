public class Main {
    public static void main(String[] args) {
        Automata coffeMachine = new Automata();
        System.out.println(coffeMachine.getState());
        coffeMachine.on();
        System.out.println(coffeMachine.getState());
        System.out.println("Read the menu " + coffeMachine.getMenu());
        System.out.println("Put your money please");
        coffeMachine.coin(9);
        System.out.println("Amount of money: " + coffeMachine.getCash() + "$");
        System.out.println(coffeMachine.getState());
        System.out.println("Choose drink please");
        coffeMachine.check();
        System.out.println(coffeMachine.getState());
        coffeMachine.choice(4);
        System.out.println(coffeMachine.getState());
        coffeMachine.coin(15);
        System.out.println("Amount of money: " + coffeMachine.getCash() + "$");
        coffeMachine.check();
        System.out.println(coffeMachine.getState());
        coffeMachine.choice(0);
        System.out.println(coffeMachine.getState());
        coffeMachine.finish();
        System.out.println("Don't forget your odd money " + coffeMachine.oddMoney(0) + "$");
        System.out.println(coffeMachine.getState());
        coffeMachine.off();
        System.out.println(coffeMachine.getState());

    }
}
