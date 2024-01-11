package com.karsyz;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    final static byte MONTHS_IN_YEAR = 12;
    final static byte PERCENT = 100;

    public static void main(String[] args) {

        // Mortgage Calculator with conditional loops and refactored
        int principal = (int) readNumber("Principal ($1k - $1M): ", 1_000, 1_000_000 );
        float annualInterestRate = (float) readNumber("Annual Interest Rate: ", 1, 30);
        byte period = (byte) readNumber("Period (Years): ", 1, 30);

        double mortgage = calculateMortgage(principal, annualInterestRate, period );

        printMortgagePayments(mortgage);
        System.out.println();
        printPaymentSchedule(period, principal, annualInterestRate);
    }

    private static void printMortgagePayments(double mortgage) {
        String mortgageFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println();
        System.out.println("MORTGAGE");
        System.out.println("--------");
        System.out.println("Monthly Payments: " + mortgageFormatted);
    }

    private static void printPaymentSchedule(byte period, int principal, float annualInterestRate) {
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");

        for(short i = 1; i <= period * MONTHS_IN_YEAR; i++) {
            double balance = calculateBalance(principal, annualInterestRate, period, i);
            System.out.println( NumberFormat.getCurrencyInstance().format(balance) );
        }
    }

    public static double calculateMortgage(
            int principal,
            float annualInterest,
            byte years) {

        float monthlyInterest = annualInterest /  PERCENT / MONTHS_IN_YEAR;
        short numberOfPayments = (short)(years * MONTHS_IN_YEAR);

        return principal
                * ( ( monthlyInterest * Math.pow( (1 + monthlyInterest), numberOfPayments ) )
                / ( Math.pow( (1 + monthlyInterest), numberOfPayments ) - 1 ) );
    }

    public static double calculateBalance(
            int principal,
            float annualInterest,
            byte years,
            short numberOfPaymentsMade) {

        float monthlyInterest = annualInterest /  PERCENT / MONTHS_IN_YEAR;
        short numberOfPayments = (short)(years * MONTHS_IN_YEAR);

        return principal
                * (Math.pow(1 + monthlyInterest, numberOfPayments) - Math.pow(1 + monthlyInterest, numberOfPaymentsMade))
                / (Math.pow( (1 + monthlyInterest), numberOfPayments ) - 1);
    }

    public static double readNumber(
            String prompt,
            double min,
            double max
    ) {
        double value;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print(prompt);
            value = scanner.nextFloat();
            if(value >= min && value <= max)
                break;
            System.out.println("Enter a value between " + (int)min + " and " + (int)max);
        }
        return value;
    }
}
