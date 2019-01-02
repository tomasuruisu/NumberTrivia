package com.thomas.numbertrivia;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<TriviaNumber> triviaList;
    private TriviaNumberAdapter triviaNumberAdapter;
    private RecyclerView recyclerView;

    String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        triviaList = new ArrayList<>();

        recyclerView = findViewById(R.id.trivias);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);

        recyclerView.setLayoutManager(layoutManager);
        triviaNumberAdapter = new TriviaNumberAdapter(this, triviaList);

        recyclerView.setAdapter(triviaNumberAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestData();
            }
        });
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

    private void updateUI() {
        if (triviaNumberAdapter == null) {
            triviaNumberAdapter = new TriviaNumberAdapter(this , triviaList);
            recyclerView.setAdapter(triviaNumberAdapter);
        } else {
            triviaNumberAdapter.notifyDataSetChanged();
        }
    }

    private void requestData() {
        NumbersApiService service = NumbersApiService.retrofit.create(NumbersApiService.class);
        retrofit2.Call<TriviaNumber> call = service.getRandomQuote();
        call.enqueue(new Callback<TriviaNumber>() {
            @Override
            public void onResponse(Call<TriviaNumber> call, Response<TriviaNumber> response) {
                TriviaNumber triviaNumber = response.body();
                Log.i(TAG,"Number: "+ triviaNumber.getNumber() +"  Quote:  "+ triviaNumber.getText());
                triviaList.add(new TriviaNumber(triviaNumber.getNumber(), triviaNumber.getText()));
                updateUI();
            }

            @Override
            public void onFailure(Call<TriviaNumber> call, Throwable t) {
                Log.i(TAG, "Something went wrong: " + t.getMessage());
            }
        });
    }
}
