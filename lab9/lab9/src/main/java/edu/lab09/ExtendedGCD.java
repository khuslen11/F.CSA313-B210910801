package edu.lab09;

public class ExtendedGCD {
    public static int gcd(int a, int b) {
        int steps = 0;
        int temp;

        while (b != 0) {
            steps = steps + 1;
            temp = b;
            b = a % b;
            a = temp;
        }

        System.out.println("Steps taken: " + steps);
        return a;
    }

    public static void main(String[] args) {
        int x = 48;
        int y = 18;
        int result = gcd(x, y);
        System.out.println("GCD of " + x + " and " + y + " = " + result);
    }
}