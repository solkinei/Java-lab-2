import org.junit.Test;

import static org.junit.Assert.*;

public class AutomataTest {

    @org.junit.Test
    public void coin() {
        Automata one = new Automata();
        assertEquals (0, one.coin(12));
        assertEquals(Automata.STATES.OFF,one.getStates());
        one.on_off();
        one.coin(16);
        assertEquals(Automata.STATES.ACCEPT,one.getStates());
        assertEquals (31, one.coin(15));
        assertEquals(Automata.STATES.ACCEPT,one.getStates());
        }

    @Test
    public void on_off() {
        Automata one = new Automata();
        assertEquals(Automata.STATES.OFF,one.getStates());
        one.on_off();
        assertEquals(Automata.STATES.WAIT,one.getStates());
        one.on_off();
        assertEquals(Automata.STATES.OFF,one.getStates());
    }

    @Test
    public void choice() {
        Automata one = new Automata();
        one.on_off();
        one.coin(31);
        one.choice("milk");
        assertEquals(Automata.STATES.ACCEPT,one.getStates());
        one.choice("tea");
        assertEquals(Automata.STATES.CHECK,one.getStates());
    }

    @Test
    public void cook() {
        Automata one = new Automata();
        one.on_off();
        one.coin(31);
        one.choice("tea");
        one.cook();
        assertEquals(1,one.getCash());
    }

    @Test
    public void cancel() {
        Automata one = new Automata();
        one.on_off();
        one.coin(31);
        one.choice("tea");
        one.cancel();
        assertEquals(0,one.getCash());
        assertEquals(Automata.STATES.WAIT,one.getStates());
    }
}