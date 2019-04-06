import org.junit.Test;
import java.util.HashMap;
import static org.junit.Assert.*;

public class AutomataTest {

    @Test
    public void on() {
        Automata testAutomata = new Automata("menu.txt");
        testAutomata.on();
        assertEquals("wrong state after on method", States.WAIT, testAutomata.getState());
        assertEquals("wrong initial cash", 0, testAutomata.getCash());
        assertEquals("wrong initial beverage", null, testAutomata.getBeverage());
    }

    @Test
    public void off() {
        Automata testAutomata = new Automata("menu.txt");
        testAutomata.on();
        testAutomata.off();
        assertEquals("wrong state after off method", States.OFF, testAutomata.getState());
        assertEquals("wrong cash after off method", 0, testAutomata.getCash());
        assertEquals("wrong beverage after off method", null, testAutomata.getBeverage());
    }

    @Test
    public void coin() {
        Automata testAutomata = new Automata("menu.txt");
        testAutomata.on();
        testAutomata.coin(20);
        assertEquals("wrong state after coin method", States.ACCEPT, testAutomata.getState());
        assertEquals("wrong cash after coin method", 20, testAutomata.getCash());
        assertEquals("wrong beverage after coin method", null, testAutomata.getBeverage());
    }

    @Test
    public void coin2() {
        Automata testAutomata = new Automata("menu.txt");
        testAutomata.on();
        testAutomata.coin(20);
        testAutomata.coin(20);
        assertEquals("wrong state after coin method", States.ACCEPT, testAutomata.getState());
        assertEquals("wrong cash after coin method", 40, testAutomata.getCash());
        assertEquals("wrong beverage after coin method", null, testAutomata.getBeverage());
    }

    @Test
    public void returnCash() {
        Automata testAutomata = new Automata("menu.txt");
        testAutomata.on();
        testAutomata.coin(20);
        assertEquals("wrong result of returnCash", 20, testAutomata.returnCash());
        assertEquals("wrong state after returnCash method", States.WAIT, testAutomata.getState());
        assertEquals("wrong cash after returnCash method", 0, testAutomata.getCash());
        assertEquals("wrong beverage after returnCash method", null, testAutomata.getBeverage());
    }

    @Test
    public void returnCash2() {
        Automata testAutomata = new Automata("menu.txt");
        testAutomata.on();
        testAutomata.coin(20);
        testAutomata.choice("latte");
        assertEquals("wrong result of returnCash", 20, testAutomata.returnCash());
        assertEquals("wrong state after returnCash method", States.WAIT, testAutomata.getState());
        assertEquals("wrong cash after returnCash method", 0, testAutomata.getCash());
        assertEquals("wrong beverage after returnCash method", null, testAutomata.getBeverage());
    }

    @Test
    public void choiceAndCheckTrue() {
        Automata testAutomata = new Automata("menu.txt");
        testAutomata.on();
        testAutomata.coin(50);
        testAutomata.choice("latte");
        assertEquals("wrong state after choice and check methods", States.CHECK, testAutomata.getState());
        assertEquals("wrong cash after choice and check methods", 50, testAutomata.getCash());
        assertEquals("wrong beverage after choice and check methods", "latte",
                testAutomata.getBeverage());
    }

    @Test
    public void choiceAndCheckFalse() {
        Automata testAutomata = new Automata("menu.txt");
        testAutomata.on();
        testAutomata.coin(40);
        testAutomata.choice("latte");
        assertEquals("wrong state after choice and check methods", States.ACCEPT, testAutomata.getState());
        assertEquals("wrong cash after choice and check methods", 40, testAutomata.getCash());
        assertEquals("wrong beverage after choice and check methods", null,
                testAutomata.getBeverage());
    }

    @Test
    public void choiceWrongNameOfBeverage() {
        Automata testAutomata = new Automata("menu.txt");
        testAutomata.on();
        testAutomata.coin(50);
        testAutomata.choice("123");
        assertEquals("wrong state after choice method", States.ACCEPT, testAutomata.getState());
        assertEquals("wrong cash after choice method", 50, testAutomata.getCash());
        assertEquals("wrong beverage after choice method", null, testAutomata.getBeverage());
    }

