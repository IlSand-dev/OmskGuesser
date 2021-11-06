package ru.fcfts.omskguesser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SightActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView image;
    Button next;
    Sight sight;
    private Sight[] sights;

    private Sight getRandomSight(){
        int x = (int)(Math.random()*sights.length);
        return sights[x];
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sigth);
        Resources res = getResources();
        sights = new Sight[]{
                new Sight(R.drawable.musical_theater, new double[]{54.982544,73.3805438}, "Омский государственный музыкальный театр",getString(R.string.musical_theater_fact)),
                new Sight(R.drawable.tower, new double[]{55.047892, 73.314927}, "Эйфелева башня",getString(R.string.eifel_tower_fact)),
                new Sight(R.drawable.temple, new double[]{54.9901355,73.3669429}, "Кафедральный собор Успения Пресвятой Богородицы", getString(R.string.temple_fact)),
                new Sight(R.drawable.orb, new double[]{54.980681, 73.3729248}, "Держава", getString(R.string.orb_fact)),
                new Sight(R.drawable.tar_gates, new double[]{54.987761, 73.367994}, "Тарские ворота", getString(R.string.tar_gates_fact)),
                new Sight(R.drawable.don_kihot, new double[]{54.9791495, 73.3803685}, "Памятник Дон Кихоту", getString(R.string.don_kihot_fact)),
                new Sight(R.drawable.dram_theater, new double[]{54.9880906, 73.3715254}, "Омский театр драмы", getString(R.string.dram_theater_fact)),
                new Sight(R.drawable.dost_museum, new double[]{54.9842319, 73.369387}, "Литературный музей им. Ф. М. Достоевского", getString(R.string.dost_museum_fact)),
                new Sight(R.drawable.stepanych, new double[]{54.9852356, 73.3742827}, "Памятник сантехнику Степанычу", getString(R.string.stepanych_fact)),
                new Sight(R.drawable.kolchyak_mansion, new double[]{54.9791494, 73.3739055}, "Особняк купца Батюшкова", getString(R.string.kolchyak_mansion_fact)),
                new Sight(R.drawable.kondr_museum, new double[]{54.9745411, 73.3800604}, "Музей Кондратия Белова", getString(R.string.kondr_museum_fact)),
                new Sight(R.drawable.mother, new double[]{54.964701, 73.353233}, "Мать-сибирячка", getString(R.string.mother_fact)),
                new Sight(R.drawable.arlekin_theater, new double[]{54.9573874,73.3874028}, "Театр кукол Арлекин", getString(R.string.arlekin_theater_fact))
        };
        sight = getRandomSight();
        image = (ImageView) findViewById(R.id.imageView);
        image.setImageDrawable(ResourcesCompat.getDrawable(res, sight.getId(), null));

        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.next:
                Intent intent = new Intent(this, MapsActivity.class);
                intent.putExtra("sight", sight);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}