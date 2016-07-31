package com.example.androidinstrumentationtests;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.androidinstrumentationtests.domain.MakeNetworkRequest;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    MakeNetworkRequest makeNetworkRequest;

    public static final String HELLO = "Hello";
    public static final String HELLO_WORLD = "Hello world!";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // inject dependencies
        DemoApplication.getApplication().getComponent().injectActivity(this);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textView = ((TextView) findViewById(R.id.text));
        textView.setText(HELLO);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeNetworkRequest();
            }
        });

    }

    private void makeNetworkRequest() {
        MakeNetworkRequest.Callback callback = new MakeNetworkRequest.Callback() {
            @Override
            public void onSuccess(String s) {
                textView.setText(s);
            }
        };
        makeNetworkRequest.execute(callback);
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
}
