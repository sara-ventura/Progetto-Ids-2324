package it.unicam.cs.ids.GeoPlus.Model.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

@Component
public class SistemaOSM {
    private static final String BASE_URL = "https://nominatim.openstreetmap.org";

    public boolean verificaComuneEsiste(String nomeComune) {
        try {
            String encodedName = URLEncoder.encode(nomeComune, StandardCharsets.UTF_8);
            String apiUrl = String.format("%s/search?city=%s&format=json&limit=1", BASE_URL, encodedName);
            String response = sendGetRequest(apiUrl);
            JSONArray jsonArray = new JSONArray(response);
            if (jsonArray.length() > 0) {
                JSONObject firstResult = jsonArray.getJSONObject(0);
                return firstResult.has("display_name") && !firstResult.getString("display_name").isEmpty();
            }
            return false;
        } catch (JSONException e) {
            System.err.println("Errore di parsing JSON: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Errore: " + e.getMessage());
            return false;
        }
    }

    public boolean contieneCoordinate(Coordinate coord, String nomeComune) {
        String apiUrl = String.format(Locale.US, "%s/reverse?format=json&lat=%.6f&lon=%.6f&zoom=10&addressdetails=1",
                BASE_URL, coord.getLatitudine(), coord.getLongitudine());
        try {
            String jsonResponse = sendGetRequest(apiUrl);
            return jsonResponse.contains("\"" + nomeComune + "\"");
        } catch (Exception e) {
            System.err.println("Errore: " + e.getMessage());
        }
        return false;
    }

    public String getNomeComuneDaCoordinate(Coordinate coord) {
        String apiUrl = String.format(Locale.US, "%s/reverse?format=json&lat=%.6f&lon=%.6f&addressdetails=1",
                BASE_URL, coord.getLatitudine(), coord.getLongitudine());
        try {
            String jsonResponse = sendGetRequest(apiUrl);
            JSONObject jsonObject = new JSONObject(jsonResponse);
            if (jsonObject.has("address")) {
                JSONObject address = jsonObject.getJSONObject("address");
                if (address.has("city")) {
                    return address.getString("city");
                } else if (address.has("town")) {
                    return address.getString("town");
                } else if (address.has("village")) {
                    return address.getString("village");
                }
            }
        } catch (JSONException e) {
            System.err.println("Errore di parsing JSON: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Errore: " + e.getMessage());
        }
        return null;
    }


    private String sendGetRequest(String urlString) throws Exception {
        URI uri = URI.create(urlString);
        URL url = uri.toURL();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        StringBuilder response = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }
        return response.toString();
    }

    public String formattaNomeComune(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return nome;
        }
        return Arrays.stream(nome.trim().toLowerCase().split("\\s+"))
                .filter(parola -> !parola.isEmpty())
                .map(parola -> Character.toUpperCase(parola.charAt(0)) + parola.substring(1))
                .collect(Collectors.joining(" "));
    }
}
