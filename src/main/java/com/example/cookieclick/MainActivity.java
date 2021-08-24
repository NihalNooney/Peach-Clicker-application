package com.example.cookieclick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.invoke.ConstantCallSite;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    TextView upgradeDisplay;
    TextView scoreDisplay;
    TextView t;
    ConstraintLayout l;
    ImageView x;
    public static ImageView upgradeIm;

    AtomicInteger score = new AtomicInteger(0);
    AtomicInteger upgrade = new AtomicInteger(50);
    AtomicInteger add = new AtomicInteger(1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        upgradeDisplay = findViewById(R.id.textView2);
        scoreDisplay=findViewById(R.id.textView);
        l=findViewById(R.id.layoutt);
        AnimationDrawable animationDrawable = (AnimationDrawable) l.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        x=findViewById(R.id.imageView);
        x.setImageResource(R.drawable.download);


        final ScaleAnimation increaseSize = new ScaleAnimation(1.0f, 1.5f, 1.0f, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        final ScaleAnimation decreaseSize= new ScaleAnimation(1.0f, .5f, 1.0f, .5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        increaseSize.setDuration(250);

        upgradeDisplay.setBackgroundColor(Color.RED);


        upgradeDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if upgrades are available => then do this
                if(score.get()>=upgrade.get()) {
                    upgradeDisplay.setBackgroundColor(Color.RED);
                    int temp = -1 * upgrade.get();
                    score.getAndAdd(temp);
                    scoreDisplay.setText("Score: " + score.get());
                    new Upgrade().start();

                    upgradeIm=new ImageView(MainActivity.this);
                    upgradeIm.setId(View.generateViewId());
                    upgradeIm.setImageResource(R.drawable.download2);
                    upgradeIm.startAnimation(increaseSize);



                    ConstraintLayout.LayoutParams params2 = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                    upgradeIm.setLayoutParams(params2);

                    l.addView(upgradeIm);

                    ConstraintSet c1 = new ConstraintSet();
                    c1.clone(l);

                    c1.connect(upgradeIm.getId(), ConstraintSet.TOP, l.getId(), ConstraintSet.TOP);
                    //binds it to the top and etc
                    c1.connect(upgradeIm.getId(), ConstraintSet.RIGHT, l.getId(), ConstraintSet.RIGHT);
                    c1.connect(upgradeIm.getId(), ConstraintSet.BOTTOM, l.getId(), ConstraintSet.BOTTOM);
                    c1.connect(upgradeIm.getId(), ConstraintSet.LEFT, l.getId(), ConstraintSet.LEFT);


                    /*
                    //adds views in random places
                    float rpositionUpgrade = (float) (Math.random() * .8);
                    float rpositionUpgrade2 = (float) (Math.random() * .2) + (float) .75;
                    */

                    c1.setVerticalBias(upgradeIm.getId(), .95f);
                    c1.setHorizontalBias(upgradeIm.getId(), .5f);
                    //sets the widget biased to a portion of the screen
                    //Here it will be a quarter way down(.25) and middle of the horizon(.5)

                    c1.applyTo(l);









                    ConstraintLayout l;


                    TextView textInCode = new TextView(MainActivity.this);
                    textInCode.setId(View.generateViewId());
                    l = findViewById(R.id.layoutt);



                    ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                    textInCode.setLayoutParams(params);

                    l.addView(textInCode);

                    ConstraintSet c = new ConstraintSet();
                    c.clone(l);

                    c.connect(textInCode.getId(), ConstraintSet.TOP, l.getId(), ConstraintSet.TOP);
                    //binds it to the top and etc
                    c.connect(textInCode.getId(), ConstraintSet.RIGHT, l.getId(), ConstraintSet.RIGHT);
                    c.connect(textInCode.getId(), ConstraintSet.BOTTOM, l.getId(), ConstraintSet.BOTTOM);
                    c.connect(textInCode.getId(), ConstraintSet.LEFT, l.getId(), ConstraintSet.LEFT);

                    //adds views in random places
                    float rposition = (float) (Math.random() * .8);
                    float rposition2 = (float) (Math.random() * .2) + (float) .75;


                    c.setVerticalBias(textInCode.getId(), rposition);
                    c.setHorizontalBias(textInCode.getId(), rposition2);
                    //sets the widget biased to a portion of the screen
                    //Here it will be a quarter way down(.25) and middle of the horizon(.5)

                    c.applyTo(l);
//this applies the changes
        /*
        increaseSize = new ScaleAnimation(af, bf, cf, df, Animation.RELATIVE_TO_SELF, .5F, Animation.RELATIVE_TO_SELF, .5F);
        //starts at original size(a,c) and goes to twice the size(b,d)
        //Starts expanding from center(.5 -> Meaning Center of the Animation) relative to itself
          increaseSize.setDuration((x));
        //animation lasts for x milliseconds
*/

                }
            }
        });





        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x.startAnimation(increaseSize);
                score.getAndAdd(add.get());

                l=findViewById(R.id.layoutt);
                 t = new TextView(MainActivity.this);
                t.setId(View.generateViewId());
                t.setText("+"+add.get());
                t.setTextSize(50);
                t.animate().setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        l.post(new Runnable() {
                            public void run() {
                                MainActivity.this.runOnUiThread(new Runnable() {
                                    public void run() {
                                        l.removeView(t);

                                    }
                                });
                            }

                        });



                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                scoreDisplay.setText("Score: "+score.get());




                ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT
                );

                t.setLayoutParams(params);

                l.addView(t);

                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(l);


                constraintSet.connect(t.getId(), ConstraintSet.TOP, l.getId(), ConstraintSet.TOP);
                constraintSet.connect(t.getId(), ConstraintSet.BOTTOM, l.getId(), ConstraintSet.BOTTOM);
                constraintSet.connect(t.getId(), ConstraintSet.LEFT, l.getId(), ConstraintSet.LEFT);
                constraintSet.connect(t.getId(), ConstraintSet.RIGHT, l.getId(), ConstraintSet.RIGHT);


                float a = (float)(Math.random()*.5)+.25f;
                constraintSet.setHorizontalBias(t.getId(), a);
                constraintSet.setVerticalBias(t.getId(), .25f);

                //applys changes again
                constraintSet.applyTo(l);

                ObjectAnimator animation = ObjectAnimator.ofFloat(t, "translationY", 0f, -500f);
                animation.setDuration(1000);
                animation.start();


                AnimatorSet aniSet = new AnimatorSet();
               aniSet.playTogether(ObjectAnimator.ofFloat(t,"translationY", 0f,-500f).setDuration(1000), ObjectAnimator.ofFloat(t, "alpha", 1f, 0f).setDuration(1000));
                aniSet.start();

                aniSet.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                l.removeView(t);
                            }
                        });
                    }
                });



                //You Still need to to getChildCount Log Statement
                //YOUR VIEWS ARE NOT DELETING!!!
                Log.d("KKKK", String.valueOf(l.getChildCount()));
            }

        });

    }
    public class Upgrade extends Thread{
        @Override
        public void run() {

            while(true) {
                if(score.get() >= upgrade.get()){
                    upgradeDisplay.setBackgroundColor(Color.GREEN);
                }
                try {
                    //time increment between each boost
                    Upgrade.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //amount of increment to score
                score.getAndAdd(10);
                runOnUiThread(new Thread(new Runnable() {
                    @Override
                    public void run() {
                        scoreDisplay.setText("Score: "+score);
                    }
                }));
            }
        }
    }

}
