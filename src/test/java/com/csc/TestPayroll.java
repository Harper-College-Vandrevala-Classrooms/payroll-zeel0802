package com.csc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestPayroll {

  Payroll payroll;

  @BeforeEach
  void setUp() {
    payroll = new Payroll();
  }

  @Test
  public void fullweek() {
    assertEquals(671.2, payroll.grosspay(40, 0, 16.78));
  }
}
