package Util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkOperations {

    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;

    public static String getJsonFromApi(String url) {

        String result;
        String inputLine;

        try {

            URL myUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection)
                    myUrl.openConnection();

                    connection.setRequestMethod(REQUEST_METHOD);
                    connection.setReadTimeout(READ_TIMEOUT);
                    connection.setConnectTimeout(CONNECTION_TIMEOUT);
                    connection.connect();
            InputStreamReader streamReader = new InputStreamReader
                    (connection.getInputStream());

            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();

            while((inputLine =reader.readLine())!=null) {
                stringBuilder.append(inputLine);

            }

            reader.close();
            streamReader.close();
            result =stringBuilder.toString();

            return result;

        }catch(Exception e){
            e.printStackTrace();
            result = "Abort";
        }

        return result;
    }

}
