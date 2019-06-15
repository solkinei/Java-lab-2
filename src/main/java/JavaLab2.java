import java.util.ArrayList;
public class JavaLab2 {
    public static void main(String[] args){
        Automata CoffeeMashine = new Automata();
        CoffeeMashine.on();
        System.out.println(CoffeeMashine.getState());
        ArrayList[] menu=CoffeeMashine.getMenu();
        for (int i=0; i<menu[0].size();i++){
            System.out.println(menu[0].get(i)+"  "+ menu[1].get(i));
        }
        CoffeeMashine.coin(30);
        CoffeeMashine.coin(30);
        System.out.println("The deposit equals: "+CoffeeMashine.getCash());
        float change = CoffeeMashine.choice(0);
        System.out.println("Take the change: "+change);
        System.out.println("The deposit equals: "+CoffeeMashine.getCash());
        System.out.println(CoffeeMashine.getState());
        CoffeeMashine.off();
        System.out.println(CoffeeMashine.getState());


    }
}
