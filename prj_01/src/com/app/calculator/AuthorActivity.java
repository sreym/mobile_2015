package com.app.calculator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Interstellar on 06.10.2015.
 */
public class AuthorActivity extends Activity {
    TextView authtext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authorlayout);
        authtext = (TextView) findViewById(R.id.authortext);
    }

    public void ClickAuthorText(View v) {
        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.flip);
//  Animation animation3 = AnimationUtils.loadAnimation(this, R.anim.flip2);
        animation1.setDuration(400);
//     animation3.setDuration(400);
        v.startAnimation(animation1);
//        v.startAnimation(animation3);
    }
}