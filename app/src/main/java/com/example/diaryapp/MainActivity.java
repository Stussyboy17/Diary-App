package com.example.diaryapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.github.orangegangsters.lollipin.lib.PinCompatActivity;
import com.github.orangegangsters.lollipin.lib.managers.AppLock;
import com.github.orangegangsters.lollipin.lib.managers.LockManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends PinCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private String [] note_array;
    public final static String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    private static final int REQUEST_CODE_ENABLE = 11;
    private static final int REQUEST_FIRST_RUN_PIN = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File internalStorageDir = getFilesDir();
        note_array = internalStorageDir.list();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ListView list = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1,
                new ArrayList<>(Arrays.asList(note_array)));
        list.setAdapter(adapter);

        Intent intent = new Intent(MainActivity.this, CustomPinActivity.class);
        intent.putExtra(AppLock.EXTRA_TYPE, AppLock.UNLOCK_PIN);
        startActivityForResult(intent, REQUEST_CODE_ENABLE);


        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.fab) {
                    final Intent intent = new Intent(MainActivity.this, WriteNote.class);
                    startActivity(intent);
                }
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent_on_click = new Intent(MainActivity.this, ReadNote.class);
                intent_on_click.putExtra(EXTRA_MESSAGE, note_array[position]);
                Log.d("MyLogs", note_array[position]);
                startActivity(intent_on_click);
            }
        });


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_slideshow || id == R.id.settings || id == R.id.nav_settings) {
                    final Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        File internalStorageDir = getFilesDir();
        note_array = internalStorageDir.list();
        ListView list = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1,
                new ArrayList<>(Arrays.asList(note_array)));
        list.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        File internalStorageDir = getFilesDir();
        note_array = internalStorageDir.list();
        ListView list = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1,
                new ArrayList<>(Arrays.asList(note_array)));
        list.setAdapter(adapter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        File internalStorageDir = getFilesDir();
        note_array = internalStorageDir.list();
        ListView list = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1,
                new ArrayList<>(Arrays.asList(note_array)));
        list.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        File internalStorageDir = getFilesDir();
        note_array = internalStorageDir.list();
        ListView list = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1,
                new ArrayList<>(Arrays.asList(note_array)));
        list.setAdapter(adapter);
    }

}
