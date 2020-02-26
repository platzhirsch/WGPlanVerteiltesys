package wgplanclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Requests {

    private static final String localhost = "http://localhost:8080/aufgabe/";

    public void get (int id) {
        try {
            URL url = new URL(localhost + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                System.out.println("+###############################################+");
                System.out.println("|############### HTTP code : " + conn.getResponseCode() + " ###############|");
                System.out.println("+###############################################+\n");
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String output;
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            conn.disconnect();

        } catch (
                MalformedURLException e) {

            e.printStackTrace();

        } catch (
                IOException e) {

            e.printStackTrace();

        }
    }

    public void post (String name, String aufTitel, String aufBeschr, Boolean done){
        try {

            URL url = new URL(localhost);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = "{\"name\":\"" + name +"\",\"aufTitel\":\"" + aufTitel +"\",\"aufBeschr\":\"" + aufBeschr +"\",\"done\":" + done +"}";
            System.out.println(input);
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                System.out.println("+###############################################+");
                System.out.println("|############### HTTP code : " + conn.getResponseCode() + " ###############|");
                System.out.println("+###############################################+");
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public void delete (int id) {
        try {
            URL url = new URL(localhost + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            conn.disconnect();

        } catch (
                MalformedURLException e) {

            e.printStackTrace();

        } catch (
                IOException e) {

            e.printStackTrace();

        }

    }

    public void put (int id, String name, String aufTitel, String aufBeschr, Boolean done){
        try {

            URL url = new URL(localhost + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = "{\"id\":" + id + ",\"name\":\"" + name + "\",\"aufTitel\":\"" + aufTitel + "\",\"aufBeschr\":\"" + aufBeschr + "\",\"done\":" + done + "}";
            System.out.println(input);
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                System.out.println("+###############################################+");
                System.out.println("|############### HTTP code : " + conn.getResponseCode() + " ###############|");
                System.out.println("+###############################################+");
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    }


