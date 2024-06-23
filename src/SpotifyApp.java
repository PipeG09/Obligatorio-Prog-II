import Entities.SpotifyAccounts;
import Entities.SpotifyData;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Scanner;


public class SpotifyApp {
    public static void main(String[] args) throws Exception {
        SpotifyData spotify = new SpotifyData();
        long[] durationFun= new long[6];
        float[] ramUsageFun= new float[6];
        String option = "0";
        boolean dataDownloaded = false;
        Scanner scanner = new Scanner(System.in);
        printSpotifyLogo();
        Boolean[] login = login();
        while (!login[0]) {
            System.out.println("\nERROR! Please Login or Create an account\n");
            login = login();
            if (login[0]) {
                break;
            }
        }
        boolean developerMode = login[1];
        if (login[2]) {
            return;
        }
        while (!option.equals("-1")) {
            Thread.sleep(500);
            printMenu(developerMode);
            Runtime runtime = Runtime.getRuntime();
            runtime.gc();
            option = scanner.nextLine();

            if (!Objects.equals(option, "1") && !dataDownloaded && !Objects.equals(option, "8") && developerMode) {
                option = "0";
                System.out.println("\nERROR! The data has not been downloaded yet\n");
            } else if (!Objects.equals(option, "7") && !Objects.equals(option, "1") && !dataDownloaded && !developerMode) {
                option = "0";
                System.out.println("\nERROR! The data has not been downloaded yet\n");
            } else {
                switch (option) {

                    case "1": {
                        if (!dataDownloaded) {
                            System.out.println("\nDownloading Spotify data...");
                            runtime.gc();
                            float memoryBefore = runtime.totalMemory() - runtime.freeMemory();
                            long startTime = System.nanoTime();
                            spotify.readData();
                            long endTime = System.nanoTime();
                            float memoryAfter = runtime.totalMemory() - runtime.freeMemory();
                            ramUsageFun[0] = (memoryAfter - memoryBefore) / 1024 / 1024;
                            durationFun[0]= endTime - startTime;
                            dataDownloaded = true;
                            System.out.println("\nData was succesfully downloaded\n");
                        } else {
                            System.out.println("\nERROR! The data has already been downloaded \n");
                        }
                        System.out.print("Press enter to continue: ");
                        scanner.nextLine();
                        break;
                    }

                    case "2": {
                        System.out.print("\nEnter a Date (YYYY-MM-DD): ");
                        String dateStr = scanner.next();
                        scanner.nextLine();
                        LocalDate date;
                        String country;
                        try {
                            date = LocalDate.parse(dateStr);
                            System.out.print("\nEnter country's abbreviation: ");
                            Thread.sleep(2000);
                            country = scanner.next().toUpperCase();
                            scanner.nextLine();
                        } catch (Exception e) {
                            System.out.print("\nERROR! The date is invalid");
                            break;
                        }
                        float memoryBefore = runtime.totalMemory() - runtime.freeMemory();
                        long startTime = System.nanoTime();
                        spotify.top10DayCountry(date, country);
                        long endTime = System.nanoTime();
                        float memoryAfter = runtime.totalMemory() - runtime.freeMemory();
                        ramUsageFun[1] = (memoryAfter - memoryBefore) / 1024 / 1024;
                        durationFun[1] = endTime - startTime;
                        System.out.println("\nPress enter to continue: ");
                        scanner.nextLine();
                        break;
                    }

                    case "3": {
                        System.out.print("\nEnter a date (YYYY-MM-DD): ");
                        String dateStr = scanner.next();
                        scanner.nextLine();

                        try {
                            LocalDate date = LocalDate.parse(dateStr);
                            runtime.gc();
                            float memoryBefore = runtime.totalMemory() - runtime.freeMemory();
                            long startTime = System.nanoTime();
                            spotify.top5SongsInTop50(date);
                            long endTime = System.nanoTime();
                            float memoryAfter = runtime.totalMemory() - runtime.freeMemory();
                            System.out.println(memoryAfter / 1024 / 1024 + " MB");
                            ramUsageFun[2] = (memoryAfter - memoryBefore) / 1024 / 1024;
                            durationFun[2] = endTime - startTime;
                            System.out.println("\nPress enter to continue: ");
                            scanner.nextLine();
                            break;
                        } catch (Exception e) {
                            System.out.println("\nERROR! The date is invalid");
                            break;
                        }

                    }

                    case "4": {
                        runtime.gc();
                        System.out.print("\nEnter one of the two dates that delimits the range (YYYY-MM-DD): ");
                        String dateStr = scanner.next();
                        scanner.nextLine();
                        System.out.print("\nEnter one of the two dates that delimits the range (YYYY-MM-DD): ");
                        String dateStr2 = scanner.next();
                        scanner.nextLine();
                        try {
                            LocalDate date1 = LocalDate.parse(dateStr);
                            LocalDate date2 = LocalDate.parse(dateStr2);
                            float memoryBefore = runtime.totalMemory() - runtime.freeMemory();
                            if (date1.isBefore(date2)) {
                                long startTime = System.nanoTime();
                                spotify.top7Artist(date1, date2);
                                long endTime = System.nanoTime();
                                float memoryAfter = runtime.totalMemory() - runtime.freeMemory();
                                ramUsageFun[3] = (memoryAfter - memoryBefore) / 1024 / 1024;
                                durationFun[3] = endTime - startTime;
                            } else {
                                long startTime = System.nanoTime();
                                spotify.top7Artist(date2, date1);
                                long endTime = System.nanoTime();
                                float memoryAfter = runtime.totalMemory() - runtime.freeMemory();
                                ramUsageFun[3] = (memoryAfter - memoryBefore) / 1024 / 1024;
                                durationFun[3] = endTime - startTime;
                            }
                            System.out.println("\nPress enter to continue: ");
                            scanner.nextLine();
                            break;

                        } catch (Exception e) {
                            System.out.println("\nERROR! One of the values you entered might be invalid\n");
                            break;
                        }
                    }

                    case "5": {
                        System.out.print("\nEnter Artist name: ");
                        String artistName = scanner.next();
                        artistName = artistName + scanner.nextLine();
                        System.out.print("\nEnter the day of which Top 50's you want to check (YYYY-MM-DD): ");
                        String day = scanner.next();
                        scanner.nextLine();
                        try {
                            LocalDate date = LocalDate.parse(day);
                            float memoryBefore = runtime.totalMemory() - runtime.freeMemory();
                            long startTime = System.nanoTime();
                            int number = spotify.artistInDate(artistName, date);
                            long endTime = System.nanoTime();
                            float memoryAfter = runtime.totalMemory() - runtime.freeMemory();
                            ramUsageFun[4] = (memoryAfter - memoryBefore) / 1024 / 1024;
                            durationFun[4]= endTime - startTime;
                            System.out.println(number);
                            System.out.println("\nPress enter to continue: ");
                            scanner.nextLine();
                        } catch (Exception e) {
                            System.out.print("\nERROR! A value you entered might be invalid\n");
                        }
                        break;
                    }

                    case "6": {
                        try {
                            System.out.print("\nEnter one of the values that delimits the range of tempo: ");
                            float tempo1 = scanner.nextFloat();
                            scanner.nextLine();
                            System.out.print("\nEnter the other value that delimits the range of tempo: ");
                            float tempo2 = scanner.nextFloat();
                            scanner.nextLine();
                            System.out.print("\nEnter one of the dates that delimits the range of time (YYYY-MM-DD): ");
                            String date = scanner.next();
                            scanner.nextLine();
                            LocalDate date1date = LocalDate.parse(date);
                            System.out.print("\nEnter the other date that delimits the range of time (YYYY-MM-DD): ");
                            String date2 = scanner.next();
                            scanner.nextLine();
                            LocalDate date2date = LocalDate.parse(date2);
                            int count;
                            if (date1date.isBefore(date2date)) {
                                float memoryBefore = runtime.totalMemory() - runtime.freeMemory();
                                long startTime = System.nanoTime();
                                count = spotify.tempoInDate(date1date, date2date, Math.min(tempo1, tempo2), Math.max(tempo1, tempo2));
                                long endTime = System.nanoTime();
                                float memoryAfter = runtime.totalMemory() - runtime.freeMemory();
                                ramUsageFun[5] = (memoryAfter - memoryBefore) / 1024 / 1024;
                                durationFun[5] = endTime - startTime;
                            } else {
                                float memoryBefore = runtime.totalMemory() - runtime.freeMemory();
                                long startTime = System.nanoTime();
                                count = spotify.tempoInDate(date2date, date1date, Math.min(tempo2, tempo1), Math.max(tempo1, tempo2));
                                long endTime = System.nanoTime();
                                float memoryAfter = runtime.totalMemory() - runtime.freeMemory();
                                ramUsageFun[5] = (memoryAfter - memoryBefore) / 1024 / 1024;
                                durationFun[5] = endTime - startTime;
                            }
                            System.out.println(count);

                        }
                        catch (Exception e) {
                            System.out.print("\nERROR! A value you entered might be invalid\n");
                        }
                        System.out.println("\nPress enter to continue: ");
                        scanner.nextLine();
                        break;
                    }

                    case "7": {
                        if (developerMode) {
                            System.out.println("\nReading csv duration = " + durationFun[0] / 1_000_000_000.0 + ", RAM Usage: " + ramUsageFun[0] + " MB");
                            System.out.println("First function duration = " + durationFun[1] / 1_000_000_000.0 + ", RAM Usage " + ramUsageFun[1] + " MB");
                            System.out.println("Second function duration = " + durationFun[2] / 1_000_000_000.0 + ", RAM Usage " + ramUsageFun[2] + " MB");
                            System.out.println("Third function duration = " + durationFun[3] / 1_000_000_000.0 + ", RAM Usage " + ramUsageFun[3] + " MB");
                            System.out.println("Fourth function duration = " + durationFun[4] / 1_000_000_000.0 + ", RAM Usage " + ramUsageFun[4] + " MB");
                            System.out.println("Fifth function duration = " + durationFun[5] / 1_000_000_000.0 + ", RAM Usage " + ramUsageFun[5] + " MB" + "\n");
                            System.out.print("Press enter to continue: ");
                            scanner.nextLine();
                            scanner.nextLine();
                        }
                        else {
                            option = "-1";
                        }
                        break;
                    }

                    case "8": {
                        if (developerMode) {
                            option = "-1";
                            break;
                        }
                    }

                    default:
                        System.out.println("\nERROR! Option not recognized\n");
                        break;
                }
            }
        }
        System.out.println("\nGoodbye and thanks for using spotify\n");
        scanner.close();
    }

