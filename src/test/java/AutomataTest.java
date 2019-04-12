import static org.junit.Assert.*;

public class AutomataTest {

    @org.junit.Test
    public void on() {
        Automata testCoffee = new Automata("menu.txt", System.out);
        testCoffee.on();
        assertEquals(STATES.WAIT, testCoffee.getState());
    }

    @org.junit.Test
    public void off() {
        Automata testCoffee = new Automata("menu.txt", System.out);
        testCoffee.on();
        testCoffee.off();
        assertEquals(STATES.OFF, testCoffee.getState());
    }

    @org.junit.Test
    public void coin() {
        Automata testCoffee = new Automata("menu.txt", System.out);
        testCoffee.on();
        testCoffee.coin(5);
        testCoffee.coin(55);
        testCoffee.coin(80);
        assertEquals(STATES.ACCEPT, testCoffee.getState());
        assertEquals(140, testCoffee.getCash());
    }

    @org.junit.Test
    public void getMenu() {
        Automata testCoffee = new Automata("menu.txt", System.out);
        testCoffee.on();
        assertEquals(testCoffee.getMenu()[4], "Mocha");
        assertEquals(testCoffee.getMenu()[5], "80");
    }

    @org.junit.Test
    public void cancel() {
        Automata testCoffee = new Automata("menu.txt", System.out);
        testCoffee.on();
        testCoffee.coin(15);
        assertEquals(15, testCoffee.cancel());
        assertEquals(0, testCoffee.getCash());
    }

    @org.junit.Test
    public void choice() {
        Automata testCoffee = new Automata("menu.txt", System.out);
        testCoffee.on();
        testCoffee.coin(45);
        assertEquals(45, testCoffee.choice("Frappe"));
        assertEquals(STATES.WAIT, testCoffee.getState());
    }

    @org.junit.Test
    public void choice1() {
        Automata testCoffee = new Automata("menu.txt", System.out);
        testCoffee.on();
        testCoffee.coin(70);
        testCoffee.choice("Americano");
        assertEquals(STATES.WAIT, testCoffee.getState());
    }

    @org.junit.Test
    public void choice2() {
        Automata testCoffee = new Automata("menu.txt", System.out);
        testCoffee.on();
        testCoffee.coin(70);
        assertEquals(70, testCoffee.cancel());
    }
}