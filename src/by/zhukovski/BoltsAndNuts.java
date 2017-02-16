package by.zhukovski;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class BoltsAndNuts {

    private int k1;
    private int k2;
    private int l1;
    private int l2;
    private int m1;
    private int m2;

    private String inputSource = "/resources/INPUT.TXT";
    private String outputSource = "/resources/OUTPUT.TXT";

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        new BoltsAndNuts().calculate();
        long endTime = System.currentTimeMillis();
        System.out.println("Потрачено времени - " + (endTime - startTime) + "мс");
        System.out.println("Используемая память - " + (Runtime.getRuntime().totalMemory() -
        Runtime.getRuntime().freeMemory())/ (1024) + "кб");

    }


    public void calculate (){

        read(inputSource);

        long cost;
        long missedBolts = (long) k1 * l1 / 100;
        long missedNuts = (long) k2 * l2 / 100;

        long unfitItemsCost = (k1 - missedBolts) > (k2 - missedNuts) ? ((k1 - missedBolts) -
                (k2 - missedNuts)) * m1 : ((k2 - missedNuts) - (k1 - missedBolts)) * m2;

        cost = (missedBolts * m1) + (missedNuts * m2) + unfitItemsCost;

        write(outputSource, cost);

    }

    public void read (String source){

        String root=System.getProperty("user.dir");
        source = root + source;

        File file = new File(source);

        Scanner scanner = null;
        try {
            scanner = new Scanner(file);

            k1 = scanner.nextInt();
            l1 = scanner.nextInt();
            m1 = scanner.nextInt();
            k2 = scanner.nextInt();
            l2 = scanner.nextInt();
            m2 = scanner.nextInt();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null){
                scanner.close();
            }
        }

    }

    public void write (String source, long cost){

        String root=System.getProperty("user.dir");

        source = root + source;

        try (FileWriter writer = new FileWriter(source, false)) {
            writer.write(Long.toString(cost));
        } catch (IOException e) {
            System.out.println("Произошла ошибка записи");
            e.printStackTrace();
        }

    }


}
