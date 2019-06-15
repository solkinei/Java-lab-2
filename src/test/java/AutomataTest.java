import org.junit.Test;

import static org.junit.Assert.*;

public class AutomataTest {

    @Test
    public void on() {
        Automata automata = new Automata();
        automata.on();
        assertEquals(STATE.WAIT, automata.getState());
    }

    @Test
    public void off() {
        Automata automata = new Automata();
        automata.on();
        automata.off();
        assertEquals(STATE.OFF, automata.getState());
    }

    @Test
    public void coin() {
        Automata automata = new Automata();
        automata.on();
        automata.coin(50);
        assertEquals(50, automata.getCash());
    }

    @Test
    public void choice() {
        Automata automata = new Automata();
        automata.on();
        automata.coin(40);
        assertEquals(40, automata.getCash());
        automata.choice("Какава");
        assertEquals(0, automata.getCash());
        automata.coin(50);
        automata.choice("Чай чёрный");
        assertEquals(20, automata.getCash());
    }

    @Test
    public void cancel() {
        Automata automata = new Automata();
        automata.on();
        automata.coin(50);
        assertEquals(50, automata.getCash());
        automata.cancel();
        assertEquals(0, automata.getCash());
    }
}