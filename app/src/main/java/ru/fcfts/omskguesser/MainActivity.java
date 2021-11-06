package ru.fcfts.omskguesser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button start;
    Button exit;
    Button instructionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        start = (Button) findViewById(R.id.start);
        exit = (Button) findViewById(R.id.exit);
        instructionButton = (Button) findViewById(R.id.instructionButton);
        start.setOnClickListener(this);
        exit.setOnClickListener(this);
        instructionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.start:
                Intent intent = new Intent(this, SightActivity.class);
                startActivity(intent);
                break;
            case R.id.exit:
                finishAndRemoveTask();
                break;
//            case R.id.instructionButton:
//                Intent intent1 = new Intent(this, InstructionActivity.class);
//                startActivity(intent1);
//                break;
            default:
                break;
        }
    }
}