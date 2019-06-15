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
    public void returnCashAfterCoin() {
        Automata testAutomata = new Automata("menu.txt");
        testAutomata.on();
        testAutomata.coin(20);
        assertEquals("wrong result of returnCash", 20, testAutomata.returnCash());
        assertEquals("wrong state after returnCash method", States.WAIT, testAutomata.getState());
        assertEquals("wrong cash after returnCash method", 0, testAutomata.getCash());
        assertEquals("wrong beverage after returnCash method", null, testAutomata.getBeverage());
    }

    @Test
    public void returnCashAfterLackOfMoney() {
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
    public void choiceEqualSumRightBeverage() {
        Automata testAutomata = new Automata("menu.txt");
        testAutomata.on();
        testAutomata.coin(50);
        Pair testPair = testAutomata.choice("latte");
        Integer expected = 0;
        assertEquals("wrong change", expected, testPair.getSum());
        assertEquals("wrong message", "Take your latte and your change.",
                testPair.getMessage());
        assertEquals("wrong state after choice method", States.WAIT,
                testAutomata.getState());
        assertEquals("wrong cash after choice method",0,
                testAutomata.getCash());
        assertEquals("wrong beverage after choice method", null,
                testAutomata.getBeverage());
    }

    @Test
    public void choiceLackOfMoneyRightBeverage() {
        Automata testAutomata = new Automata("menu.txt");
        testAutomata.on();
        testAutomata.coin(40);
        Pair testPair = testAutomata.choice("latte");
        Integer expected = -10;
        assertEquals("wrong change", expected, testPair.getSum());
        assertEquals("wrong message", "Lack of money! Add more coins!",
                testPair.getMessage());
        assertEquals("wrong state after choice method", States.ACCEPT,
                testAutomata.getState());
        assertEquals("wrong cash after choice method",40,
                testAutomata.getCash());
        assertEquals("wrong beverage after choice method", null,
                testAutomata.getBeverage());
    }

    @Test
    public void choiceWrongBeverage() {
        Automata testAutomata = new Automata("menu.txt");
        testAutomata.on();
        testAutomata.coin(50);
        Pair testPair = testAutomata.choice("123");
        Integer expected = 0;
        assertEquals("wrong change", expected, testPair.getSum());
        assertEquals("wrong message", "There is no such beverage in menu! Choose again!",
                testPair.getMessage());
        assertEquals("wrong state after choice method", States.ACCEPT, testAutomata.getState());
        assertEquals("wrong cash after choice method", 50, testAutomata.getCash());
        assertEquals("wrong beverage after choice method", null, testAutomata.getBeverage());
    }

    @Test
    public void choiceAddExcessMoney() {
        Automata testAutomata = new Automata("menu.txt");
        testAutomata.on();
        testAutomata.coin(40);
        testAutomata.choice("latte");
        testAutomata.coin(50);
        Pair testPair = testAutomata.choice("latte");
        Integer expected = 40;
        assertEquals("wrong change", expected, testPair.getSum());
        assertEquals("wrong message", "Take your latte and your change.",
                testPair.getMessage());
        assertEquals("wrong state after choice choice method", States.WAIT,
                testAutomata.getState());
        assertEquals("wrong cash after choice choice method", 0,
                testAutomata.getCash());
        assertEquals("wrong beverage after choice method", null,
                testAutomata.getBeverage());
    }

    @Test
    public void returnMoneyInWrongPlace() {
        Automata testAutomata = new Automata("menu.txt");
        testAutomata.on();
        testAutomata.coin(60);
        testAutomata.choice("latte");
        assertEquals("wrong result of returnCash", 0, testAutomata.returnCash());
        assertEquals("wrong state after choice and returnCash methods",
                States.WAIT, testAutomata.getState());
        assertEquals("wrong cash after choice and returnCash methods",
                0, testAutomata.getCash());
        assertEquals("wrong beverage after choice and returnCash methods",
                null, testAutomata.getBeverage());
    }

    @Test
    public void returnMoneyInWrongPlace2() {
        Automata testAutomata = new Automata("menu.txt");
        testAutomata.on();
        assertEquals("wrong result of returnCash", 0, testAutomata.returnCash());
        assertEquals("wrong state after returnCash method",
                States.WAIT, testAutomata.getState());
        assertEquals("wrong cash after returnCash method",
                0, testAutomata.getCash());
        assertEquals("wrong beverage after returnCash  method",
                null, testAutomata.getBeverage());
    }

    @Test
    public void choiceInWrongPlace() {
        Automata testAutomata = new Automata("menu.txt");
        testAutomata.on();
        Pair testPair = testAutomata.choice("latte");
        assertNull(testPair);
        assertEquals("wrong state after choice method", States.WAIT,
                testAutomata.getState());
        assertEquals("wrong cash after choice method", 0,
                testAutomata.getCash());
        assertEquals("wrong beverage after choice method", null,
                testAutomata.getBeverage());
    }

    @Test
    public void choiceInWrongPlace2() {
        Automata testAutomata = new Automata("menu.txt");
        Pair testPair = testAutomata.choice("latte");
        assertNull(testPair);
        assertEquals("wrong state after choice method", States.OFF,
                testAutomata.getState());
        assertEquals("wrong cash after choice method", 0,
                testAutomata.getCash());
        assertEquals("wrong beverage after choice method", null,
                testAutomata.getBeverage());
    }

    @Test
    public void choiceInWrongPlace3() {
        Automata testAutomata = new Automata("menu.txt");
        testAutomata.on();
        testAutomata.coin(70);
        testAutomata.choice("latte");
        Pair testPair = testAutomata.choice("latte");
        assertNull(testPair);
        assertEquals("wrong state after choice method", States.WAIT,
                testAutomata.getState());
        assertEquals("wrong cash after choice method", 0,
                testAutomata.getCash());
        assertEquals("wrong beverage after choice method", null,
                testAutomata.getBeverage());
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