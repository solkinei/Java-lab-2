import org.junit.Test;

import static org.junit.Assert.*;

public class AutomataTest {
Automata auto = new Automata();
    @org.junit.Test
    public void on() {
        assertEquals("WAIT",auto.on());
    }

    @org.junit.Test
    public void off() {
        assertEquals("OFF",auto.off());
    }

    @org.junit.Test
    public void getState() {
        auto.on();
        assertEquals("WAIT",auto.getState());
        auto.off();
        assertEquals("OFF",auto.getState());
    }

    @Test
    public void getCash() {
        assertEquals(0,auto.getCash());
    }

    @Test
    public void getDrink() {
        assertEquals("NOTHING",auto.getDrink());
    }

    @org.junit.Test
    public void coin() {
        assertEquals(50,auto.coin(50));
    }


    @org.junit.Test
    public void choice() {
        assertEquals("TEE",auto.choice("TEE"));
    }

    @org.junit.Test
    public void choice1() {
        assertEquals("NOTHING",auto.choice());
    }

    @org.junit.Test
    public void getPriceForChoise() {
        assertEquals(10,auto.getPriceForChoise("COFFEE"));
    }

    @org.junit.Test
    public void check() {
        assertEquals(true,auto.check());
    }

    @org.junit.Test
    public void cook() {
        auto.on();
        assertEquals("WAIT",auto.cook());
    }

    @org.junit.Test
    public void cancel() {
        assertEquals("WAIT",auto.cancel());
    }

    @org.junit.Test
    public void finish() {
        assertEquals("OFF",auto.finish());
    }



}