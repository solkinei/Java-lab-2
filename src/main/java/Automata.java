import java.util.*;

public class Automata {

    private int cash;
    private STATE state;
    private Map<String, Integer> menuPrices = new HashMap<>();

    public Automata() {                                    // конструктор
        state = STATE.OFF;
        this.cash = 0;
        menuPrices.put("Чай чёрный", 30);
        menuPrices.put("Чай зелёный", 20);
        menuPrices.put("Кофе чёрный", 50);
        menuPrices.put("Кофе с молоком", 60);
        menuPrices.put("Какава", 40);
    }

    public void setCash(int cash){
        this.cash = cash;
    }
    public int getCash(){
        return cash;
    }

    public STATE getState(){                                // отображение текущего состояния для пользователя
        return state;
    }

    public void getMemu(){                                  // отображение меню с напитками и ценами для пользователя
        for (Map.Entry<String, Integer> entry : menuPrices.entrySet()) {
            String drink = entry.getKey();
            Integer price = entry.getValue();
        }
    }

    public void on(){                                              // включение автомата
        if(state == STATE.OFF){
            state = STATE.WAIT;
        }
    }

    public void off(){                                             // выключение автомата
        if(state == STATE.WAIT){
            state = STATE.OFF;
        }
    }

    public void coin(int transaction){                             // занесение денег на счёт пользователем
        if(state == STATE.ACCEPT || state == STATE.CHECK){
            cash += transaction;
        }
        if(state == STATE.WAIT){
            state = STATE.ACCEPT;
            cash += transaction;
        }
    }

    public void choice(String drink){                              // выбор напитка пользователем
        if(state == STATE.ACCEPT){
            if(check(drink) == true){
                cook(drink);
            }
            else cancel();
        }
    }

    public boolean check(String drink){                             // проверка наличия необходимой суммы
        if(cash < menuPrices.get(drink)){
            return false;
        }
        state = STATE.CHECK;
        return true;
    }

    public void cancel(){                                          // отмена сеанса обслуживания пользователем
        if(state == STATE.ACCEPT || state == STATE.CHECK){
            cash = 0;
            state = STATE.WAIT;
        }
    }

    public void cook(String drink){                                // имитация процесса приготовления напитка
        state = STATE.COOK;
        //Thread.sleep(3000);
        finich(drink);
    }

    public void finich(String drink){                              // завершение обслуживания пользователя
        cash -= menuPrices.get(drink);
        state = STATE.WAIT;
    }
}
