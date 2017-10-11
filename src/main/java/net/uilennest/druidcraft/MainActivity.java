package net.uilennest.druidcraft;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
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

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle myBundle) {
        super.onCreate(myBundle);
        setContentView(R.layout.activity_main);
        //setContentView(R.layout.ff);
        this.cards = getResources().obtainTypedArray(R.array.cards);
        this.meanings = getResources().obtainTypedArray(R.array.meanings);
        this.currentMeaning = this.meanings.getString(0);
        // this.vibrator = ((Vibrator)getSystemService("vibrator"));

        mTextMessage = (TextView) findViewById(R.id.about);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
