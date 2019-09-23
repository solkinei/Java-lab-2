import org.junit.Test;

import static org.junit.Assert.*;

public class AutomataTest {

    @Test
    public void on() {
        Automata barista=new Automata();
        barista.on();
        assertEquals(Automata.STATES.WAIT,barista.getState());
    }

    @Test
    public void off() {
        Automata barista=new Automata();
        barista.off();
        assertEquals(Automata.STATES.OFF,barista.getState());
    }

    @Test
    public void coin() {
        Automata barista=new Automata();
        barista.on();
        barista.coin(5);
        barista.coin(40);
        barista.coin(75);
        assertEquals(Automata.STATES.ACCEPT,barista.getState());
        assertEquals(120,barista.getCash());
    }

    @Test
    public void setMenu() {
        Automata barista = new Automata();
        barista.on();
        barista.setMenu("Tea", 10);
        assertEquals("{Tea=10}",barista.getMenu().toString());
        barista.setMenu("Coffee", 15);
        assertEquals("{Tea=10, Coffee=15}",barista.getMenu().toString());
        barista.setMenu("Cocoa", 55);
        assertEquals("{Tea=10, Coffee=15, Cocoa=55}",barista.getMenu().toString());
    }

    @Test
    public void choice() {
        Automata barista=new Automata();
        barista.on();
        barista.coin(40);
        barista.setMenu("Tea", 10);
        assertEquals(40,barista.choice("Tea"));
        barista.setMenu("Cocoa", 55);
        assertEquals(Automata.STATES.CHECK,barista.getState());
    }

    @Test
    public void cancel() {
        Automata barista = new Automata();
        barista.on();
        barista.coin(40);
        barista.setMenu("Tea", 10);
        barista.choice("Tea");
        barista.cancel();
        assertEquals(0,barista.getCash());
        assertEquals(Automata.STATES.WAIT,barista.getState());
    }

    @Test
    public void cook() {
        Automata barista = new Automata();
        barista.on();
        barista.coin(40);

        barista.setMenu("Tea", 10);
        barista.choice("Tea");
        barista.cook("Tea");
        assertEquals(30,barista.getCash());
        assertEquals(Automata.STATES.COOK,barista.getState());
    }
}