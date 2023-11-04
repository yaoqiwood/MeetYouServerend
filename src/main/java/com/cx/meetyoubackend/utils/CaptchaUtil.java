package com.cx.meetyoubackend.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CaptchaUtil {

  private static final int WIDTH = 100;
  private static final int HEIGHT = 50;
  private static final String CHAR_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
  private static final int STRING_LENGTH = 4;

  public static String generateCaptchaCode() {
    Random random = new Random();
    StringBuilder captcha = new StringBuilder();
    while (captcha.length() < STRING_LENGTH) {
      int index = random.nextInt(CHAR_STRING.length());
      captcha.append(CHAR_STRING.charAt(index));
    }
    return captcha.toString();
  }

  public static BufferedImage generateCaptchaImage(String captchaCode) {
    BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    Graphics g = image.getGraphics();
    Random random = new Random();

    // 设置背景色
    g.setColor(getRandomColor(200, 250));
    g.fillRect(0, 0, WIDTH, HEIGHT);

    // 设置字体
    g.setFont(new Font("Arial", Font.BOLD, 20));

    // 绘制验证码
    g.setColor(getRandomColor(0, 160));
    g.drawString(captchaCode, 10, 25);

    // 添加噪点
    for (int i = 0; i < 100; i++) {
      int x = random.nextInt(WIDTH);
      int y = random.nextInt(HEIGHT);
      g.setColor(getRandomColor(120, 240));
      g.drawOval(x, y, 1, 1);
    }

    g.dispose();
    return image;
  }

  private static Color getRandomColor(int fc, int bc) {
    Random random = new Random();
    if (fc > 255) {
      fc = 255;
    }
    if (bc > 255) {
      bc = 255;
    }
    int r = fc + random.nextInt(bc - fc);
    int g = fc + random.nextInt(bc - fc);
    int b = fc + random.nextInt(bc - fc);
    return new Color(r, g, b);
  }
}
