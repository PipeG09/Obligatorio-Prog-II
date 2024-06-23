package Entities;

import java.io.*;


public class SpotifyAccounts {
    public static boolean createAccount(String username, String password) {
        File file = new File("csv/accounts.csv");
        if (username.contains(",") || password.contains(",")) {
            System.out.println("Invalid username or password");
            return false;
        }
        String[] data = {username, password};
        try (FileWriter fileWriter = new FileWriter(file, true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
             if (!usernameUsed(username)) {
                 for (int i = 0; i < data.length; i++) {
                     printWriter.print(data[i]);
                     if (i < data.length - 1) {
                         printWriter.print(",");
                     }
                 }
                 printWriter.println();
             }
             else {
                 return false;
             }
        } catch (Exception _) {}
        System.out.println("Account created");
        return true;
    }

    public static boolean verifyAccount(String username, String password) {
        File file = new File("csv/accounts.csv");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                String[] row = line.split(",");
                if (row[0].equals(username)) {
                    if (row[1].equals(password)) {
                        return true;
                    }
                    System.out.println("Incorrect password\n");
                    return false;
                }
                line = reader.readLine();
            }
        } catch (Exception _) {}
        return false;
    }

    private static boolean usernameUsed(String username) {
        File file = new File("csv/accounts.csv");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                String[] row = line.split(",");
                if (row[0].equals(username)) {
                    return true;
                }
                line = reader.readLine();
            }
        } catch (Exception _) {}
        return false;
    }
}
