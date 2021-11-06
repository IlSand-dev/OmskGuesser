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

public class InstructionActivity extends AppCompatActivity implements View.OnClickListener {
    private int screen = 0;
    private final int[] imageIds = new int[]{R.drawable.instruction};
    private String[] texts;

    private Resources res;

    private ImageView instructionImage;
    private TextView instructionText;
    private Button nextButton;
    private Button previousButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);

       nextButton = (Button) findViewById(R.id.nextButton);
       previousButton = (Button) findViewById(R.id.previousButton);

       previousButton.setVisibility(View.GONE);

       nextButton.setOnClickListener(this);
       previousButton.setOnClickListener(this);
       res = getResources();
       texts = new String[]{getString(R.string.instruction)};
       instructionImage = (ImageView) findViewById(R.id.instructionImage);
       instructionImage.setImageDrawable(ResourcesCompat.getDrawable(res, imageIds[screen], null));
       instructionText.setText(texts[screen]);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.nextButton:
                screen++;
                if (screen == imageIds.length-1){
                    nextButton.setText("Попробовать");
                }else if(screen == imageIds.length){
                    Intent intent = new Intent(this, SightActivity.class);
                    startActivity(intent);
                    break;
                }
                previousButton.setVisibility(View.VISIBLE);
                instructionImage.setImageDrawable(ResourcesCompat.getDrawable(res, imageIds[screen], null));
                instructionText.setText(texts[screen]);
                break;
            case R.id.previousButton:
                screen--;
                if (screen == 0){
                    previousButton.setVisibility(View.GONE);
                    instructionImage.setImageDrawable(ResourcesCompat.getDrawable(res, imageIds[screen], null));
                    instructionText.setText(texts[screen]);
                }
                nextButton.setText("Далее");
                break;
            default:
                break;
        }
    }
}