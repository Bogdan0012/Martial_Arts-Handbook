package com.example.martial_arts_handbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class InfoArtActivity extends AppCompatActivity {
    private Button btnBack;
    private String artName;
    private TextView artNameTV;
    private TextView artCountryTV;
    private TextView artDescriptionTV;
    private String url;
    private ImageView artImage;
    private Button prev;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_art);

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.rotate);
        //Animation animSizeDec = AnimationUtils.loadAnimation(this, R.anim.size_decrease);
        Animation animSizeInc = AnimationUtils.loadAnimation(this, R.anim.size_increase);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                artImage.startAnimation(anim);
                btnBack.startAnimation(animSizeInc);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Intent intent = getIntent();
        artName = intent.getExtras().getString("aName");
        artNameTV = (TextView)findViewById(R.id.artTitle);
        artNameTV.setText(artName);

        artCountryTV= (TextView)findViewById(R.id.artCountry);
        artDescriptionTV= (TextView)findViewById(R.id.artDescription);
        artDescriptionTV.setMovementMethod(new ScrollingMovementMethod());

        Bundle bundle = getIntent().getExtras();
        ArrayList<Art> arraylist = bundle.getParcelableArrayList("mylist");

        for(int i = 0; i<arraylist.size(); i++){
            if(Objects.equals(arraylist.get(i).name, artName)){
                artCountryTV.setText(arraylist.get(i).country);
                artDescriptionTV.setText("\t\t"+arraylist.get(i).description);
                url = arraylist.get(i).url;
            }
        }

        //artImage
        artImage = (ImageView)findViewById(R.id.imageViewArt);
        ArrayList<Integer> imgs = new ArrayList<>();
        if(Objects.equals(url, "aikido")){
            imgs.add(R.drawable.aikido1);
            imgs.add(R.drawable.aikido2);
            imgs.add(R.drawable.aikido3);
        } else if(Objects.equals(url, "aikijujitsu")){
            imgs.add(R.drawable.aikijujitsu1);
            imgs.add(R.drawable.aikijujitsu2);
            imgs.add(R.drawable.aikijujitsu3);
        } else if(Objects.equals(url, "araki_ryu")){
            imgs.add(R.drawable.araki_ryu1);
            imgs.add(R.drawable.araki_ryu2);
            imgs.add(R.drawable.araki_ryu3);
        } else if(Objects.equals(url, "bajutsu")){
            imgs.add(R.drawable.bajutsu1);
            imgs.add(R.drawable.bajutsu2);
            imgs.add(R.drawable.bajutsu3);
        } else if(Objects.equals(url, "bajiquan")){
            imgs.add(R.drawable.bajiquan1);
            imgs.add(R.drawable.bajiquan2);
            imgs.add(R.drawable.bajiquan3);
        } else if(Objects.equals(url, "bando")){
            imgs.add(R.drawable.bando1);
            imgs.add(R.drawable.bando2);
            imgs.add(R.drawable.bando3);
        } else if(Objects.equals(url, "choy_li_fut")){
            imgs.add(R.drawable.choy_li_fut1);
            imgs.add(R.drawable.choy_li_fut2);
            imgs.add(R.drawable.choy_li_fut3);
        } else if(Objects.equals(url, "chun_kuk_do")){
            imgs.add(R.drawable.chun_kuk_do1);
            imgs.add(R.drawable.chun_kuk_do2);
            imgs.add(R.drawable.chun_kuk_do3);
        } else if(Objects.equals(url, "combat_hapkido")){
            imgs.add(R.drawable.combat_hapkido1);
            imgs.add(R.drawable.combat_hapkido2);
            imgs.add(R.drawable.combat_hapkido3);
        } else if(Objects.equals(url, "judo")){
            imgs.add(R.drawable.judo1);
            imgs.add(R.drawable.judo2);
            imgs.add(R.drawable.judo3);
        } else if(Objects.equals(url, "jujutsu")){
            imgs.add(R.drawable.jujutsu1);
            imgs.add(R.drawable.jujutsu2);
            imgs.add(R.drawable.jujutsu3);
        } else if(Objects.equals(url, "kendo")){
            imgs.add(R.drawable.kendo1);
            imgs.add(R.drawable.kendo2);
            imgs.add(R.drawable.kendo3);
        } else{
            imgs.add(R.drawable.kendo1);
            imgs.add(R.drawable.kendo2);
            imgs.add(R.drawable.kendo3);
        }

        final int[] counter = {0};

        artImage.setImageResource(imgs.get(counter[0]));
        prev = (Button)findViewById(R.id.prBtn);
        next = (Button)findViewById(R.id.nextBtn);

        prev.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                if(counter[0] > 0) counter[0]--;
                artImage.setImageResource(imgs.get(counter[0]));
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                if(counter[0] < imgs.size()-1) counter[0]++;
                artImage.setImageResource(imgs.get(counter[0]));
            }
        });

    }
}