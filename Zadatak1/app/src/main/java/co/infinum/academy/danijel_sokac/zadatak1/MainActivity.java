package co.infinum.academy.danijel_sokac.zadatak1;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.view.ViewGroup.LayoutParams;


public class MainActivity extends AppCompatActivity {
    private int counter = 0;
    private Button counterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCounterButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.resize) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setCounterButton() {
        counterButton = (Button) findViewById(R.id.counter_button);
    }

    public void counterButtonClick(View v) {
        counter++;
        displayCounterChanges(counter);
    }

    private void displayCounterChanges(int counter) {
        changeCounterButtonText(counter);
        changeCounterButtonColor(counter);
    }

    void changeCounterButtonText(int counter) {
        counterButton.setText(String.valueOf(counter));
    }

    void changeCounterButtonColor(int counter) {
        if (counter % 2 == 0) {
            counterButton.setBackgroundColor(Color.BLUE);
            counterButton.setTextColor(Color.WHITE);
        } else {
            counterButton.setBackgroundColor(Color.RED);
            counterButton.setTextColor(Color.BLACK);
        }
    }

    public void resizeCounterButton(MenuItem resizeItem){
        LayoutParams lp = counterButton.getLayoutParams();
//        ObjectAnimator animX, animY;
        if (resizeItem.getTitle().toString().equals(getString(R.string.resize_grow))) {
            resizeItem.setTitle(R.string.resize_shrink);
//            animX = ObjectAnimator.ofFloat(counterButton, "x", LayoutParams.MATCH_PARENT);
//            animY = ObjectAnimator.ofFloat(counterButton, "y", LayoutParams.MATCH_PARENT);
            lp.width = LayoutParams.MATCH_PARENT;
            lp.height = LayoutParams.MATCH_PARENT;
        } else {
            resizeItem.setTitle(R.string.resize_grow);
//            animX = ObjectAnimator.ofFloat(counterButton, "x", LayoutParams.WRAP_CONTENT);
//            animY = ObjectAnimator.ofFloat(counterButton, "y", LayoutParams.WRAP_CONTENT);
            lp.width = LayoutParams.WRAP_CONTENT;
            lp.height = LayoutParams.WRAP_CONTENT;
        }
        counterButton.setLayoutParams(lp);
//        AnimatorSet animSetXY = new AnimatorSet();
//        animSetXY.playTogether(animX, animY);
//        animSetXY.start();
    }
}
