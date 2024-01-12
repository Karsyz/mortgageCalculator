package com.karsyz;

public class Main {
    public static void main(String[] args) {
        int principal = (int) Console.readNumber("Principal ($1k - $1M): ", 1_000, 1_000_000 );
        float annualInterestRate = (float) Console.readNumber("Annual Interest Rate: ", 1, 30);
        byte period = (byte) Console.readNumber("Period (Years): ", 1, 30);

        var calculator = new MortgageCalculator(principal, annualInterestRate, period);
        var report = new MortgageReport(calculator);

        report.printMortgagePayment();
        report.printPaymentSchedule();
    }
}
