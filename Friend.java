/*
 * HomeFriend
 */

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;


public class Friend {

    public static void openChrome() {
        try {
            Process open = Runtime.getRuntime().exec("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void enableDiary() throws FileNotFoundException {
        System.out.println("You're in diary mode! Please, write your node.");
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy_HH_mm");
        Scanner scanner = new Scanner(System.in);
        String name = "note" + format.format(d) + ".txt";
        PrintWriter pw = new PrintWriter(name);
        String note = scanner.nextLine();
        pw.println(note);
        pw.close();
    }

    public static boolean check(String str) throws FileNotFoundException {
        if (str.equalsIgnoreCase("Включи дневник")) {
            enableDiary();
            return true;
        }
        else if (str.equals("Открой браузер")) {
            openChrome();
            return true;
        }
        return false;
    }

    public static void main(String [] args) throws FileNotFoundException {
        final int N = 70, M = 20;
        String [][] dictionary = new String [N][M];
        Scanner scan = new Scanner(new File("dictionary.txt"));
        int lineCount = 0;
        do {
            String currentLine = scan.nextLine();
            dictionary[lineCount] = currentLine.split("@");
            lineCount++;
        } while (scan.hasNextLine());
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String words[], word;
        int r;
        boolean flag = false;
        Random random = new Random();
        while (!line.equalsIgnoreCase("Пока")) { 
            if (check(line)) {
                System.out.println("Что-нибудь еще?");
                line = sc.nextLine();
                continue;
            }
            words = line.split(" ");
            boolean markCheck = true;
            for (int i = 0; i < words.length; i++) {
                word = words[i];
                flag = false;
                for (int j = 0; j < lineCount && !flag; j++) {
                    if (word.equalsIgnoreCase(dictionary[j][0])) {
                        flag = true;
                        markCheck = false;
                        r = random.nextInt(dictionary[j].length - 2);
                        System.out.println(dictionary[j][r + 1]);
                    }
                }
            }
            if (markCheck) {
                r = random.nextInt(dictionary[1].length - 2);
                System.out.println(dictionary[1][r + 1]);
            }
            line = sc.nextLine();
        }
    }
}
