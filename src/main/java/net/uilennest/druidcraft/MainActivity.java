package net.uilennest.druidcraft;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static final int DIALOG_ABOUT = 1;
    static final int DIALOG_MEANING = 0;
    static final int MAX_CARDS = 78;
    static final int MAX_CARDS_GREAT_ARCANA = 22;
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

    private void switchToLayout1() {
        LinearLayout layout1 = (LinearLayout)findViewById(R.id.layout1);
        LinearLayout layout2 = (LinearLayout)findViewById(R.id.layout2);
        layout1.setVisibility(View.VISIBLE);
        layout2.setVisibility(View.INVISIBLE);
    }

    // card gui
    private void switchToLayout2() {
    LinearLayout layout1 = (LinearLayout)findViewById(R.id.layout1);
    LinearLayout layout2 = (LinearLayout)findViewById(R.id.layout2);
        layout1.setVisibility(View.INVISIBLE);
        layout2.setVisibility(View.VISIBLE);
    }

    // === functions called from the GUI elements (views) =========================
    public void doChangeMainCard(View paramView) {
        showTopMessage("change main card");

        Integer card_id = Common.pickCard(Integer.valueOf(MAX_CARDS));
        Drawable card = this.cards.getDrawable(card_id);
        String meaning = this.meanings.getString(card_id);
        this.currentMeaning = meaning;
        ((ImageView)findViewById(R.id.mainCard)).setImageDrawable(card);
    }

    public void doSingleCard(View paramView)
    {
        showTopMessage("do single card");

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
                case R.id.navigation_home:
                    showTopMessage("Home");
                    // showSingleCard

                    return true;
                case R.id.navigation_dashboard:
                    showTopMessage("Spread");
                    return true;
                case R.id.navigation_notifications:
                    showTopMessage("Notify");
                    return true;
            }
            return false;

        }

    };

    // 2131165185 = R.id.Layout1
    // 2131165187 = R.id.Layout2
    // 2131165188  =


    @Override
    protected void onCreate(Bundle myBundle) {
        super.onCreate(myBundle);
        setContentView(R.layout.activity_main);
        //setContentView(R.layout.ff);
        this.cards = getResources().obtainTypedArray(R.array.cards);
        this.meanings = getResources().obtainTypedArray(R.array.meanings);
        this.currentMeaning = this.meanings.getString(0);
        // this.vibrator = ((Vibrator)getSystemService("vibrator"));

        switchToLayout1();

        topTextMessage = (TextView) findViewById(R.id.top_message);
        bottomTextMessage = (TextView) findViewById(R.id.docs);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
