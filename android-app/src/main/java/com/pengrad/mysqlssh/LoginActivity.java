package com.pengrad.mysqlssh;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import org.androidannotations.annotations.*;

/**
 * User: stas
 * Date: 05.06.14 0:29
 */

@EActivity(R.layout.activity_login)
public class LoginActivity extends ActionBarActivity {

    @ViewById
    protected EditText host, user, password, database, port;

    @ViewById(R.id.login_form)
    protected View mLoginFormView;

    @ViewById(R.id.login_status)
    protected View mLoginStatusView;

    @AfterViews
    protected void init() {

    }

    @Click(R.id.button_connect)
    protected void attemptConnect() {
        /* Validation
        // Reset errors.
        mViewUser.setError(null);
        mViewPassword.setError(null);

        // Store values at the time of the login attempt.
        mEmail = mViewUser.getText().toString();
        mPassword = mViewPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password.
        if (TextUtils.isEmpty(mPassword)) {
            mViewPassword.setError(getString(R.string.error_field_required));
            focusView = mViewPassword;
            cancel = true;
        }


        if (cancel) {
            // There was an error; don't attempt login and focus the first form field with an error.
            focusView.requestFocus();
        } else {
            showProgress(true);
            connect();
        }
        */

        showProgress(true);
        connect();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginStatusView.setVisibility(View.VISIBLE);
            mLoginStatusView.animate().setDuration(shortAnimTime).alpha(show ? 1 : 0)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            mLoginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
                        }
                    });

            mLoginFormView.setVisibility(View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(show ? 0 : 1)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                        }
                    });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show and hide the relevant UI components.
            mLoginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Background
    protected void connect() {
        SystemClock.sleep(2000);
        endConnect();
    }

    @UiThread
    protected void endConnect() {
        showProgress(false);
    }
}
