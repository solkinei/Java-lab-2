import javafx.util.Pair;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

public class CoffeeAutomata implements Automata {

    private State mState = State.OFF;
    private int mCash = 0;
    private List<Pair<String, Integer>> mGoods = new ArrayList<>();

    //Visible for testing
    void init(){
        //read config file here
        mGoods.add(new Pair<>("latte", 55));
        mGoods.add(new Pair<>("americano", 20));
        mGoods.add(new Pair<>("irish", 70));
        mGoods.add(new Pair<>("espresso", 30));
        mGoods.add(new Pair<>("milk", 50));
    }

    //used for testing
    int getCash(){
        return mCash;
    }

    //used for testing
    void setCash(int cash){
        mCash = cash;
    }

    //used for testing
    void setState(State state){
        mState = state;
    }

    @Override
    public void on() {
        if(mState != State.OFF) return;
        init();
        mState = State.WAIT;
    }

    @Override
    public void off() {
        if(mState == State.WAIT) mState = State.OFF;
    }

    @Override
    public void coin(int value) {
        if(mState != State.WAIT && mState != State.ACCEPT) return;
        mCash += value;
        mState = State.ACCEPT;
    }

    @Override
    public List<Pair<String, Integer>> getMenu() {
        return mGoods;
    }

    @Override
    public State getState() {
        return mState;
    }

    @Override
    public void choice(int goodsNumber) {
        if(mState != State.ACCEPT) return;
        mState = State.CHECK;
        check(goodsNumber);
    }

    @Override
    public void check(int goodsNumber) {
        if(mState != State.CHECK) return;
        if(mGoods.get(goodsNumber).getValue() <= mCash) cook(goodsNumber);
    }

    @Override
    public void cancel() {
        if(mState != State.ACCEPT && mState != State.CHECK) return;
        mCash = 0;
        mState = State.WAIT;
    }

    @Override
    public void cook(int goodsNumber) {
        if(mState != State.CHECK) return;
        mState = State.COOK;
        finish(mGoods.get(goodsNumber).getValue());
    }

    @Override
    public int finish(int goodsPrice) {
        if(mState != State.COOK) return 0;
        int change = mCash - goodsPrice;
        //return change
        mCash = 0;
        mState = State.WAIT;
        return change;
    }
}

