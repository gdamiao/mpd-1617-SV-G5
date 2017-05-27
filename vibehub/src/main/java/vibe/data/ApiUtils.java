package main.java.vibe.data;

import com.google.gson.Gson;

import java.io.*;
import java.net.URL;

public class ApiUtils {

    public static final Gson gson = new Gson();
    public static String getApiKey(String url){
        try {
            URL keyFile = ClassLoader.getSystemResource(url);
            if(keyFile == null) {
                throw new IllegalStateException(
                        "YOU MUST GOT a KEY in api.setlist.fm and place it in src/main/resources/"+url);
            } else {
                InputStream keyStream = keyFile.openStream();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(keyStream))) {
                    return reader.readLine();
                }
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
