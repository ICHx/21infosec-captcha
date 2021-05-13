package infosec.captcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

public class Captcha {

    private int answer;

    private OutputStream out;

    public int getAnswer() {
        return answer;
    }

    public Captcha(OutputStream out) {
        this.out = out;
        init();
    }

    void init() {
        int a, b, c;
        a = random();
        b = random();
        c = randomOpr();
        System.out.println("a=" + a + " b=" + b + " c=" + c);

        switch (c) {
            case 0:
                answer = a + b;
                break;
            case 1:
                answer = a - b;
                break;
            case 2:
                answer = a * b;
                break;

            default:
                System.err.println("E: Impossible opr");
                break;
        }
        System.out.println("ans=" + answer);

        generateImage(a, b, c);
    }

    private boolean generateImage(int a, int b, int c) {
        int length = 400;
        int height = 200;
        var bi = new BufferedImage(length, height, BufferedImage.TYPE_INT_RGB);
        var g = (Graphics2D) bi.getGraphics();
        // g.setColor(Color.gray);
        g.setColor(new Color(random(128)+128,random(128)+128,random(128)+128));
        g.fillRect(0, 0, length, height);
        g.setFont(new Font("Arial", Font.BOLD, random(30)+15));

        char opr = 0;

        switch (c) {
            case 0:
                opr = '+';
                break;
            case 1:
                opr = '-';
                break;
            case 2:
                opr = '*';
                break;
            default:
                System.err.println("E: Impossible opr");
                break;
        }

        String str = String.valueOf(a) + opr + String.valueOf(b);
        System.out.println(str);

        // for (int i = 0; i < str.length(); i++) {
        //     var ch = str.charAt(i);
        //     g.draw
        // }
        g.setColor(new Color(random(128),random(128),random(128)));
        g.drawString(str, random(length >>1) + (length >> 2), random(height>>1) + (height >> 2)); // todo

        try {
            ImageIO.write(bi, "png", out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    static int random() {
        return (int) (Math.random() * 100);
    }

    static int random(int a) {
        return (int) (Math.random() * a);
    }

    static int random(double a) {
        return (int) (Math.random() * a);
    }

    static int randomOpr() {
        // 0,1,2 = + - *
        return (int) (Math.random() * 3);
    }
}
