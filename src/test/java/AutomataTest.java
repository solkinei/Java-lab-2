import org.junit.Test;

import static org.junit.Assert.*;

public class AutomataTest {
    Automata CoffeeMashine = new Automata();

    @Test
    public void getState() {
        CoffeeMashine.on();
        assertEquals(State.WAIT, CoffeeMashine.getState());
    }

    @Test
    public void getMenu() {
        assertEquals("1. coffee", CoffeeMashine.getMenu()[0].get(0));
    }

    @Test
    public void coin() {
        CoffeeMashine.on();
        CoffeeMashine.coin(5);
        CoffeeMashine.coin(10);
        assertEquals(State.ACCEPT, CoffeeMashine.getState());
        assertEquals(15,CoffeeMashine.getCash(),0.01 );
    }

    @Test
    public void cancel() {
        CoffeeMashine.on();
        CoffeeMashine.coin(50);
        assertEquals(50,CoffeeMashine.cancel(),0.01 );
        assertEquals(State.WAIT, CoffeeMashine.getState());
    }

    @Test
    public void choice() {
        CoffeeMashine.on();
        CoffeeMashine.coin(60);
        assertEquals(10,CoffeeMashine.choice(0),0.01 );
        assertEquals(State.WAIT, CoffeeMashine.getState());
    }

    @Test
    public void choice_1() {
        CoffeeMashine.on();
        CoffeeMashine.coin(40);
        assertEquals(40,CoffeeMashine.choice(0),0.01 );
        assertEquals(State.WAIT, CoffeeMashine.getState());
    }
    @Test

    public void choice_2() {
        CoffeeMashine.on();
        CoffeeMashine.coin(40);
        assertEquals(40,CoffeeMashine.choice(10),0.01 );
        assertEquals(State.WAIT, CoffeeMashine.getState());
    }

}