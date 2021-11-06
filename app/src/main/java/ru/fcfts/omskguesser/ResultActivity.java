package ru.fcfts.omskguesser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView sightImage;
    TextView sightName;
    TextView difference;
    TextView sightFact;
    Button returnButton;
    Button againButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();closeContextMenu();
        setContentView(R.layout.activity_result);
        sightImage = (ImageView) findViewById(R.id.sightImage);
        sightName = (TextView) findViewById(R.id.sightName);
        difference = (TextView) findViewById(R.id.difference);
        sightFact = (TextView) findViewById(R.id.sightFact);
        againButton = (Button) findViewById(R.id.againButton);
        returnButton = (Button) findViewById(R.id.menuButton);

        Resources res = getResources();

        Sight sight = (Sight) (getIntent().getExtras().get("sight"));
        int distance = (int) getIntent().getExtras().getDouble("distance");

        String stringDist;

        if (distance > 1000){
            double doubleDist = (double) distance;
            doubleDist /= 1000;
            stringDist = "Вы ошиблись на " + doubleDist + "км";
        }else if(distance > 100){
            stringDist = "Вы ошиблись на " + distance + "м";
        }else{
            stringDist = "Поздравляю! Вы верно указали месторасположение";
        }

        sightImage.setImageDrawable(ResourcesCompat.getDrawable(res, sight.getId(), null));
        sightName.setText(sight.getName());
        difference.setText(stringDist);
        sightFact.setText(sight.getFact());

        againButton.setOnClickListener(this);
        returnButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.againButton:
                Intent intent = new Intent(this, SightActivity.class);
                startActivity(intent);
                break;
            case R.id.menuButton:
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
                break;
            default:
                break;
        }
    }
}