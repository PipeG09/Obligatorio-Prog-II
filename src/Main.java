import Entities.SpotifyData;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception {
        SpotifyData spotify = new SpotifyData();
        long durationFun0 = -1;
        long durationFun1 = -1;
        long durationFun2 = -1;
        long durationFun3 = -1;
        long durationFun4 = -1;
        long durationFun5 = -1;
        int option = 0;
        Scanner scanner = new Scanner(System.in);
        while (option != -1) {
            System.out.println("\n-----------------------------------");
            System.out.println("Welcome to Spotify \n Menu \n --------------------------");
            System.out.println("1. Load Data");
            System.out.println("2. Get Top 10 by country and date");
            System.out.println("3 Get Top 5 songs in more Top 50's for a specific day");
            System.out.println("4. Top 7 artist with more appearances in all Top 50's for a specific range of time");
            System.out.println("5. Get how many times a specific artist appears in the Top 50's in a given date ");
            System.out.println("6. Amount of songs with a particular tempo in Top 50's for a range of dates");
            System.out.print(" Choose your option: ");
            Thread.sleep(1000);
            option = scanner.nextInt();

            switch (option) {
                case 1: {
                    System.out.println("\nStarting data download");
                    long startTime = System.nanoTime();
                    spotify.readData();
                    long endTime = System.nanoTime();
                    durationFun0 = endTime - startTime;
                    break;
                }
                case 2: {

                    System.out.print("\nEnter a Date (YYYY-MM-DD): ");
                    String dateStr = scanner.next();
                    scanner.nextLine();
                    LocalDate date;
                    String country;
                    try {
                        date = LocalDate.parse(dateStr);
                        System.out.print("\nEnter country's abbreviation: ");
                        Thread.sleep(2000);
                        country = scanner.next();
                        scanner.nextLine();

                    } catch (Exception e) {
                        System.out.print("\nOne of the values you entered might be invalid");
                        break;
                    }
                    long startTime = System.nanoTime();
                    spotify.top10DayCountry(date, country);
                    long endTime = System.nanoTime();
                    durationFun1 = endTime - startTime;
                    break;
                }
                case 3: {
                    System.out.print("\nEnter a date (YYYY-MM-DD): ");
                    String dateStr = scanner.next();
                    scanner.nextLine();

                    try {
                        LocalDate date = LocalDate.parse(dateStr);
                        long startTime = System.nanoTime();
                        spotify.top5SongsInTop50(date);
                        long endTime = System.nanoTime();
                        durationFun2 = endTime - startTime;

                        break;
                    } catch (Exception e) {
                        System.out.println("\n The date is invalid");
                        break;
                    }
                }
                case 4: {

                    System.out.print("\nEnter one of the two dates that delimits the range (YYYY-MM-DD): ");
                    String dateStr = scanner.next();
                    scanner.nextLine();
                    System.out.print("\nEnter one of the two dates that delimits the range (YYYY-MM-DD): ");
                    String dateStr2 = scanner.next();
                    scanner.nextLine();
                    try {
                        LocalDate date1 = LocalDate.parse(dateStr);
                        LocalDate date2 = LocalDate.parse(dateStr2);
                        if (date1.isBefore(date2)) {
                            long startTime = System.nanoTime();
                            spotify.top7Artist(date1, date2);
                            long endTime = System.nanoTime();
                            durationFun3 = endTime - startTime;
                            break;

                        } else {
                            long startTime = System.nanoTime();
                            spotify.top7Artist(date2, date1);
                            long endTime = System.nanoTime();
                            durationFun3 = endTime - startTime;
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println("\nOne of the values you entered might be invalid");
                        break;
                    }
                }
                case 5: {
                    System.out.print("\nEnter Artist name separated by coma (eg: Post,Malone ): ");
                    String artistName = scanner.next();
                    artistName=artistName + scanner.nextLine();
                    System.out.print("\nEnter the Top 50's day you want to check: ");
                    String day = scanner.next();
                    scanner.nextLine();
                    try {
                        LocalDate date = LocalDate.parse(day);
                        long startTime = System.nanoTime();
                        int number = spotify.artistInDate(artistName, date);
                        long endTime = System.nanoTime();
                        durationFun4 = endTime - startTime;
                        System.out.println(number);

                    } catch (Exception e) {
                        System.out.print("\nA value you entered might be invalid");
                    }

                }
                case 6: {
                    break;
                }
                case 7: {

                    System.out.println("Reading csv duration = " + durationFun0 / 1_000_000_000.0);
                    System.out.println("First function duration = " + durationFun1 / 1_000_000_000.0);
                    System.out.println("Second function duration = " + durationFun2 / 1_000_000_000.0);
                    System.out.println("Third function duration = " + durationFun3 / 1_000_000_000.0);
                    System.out.println("Fourth function duration = " + durationFun4 / 1_000_000_000.0);

                }
                default:
                    System.out.println("option not recognized");
                    break;
            }
        }
        scanner.close();
    }
}













//        LocalDate date = LocalDate.parse("2024-01-01");
//        long startTime = System.nanoTime();
//        spotify.readData();
//        long endTime = System.nanoTime();
//        long duration = endTime - startTime;
//        System.out.println("Reading csv duration = "+duration/1_000_000_000.0);
//
//        startTime = System.nanoTime();
//        spotify.top10DayCountry(date,"UY");
//        endTime = System.nanoTime();
//        duration = endTime - startTime;
//        System.out.println("First function duration = "+duration/1_000_000_000.0);
//
//        startTime = System.nanoTime();
//        spotify.top5SongsInTop50(date);
//        endTime= System.nanoTime();
//        duration = endTime - startTime;
//        System.out.println("Second function duration = "+duration/1_000_000_000.0 +" \n");
//
//        startTime = System.nanoTime();
//        LocalDate endDate = LocalDate.parse("2024-05-13");
//        spotify.top7Artist(date,endDate);
//        endTime= System.nanoTime();
//        duration = endTime - startTime;
//        System.out.println("Third function duration = "+duration/1_000_000_000.0);
//
//        LocalDate date1= LocalDate.parse("2024-01-10");
//        int number= spotify.artistInDate("Noah Kahan",date1);
//        System.out.println(number);





