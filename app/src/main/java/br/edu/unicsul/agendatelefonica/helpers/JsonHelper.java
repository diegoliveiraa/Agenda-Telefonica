package br.edu.unicsul.agendatelefonica.helpers;

import android.os.AsyncTask;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonHelper {

    JsonCallback jsonCallback;

    public JsonHelper(JsonCallback jsonCallback) {
        this.jsonCallback = jsonCallback;
    }

    public interface JsonCallback {

        void onJsonDownloaded(String json);
    }

    public void downloadJson(String url) {

        new DownloadJson().execute(url);
    }


    private class DownloadJson extends AsyncTask<String, Void, String> {
        @Override
        //doInBackground executa a thread fora da UI
        protected String doInBackground(String... params) {
            // params[0] é a URL recebida
            try {
                return downloadJSON(params[0]); // executa a solicit. HTTP
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        //onPostExecute processa o resultado do AsyncTask (resposta do servidor)
        protected void onPostExecute(String result) {

            jsonCallback.onJsonDownloaded(result);

        }

        private String downloadJSON(String myurl) throws IOException {
            InputStream is = null;
            String respostaHttp = "";
            HttpURLConnection conn = null;
            InputStream in = null;
            ByteArrayOutputStream bos = null;
            int len;
            try {
                URL u = new URL(myurl);
                conn = (HttpURLConnection) u.openConnection();
                conn.setConnectTimeout(7000); // 7 segundos de timeout
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();
                in = conn.getInputStream();
                bos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                while ((len = in.read(buffer)) > 0) {
                    bos.write(buffer, 0, len);
                }
                respostaHttp = bos.toString("UTF-8");
                Log.d("xxx", "downloadJSON: " + respostaHttp);
                return respostaHttp;
            } catch (Exception ex) {
                Log.d("xxx", "downloadJSON Exception: " + ex);
                return null;
            } finally {
                if (in != null) in.close();
            }
        }
    }
}
