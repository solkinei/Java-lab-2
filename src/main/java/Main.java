public class Main {
    public static void main(String args[]) {
        Automata Burda = new Automata();
        Burda.choice(2);

        Burda.on();
        Burda.coin(5);
        Burda.choice(5);
        Burda.coin(100);
        Burda.off();
        Burda.choice(5);
        Burda.on();
        Burda.off();


    }
}
