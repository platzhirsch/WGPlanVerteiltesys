package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Requests {

    private static final String localhost = "http://localhost:8080/aufgabe/";
    private String jsOutput;
    private String druckerInput;

    public void setJsOutput(String output) {
        jsOutput = output;
    }

    public String getJsOutput() {
        return jsOutput;
    }

    public String getDruckerInput() {
        return druckerInput;
    }

    public void setDruckerInput(String druckerInput) {
        this.druckerInput = druckerInput;
    }

    //Umformatieren des Rückgabewertes
    public String drucker(String druckerInput) {
        for (int i = 1; i <= druckerInput.length(); i++) {
            druckerInput = druckerInput.replaceAll("[{]", "");
            druckerInput = druckerInput.replaceAll("[\"]", " ");
            druckerInput = druckerInput.replaceAll("[\\[]", "");
            druckerInput = druckerInput.replaceAll("[]]", "");
            druckerInput = druckerInput.replaceAll("[,]", "\n");
            druckerInput = druckerInput.replaceAll("[}]", "\n\n");

            druckerInput = druckerInput.replaceAll("id", "ID");
            druckerInput = druckerInput.replaceAll("name", "Name");
            druckerInput = druckerInput.replaceAll("aufTitel", "Aufgaben Titel");
            druckerInput = druckerInput.replaceAll("aufBeschr", "Aufgaben Beschreibung");
            druckerInput = druckerInput.replaceAll("done", "Erledigt");
            druckerInput = druckerInput.replaceAll("true", " Ja");
            druckerInput = druckerInput.replaceAll("false", " Nein");
        }
        String druckerOutput = druckerInput;
        return druckerOutput;
    }

    public void get(int id) {
        try {
            URL url = new URL(localhost + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String output;
            while ((output = br.readLine()) != null) {
                setDruckerInput(output);
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

    public void post(String name, String aufTitel, String aufBeschr, Boolean done) {
        try {
            URL url = new URL(localhost);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = "{\"name\":\"" + name + "\",\"aufTitel\":\"" + aufTitel + "\",\"aufBeschr\":\"" + aufBeschr + "\",\"done\":" + done + "}";
            System.out.println(input);
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

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

    public void delete(int id) {
        try {
            URL url = new URL(localhost + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Accept", "application/json");

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

    public void put(int id, String name, String aufTitel, String aufBeschr, Boolean done) {
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


