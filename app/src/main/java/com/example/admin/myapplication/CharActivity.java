package com.example.admin.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class CharActivity extends MainActivity {

    StatResult st;
    String nameChar;
    String charValue;
    String HttpCharResult;
    TextView Text_Gold;
    TextView Text_Energy;
    TextView Text_Vitality;
    TextView Text_Hp;
    TextView Text_Xp;
    TextView Text_Fightvalue;
    TextView Text_Lvl;
    TextView Text_NameChar;
    TextView Text_Devilstones;
    int pos;


    // Логирование
    private static final String Tag = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char);

        Text_NameChar = (TextView) findViewById(R.id.textView_NameChar);
        Text_Gold = (TextView) findViewById(R.id.textView_gold);
        Text_Energy = (TextView) findViewById(R.id.textView_energy);
        Text_Vitality= (TextView) findViewById(R.id.textView_vitality);
        Text_Hp = (TextView) findViewById(R.id.textView_hp);
        Text_Xp = (TextView) findViewById(R.id.textView_xp);
        Text_Fightvalue = (TextView) findViewById(R.id.textView_fightvalue);
        Text_Lvl = (TextView) findViewById(R.id.textView_lvl);
        Text_Devilstones = (TextView) findViewById(R.id.textView_devilstones);


        Log.d(Tag, "Активити2.получить имя персонажа");

                        HttpCharResult = MHR.responseStr;
        /**                st = new StatResult();
                        st.execute( MHR.responseStr);
         */

        // получаем имя персонажа
        pos = HttpCharResult.indexOf("/profile/player");
        nameChar = HttpCharResult.substring(pos + 17, pos + 50);
        nameChar =nameChar.substring(nameChar.indexOf(">") + 1, nameChar.indexOf("<"));
        setTitle(nameChar);
        Text_NameChar.setText(nameChar);

    //получаем infobar_gold персонажа
    pos = HttpCharResult.indexOf("infobar_line_value") + 18;
    charValue = HttpCharResult.substring(pos, pos + 25);
    charValue = charValue.substring(charValue.indexOf(">") + 1, charValue.indexOf("<"));
    Text_Gold.setText(charValue);
        Log.d(Tag, "Активити2.получить золото персонажа");
try {
    // получаем energy
    pos = HttpCharResult.indexOf("infobar_energy")+15;
    charValue = HttpCharResult.substring(pos, pos + 450);
    pos = charValue.indexOf("infobar_line_value") + 18;
    charValue = charValue.substring(pos, pos + 25);
    charValue = charValue.substring(charValue.indexOf(">") + 1, charValue.indexOf("<"));
    charValue = charValue.replace("&nbsp;", "");
    Text_Energy.setText(charValue);
    Log.d(Tag, "Активити2.получить энергию персонажа");
} catch (Exception e){

}
        try{
    // получаем vitality
    pos = HttpCharResult.indexOf("infobar_vitality")+17;
    charValue = HttpCharResult.substring(pos, pos + 400);
    pos = charValue.indexOf("infobar_line_value") + 18;
    charValue = charValue.substring(pos, pos + 50);
    charValue = charValue.substring(charValue.indexOf(">") + 1, charValue.indexOf("<"));
    charValue = charValue.replace("&nbsp;", "");
    Text_Vitality.setText(charValue);
            Log.d(Tag, "Активити2.получить виталити персонажа");

    // получаем hp
    pos = HttpCharResult.indexOf("infobar_hp")+11;
    charValue = HttpCharResult.substring(pos, pos + 450);
    pos = charValue.indexOf("infobar_line_value") + 18;
    charValue = charValue.substring(pos, pos + 50);
    charValue = charValue.substring(charValue.indexOf(">") + 1, charValue.indexOf("<"));
    charValue = charValue.replace("&nbsp;", "");
    Text_Hp.setText(charValue);
            Log.d(Tag, "Активити2.получить hp персонажа");

    // получаем xp
    pos = HttpCharResult.indexOf("infobar_xp")+11;
    charValue = HttpCharResult.substring(pos, pos + 450);
    pos = charValue.indexOf("infobar_line_value") + 18;
    charValue = charValue.substring(pos, pos + 50);
    charValue = charValue.substring(charValue.indexOf(">") + 1, charValue.indexOf("<"));
    charValue = charValue.replace("&nbsp;", "");
    Text_Xp.setText(charValue);
            Log.d(Tag, "Активити2.получить xp персонажа");

    // получаем боевой показатель
    pos = HttpCharResult.indexOf("infobar_fightvalue")+19;
    charValue = HttpCharResult.substring(pos, pos + 450);
    pos = charValue.indexOf("infobar_line_value") + 18;
    charValue = charValue.substring(pos, pos + 50);
    charValue = charValue.substring(charValue.indexOf(">") + 1, charValue.indexOf("<"));
    Text_Fightvalue.setText(charValue);
            Log.d(Tag, "Активити2.получить боевой показатель персонажа");

    // получаем level
    pos = HttpCharResult.indexOf("infobar_level")+14;
    charValue = HttpCharResult.substring(pos, pos + 450);
    pos = charValue.indexOf("infobar_line_value") + 18;
    charValue = charValue.substring(pos, pos + 50);
    charValue = charValue.substring(charValue.indexOf(">") + 1, charValue.indexOf("<"));
    Text_Lvl.setText(charValue);
            Log.d(Tag, "Активити2.получить уровень персонажа");

    // получаем devilstones
    pos = HttpCharResult.indexOf("infobar_devilstones")+20;
    charValue = HttpCharResult.substring(pos, pos + 550);
    pos = charValue.indexOf("infobar_line_value") + 18;
    charValue = charValue.substring(pos, pos + 50);
    charValue = charValue.substring(charValue.indexOf(">") + 1, charValue.indexOf("<"));
    Text_Devilstones.setText(charValue);
            Log.d(Tag, "Активити2.получить камни персонажа");

} catch (Exception e) {

        }

        Toast toast = Toast.makeText(CharActivity.this, "Данные загружены", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
                }


    public class StatResult extends AsyncTask<String, Void, String> {

        private Exception exception;

        protected String doInBackground(String... params) {
            try {
                pos = params[0].indexOf("/profile/player");
                nameChar = params[0].substring(pos + 17, pos + 50);
                nameChar =nameChar.substring(nameChar.indexOf(">") + 1, nameChar.indexOf("<"));
                return nameChar;
            } catch (Exception e) {
                this.exception = e;
                return exception.toString();
            }
        }

        protected void onPostExecute(String result) {
            Log.d(Tag, "Активити2.записать имя персонажа в тексвью");
            Text_NameChar.setText(nameChar);
/**            try {
 FileWriter writer = new FileWriter("test.txt");
 writer.write(responseStr);
 } catch (IOException e) {
 e.printStackTrace();
 }**/
        }
    }
}

