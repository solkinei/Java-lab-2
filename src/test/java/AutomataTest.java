import org.junit.Test;

import java.util.Map;
import java.util.HashMap;

import static org.junit.Assert.*;

public class AutomataTest {

    @Test
    public void getCash() {
        Automata A = new Automata(0);
        assertEquals(0, A.getCash(), 0.005);
        A.on();
        A.coin(15);
        assertEquals(15, A.getCash(), 0.005);
        A.addToMenu("Stolichnaya", 10.4F);
        A.choice("Stolichnaya");
        A.cook("Stolichnaya");
        assertEquals(4.60F, A.getCash(), 0.005);
    }

    @Test
    public void on() {
        Automata A = new Automata(0);
        A.on();
        assertEquals(Automata.State.WAIT, A.getState());
    }

    @Test
    public void off() {
        Automata A = new Automata(0);
        A.on();
        A.off();
        assertEquals(Automata.State.OFF, A.getState());
        A.on();
        A.coin(5);
        A.off();
        assertNotEquals(Automata.State.OFF, A.getState());
    }

    @Test
    public void coin() {
        Automata A = new Automata(10);
        assertEquals(10F, A.getCash(), 0.005);
        A.on();
        A.coin(15);
        assertEquals(25, A.getCash(), 0.005);
        assertEquals(Automata.State.ACCEPT, A.getState());
        assertTrue(A.coin(5));
        A.cancel();
        A.off();
        assertTrue(!A.coin(5));

    }

    @Test
    public void addToMenu() {
        Automata A = new Automata(0);
        A.on();
        A.coin(15);
        A.addToMenu("Stolichnaya", 10.4F);
        assertEquals(10.4F, A.choice("Stolichnaya"), 0.005);
        A.cook("Stolichnaya");
        A.finish();
        A.coin(10);
        assertEquals(-1, A.choice("Milk"), 0.005);
        A.cancel();
        assertEquals(0, A.choice("Stolichnaya"), 0.005);
    }

    @Test
    public void printMenu() {
        Automata A = new Automata(0);
        A.on();
        A.coin(15);
        A.addToMenu("Stolichnaya", 10.4F);
        A.addToMenu("Zhiguly", 0.60F);
        Map<String, Float> test = new HashMap();
        test.put("Stolichnaya", 10.4F);
        test.put("Zhiguly", 0.60F);
        assertTrue(test.equals(A.printMenu()));

    }

    @Test
    public void printState() {
    }

    @Test
    public void choice() {
        Automata A = new Automata(0);
        A.on();
        A.coin(15);
        A.addToMenu("Stolichnaya", 10.4F);
        assertEquals(10.4F, A.choice("Stolichnaya"), 0.005);
        A.cancel();
        A.coin(15);
        assertEquals(-1, A.choice("Milk"), 0.005);
        A.cancel();
        assertEquals(0, A.choice("Stolichnaya"), 0.005);
    }

    @Test
    public void check() {
        Automata A = new Automata(0);
        A.on();
        A.coin(18);
        A.addToMenu("Stolichnaya", 10.4F);
        A.choice("Stolichnaya");
        assertTrue(A.check(15));
        assertTrue(!A.check(20));
    }

    @Test
    public void cancel() {
        Automata A = new Automata(0);
        assertNotEquals(Automata.State.WAIT, A.getState());
        A.on();
        A.coin(18);
        A.cancel();
        assertEquals(Automata.State.WAIT, A.getState());
    }

    @Test
    public void cook() {
        Automata A = new Automata(0);
        A.on();
        A.coin(15);
        A.addToMenu("Stolichnaya", 10.4F);
        A.choice("Stolichnaya");
        A.cook("Stolichnaya");
        assertEquals(4.60F, A.getCash(), 0.005);
        assertEquals(Automata.State.COOK, A.getState());
    }

    @Test
    public void finish() {
        Automata A = new Automata(0);
        A.on();
        A.coin(15);
        A.finish();
        assertNotEquals(Automata.State.WAIT, A.getState());
        A.addToMenu("Stolichnaya", 10.4F);
        A.choice("Stolichnaya");
        A.cook("Stolichnaya");
        A.finish();
        assertEquals(Automata.State.WAIT, A.getState());
    }

}