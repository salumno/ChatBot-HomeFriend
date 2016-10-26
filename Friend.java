
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Friend {

    public static void openChrome() {
        try {
            Process open = Runtime.getRuntime().exec("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void openVk() {
        try {
            Process open = Runtime.getRuntime().exec("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe --app=\"https://vk.com/\"");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void openMail() {
        try {
            Process open = Runtime.getRuntime().exec("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe --app=\"https://mail.google.com/\"");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void openTranslator() {
        try {
            Process open = Runtime.getRuntime().exec("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe --app=\"https://translate.google.ru/\"");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void openRep() {
        try {
            Process open = Runtime.getRuntime().exec("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe --app=\"https://bitbucket.org/VladislavToporov/toporov_11601\"");
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
        else if (str.equalsIgnoreCase("Открой браузер")) {
            openChrome();
            return true;
        }
        else if (str.equalsIgnoreCase("Открой вконтакте")) {
            openVk();
            return true;
        }
        else if (str.equalsIgnoreCase("Открой почту")) {
            openMail();
            return true;
        }
        else if (str.equalsIgnoreCase("Открой переводчик")) {
            openTranslator();
            return true;
        }
        else if (str.equalsIgnoreCase("Открой мой репозиторий")) {
            openRep();
            return true;
        }
        return false;
    }

    public static boolean checkWithReg(String word, String pattern) {
        String temp = word.toLowerCase();
        Pattern p = Pattern.compile(pattern, Pattern.UNICODE_CHARACTER_CLASS);
        Matcher m = p.matcher(temp);
        return m.matches();
    }

    public static void main(String [] args) throws FileNotFoundException {
        System.out.println("Привет! Меня зовут HomeFriend! Поболтаем?");
        final int N = 70, M = 20;
        String [][] dictionary = new String [N][M];
        String[] pattern = new String[N];
        int lineCount = 0;

        Scanner sc = new Scanner(new File("deque.txt")); // Заполняем массив паттернов
        do {
            pattern[lineCount] = sc.nextLine();
            lineCount++;
        } while (sc.hasNextLine());

        sc = new Scanner(new File("testdeque.txt")); // Заполняем массив соответствующих паттернам ответов
        int inx = 0;
        do {
            String currentLine = sc.nextLine();
            dictionary[inx] = currentLine.split("@");
            inx++;
        } while (sc.hasNextLine());

        sc = new Scanner(System.in);
        String line = sc.nextLine();
        String words[], word;
        int r;
        boolean flag;
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
                if (word.charAt(word.length() - 1) == '.' || word.charAt(word.length() - 1) == '?' ||
                        word.charAt(word.length() - 1) == ',' || word.charAt(word.length() - 1) == '!') {
                    word = word.substring(0, word.length() - 1);
                }
                flag = false;
                for (int j = 0; j < lineCount && !flag; j++) {
                    if (checkWithReg(word, pattern[j])) {
                        flag = true;
                        markCheck = false;
                            r = random.nextInt(dictionary[j].length - 1);
                        System.out.print(dictionary[j][r] + " ");
                    }
                }
            }
            if (markCheck) {
                r = random.nextInt(dictionary[0].length - 1);
                System.out.print(dictionary[0][r]);
            }
            System.out.println();
            line = sc.nextLine();
        }
        r = random.nextInt(dictionary[5].length - 1); // Hardcode: line with parting words = 5?
        System.out.println(dictionary[5][r]);
    }
}