import org.junit.Test;

import static org.junit.Assert.*;

public class CoffeeAutomataTest {

    private CoffeeAutomata mAutomata = new CoffeeAutomata();

    @Test
    public void testOnWithIncorrectState(){
        final State testState = State.CHECK;
        final State expectedState = State.CHECK;
        mAutomata.setState(testState);
        mAutomata.on();
        assertSame(mAutomata.getState(), expectedState);
    }

    @Test
    public void testOnWithCorrectState(){
        final State testState = State.OFF;
        final State expectedState = State.WAIT;
        mAutomata.setState(testState);
        mAutomata.on();
        assertSame(mAutomata.getState(), expectedState);
    }

    @Test
    public void testOffWithIncorrectState(){
        final State testState = State.CHECK;
        final State expectedState = State.CHECK;
        mAutomata.setState(testState);
        mAutomata.off();
        assertSame(mAutomata.getState(), expectedState);
    }

    @Test
    public void testOffWithCorrectState(){
        final State testState = State.WAIT;
        final State expectedState = State.OFF;
        mAutomata.setState(testState);
        mAutomata.off();
        assertSame(mAutomata.getState(), expectedState);
    }

    @Test
    public void testCoinWithIncorrectState(){
        final State testState = State.CHECK;
        final State expectedState = State.CHECK;
        final int coin = 10;
        mAutomata.setState(testState);
        mAutomata.coin(coin);
        assertSame(mAutomata.getState(), expectedState);
    }

    @Test
    public void testCoinWithCorrectState(){
        final State testState1 = State.WAIT;
        final State testState2 = State.ACCEPT;
        final State expectedState = State.ACCEPT;
        final int coin1 = 10;
        final int coin2 = 5;
        final int expectedSum1 = 10;
        final int expectedSum2 = 15;
        mAutomata.setState(testState1);
        mAutomata.coin(coin1);
        assertSame(mAutomata.getState(), expectedState);
        assertSame(mAutomata.getCash(), expectedSum1);
        mAutomata.setState(testState2);
        mAutomata.coin(coin2);
        assertSame(mAutomata.getState(), expectedState);
        assertSame(mAutomata.getCash(), expectedSum2);
    }

    @Test
    public void testChoiceWithIncorrectState(){
        final State testState = State.OFF;
        final State expectedState = State.OFF;
        final int choice = 2;
        mAutomata.setState(testState);
        mAutomata.choice(choice);
        assertSame(mAutomata.getState(), expectedState);
    }

    @Test
    public void testChoiceWithCorrectState(){
        final State testState = State.ACCEPT;
        final State expectedState = State.CHECK;
        final int choice = 2;
        mAutomata.setState(testState);
        mAutomata.init();
        mAutomata.choice(choice);
        assertSame(mAutomata.getState(), expectedState);
    }

    @Test
    public void testCheckWithIncorrectState(){
        final State testState = State.WAIT;
        final State expectedState = State.WAIT;
        final int choice = 2;
        mAutomata.setState(testState);
        mAutomata.check(choice);
        assertSame(mAutomata.getState(), expectedState);
    }

    @Test
    public void testCheckWithCorrectState(){
        final State testState = State.CHECK;
        final State expectedState = State.CHECK;
        final int choice = 2;
        mAutomata.setState(testState);
        mAutomata.init();
        mAutomata.check(choice);
        assertSame(mAutomata.getState(), expectedState);
    }

    @Test
    public void testCancelWithIncorrectState(){
        final State testState = State.OFF;
        final State expectedState = State.OFF;
        mAutomata.setState(testState);
        mAutomata.cancel();
        assertSame(mAutomata.getState(), expectedState);
    }

    @Test
    public void testCancelWithCorrectState(){
        final State testState1 = State.CHECK;
        final State testState2 = State.ACCEPT;
        final State expectedState = State.WAIT;
        final int currentCash = 50;
        final int expectedCash = 0;

        mAutomata.setState(testState1);
        mAutomata.setCash(currentCash);
        mAutomata.cancel();
        assertSame(mAutomata.getState(), expectedState);
        assertSame(mAutomata.getCash(), expectedCash);

        mAutomata.setState(testState2);
        mAutomata.setCash(currentCash);
        mAutomata.cancel();
        assertSame(mAutomata.getState(), expectedState);
        assertSame(mAutomata.getCash(), expectedCash);
    }

    @Test
    public void testCookWithIncorrectState(){
        final State testState = State.OFF;
        final State expectedState = State.OFF;
        final int choice = 2;
        mAutomata.setState(testState);
        mAutomata.cook(choice);
        assertSame(mAutomata.getState(), expectedState);
    }

    @Test
    public void testCookWithCorrectState(){
        final State testState = State.CHECK;
        final State expectedState = State.WAIT;
        final int choice = 2;
        mAutomata.setState(testState);
        mAutomata.init();
        mAutomata.cook(choice);
        assertSame(mAutomata.getState(), expectedState);
    }

    @Test
    public void testFinishWithIncorrectState(){
        final State testState = State.OFF;
        final State expectedState = State.OFF;
        final int goodsPrice = 50;
        mAutomata.setState(testState);
        mAutomata.finish(goodsPrice);
        assertSame(mAutomata.getState(), expectedState);
    }

    @Test
    public void testFinishWithCorrectState(){
        final State testState = State.COOK;
        final State expectedState = State.WAIT;
        final int goodsPrice = 50;
        final int currentCash = 70;
        final int expectedChange = 20;

        mAutomata.setState(testState);
        mAutomata.setCash(currentCash);
        int change = mAutomata.finish(goodsPrice);
        assertSame(mAutomata.getState(), expectedState);
        assertSame(mAutomata.getCash(),0);
        assertSame(change, expectedChange);
    }
}