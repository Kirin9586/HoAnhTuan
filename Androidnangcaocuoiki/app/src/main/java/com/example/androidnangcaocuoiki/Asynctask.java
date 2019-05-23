package com.example.androidnangcaocuoiki;

import android.content.Context;
import android.os.AsyncTask;
import android.util.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
public class Asynctask extends AsyncTask<String,Boolean, JSONObject> {
    private Map<String,String> mMap;
    private Context mcontext;
    private iData mData;
    public Asynctask(Context mcontext, iData mData, Map<String,String>mMap){
        this.mcontext = mcontext;
        this.mMap = mMap;
        this.mData = mData;

    }
    @Override
    protected JSONObject doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("Content-Type","application/json");
            connection.setRequestMethod("POST");

            JSONObject jsonObject = new JSONObject();
            Iterator iterator  = mMap.keySet().iterator();
            while (iterator.hasNext()){
                String key = (String) iterator.next();
                String value = mMap.get(key);
                jsonObject.put(key,value);
            }
            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            bufferedWriter.write(String.valueOf(jsonObject));
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            //------------//
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            String result;
            while ((result = bufferedReader.readLine()) != null){
                stringBuffer.append(result);
            }
            result  = stringBuffer.toString();
            JSONObject parentObject = new JSONObject(result);
            return parentObject;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
            if(jsonObject != null){
                try {
                    int result =jsonObject.getInt("result");
                    String mMessage = jsonObject.getString("response_message");
                    if (result > 0){
                        JSONArray child = jsonObject.getJSONArray("response_data");
                        mData.onGetDataSuccess(mMessage,child);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
    }
}