    public static Boolean[] login() {
        System.out.println("1. Login\n2. Create new user\n3. Guest\n4. Exit");
        System.out.print("   Choose your option: ");
        LinkedList<String> developers = new LinkedList<>();
        developers.add("rolo");
        developers.add("Pipeg");
        developers.add("degiu2110");
        Scanner scanner2 = new Scanner(System.in);
        String variable = scanner2.nextLine();
        switch (variable) {
            case "1" -> {
                System.out.print("\nEnter the username: ");
                String username = scanner2.next();
                System.out.print("Enter the password: ");
                String password = scanner2.next();
                return new Boolean[] {SpotifyAccounts.verifyAccount(username, password), developers.contains(username), false};
            }
            case "2" -> {
                System.out.print("\nEnter the username: ");
                String username = scanner2.next();
                System.out.print("Enter the password: ");
                String password = scanner2.next();
                return new Boolean[] {SpotifyAccounts.createAccount(username, password), developers.contains(username), false};
            }
            case "3" -> {
                return new Boolean[] {true, false, false};
            }
            case "4" -> {
                return new Boolean[] {true, true, true};
            }
            default -> {
                System.out.println("\nERROR! Option not recognized\n");
                return login();
            }
        }
    }


    public static void printSpotifyLogo() throws InterruptedException {
        String asciiSpotifyLogo = """
                ⠀⠀⠀⠀⠀⠀⠀⢀⣠⣤⣤⣶⣶⣶⣶⣤⣤⣄⡀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⢀⣤⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣤⡀⠀⠀⠀⠀
                ⠀⠀⠀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⠀⠀⠀
                ⠀⢀⣾⣿⡿⠿⠛⠛⠛⠉⠉⠉⠉⠛⠛⠛⠿⠿⣿⣿⣿⣿⣿⣷⡀⠀
                ⠀⣾⣿⣿⣇⠀⣀⣀⣠⣤⣤⣤⣤⣤⣀⣀⠀⠀⠀⠈⠙⠻⣿⣿⣷⠀
                ⢠⣿⣿⣿⣿⡿⠿⠟⠛⠛⠛⠛⠛⠛⠻⠿⢿⣿⣶⣤⣀⣠⣿⣿⣿⡄
                ⢸⣿⣿⣿⣿⣇⣀⣀⣤⣤⣤⣤⣤⣄⣀⣀⠀⠀⠉⠛⢿⣿⣿⣿⣿⡇
                ⠘⣿⣿⣿⣿⣿⠿⠿⠛⠛⠛⠛⠛⠛⠿⠿⣿⣶⣦⣤⣾⣿⣿⣿⣿⠃
                ⠀⢿⣿⣿⣿⣿⣤⣤⣤⣤⣶⣶⣦⣤⣤⣄⡀⠈⠙⣿⣿⣿⣿⣿⡿⠀
                ⠀⠈⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣾⣿⣿⣿⣿⡿⠁⠀
                ⠀⠀⠀⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠀⠀⠀
                ⠀⠀⠀⠀⠈⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠛⠁⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠈⠙⠛⠛⠿⠿⠿⠿⠛⠛⠋⠁⠀⠀⠀⠀⠀⠀⠀

                """;

        String asciiSpotifyName = """
                 .oooooo..o                          .    o8o   .o88o.  \s
                d8P'    `Y8                        .o8    `"'   888 `"            \s
                Y88bo.      oo.ooooo.   .ooooo.  .o888oo oooo  o888oo  oooo    ooo\s
                 `"Y8888o.   888' `88b d88' `88b   888   `888   888     `88.  .8' \s
                     `"Y88b  888   888 888   888   888    888   888      `88..8'  \s
                oo     .d8P  888   888 888   888   888 .  888   888       `888'   \s
                8""88888P'   888bod8P' `Y8bod8P'   "888" o888o o888o       .8'    \s
                             888                                       .o..P'     \s
                            o888o                                      `Y8P'      \s
                """;

        String[] logoLines = asciiSpotifyLogo.split("\n");
        String[] nameLines = asciiSpotifyName.split("\n");

        int logoHeight = logoLines.length;
        int nameHeight = nameLines.length;

        int paddingLines = (logoHeight - nameHeight) / 2;

        StringBuilder combinedAscii = new StringBuilder();

        for (int i = 0; i < logoHeight; i++) {
            combinedAscii.append(logoLines[i]);
            if (i >= paddingLines && i < paddingLines + nameHeight) {
                combinedAscii.append("  ").append(nameLines[i - paddingLines]);
            }
            System.out.println(combinedAscii);
            Thread.sleep(250);
            combinedAscii = new StringBuilder();
        }

        System.out.println("\n\n");
    }


