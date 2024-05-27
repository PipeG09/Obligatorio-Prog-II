import Entities.SpotifyData;
import java.time.LocalDate;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception {
        SpotifyData spotify = new SpotifyData();
        long[] durationFun= new long[6];
        int option = 0;
        boolean dataDownloaded = false;
        Scanner scanner = new Scanner(System.in);
        printSpotifyLogo();

        while (option != -1) {
            Thread.sleep(500);
            printMenu();
            option = scanner.nextInt();
            if (option != 1 && !dataDownloaded && option != 8) {
                option = 0;
                System.out.println("\nThe data has not been downloaded yet\n");
            }
            else {
                switch (option) {

                    case 1: {
                        if (!dataDownloaded) {
                            System.out.println("\nDownloading Spotify data...\n");
                            long startTime = System.nanoTime();
                            spotify.readData();
                            long endTime = System.nanoTime();
                            durationFun[0]= endTime - startTime;
                            dataDownloaded = true;
                            System.out.println("\nData was succesfully downloaded\n");
                        } else {
                            System.out.println("\nERROR! The data has already been downloaded \n");
                        }
                        System.out.print("Press enter to continue: ");
                        scanner.nextLine();
                        scanner.nextLine();
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
                            System.out.print("\nERROR! One of the values you entered might be invalid");
                            break;
                        }
                        long startTime = System.nanoTime();
                        spotify.top10DayCountry(date, country);
                        long endTime = System.nanoTime();
                        durationFun[1] = endTime - startTime;
                        System.out.println("\nPress enter to continue: ");
                        scanner.nextLine();
                        scanner.nextLine();
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
                            durationFun[2] = endTime - startTime;
                            System.out.println("\nPress enter to continue: ");
                            scanner.nextLine();
                            break;
                        } catch (Exception e) {
                            System.out.println("\nERROR! The date is invalid");
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
                                durationFun[3] = endTime - startTime;
                            } else {
                                long startTime = System.nanoTime();
                                spotify.top7Artist(date2, date1);
                                long endTime = System.nanoTime();
                                durationFun[3] = endTime - startTime;
                            }
                            System.out.println("\nPress enter to continue: ");
                            scanner.nextLine();
                            scanner.nextLine();
                            break;

                        } catch (Exception e) {
                            System.out.println("\nERROR! One of the values you entered might be invalid\n");
                            break;
                        }
                    }

                    case 5: {
                        System.out.print("\nEnter Artist name: ");
                        String artistName = scanner.next();
                        artistName = artistName + scanner.nextLine();
                        System.out.print("\nEnter the day of which Top 50's you want to check (YYYY-MM-DD): ");
                        String day = scanner.next();
                        scanner.nextLine();
                        try {
                            LocalDate date = LocalDate.parse(day);
                            long startTime = System.nanoTime();
                            int number = spotify.artistInDate(artistName, date);
                            long endTime = System.nanoTime();
                            durationFun[4]= endTime - startTime;
                            System.out.println(number);
                            System.out.println("\nPress enter to continue: ");
                            scanner.nextLine();
                            scanner.nextLine();
                        } catch (Exception e) {
                            System.out.print("\nERROR! A value you entered might be invalid\n");
                        }
                        break;
                    }

                    case 6: {
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
                            long startTime = System.nanoTime();
                            count = spotify.tempoInDate(date1date, date2date, Math.min(tempo1, tempo2), Math.max(tempo1, tempo2));
                            long endTime = System.nanoTime();
                            durationFun[5] = endTime - startTime;
                        } else {
                            long startTime = System.nanoTime();
                            count = spotify.tempoInDate(date2date, date1date, Math.min(tempo2, tempo1), Math.max(tempo1, tempo2));
                            long endTime = System.nanoTime();
                            durationFun[5] = endTime - startTime;
                        }
                        System.out.println(count);
                        System.out.println("\nPress enter to continue: ");
                        scanner.nextLine();
                        scanner.nextLine();
                        break;
                    }

                    case 7: {
                        System.out.println("\nReading csv duration = " + durationFun[0] / 1_000_000_000.0);
                        System.out.println("First function duration = " + durationFun[1] / 1_000_000_000.0);
                        System.out.println("Second function duration = " + durationFun[2] / 1_000_000_000.0);
                        System.out.println("Third function duration = " + durationFun[3] / 1_000_000_000.0);
                        System.out.println("Fourth function duration = " + durationFun[4] / 1_000_000_000.0);
                        System.out.println("Fifth function duration = " + durationFun[5] / 1_000_000_000.0 + "\n");
                        System.out.print("Press enter to continue: ");
                        scanner.nextLine();
                        scanner.nextLine();
                        break;
                    }

                    case 8: {
                        option = -1;
                        break;
                    }

                    default:
                        System.out.println("\nERROR! Option not recognized\n");
                        break;
                }
            }
        }
        printThanksOsitos();
        System.out.println("\nGoodbye and thanks for using spotify\n");
        scanner.close();
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


    public static void printMenu() throws InterruptedException {
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
        System.out.println("7. View the performance in tasks previously carried out ");
        System.out.println("8. Exit ");
        System.out.print("   Choose your option: ");
    }

    public static void printThanksOsitos() {
        String thanks = "   _     _      _     _      _     _      _     _      _     _      _     _   \n" +
                "  (c).-.(c)    (c).-.(c)    (c).-.(c)    (c).-.(c)    (c).-.(c)    (c).-.(c)  \n" +
                "   / ._. \\      / ._. \\      / ._. \\      / ._. \\      / ._. \\      / ._. \\   \n" +
                " __\\( Y )/__  __\\( Y )/__  __\\( Y )/__  __\\( Y )/__  __\\( Y )/__  __\\( Y )/__ \n" +
                "(_.-/'-'\\-._)(_.-/'-'\\-._)(_.-/'-'\\-._)(_.-/'-'\\-._)(_.-/'-'\\-._)(_.-/'-'\\-._)\n" +
                "   || T ||      || H ||      || A ||      || N ||      || K ||      || S ||   \n" +
                " _.' `-' '._  _.' `-' '._  _.' `-' '._  _.' `-' '._  _.' `-' '._  _.' `-' '._ \n" +
                "(.-./`-'\\.-.)(.-./`-'\\.-.)(.-./`-'\\.-.)(.-./`-'\\.-.)(.-./`-'\\.-.)(.-./`-`\\.-.)\n" +
                " `-'     `-'  `-'     `-'  `-'     `-'  `-'     `-'  `-'     `-'  `-'     `-' ";
        System.out.println(thanks);
    }
}


















