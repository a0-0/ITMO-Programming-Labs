package ITMO;

import static java.lang.Math.*;

/***
 *       ФИО: Абрабоу Ахмед Е.А.И
 *       №: 333879
 *       Date: 01/10/2021
 *
 *       Создать одномерный массив p типа short. Заполнить его чётными числами от 4 до 20 включительно в порядке возрастания.
 *       Создать одномерный массив x типа float. Заполнить его 13-ю случайными числами в диапазоне от -10.0 до 4.0.
 *       Создать двумерный массив h размером 9x13. Вычислить его элементы по следующей формуле (где x = x[j]):
 *       если p[i] = 4, то h[i][j]=sin⎛⎝⎜⎜⎛⎝⎜⎜(x)xx−14sin(x)+1⎞⎠⎟⎟3⎞⎠⎟⎟;
 *       если p[i] ∈ {8, 12, 14, 20}, то h[i][j]=arcsin(cos((x−−√3tan(x)+3)2));
 *       для остальных значений p[i]: h[i][j]=arcsin⎛⎝⎜⎜⎜⎜⎜1e∣∣∣∣(tan(0.5x))(arcsin(x−314))ex∣∣∣∣⎞⎠⎟⎟⎟⎟⎟.
 *       Напечатать полученный в результате массив в формате с четырьмя знаками после запятой.
 **/

public class S333879_lab1 {
    public static void main(String[] args) {
        short a[] = {4, 6, 8, 10, 12, 14, 16, 18, 20};
        float x[] = new float[13];
        for (int i = 0; i < 13; i++) {
            float D = (float) ((random() * ((4 - (-10)))) + (-10));   // This Will Create A Random Number between Your Min And Max.
            x[i] = D;
        }

        double A[][] = new double[9][13];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 13; j++) {
                double X = (double) x[j];

                if (a[i] == 4) {
                    A[i][j] = sin(pow((pow(X, (X / (X - 0.25))) / sin(X) + 1), 3));
                }
                if (a[i] == 8 || a[i] == 12 || a[i] == 14 || a[i] == 20) {
                    A[i][j] = asin(cos(pow(((whenBaseIs125AndNIs3_thenNthIs5(X)) / (tan(X) + 3)), 2)));
                } else {
                    A[i][j] = asin(1 / (pow(E, (pow(tan(0.5 / X), pow((asin(X - 3 / 14)), pow(E, X)))))));
                }
                System.out.printf("%10.4f", A[i][j]);
            }
            System.out.println();
        }
    }

    static double diff(double n, double mid) {
        if (n > (mid * mid * mid))
            return (n - (mid * mid * mid));
        else
            return ((mid * mid * mid) - n);
    }

    public static double whenBaseIs125AndNIs3_thenNthIs5(double X) {
        double out = Math.pow(X, 1 / 3);
        return out;
    }
}
