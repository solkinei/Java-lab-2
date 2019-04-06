public class AutomataMain {

    public static void main(String [] args){
        Automata shaytanMachine = new Automata();
        shaytanMachine.on();
        shaytanMachine.getState();
        shaytanMachine.choice("Чай чёрный");
        shaytanMachine.coin(50);
        shaytanMachine.getState();
        shaytanMachine.off();
    }
}
