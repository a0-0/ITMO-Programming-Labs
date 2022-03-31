import static java.lang.Math.*;
import static java.lang.Math.*;

public class lab1 {
        public static void main(String[] args) {
            // 1
            int[] a = {4, 6, 8, 10, 12, 14, 16, 18, 20, 22};

            // 2
            float x[] = new float[12];
            for (int i = 0; i < 12; i++) {
                float D = (float) ((random() * ((10 - (-4)))) + (-4));   // This Will Create A Random Number between Your Min And Max.
                x[i] = D;
            }//3
            double A[][] = new double[10][12];
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 12; j++) {
                    double X = (double) x[j];

                    if (a[i] == 22) {
                        A[i][j] = asin(PI / 4 * (pow(E, X)));
                    }
                    if (a[i] == 6 || a[i] == 8 || a[i] == 12 || a[i] == 16 || a[i] == 20) {
                        A[i][j] = pow((((whenBaseIs125AndNIs3_thenNthIs5(sin(X))) - 1) / 2), 2);
                    } else {
                        A[i][j] = log(pow(sin(pow(log((5 + pow(cos(X), 2)) / (acos((X + 3) / 14))), 2)), 2));
                    }
                    // 4
                    System.out.printf("%10.5f", A[i][j]);
                }
                System.out.println();
            }
        }

        public static double whenBaseIs125AndNIs3_thenNthIs5(double X) {
            double out = Math.pow(X, 1 / 3);
            return out;
        }



}
