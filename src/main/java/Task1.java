public class Task1 {
    public static void main(String[] args) {
        Automata coffee = new Automata("menu.txt", System.out);
        coffee.getState();
        System.out.println("The state of automata is " + coffee.getState());
        System.out.println();

        coffee.on();
        System.out.println("The state of automata is " + coffee.getState());
        System.out.println();

        coffee.getMenu();

        for (int i = 0; i < coffee.getMenu().length - 1; i += 2) {
            System.out.println(coffee.getMenu()[i] + " costs " + coffee.getMenu()[i + 1] + " rubles;");
        }
        System.out.println();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Deposit money to your account and select a drink");
        System.out.println();

        coffee.coin(50);
        coffee.coin(70);
        System.out.println("The state of automata is " + coffee.getState());
        System.out.println();

        coffee.choice("Cappucino");
        System.out.println("The state of automata is " + coffee.getState());
        System.out.println();

        coffee.off();
        System.out.println("The state of automata is " + coffee.getState());
        System.out.println();
    }
}
