package org.example;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static final String API_KEY = "02a9ab20687341114d1eaa941d545fc7";
    static LinkedTreeMap data;

    public static void function(String city_name){
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + city_name + "&appid=" + API_KEY);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();

            if (responseCode != 200) {
                System.out.println("Error occurred...code: " + responseCode);
            } else {

                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }

                scanner.close();


                Gson parse = new Gson();
                data = (LinkedTreeMap) parse.fromJson(informationString.toString(), LinkedTreeMap.class).get("main");

            }
        } catch (Exception e) {
            System.out.println("Exception occurred");
            e.printStackTrace();
        }
    }
}

