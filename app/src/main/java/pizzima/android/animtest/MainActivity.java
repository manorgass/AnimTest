package pizzima.android.animtest;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    Animation animScaleDown, animScaleUp;
    ArrayList<View> backgroundBank;
    HashMap<Integer, View> backgroundMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animScaleDown = AnimationUtils.loadAnimation(this, R.anim.anim_scale_down);
        animScaleDown.setFillAfter(true);
        animScaleUp = AnimationUtils.loadAnimation(this, R.anim.anim_scale_up);
        animScaleUp.setFillAfter(true);

        backgroundBank = new ArrayList<>();
        backgroundBank.add(findViewById(R.id.background1));
        backgroundBank.add(findViewById(R.id.background2));
        backgroundBank.add(findViewById(R.id.background3));

        backgroundMap = new HashMap<>();
        backgroundMap.put(R.id.icon1, findViewById(R.id.background1));
        backgroundMap.put(R.id.icon2, findViewById(R.id.background2));
        backgroundMap.put(R.id.icon3, findViewById(R.id.background3));
    }

    Handler handler = new Handler();
    boolean isClickable = true;

    public void onClickIcon(View view) {
        Log.e("TEST", "hey");
        if (isClickable)
            isClickable = false;
        else
            return;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                isClickable = true;
            }
        }, 300);
        view = backgroundMap.get(view.getId());

        view.setSelected(!view.isSelected());
        for (View tmpView : backgroundBank) {
            if (tmpView == view) {
                view.startAnimation(view.isSelected() ? animScaleUp : animScaleDown);
            } else if (tmpView.isSelected()) {
                tmpView.setSelected(false);
                tmpView.startAnimation(animScaleDown);
            }
        }
    }
}
