package com.mintedtech.guessing_game;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private GuessingGame mGame;
    private Snackbar mSnackBar;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("GAME", mGame.getJSONStringFromThis());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();
        setupFAB();
        mGame = savedInstanceState != null
                ? GuessingGame.getGuessingGameObjectFromJSONString(
                savedInstanceState.getString("GAME"))
                : new GuessingGame();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setupFAB() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> mGame.startNewGame());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleFABClick();
            }
        });
    }
    private void handleFABClick() {
        Toast.makeText(getApplicationContext(),
                R.string.new_game,
                Toast.LENGTH_SHORT).show();
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void pick123(View view) {
        Button currentButton = (Button) view;
        String currentButtonText = currentButton.getText().toString();
        String resultsText =
                currentButtonText.equals(String.valueOf(mGame.getWinningNumber()))
                        ? String.format(Locale.US, "Yes! %s is the right number.",
                        currentButtonText)
                        : String.format(Locale.US, "Sorry. %s is not the right number.",
                        currentButtonText);
        Snackbar.make(view, resultsText, Snackbar.LENGTH_SHORT).show();
    }
}