    public static void printMenu(boolean developerMode) throws InterruptedException {
        if (developerMode) {
            System.out.println("\nDeveloper Account");
        }
        String menu = """ 
                
                ---------------------------------------------------
                oooo     oooo ooooooooooo oooo   oooo ooooo  oooo\s
                 8888o   888   888    88   8888o  88   888    88 \s
                 88 888o8 88   888ooo8     88 888o88   888    88 \s
                 88  888  88   888    oo   88   8888   888    88 \s
                o88o  8  o88o o888ooo8888 o88o    88    888oo88  \s
                ---------------------------------------------------
                
                """;

        System.out.println(menu);
        Thread.sleep(500);
        System.out.println("1. Load Data ");
        System.out.println("2. Get Top 10 by country and date ");
        System.out.println("3. Get Top 5 songs in more Top 50's for a specific day ");
        System.out.println("4. Top 7 artist with more appearances in all Top 50's for a specific range of time");
        System.out.println("5. Get how many times a specific artist appears in the Top 50's in a given date ");
        System.out.println("6. Amount of songs in Top 50's for a range of dates within a range of tempo ");
        if (developerMode) {
            System.out.println("7. View the performance in tasks previously carried out ");
            System.out.println("8. Exit ");
        } else {
            System.out.println("7. Exit ");
        }
        System.out.print("   Choose your option: ");
    }
}


















