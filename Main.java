import java.io.PrintStream;
import java.util.Scanner;

class Product {
    private String name;
    private double cost;
    private double price;
    private int k = 0;
    private double [] m = new double [30];
    private boolean f = false;
    //Добавление товара
    public Product(String name, double cost, double price) {
        this.name = name;
        if (cost > 0) {
            this.cost = cost;
        } else {
            this.cost = 0;
        }
        if (price > 0) {
            this.price = price;
        } else {
            this.price = 0;
        }
        if (cost > price && !f) {
            this.price = this.cost;
        }
        m[k] = price;
        k += 1;
    }
    //Создание новой цены
    public void newPrice(double price) {
        if (cost < price || f) {
            this.price = price;
        } else {
            price = cost;
        }
        m[k] = price;
        k += 1;
    }
    //Наценка
    public void markup(double x) {
        price = price * (1 + x / 100);
        m[k] = price;
        k += 1;
    }

    //Скидка;
    public void sale(double x) {
        if (cost < price * (1 - x / 100) || f) {
            price = price * (1 - x / 100);
        } else {
            price = cost;
        }
        m[k] = price;
        k += 1;
    }

    //Изменение цены;
    public void changePrice(double x) {
        if (cost < price + x || f) {
            price = price + x;
        } else {
            price = cost;
        }
        m[k] = price;
        k += 1;
    }

    //Средняя цена;
    public double averagePrice() {
        double st = 0;
        for (int i = 0; i < m.length; i++) {
                st += m[i];
        }
        return st / k;
    }

    //Средняя наценка;
    public double averageMarkup() {
        double st = 0;
        for (int i = 0; i < m.length; i++) {
            if (m[i] != 0) {
                st += m[i] - cost;
            }
        }
        return st / k;
    }

    //Возможная выручка;
    public double revenue(double x) {
        return x * price;
    }

    //Разрешение на стоимость ниже себестоимости;
    public void belowCost(boolean f) {
        this.f = f;
    }

    //Печать истории цен;
    public void historyPrice() {
        int st = 0;
        System.out.print("[");
        for (int i = 0; i < m.length; i++) {
            if (st == k - 1) {
                System.out.print(m[i] + "]");
                break;
            }
            if (m[i] != 0) {
                System.out.print(m[i] + ", ");
                st += 1;
            }
        }
    }
    //Добавление товара по 2 параметрам;
    public Product(String name, double cost) {
        this.name = name;
        this.cost = cost;
        price = cost;
    }
    //Настройка вывода через print;
    @Override
    public String toString() {
        return "Товар - " + name + " - " + cost + " - " + price;
    }
}

public class Main {
    public static Scanner in = new Scanner(System.in);
    public static PrintStream out = System.out;
    public static void main(String[] args) {
        Product [] a = new Product[1000];
        int st = 0;
        boolean f = true;
        while (f) {
            out.println("Введите номер действия:");
            out.println("Добавить товар - 1");
            out.println("Редактировать товар - 2");
            if (in.nextInt() == 1) {
                out.println("Создать с помощью названия, себестоимости, цены - 1");
                out.println("Создать с помощью названия и себестоимости - 2");
                if (in.nextInt() == 1) {
                    out.println("Введите данные:");
                    Product y = new Product(in.next(), in.nextDouble(), in.nextDouble());
                    a[st] = y;
                } else {
                    out.println("Введите данные:");
                    Product y = new Product(in.next(), in.nextDouble());
                    a[st] = y;
                }
                st += 1;
                continue;
            } else {
                out.print("Введите номер товара: ");
                out.println();
                int num = in.nextInt() - 1;
                out.println(a[num]);
                out.println("Список доступных действий, введите номер:");
                out.println("Создание новой цены - 1");
                out.println("Создание наценки - 2");
                out.println("Создание скидки - 3");
                out.println("Изменить цену - 4");
                out.println("Средняя цена - 5");
                out.println("Средняя наценка - 6");
                out.println("Возможная выручка - 7");
                out.println("Разрешение на стоимость ниже себестоимости - 8");
                out.println("История изменения цен - 9");
                out.println("Конец - 10");
                int t = 0;
                while (t != 10) {
                    t = in.nextInt();
                    if (t == 1) {
                        out.print("Введите значение: ");
                        a[num].newPrice(in.nextDouble());
                        out.println(a[num]);
                        out.println("Введите номер:");
                    } else if (t == 2) {
                        out.print("Введите значение: ");
                        a[num].markup(in.nextDouble());
                        out.println(a[num]);
                        out.println("Введите номер:");
                    } else if (t == 3) {
                        out.print("Введите значение: ");
                        a[num].sale(in.nextDouble());
                        out.println(a[num]);
                        out.println("Введите номер:");
                    } else if (t == 4) {
                        out.print("Введите значение: ");
                        a[num].changePrice(in.nextDouble());
                        out.println(a[num]);
                        out.println("Введите номер:");
                    } else if (t == 5) {
                        out.println(a[num].averagePrice());
                        out.println("Введите номер:");
                    } else if (t == 6) {
                        out.println(a[num].averageMarkup());
                        out.println("Введите номер:");
                    } else if (t == 7) {
                        out.print("Введите значение: ");
                        out.println(a[num].revenue(in.nextDouble()));
                        out.println("Введите номер:");
                    } else if (t == 8) {
                        out.print("Введите true или false: ");
                        a[num].belowCost(in.nextBoolean());
                        out.println("Успешно!");
                        out.println("Введите номер:");
                    } else if (t == 9) {
                        a[num].historyPrice();
                        out.println();
                        out.println("Введите номер:");
                    }
                }
            }
        }
        out.print("Конец");
    }
}