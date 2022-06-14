package com.mark.colocpatternsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeEffectActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_effect);

        //遷移先
        Intent intent = new Intent(HomeEffectActivity.this, EffectActivity.class);

        //-- リスナー
        findViewById(R.id.heart1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(EffectActivity.KEY_KIND, EffectActivity.HEART1);
                startActivity(intent);
            }
        });

        findViewById(R.id.heart2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(EffectActivity.KEY_KIND, EffectActivity.HEART2);
                startActivity(intent);
            }
        });

        findViewById(R.id.heart3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(EffectActivity.KEY_KIND, EffectActivity.HEART3);
                startActivity(intent);
            }
        });

        findViewById(R.id.triangle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(EffectActivity.KEY_KIND, EffectActivity.TRIANGLE);
                startActivity(intent);
            }
        });

        findViewById(R.id.dia).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(EffectActivity.KEY_KIND, EffectActivity.DIA);
                startActivity(intent);
            }
        });

        findViewById(R.id.star).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(EffectActivity.KEY_KIND, EffectActivity.STAR);
                startActivity(intent);
            }
        });

        findViewById(R.id.sparcle_short).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(EffectActivity.KEY_KIND, EffectActivity.SPARCLE_SHORT);
                startActivity(intent);
            }
        });

        findViewById(R.id.sparcle_thin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(EffectActivity.KEY_KIND, EffectActivity.SPARCLE_SHIN);
                startActivity(intent);
            }
        });

        findViewById(R.id.sparcle_random).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(EffectActivity.KEY_KIND, EffectActivity.SPARCLE_RANDOM);
                startActivity(intent);
            }
        });

        findViewById(R.id.sparcle_long).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(EffectActivity.KEY_KIND, EffectActivity.SPARCLE_LONG);
                startActivity(intent);
            }
        });


        findViewById(R.id.flower).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(EffectActivity.KEY_KIND, EffectActivity.FLOWER);
                startActivity(intent);
            }
        });

        findViewById(R.id.sakura).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(EffectActivity.KEY_KIND, EffectActivity.SAKURA);
                startActivity(intent);
            }
        });

        findViewById(R.id.o2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(EffectActivity.KEY_KIND, EffectActivity.O2);
                startActivity(intent);
            }
        });



    }
}