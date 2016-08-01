package com.android.mayojava.trivago.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Base activity
 */
public class BaseActivity extends AppCompatActivity {
    public void startFragment(Fragment fragment, int container) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(container, fragment);
        transaction.commit();
    }
}
