package com.example.admin.myapplication;

import android.inputmethodservice.ExtractEditText;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    Button button_connect;
    ImageView imageView;
    Toast toast;
    MyHttpResponse MHR;
    String responseStr;
    ExtractEditText memo;

    // Логирование
    private static final String Tag = "myLogs";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //изменение заголовка
        setTitle("Мое приложение");
        //вывести в логи отладчика
        Log.d(Tag, "Заголовок изменен!");

        imageView = (ImageView) findViewById(R.id.imageView);

        button_connect = (Button) findViewById(R.id.button_connect);

        memo = (ExtractEditText) findViewById(R.id.memo);


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button_connect:
                        toast = Toast.makeText(MainActivity.this, "АВТОРИЗАЦИЯ ПРОШЛА УСПЕШНО!", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        //toast.show();
                        MHR = new MyHttpResponse();
                        MHR.execute();
                        break;
                }
            }
        };
        button_connect.setOnClickListener(onClickListener);
        client2 = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    class MyHttpResponse extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected String doInBackground(Void... params) {
            try {
                //noinspection deprecation
                HttpClient client = new DefaultHttpClient();

                HttpPost post = new HttpPost(getString(R.string.URL));
                Log.d(Tag, "Запрос начат: " + getString(R.string.URL));

                List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
                nameValuePair.add(new BasicNameValuePair("user", "BupycXp"));
                nameValuePair.add(new BasicNameValuePair("pass", "321ewq#@!"));
                post.setEntity(new UrlEncodedFormEntity(nameValuePair,"UTF-8"));
                Log.d(Tag, "Параметры добавлены");
                Log.d(Tag, nameValuePair.toString());

                try {
                    post.setEntity(new UrlEncodedFormEntity(nameValuePair));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    Log.d(Tag, e.toString());
                }

                try {
                    HttpResponse response = client.execute(post);
                    Log.d(Tag, response.toString());

                    responseStr = response.getEntity().toString();
                    Log.d(Tag, responseStr);

                    responseStr = EntityUtils.toString(response.getEntity());
                    //noinspection deprecation
                    response.getEntity().consumeContent();
                    return responseStr;

                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                    Log.d(Tag, e.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d(Tag, e.toString());
                    return null;

                } catch (Exception e) {
                    this.exception = e;
                    Log.d(Tag, e.toString());
                    return null;
                }
            } catch (Exception e) {
                this.exception = e;
                Log.d(Tag, e.toString());
                return null;
            }
            return responseStr;
        }


        protected void onPostExecute(String result) {
            toast.show();
        }

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        menu.add(2, 5, 105, "пункт5").setCheckable(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.character_overview:
//                imageView.setVisibility(View.INVISIBLE);
                break;
            case R.id.character_cache:
                toast = Toast.makeText(MainActivity.this, "Выбран пункт меню " + getString(R.string.menu_character_cache), Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.character_tribe:
                toast = Toast.makeText(MainActivity.this, "Выбран пункт меню " + getString(R.string.menu_character_tribe), Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.character_set:
                toast = Toast.makeText(MainActivity.this, "Выбран пункт меню " + getString(R.string.menu_character_set), Toast.LENGTH_SHORT);
                toast.show();
                break;
            case 5:
                item.setChecked(!item.isChecked());
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.admin.myapplication/http/host/path")
        );
        AppIndex.AppIndexApi.start(client2, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.admin.myapplication/http/host/path")
        );
        AppIndex.AppIndexApi.end(client2, viewAction);
        client2.disconnect();
    }



};
