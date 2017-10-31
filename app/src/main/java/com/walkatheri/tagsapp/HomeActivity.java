package com.walkatheri.tagsapp;

import android.graphics.Typeface;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    private EditText composeED;
    private ImageView tagsIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tagsIV = (ImageView) findViewById(R.id.home_tags_iv);
        tagsIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TagsDialog tagsDialog = new TagsDialog(HomeActivity.this);
                tagsDialog.show();
            }
        });

        composeED = (EditText) findViewById(R.id.home_compose_ed);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/shine.ttf");
        composeED.setTypeface(typeface);

        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        final NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_layout);

        ImageView helpIV = (ImageView) findViewById(R.id.home_help_iv);

        helpIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.END);
            }
        });
    }
}
