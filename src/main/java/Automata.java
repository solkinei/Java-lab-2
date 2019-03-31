enum STATES {
    OFF,
    ON,
    WAIT,
    ACCEPT,
    CHECK,
    COOK
}

class Automata {
    // Не static! У каждого нового автомата будей свой баланс, а не один общий для всех.
    int cash = 0;
    String menu[] = {"Espresso", "Americano", "Latte", "Mocco", "Chocolate", "Kotletki s pureshkoy"};
    int price[] = {5, 8, 10, 8, 7, 20};

    // Constructor:
    Automata() {

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

    STATES on(STATES state) {
        return state = STATES.ON;
    }

    STATES off(STATES state) {
        return state = STATES.OFF;
    }

    STATES coin(int insertedCoin, STATES state) {
        cash += insertedCoin;
        return state = STATES.ACCEPT;
    }

    STATES printMenu(STATES state) {
        for (int i = 0; i < price.length; i++) {
            System.out.println(i + " - " + menu[i] + " - " + price[i] + "credits");
        }
        return state = STATES.WAIT;
    }

    void printState(STATES state) {
        System.out.println("State = " + state);
    }

    STATES choice(STATES state, int buttonNumber){
        if (check(state,buttonNumber)==true) {
            return state = STATES.COOK;
        }
        System.out.println("Not enough cash!");
        return state=STATES.WAIT;
    }

    boolean check(STATES state, int buttonNumber){
        if (cash<price[buttonNumber]){
                return false;
        }
        state=STATES.CHECK;
        return true;
    }

}
