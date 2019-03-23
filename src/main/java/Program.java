import java.util.Map;
import java.util.Scanner;
import java.lang.Thread.*;


class Choice {
    static void printChoice() {
        System.out.println("Choose or lose:");
        System.out.println("1 - on");
        System.out.println("2 - pay");
        System.out.println("3 - choice drink");
        System.out.println("5 - print menu");
        System.out.println("6 - add drink to menu");
        System.out.println("8 - print this text");
        System.out.println("9 - cancel");
        System.out.println("0 - off");
        System.out.println("-1 - destroy automata");
    }
}

public class Program {
    public static void main(String[] args) {
        Automata A = new Automata(0);
        Choice.printChoice();
        while (true) {
            System.out.print("Push button: ");
            String buf;
            Scanner in = new Scanner(System.in);
            buf = in.nextLine();
            int button = 8;
            try {
                button = Integer.parseInt(buf);
            } catch (NumberFormatException ex) {
                System.out.println("Wrong choice");
                Choice.printChoice();
                continue;
            }
            switch (button) {
                case 1: {
                    A.on();
                    break;
                }
                case 2: {
                    System.out.print("Give me your money: ");
                    String buf1;
                    Scanner in1 = new Scanner(System.in);
                    buf1 = in.nextLine();
                    int money = Integer.parseInt(buf1);
                    if (!A.coin(money)) {
                        System.out.println("I am not ready for your money");
                    }
                    break;
                }
                case 3: {
                    System.out.println("What would you like to drink: ");
                    String buf1;
                    Scanner in1 = new Scanner(System.in);
                    buf1 = in.nextLine();
                    float cost = A.choice(buf1);
                    if (cost == 0) {
                        System.out.println("I am not ready for choice");
                    } else if (cost == -1) {
                        System.out.println("I haven't this drink");

                    } else if (!A.check(cost)) {
                        System.out.println("Not enough money");
                    } else {
                        System.out.printf("State - %s\n", A.getState());
                        System.out.printf("Cash - %.2f rub\n", A.getCash());
                        A.cook(buf1);
                        System.out.printf("State - %s\n", A.getState());
                        System.out.printf("Cash - %.2f rub\n", A.getCash());
                        A.finish();
                    }
                    break;
                }
                case 5: {
                    Map<String, Float> menu = A.printMenu();
                    if (menu == null) {
                        System.out.println("I am empty");
                    } else {
                        for (String key : menu.keySet()) {
                            System.out.printf("Drink %s costs %.2f rub\n", key, menu.get(key));
                        }
                    }
                    break;
                }
                case 6: {
                    System.out.println("Input drink");
                    String buf1;
                    Scanner in1 = new Scanner(System.in);
                    buf1 = in.nextLine();
                    System.out.println("Input cost");
                    String buf2;
                    Scanner in2 = new Scanner(System.in);
                    buf2 = in.nextLine();
                    float cost = Integer.parseInt(buf2);
                    A.addToMenu(buf1, cost);
                    break;
                }
                case 8: {
                    Choice.printChoice();
                    break;
                }
                case 9: {
                    A.cancel();
                    break;
                }
                case 0: {
                    A.off();
                    break;
                }
                case -1: {
                    A = null;
                    break;
                }
            }
            if (A == null) {
                System.out.println("I'll be back");
                break;
            }
            System.out.printf("State - %s\n", A.getState());
            System.out.printf("Cash - %.2f rub\n", A.getCash());


        }
    }
}