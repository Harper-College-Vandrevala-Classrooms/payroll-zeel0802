package com.csc;
import java.util.Scanner;

import java.util.Scanner;

public class Payroll {
  // Implement your solution here!

  private static float UNION_FEES = (float) 10.00;

  public static void main(String[] args) {
        
    System.out.print("Welcome to the Payroll Program!\n\n");
    float hours;
    int dependents;
    int lifeInsurance;
    float pay;
    float net;

    hours = checkFloat("How many hours did you work this week? "
                    , "\nInput was invalid. Please input a positive number.\n");
    dependents = checkInt("How many children do you have? "
                    , "\nInput was invalid. Please input a positive number.\n");
    lifeInsurance = lifeInsuranceSelection(dependents);

    if (hours > 40)
    {
      pay = (float) 16.78 * 40;
      pay = pay + ((hours - 40) * (float) 1.5 * (float) 16.78);
    }
    else
    {
      pay = hours * (float) 16.78;
    }

    System.out.print("Payroll Stub:\n");
    System.out.print("Hours:   " + String.format("%.2f", hours));
    System.out.print("Rate:   16.75 $/hr");
    System.out.print("Gross:   $ " + String.format("%.2f", pay));

    System.out.print("\nSocSec:   $ " + String.format("%.2f", (pay * (float) 0.06)));
    System.out.print("FedTax:   $ " + String.format("%.2f", (pay * (float) 0.14)));
    System.out.print("StTax:   $ " + String.format("%.2f", (pay * (float) 0.05)));
    System.out.print("Union:   $ 10.00");
    System.out.print("Ins:   $ " + ); // Needs logic
    System.out.print("LifeIns:   $ " + String.format(".2f", lifeInsurance));

    net = pay - (pay * (float) .25) - UNION_FEES - lifeInsurance;

    System.out.print("\nNet:   $ " + String.format("%.2f", net));
  }

  public static int checkInt(String checkString, String errorString)
  {
    Scanner in = new Scanner(System.in);
    boolean check = false;
    int userInput = 0;

    do { 
      System.out.print(checkString);
      if (in.hasNextInt())
      {
        userInput = in.nextInt();

        if(userInput >= 0)
        {
          check = true;
        }
      }
      else
      {
        System.out.print(errorString);
      }
    } while (check);

    in.close();
    return userInput;
  }

  public static float checkFloat(String checkString, String errorString)
  {
    Scanner in = new Scanner(System.in);
    boolean check = false;
    float userInput = 0;

    do { 
      System.out.print(checkString);
      if (in.hasNextFloat())
      {
        userInput = in.nextFloat();

        if(userInput >= 0)
        {
          check = true;
        }
      }
      else
      {
        System.out.print(errorString);
      }
    } while (check);

    in.close();
    return userInput;
  }

  public static int lifeInsuranceSelection(int dependents)
  {
    Scanner in = new Scanner(System.in);
    boolean check = false;

    int userInput = 0;

    do { 
      userInput = checkInt("""
                           Which life insurance plan do you want to select?
                           
                             (1) no plan
                             (2) single plan
                             (3) married plan
                             (4) married with children plan
                           ""","\nInvalid input. Input must be '1','2', '3' or '4'.\n");
      if(userInput > 0 && userInput <= 3)
      {
        check = true;
      }
      else if(userInput == 4)
      {
        if(dependents == 0)
        {
          System.out.print("""
                           Invalid input. You need at least one child to select plan 4.
                           Please select a different plan""");
        }
        else
        {
          check = true;
        }
      }
      else
      {
        System.out.print("\nInvalid input. Input must be '1','2', '3' or '4'.\n");
      }
    } while (check);

    in.close();

    switch (userInput)
    {
      case 1:
        return 0;
      case 2:
        return 5;
      case 3:
        return 10;
      case 4:
        return 15;
    }
  return 0;

  public static void main(String[] args) {
    try (Scanner hours = new Scanner(System.in)) {
      Payroll payroll = new Payroll();
      double pay;
      System.out.println("Welcome to the Payroll Program!!");
      System.out.println("Enter hom many hours in decimal form you worked and the number of children you have:");
      double time = hours.nextInt();
      int family = hours.nextInt();
      if (family < 0)
        family = -(family);
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
    }
    System.out.print("thank you for loging your hours with us!!\n\n");
  }

  public double grosspay(double time, int children, double pay) {
    //the raw calcuated payout
    double grosspay;
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
    // validation for too little funds and if so the output of the function
    // is switched to a different formatted string along with a dedicated
    // output
    if (money < 60) {
      double total = money - SS - FedIn - StateIn;
      System.out.print(String.format(
          "Expenses:\n\nSS:                $ %.2f\nFedIn:             $ %.2f\nStateIn:           $ %.2f\nnet:               $ %.2f\n\n\n",
          SS, FedIn, StateIn, total));
      return String.format("You still owe:\n\nUnion:             $ %.2f\nIns:               $ %.2f\n\n", union, Ins);
    }
    double total = money - SS - FedIn - StateIn - union - Ins;
    return String.format(
        "Expenses:\n\nSS:                $ %.2f\nFedIn:             $ %.2f\nStateIn:           $ %.2f\nUnion:             $ %.2f\nIns:               $ %.2f\n\nNet:               $ %.2f\n\n\n",
        SS, FedIn, StateIn, union, Ins, total);
  }
}