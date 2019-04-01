import org.junit.Test;

import static org.junit.Assert.*;

public class AutomataTest {

    @Test
    public void on() {
        Automata onTest = new Automata();
        onTest.on();
        assertEquals(STATES.WAIT, onTest.printState());
    }

    @Test
    public void off() {
        Automata offTest = new Automata();
        offTest.off();
        assertEquals(STATES.OFF, offTest.printState());
        offTest.on();
        assertEquals(STATES.WAIT, offTest.printState());
        offTest.off();
        assertEquals(STATES.OFF, offTest.printState());
    }

    @Test
    public void coin() {
        Automata coinTest = new Automata();
        coinTest.on();
        assertEquals(0, coinTest.cash);
        coinTest.coin(5);
        assertEquals(5, coinTest.cash);
        assertEquals(STATES.ACCEPT, coinTest.state);
    }


    @Test
    public void choice() {
        Automata choiceTest = new Automata();
        choiceTest.on();
        choiceTest.coin(1);
        choiceTest.choice(2);
        assertEquals(STATES.WAIT, choiceTest.printState());
        assertEquals(false, choiceTest.check(5));
        choiceTest.coin(999);
        assertEquals(true, choiceTest.check(5));
        choiceTest.choice(5);
        assertEquals(STATES.WAIT, choiceTest.printState());
    }


}