package com.csc;
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
    System.out.print("Ins:   $ 10.00");

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
  }
}
