import Entities.SpotifyData;

import java.time.LocalDate;


public class Main {
    public static void main(String[] args) throws Exception {
        SpotifyData main = new SpotifyData();

        LocalDate date = LocalDate.parse("2024-05-10");
        long startTime = System.nanoTime();
        main.readData();
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Reading csv duration = "+duration/1_000_000_000.0);

        startTime = System.nanoTime();
        main.top10DayCountry(date,"UY");
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("First function duration = "+duration/1_000_000_000.0);

        startTime = System.nanoTime();
        main.top5SongsInTop50(date);
        endTime= System.nanoTime();
        duration = endTime - startTime;
        System.out.println("Second function duration = "+duration/1_000_000_000.0);

        System.out.println("Artists: ");
        System.out.println(main.getArtists().size());
        System.out.println(main.getArtists().loadFactor());
        System.out.println(main.getArtists().get("Benson Boone").size());
        System.out.println(main.getArtists().get("Benson Boone").loadFactor());


//        System.out.println("Songs ");
//        System.out.println(main.getSongs().size());
//        System.out.println(main.getSongs().loadFactor());
//
    System.out.println(main.getDailyRanks().size());
    System.out.println(main.getDailyRanks().loadFactor());
//        System.out.println(main.getDailyRanks().get(date).size());
//        System.out.println(main.getDailyRanks().get(date).loadFactor());
//        System.out.println(main.getDailyRanks().get(date).get("Global").size());
//        System.out.println(main.getDailyRanks().get(date).get("UY").loadFactor());




        //LocalDate date= LocalDate.parse("2024-03-18");
       // main.rankingByDayCountry(date,"Global");

    }
}

/*
"spotify_id,""name"",""artists"",""daily_rank"",""daily_movement"",""weekly_movement"",""country"",""snapshot_date"",""popularity"",""is_explicit"",""duration_ms"",""album_name"",""album_release_date"",""danceability"",""energy"",""key"",""loudness"",""mode"",""speechiness"",""acousticness"",""instrumentalness"",""liveness"",""valence"",""tempo"",""time_signature""";;
"6tNQ70jh4OwmPGpYy6R2o9,""Beautiful Things"",""Benson Boone"",""2"",""48"",""-1"","""",""2024-03-18"",""100"",""False"",""180304"",""Beautiful Things"",""2024-01-18"",""0.472"",""0.471"",""10"",""-5.692"",""1"",""0.0603"",""0.151"",""0.0"",""0.14"",""0.219"",""105.029"",""3""";;
"7AYmToZ5y67fBjq4RLFbuq,""TEXAS HOLD 'EM"",""Beyoncé"",""9"",""41"",""0"","""",""2024-03-18"",""87"",""True"",""235636"",""TEXAS HOLD 'EM"",""2024-02-10"",""0.725"",""0.709"",""2"",""-6.514"",""1"",""0.072"",""0.588"",""0.0"",""0.135"",""0.353"",""110.024"",""4""";;
"1VNWdmXSbaNrnWod10WIL0,""One Of The Girls (with JENNIE, Lily Rose Depp)"",""The Weeknd, JENNIE, Lily-Rose Depp"",""9"",""41"",""3"","""",""2024-03-18"",""86"",""False"",""244684"",""The Highlights (Deluxe)"",""2024-02-09"",""0.531"",""0.652"",""8"",""-6.249"",""1"",""0.0325"",""0.0524"",""1.39e-06"",""0.162"",""0.179"",""87.521"",""4""";;
"0R6NfOiLzLj4O5VbYSJAjf,""La Diabla"",""Xavi"",""11"",""39"",""-1"","""",""2024-03-18"",""96"",""False"",""172264"",""La Diabla"",""2023-11-30"",""0.751"",""0.819"",""11"",""-4.372"",""0"",""0.0409"",""0.255"",""0.0"",""0.236"",""0.697"",""131.842"",""3""";;
"3vkCueOmm7xQDoJ17W1Pm3,""My Love Mine All Mine"",""Mitski"",""11"",""39"",""5"","""",""2024-03-18"",""96"",""False"",""137773"",""The Land Is Inhospitable and So Are We"",""2023-09-15"",""0.504"",""0.308"",""9"",""-14.958"",""1"",""0.0321"",""0.868"",""0.135"",""0.158"",""0.121"",""113.95"",""4""";;
"5D34wRmbFS29AjtTOP2QJe,""yes, and?"",""Ariana Grande"",""12"",""38"",""-6"","""",""2024-03-18"",""84"",""True"",""214994"",""eternal sunshine"",""2024-03-08"",""0.785"",""0.766"",""1"",""-6.551"",""1"",""0.0503"",""0.194"",""7.57e-05"",""0.107"",""0.804"",""119.029"",""4""";;
"7bywjHOc0wSjGGbj04XbVi,""LUNA"",""Feid, ATL Jacob"",""13"",""37"",""8"","""",""2024-03-18"",""95"",""False"",""196800"",""FERXXOCALIPSIS"",""2023-12-01"",""0.774"",""0.86"",""7"",""-2.888"",""0"",""0.13"",""0.131"",""0.0"",""0.116"",""0.446"",""100.019"",""4""";;
"0mflMxspEfB0VbI1kyLiAv,""Stick Season"",""Noah Kahan"",""13"",""37"",""6"","""",""2024-03-18"",""95"",""False"",""182346"",""Stick Season"",""2022-10-14"",""0.662"",""0.488"",""9"",""-6.894"",""1"",""0.0682"",""0.782"",""0.0"",""0.102"",""0.817"",""117.913"",""4""";;


 */
