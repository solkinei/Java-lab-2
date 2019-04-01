import org.junit.Test;

import static org.junit.Assert.*;

public class AutomataTest {

    @Test
    public void on() {
        Automata automata = new Automata();
        automata.on();
        assertEquals(States.WAIT,automata.getState());
    }

    @Test
    public void off() {
        Automata automata = new Automata();
        automata.off();
        assertEquals(States.OFF,automata.getState());
    }

    @Test
    public void finish() {
        Automata automata = new Automata();
        automata.finish();
        assertEquals(States.WAIT,automata.getState());
    }

    @Test
    public void coin() {
        Automata automata = new Automata();
        automata.coin(10);
        assertEquals(10,automata.getCash());
        assertEquals(States.ACCEPT,automata.getState());
    }

    @Test
    public void cook() {
        Automata automata = new Automata();
        automata.cook();
        assertEquals(States.COOK,automata.getState());
    }

    @Test
    public void check() {
        Automata automata = new Automata();
        automata.check();
        assertEquals(States.CHECK,automata.getState());
    }

    @Test
    public void cancel() {
        Automata automata = new Automata();
        automata.cancel();
        assertEquals(States.WAIT,automata.getState());
    }

    @Test
    public void getCash() {
        Automata automata = new Automata();
        automata.setCash(10);
        assertEquals(10,automata.getCash());
        automata.setCash(17);
        assertEquals(27,automata.getCash());
    }

    @Test
    public void getState() {
        Automata automata = new Automata();
        assertEquals(States.OFF,automata.getState());
    }

    @Test
    public void setCash() {
        Automata automata = new Automata();
        automata.setCash(70);
        assertEquals(70,automata.getCash());
    }

    @Test
    public void setState() {
        Automata automata = new Automata();
        automata.setState(States.CHECK);
        assertEquals(States.CHECK,automata.getState());
    }

    @Test
    public void choice() {
        Automata automata = new Automata();
        automata.choice(10);
        assertEquals(States.WAIT,automata.getState());
    }
}