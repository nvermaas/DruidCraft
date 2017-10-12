package net.uilennest.druidcraft;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Context;

public class MainActivity extends AppCompatActivity {
    static final int DIALOG_ABOUT = 1;
    static final int DIALOG_MEANING = 0;
    static final int MAX_CARDS = 78;
    static final int MAX_CARDS_GREAT_ARCANA = 22;
    private TypedArray hiddencards;
    private TypedArray cards;
    private String currentMeaning;
    private TypedArray meanings;
    private Vibrator vibrator;


    // main gui
    private void showTopMessage(String message) {
        topTextMessage.setText(message);
    }
    private void showBottomMessage(String message) {
        bottomTextMessage.setText(message);
    }
    private void doPreviousLayout() {
        switchToLayout1();
    }

    // main screen
    private void switchToLayout1() {
        LinearLayout layout1 = (LinearLayout)findViewById(R.id.layout1);
        LinearLayout layout2 = (LinearLayout)findViewById(R.id.layout2);
        layout1.setVisibility(View.VISIBLE);
        layout2.setVisibility(View.GONE);
    }

    // card gui
    private void switchToLayout2() {
    LinearLayout layout1 = (LinearLayout)findViewById(R.id.layout1);
    LinearLayout layout2 = (LinearLayout)findViewById(R.id.layout2);
        layout1.setVisibility(View.GONE);
        layout2.setVisibility(View.VISIBLE);
    }

    // === functions called from the GUI elements (views) =========================
    public void doChangeCard(View view) {
        Integer card_id = Common.pickCard(Integer.valueOf(MAX_CARDS));
        Drawable card = this.cards.getDrawable(card_id);

        String meaning = this.meanings.getString(card_id);

        this.currentMeaning = meaning;
        ((ImageView)findViewById(R.id.mainCard)).setImageDrawable(card);
        //showTopMessage(meaning);
    }

    public void doSingleCard(View view) {
        Integer card_id = Common.pickCard(Integer.valueOf(MAX_CARDS));
        Drawable card = this.cards.getDrawable(card_id);
        String meaning = this.meanings.getString(card_id);
        this.currentMeaning = meaning;

        doShowCard(card, meaning);
    }

    public void doSingleCard() {
        Integer card_id = Common.pickCard(Integer.valueOf(MAX_CARDS));
        Drawable card = this.cards.getDrawable(card_id);
        String meaning = this.meanings.getString(card_id);
        this.currentMeaning = meaning;

        doShowCard(card, meaning);
    }

    public void doShowCard(Drawable card, String meaning) {
        switchToLayout2();
        ((ImageView)findViewById(R.id.mainCard)).setImageDrawable(card);
        ((ImageView)findViewById(R.id.card)).setImageDrawable(card);
    }

    public void doShowMeaning(View view) {
        Common.showDialog(MainActivity.this,this.currentMeaning);
        //showTopMessage(this.currentMeaning);
    }

    public void startCardViewActivity()
    {
        startActivity(new Intent(this, CardViewActivity.class));
    }

    private TextView topTextMessage;
    private TextView bottomTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_single:
                    //showTopMessage("Home");
                    // showSingleCard
                    doSingleCard(null);

                    return true;
                case R.id.navigation_spread:
                    doCircleSpread(null);
                    //showTopMessage("Spread");
                    return true;
                case R.id.navigation_time:
                    doTimeSpread(null);
                    //showTopMessage("Notify");
                    return true;
            }
            return false;

        }

    };

    // catch the back pressed to switch to main layout
    public void onBackPressed() {
        if (((LinearLayout)findViewById(R.id.layout2)).getVisibility() == View.VISIBLE) {
            switchToLayout1();
            return;
        }
        super.onBackPressed();
    }
//=== Interface to the other Activities ========================
    public void doTimeSpread(View view)
    {
        startActivity(new Intent(this, TimeSpreadActivity.class));
    }
    public void doCircleSpread(View view)
    {
        startActivity(new Intent(this, CircleSpreadActivity.class));
    }

    @Override
    protected void onCreate(Bundle myBundle) {
        super.onCreate(myBundle);
        setContentView(R.layout.activity_main);

        this.cards = getResources().obtainTypedArray(R.array.cards);
        this.hiddencards = getResources().obtainTypedArray(R.array.hiddencards);
        this.meanings = getResources().obtainTypedArray(R.array.meanings);
        this.currentMeaning = this.meanings.getString(0);
        this.vibrator = ((Vibrator)getSystemService(Context.VIBRATOR_SERVICE));

        switchToLayout1();

        topTextMessage = (TextView) findViewById(R.id.top_message);
        bottomTextMessage = (TextView) findViewById(R.id.docs);

        // create listeners
        // for mainCard on layout1 screen
        findViewById(R.id.mainCard).setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                doChangeCard(view);
            }
        });

        findViewById(R.id.mainCard).setOnLongClickListener(new View.OnLongClickListener()
        {
            public boolean onLongClick(View view)
            {
                doShowMeaning(view);
                return false;
            }
        });

        // for card on layout2 screen
        findViewById(R.id.card).setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                doPreviousLayout();
            }
        });

        findViewById(R.id.card).setOnLongClickListener(new View.OnLongClickListener()
        {
            public boolean onLongClick(View view)
            {
                doShowMeaning(view);
                //vibrator.vibrate(50L);
                return false;
            }
        });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
