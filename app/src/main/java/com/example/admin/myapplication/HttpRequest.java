package com.example.admin.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BupycXp on 18.01.2016.
 */
public class HttpRequest extends AsyncTask<String, Void, String> {

    private static final String Tag = "myLogs";
    String responseStr;


        private Exception exception;

        protected String doInBackground(String... params) {
            try {
                //noinspection deprecation
                HttpClient client = new DefaultHttpClient();

                HttpPost post = new HttpPost(params[0].toString());

                List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
                nameValuePair.add(new BasicNameValuePair("user", "BupycXp"));
                nameValuePair.add(new BasicNameValuePair("pass", "321ewq#@!"));
                post.setEntity(new UrlEncodedFormEntity(nameValuePair, "UTF-8"));

                try {
                    post.setEntity(new UrlEncodedFormEntity(nameValuePair));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                try {
                    HttpResponse response = client.execute(post);
                    responseStr = response.getEntity().toString();
                    responseStr = EntityUtils.toString(response.getEntity());
                    //noinspection deprecation
                    response.getEntity().consumeContent();
                    return responseStr;

                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;

                } catch (Exception e) {
                    this.exception = e;
                    return null;
                }
            } catch (Exception e) {
                this.exception = e;
                return null;
            }
            return responseStr;
        }


        protected void onPostExecute(String result) {

            try {
                FileWriter writer = new FileWriter("test.txt");
                writer.write(responseStr);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }