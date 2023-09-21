package com.example.martial_arts_handbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {
    private ListView lv;                            // List view
    private Button btnSearchClear;                  // Button clear
    ArrayAdapter<String> adapter;                   // Listview Adapter
    EditText inputSearch;                           // Search EditText
    //ArrayList<HashMap<String, String>> artList;     // ArrayList for Listview
    String artName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.rotate);
        Animation animSizeDec = AnimationUtils.loadAnimation(this, R.anim.size_decrease);
        Animation animSizeInc = AnimationUtils.loadAnimation(this, R.anim.size_increase);

        // Listview Data
        Art a1 = new Art("Aikido", "JP", "Aikido is a martial arts style focused on redirecting the attack away from you. Aikido concentrates on throwing, joint locks, traditional Japanese weapons, etc.", "aikido");
        Art a2 = new Art("Aikijujitsu", "JP", "Aikijujitsu is a sub-genre of Jujutsu. In contrast to Jujutsu, Aikijujitsu focuses more heavily on blending with the opponent, moving joint-locks, and other esoteric principles.", "aikijujitsu");
        Art a3 = new Art("Araki Ryu", "JP", "Araki Ryu is a martial arts focused on traditional Japanese weapons such as the sword, spear, staff, etc.", "araki_ryu");
        Art a4 = new Art("Bajutsu", "JP", "Bajutsu is a martial arts focused on military equestrianism.\n" +
                "Bakom – Bakom (also known as Vacon) is a Peruvian martial arts that combines Jujutsu with street fighting techniques. It was designed for survival in the slums of Peru.", "bajutsu");
        Art a5 = new Art("Bajiquan", "CN", "Bajiquan is a Chinese martial arts style that is famous for its explosive power and elbow strikes.", "bajiquan");
        Art a6 = new Art("Bando", "MM", "Bando is a martial arts style from the Southeast Asian country of Myanmar (formerly known as Burma).", "bando");
        Art a7 = new Art("Choy Li Fut", "CN", "Choy Li Fut (or Cai Li Fo) is a substyle of Kung Fu that combines long and short-range techniques.", "choy_li_fut");
        Art a8 = new Art("Chun Kuk Do", "KR", "Chun Kuk Do is a Korean and American hybrid system created by Chuck Norris (martial artist and movie star). In 2015, this martial arts was renamed to the Chuck Norris System.", "chun_kuk_do");
        Art a9 = new Art("Combat Hapkido", "KR", "Combat Hapkido is seen as a spin-off of traditional Hapkido. It has a much greater focus on self-defense and grappling than traditional Hapkido.", "combat_hapkido");
        Art a10 = new Art("Judo", "JP", "Judo is a Japanese martial arts style focused on grappling, joint locks and throws.", "judo");
        Art a11 = new Art("Jujutsu", "JP", "Jujutsu is a martial arts style focused on joint locks, holds and throws. It tries to redirect or manipulate the force of an attack in order to defeat the attacker.", "jujutsu");
        Art a12 = new Art("Kendo", "JP", "Kendo is a martial arts style focused on sword fighting (i.e. Bokken and Katana).\nThe Japanese warrior had no contempt for learning or the arts. Although Kenjutsu, ìthe art of swordsmanship,î had been recorded since the 8th century, it gained new prominence and took on religious and cultural aspects as well. Sword making became a revered art. Zen and other sects of Buddhism developed and the samurai often devoted time to fine calligraphy or poetry.\nAlthough the outward appearance and some of the ideals have changed with the changing needs of the people, Kendo continues to build character, self-discipline and respect. Despite a sportlike atmosphere, Kendo remains steeped in tradition which must never be forgotten. For here lies the strength of Kendo which has carried it throughout history and will carry it far into the future.”", "kendo");
        ArrayList<Art> arraylist = new ArrayList<Art>();
        arraylist.add(a1);
        arraylist.add(a2);
        arraylist.add(a3);
        arraylist.add(a4);
        arraylist.add(a5);
        arraylist.add(a6);
        arraylist.add(a7);
        arraylist.add(a8);
        arraylist.add(a9);
        arraylist.add(a10);
        arraylist.add(a11);
        arraylist.add(a12);
        String artNames[] = {"Aikido", "Aikijujitsu", "Araki Ryu", "Bajutsu", "Bajiquan", "Bando",
                        "Choy Li Fut", "Chun Kuk Do", "Combat Hapkido", "Judo", "Jujutsu", "Kendo"};

        lv = (ListView) findViewById(R.id.listView);
        btnSearchClear = (Button) findViewById(R.id.btnSearchClear);
        inputSearch = (EditText) findViewById(R.id.etSearch);
        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                MainActivity.this.adapter.getFilter().filter(cs);
            }
            @Override public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}
            @Override public void afterTextChanged(Editable arg0) {}
        });

        // Adding items to listview
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.art_name, artNames);
        lv.setAdapter(adapter);

        // Button delete text
        btnSearchClear.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                inputSearch.getText().clear();
                btnSearchClear.startAnimation(animSizeInc);
            }
        });

        // List item onClick
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                btnSearchClear.startAnimation(anim);
                lv.startAnimation(animSizeDec);

                Intent intent = new Intent(getApplicationContext(), InfoArtActivity.class);

                artName = parent.getItemAtPosition(position).toString();
                intent.putExtra("aName", artName);

                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("mylist", arraylist);
                intent.putExtras(bundle);

                startActivity(intent);
                finish();
            }
        });
    }

}