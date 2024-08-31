package com.csc;

import java.util.Scanner;

public class Payroll {

  public static void main(String[] args) {
    Scanner hours = new Scanner(System.in);
    Payroll payroll = new Payroll();
    double pay;
    System.out.println("Welcome to the Payroll Program!!");
    System.out.println("Enter hom many hours in decimal form you worked and the number of children you have:");
    double time = hours.nextInt();
    int family = hours.nextInt();
    if (family < 0)
      family = 0;
    // asking for a custom rate
    System.out.print("what is your pay rate?\n");
    pay = hours.nextDouble();
    // payroll text layout seperate from calculations for ease of use in
    // methods
    System.out.print("Payroll Stub\n\n\n");
    System.out.print("Hours you worked:  " + time + "\n");
    System.out.print("Your current rate: " + pay + " $/hr \n");
    System.out.print("Your Total pay:    $ " + payroll.grosspay(time, family, pay) + "\n\n");
    System.out.print(payroll.expenses(payroll.grosspay(time, family, pay), family));
    System.out.print("thank you for loging your hours with us!!");
  }

  public double grosspay(double time, int children, double pay) {

    double grosspay;
    // going to be used in multiple methods
    if (time > 40) {
      double overtime = time - 40;
      grosspay = (time - overtime) * pay;
      grosspay += (1.5 * overtime * pay);
    } else
      grosspay = pay * time;
    return grosspay;
  }

  public String expenses(double money, int child) {
    double SS = money * .06;
    double FedIn = money * .14;
    double StateIn = money * .05;
    double union = 10.00;
    double Ins;
    if (child >= 3)
      Ins = 35.00;
    else
      Ins = 15.00;
    double total = money - SS - FedIn - StateIn - Ins;
    return String.format(
        "Expenses:\n\nSS:                $ %.2f\nFedIn:             $ %.2f\nStateIn:           $ %.2f\nUnion:             $ %.2f\nIns:               $ %.2f\n\nNet:               $ %.2f\n\n\n",
        SS, FedIn, StateIn, union, Ins, total);
  }
}