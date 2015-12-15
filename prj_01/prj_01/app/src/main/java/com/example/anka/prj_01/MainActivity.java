package com.example.anka.prj_01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;


public class MainActivity extends Activity {
    public TextView window;

    private final StringBuffer exercise = new StringBuffer();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Singleton.getInstance().setMainActivity(this);
        setContentView(R.layout.activity_main);

        Button[] buttons = new Button[17];

        window = (TextView) findViewById(R.id.window);

        for (int i = 0 ; i < 14; i++) {
            int resId = getResources().getIdentifier("button" + i, "id", getPackageName());
            buttons[i] = (Button) this.findViewById(resId);

            final int j = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (j < 10) {
                        exercise.append(j);
                    }
                    else {
                        switch (j) {
                            case 10:
                                exercise.append("*");
                                break;
                            case 11:
                                exercise.append("+");
                                break;
                            case 12:
                                exercise.append("-");
                                break;
                            case 13:
                                exercise.append("/");
                                break;
                        }
                    }
                    window.setText(exercise);
                }
            });
        }

        buttons[14] = (Button) findViewById(R.id.clear);
        buttons[14].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exercise.delete(0, exercise.length());
                window.setText(exercise);
            }
        });

        buttons[16] = (Button) findViewById(R.id.about);
        buttons[16].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        buttons[15] = (Button) findViewById(R.id.result);
        buttons[15].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExpressionUtils.get();
            }
        });
    }

    public StringBuffer getExercise() {
        return exercise;
    }
}