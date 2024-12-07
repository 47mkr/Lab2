import java.io.PrintStream;
import java.util.Scanner;
public class Main {
    public static Scanner in = new Scanner(System.in);
    public static PrintStream out = System.out;
    public static void main(String[] args) {
        int n = in.nextInt(), m = in.nextInt();
        int [][] a = new int[n][m];
        int [][] b = new int[3][m];
        for (int i = 0; i < a.length; i++) {
            for (int k = 0; k < a[i].length; k++) {
                a[i][k] = in.nextInt();
            }
        }
        //Поиск максимума строк, минимума строк, среднего арифметического сумм строк;
        int strmin = 0, strmax = 0, total = 0, t = 0, i1 = 0, i2 = 0;
        double otkl = 0;
        for (int st = 0; st < n - 1; st++) {
            i1 = 0;
            i2 = 0;
            for (int dm = 0; dm < m; dm++) {
                i1 += a[st][dm];
                i2 += a[st + 1][dm];
            }
            if (i1 > i2) {
                strmax = i1;
                strmin = i2;
                t = st + 2;
            }
            else {
                strmax = i2;
                strmin = i1;
                t = st + 1;
            }
        }
        for (int st = 0; st < n; st++) {
            for (int dm = 0; dm < m; dm++) {
                total += a[st][dm];
            }
        }
        //Поиск стандартного отклонения сумм строк;
        for (int st = 0, i = 0; st < n; st++) {
            for (int dm = 0; dm < m; dm++) {
                i += a[st][dm];
            }
            otkl += Math.pow(i - ((double)total / n), 2);
            i = 0;
        }
        out.printf("Минимальное значение суммы среди строк: %d\nНомер строки минимального значения: %d\n", strmin, t);
        out.printf("Разница между максимальным и минимальным значением сумм строк: %d\n", strmax - strmin);
        out.printf("Среднее арифметическое значение сумм строк: %.2f\n", (double)total / n);
        out.printf("Стандартное отклонение сумм строк: %.2f\n", Math.sqrt(otkl / n));
        out.println();

        //Поиск максимума столбца, минимума столбца, суммы квадратов столбца;
        for (int st = 0; st < m; st++) {
            int max = a[0][st], min = a[0][st];
            double sr = Math.pow(a[0][st], 2);
            for (int dm = 0; dm < n - 1; dm++) {
                if (max < a[dm + 1][st]) {
                    max = a[dm + 1][st];
                }
                if (min > a[dm + 1][st]) {
                    min = a[dm + 1][st];
                }
                sr = sr + Math.pow(a[dm + 1][st], 2);
            }
            b[0][st] = max;
            b[1][st] = min;
            b[2][st] = (int)(sr);
        }

        //Сортировка массива в соответствии с условием;
        for (int st = 0; st < n; st ++) {
            for (int i = 0; i < m - 1; i++) {
                for (int j = i + 1; j < m; j++) {
                    if (b[0][i] < b[0][j]) {
                        int temp = a[st][i];
                        a[st][i] = a[st][j];
                        a[st][j] = temp;
                    } else if (b[0][i] == b[0][j]) {
                        if (b[1][i] < b[1][j]) {
                            int temp = a[st][i];
                            a[st][i] = a[st][j];
                            a[st][j] = temp;
                        } else if (b[1][i] == b[1][j]) {
                            if (b[2][i] > b[2][j]) {
                                int temp = a[st][i];
                                a[st][i] = a[st][j];
                                a[st][j] = temp;
                            }
                        }
                    }
                }
            }
        }
        //Вывод треугольником с помощью 1 цикла;
        int r1 = 0, r2 = 1, r3 = m * n, k = 0, i = 0;
        for (int p = 0; p < n * m; p++) {
            out.print(a[i][k] + " ");
            k++;
            r1++;
            r3--;
            if (r1 == r2 && r3 >= r2 + 2) {
                out.println();
                r1 = 0;
                r2 += 2;
            } else if (r1 == r2 && r3 < r2 + 2) {
                r1 = 0;
                r2 += 2;
            }
            if (k == m) {
                k = 0;
                i++;
            }
        }
    }
}