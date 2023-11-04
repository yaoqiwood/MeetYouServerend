package com.cx.meetyoubackend;

import com.cx.meetyoubackend.utils.BcryptUtil;

public class UtilsTest {

  public static void main(String[] args) {
    String rawPassword = "myPassword123";
    String encodedPassword = BcryptUtil.encode(rawPassword);

    System.out.println("Encoded Password: " + encodedPassword);

    boolean isMatch = BcryptUtil.matches(rawPassword, encodedPassword);
    System.out.println("Password matches: " + isMatch);
  }


}
