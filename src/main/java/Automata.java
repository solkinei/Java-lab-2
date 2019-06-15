import javafx.util.Pair;

import java.util.List;

public interface Automata {
    void on();
    void off();
    void coin(int value);
    List<Pair<String, Integer>> getMenu();
    State getState();
    void choice(int goodsNumber);
    void check(int goodsNumber);
    void cancel();
    void cook(int goodsNumber);
    int finish(int goodsPrice);
}