    @Test
    public void choiceAndCheckAddMoney() {
        Automata testAutomata = new Automata("menu.txt");
        testAutomata.on();
        testAutomata.coin(40);
        testAutomata.choice("latte");
        testAutomata.coin(50);
        testAutomata.choice("latte");
        assertEquals("wrong state after choice and check methods", States.CHECK,
                testAutomata.getState());
        assertEquals("wrong cash after choice and check methods", 90,
                testAutomata.getCash());
        assertEquals("wrong beverage after choice and check methods", "latte",
                testAutomata.getBeverage());
    }

    @Test
    public void choiceAndCheckReturnMoney() {
        Automata testAutomata = new Automata("menu.txt");
        testAutomata.on();
        testAutomata.coin(40);
        testAutomata.choice("latte");
        assertEquals("wrong result of returnCash", 40, testAutomata.returnCash());
        assertEquals("wrong state after choice, check and returnCash methods",
                States.WAIT, testAutomata.getState());
        assertEquals("wrong cash after choice, check and returnCash methods",
                0, testAutomata.getCash());
        assertEquals("wrong beverage after choice, check and returnCash  methods",
                null, testAutomata.getBeverage());
    }

    @Test
    public void cook() {
        Automata testAutomata = new Automata("menu.txt");
        testAutomata.on();
        testAutomata.coin(50);
        testAutomata.choice("latte");
        Pair testPair = testAutomata.cook();
        Integer expected = 0;
        assertEquals("wrong change", expected, testPair.getSum());
        assertEquals("wrong beverage", "latte", testPair.getBeverage());
        assertEquals("wrong state after cook and finish methods",
                States.WAIT, testAutomata.getState());
        assertEquals("wrong cash after cook and finish methods",
                0, testAutomata.getCash());
        assertEquals("wrong beverage after cook and finish methods",
                null, testAutomata.getBeverage());
    }

    @Test
    public void cookWithExcessOfMoney() {
        Automata testAutomata = new Automata("menu.txt");
        testAutomata.on();
        testAutomata.coin(55);
        testAutomata.choice("latte");
        Pair testPair = testAutomata.cook();
        Integer expected = 5;
        assertEquals("wrong change", expected, testPair.getSum());
        assertEquals("wrong beverage", "latte", testPair.getBeverage());
        assertEquals("wrong state after cook and finish methods",
                States.WAIT, testAutomata.getState());
        assertEquals("wrong cash after cook and finish methods",
                0, testAutomata.getCash());
        assertEquals("wrong beverage after cook and finish methods",
                null, testAutomata.getBeverage());
    }

    @Test
    public void cookInWrongOrder() {
        Automata testAutomata = new Automata("menu.txt");
        testAutomata.on();
        Pair testPair = testAutomata.cook();
        assertEquals("method cook should return null after being called in wrong order",
                null, testPair);
        assertEquals("wrong state after cook called in wrong order",
                States.WAIT, testAutomata.getState());
        assertEquals("wrong cash after cook called in wrong order",
                0, testAutomata.getCash());
        assertEquals("wrong beverage after cook called in wrong order",
                null, testAutomata.getBeverage());
    }

    @Test
    public void cookInWrongOrder2() {
        Automata testAutomata = new Automata("menu.txt");
        testAutomata.on();
        testAutomata.coin(55);
        Pair testPair = testAutomata.cook();
        assertEquals("method cook should return null after being called in wrong order",
                null, testPair);
        assertEquals("wrong state after cook called in wrong order",
                States.ACCEPT, testAutomata.getState());
        assertEquals("wrong cash after cook called in wrong order",
                55, testAutomata.getCash());
        assertEquals("wrong beverage after cook called in wrong order",
                null, testAutomata.getBeverage());
    }

    @Test
    public void getMenu() {
        Automata testAutomata = new Automata("menu.txt");
        HashMap<String, Integer> expected = new HashMap<String, Integer>();
        expected.put("tea", 35);
        expected.put("latte", 50);
        expected.put("capuccino", 45);
        expected.put("water", 20);
        expected.put("mochaccino", 55);
        assertEquals("method getMenu does not work correctly", expected, testAutomata.getMenu());
    }
}