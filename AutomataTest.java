import static org.junit.Assert.*;

public class AutomataTest {

    @org.junit.Test
    public void testOn() {
        Automata a = new Automata();
        a.on();
        assertEquals(STATES.WAIT, a.getState());
    }

    @org.junit.Test
    public void testOff() {
        Automata a = new Automata();
        a.off();
        assertEquals(STATES.OFF, a.getState());
    }

    @org.junit.Test
    public void testCoin() {
        Automata a = new Automata();
        a.on();
        a.coin(20);
        a.coin(15);
        a.coin(5);
        assertEquals(40, a.getCash());
        assertEquals(STATES.ACCEPT, a.getState());
    }

    @org.junit.Test
    public void testChoice() {
        Automata a = new Automata();
        a.on();
        a.coin(100);
        assertEquals(2, a.choice("water"));
        assertEquals(STATES.CHECK, a.getState());
    }
}