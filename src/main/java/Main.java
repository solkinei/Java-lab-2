public class Main {
    public static void main(String[] args) {
        Automata automata = new CoffeeAutomata();
        automata.on();
        automata.coin(10);
        automata.coin(40);
        automata.choice(3);
    }
}
