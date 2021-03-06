package com.example.admin.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;


public class MainActivity extends AppCompatActivity {

    Button button_connect;
    ImageView imageView;
    HttpRequest MHR;
    public static String HttpResult;


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


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button_connect:
                        MHR = new HttpRequest();
                        MHR.execute(getString(R.string.URL_login));
                        HttpResult = MHR.responseStr;
                        try {
                            Thread.sleep(2000);
                            if (HttpResult != null) {
                                Intent char_intent = new Intent(MainActivity.this, CharActivity.class);
                                startActivity(char_intent);
                            } else {
                                Toast toast = Toast.makeText(MainActivity.this, "Not connection!", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

//                        toast = Toast.makeText(MainActivity.this, "Авторизация прошла успешно!", Toast.LENGTH_SHORT);
//                        toast.setGravity(Gravity.CENTER, 0, 0);
                        break;
                }
            }
        };
        button_connect.setOnClickListener(onClickListener);
        client2 = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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
