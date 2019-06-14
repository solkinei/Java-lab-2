import java.util.ArrayList;

enum STATES {
    OFF,
    WAIT,
    ACCEPT,
    CHECK,
    COOK
}

class Automata {
    int cash = 0;
    public ArrayList<String> menu = new ArrayList<String>();
    ArrayList<Integer> price = new ArrayList<Integer>();

    STATES state;

    // Constructor:
    Automata() {
        state = STATES.OFF;

        menu.add("Espresso");
        menu.add("Americano");
        menu.add("Latte");
        menu.add("Mocco");
        menu.add("Chocolate");
        menu.add("Kotletki s pureshkoy");
        price.add(5);
        price.add(8);
        price.add(10);
        price.add(8);
        price.add(7);
        price.add(20);
    }

    /*
    конструктор
on() - включение автомата;
off() - выключение автомата;
coin() - занесение денег на счёт пользователем;
printMenu() - отображение меню с напитками и ценами для пользователя;
printState() - отображение текущего состояния для пользователя;
choice() - выбор напитка пользователем;
check() - проверка наличия необходимой суммы;
cancel() - отмена сеанса обслуживания пользователем;
cook() - имитация процесса приготовления напитка;
finish() - завершение обслуживания пользователя.
*/

    void on() {
        if (state != STATES.OFF) {        // только выключенный автомат можно включить
            return;
        }
        state = STATES.WAIT;
    }

    void off() {
        if (state != STATES.WAIT) {        // только в режиме ожидания автомат можно выключить
            return;
        }
        state = STATES.OFF;
    }

    void coin(int insertedCoin) {
        if (state != STATES.WAIT && state != STATES.ACCEPT) {
            return;
        }
        cash += insertedCoin;
        state = STATES.ACCEPT;
    }

    ArrayList<String> printMenu() {
        return menu;
    }

    STATES printState() {
        return state;
    }

    void choice(int buttonNumber) {
        if (check(buttonNumber) == true) {
            cash -= price.get(buttonNumber);
            cook();
            return;
        }
        cancel();
    }

    boolean check(int buttonNumber) {
        if (cash < price.get(buttonNumber)) {
            return false;
        }
        state = STATES.CHECK;
        return true;
    }

    void cook() {
        state = STATES.COOK;
        finish();
    }

    void finish() {
        state = STATES.WAIT;
        // обслуживание завершено
    }

    void cancel() {
        if (state != STATES.ACCEPT) {
            return;
        }
        state = STATES.WAIT;
        // отмена и переход назад в WAIT
    }
}
