package com.pengrad.mysqlssh;

import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * User: stas
 * Date: 27.05.14 23:55
 */

@EActivity(R.layout.activity_main)
public class MainActivity extends ActionBarActivity {

    @ViewById
    TextView text;

    @AfterViews
    protected void init() {
        text.setText("Test");
    }
}
