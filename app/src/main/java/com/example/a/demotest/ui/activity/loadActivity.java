package com.example.a.demotest.ui.activity;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a.demotest.R;
import com.example.a.demotest.bean.MyDatabaseHelper;
import com.example.a.demotest.contract.Contract;

import java.util.concurrent.Future;

public class loadActivity extends BaseActivity {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;
    private CheckBox rememberPass;
    private Contract.Presenter presenter;
    private Button register;
    private MyDatabaseHelper dbHelper;
    private static final String TAG = "loadActivity";


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        accountEdit = findViewById(R.id.account);
        passwordEdit = findViewById(R.id.password);
        rememberPass = findViewById(R.id.remember_pass);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        accountEdit.setInputType(InputType.TYPE_CLASS_NUMBER);

        final Cursor[] cursor = new Cursor[1];
        dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 4);
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        boolean isRemember = pref.getBoolean("remember_password", false);
        if (isRemember) {
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursor[0] = db.query("Book", null, null, null, null, null, null);
                int flag = 0;
                String accountinput = accountEdit.getText().toString();
                String passwordinput = passwordEdit.getText().toString();
                if (cursor[0].moveToFirst()) {
                    do {
                        String acc = cursor[0].getString(cursor[0].getColumnIndex("account"));
                        String pass = cursor[0].getString(cursor[0].getColumnIndex("password"));
                        if (accountinput.equals(acc) && passwordinput.equals(pass)) {
                            flag = 1;
                            Log.e(TAG, "44444");
                            break;
                        }
                    } while (cursor[0].moveToNext());
                }
                cursor[0].close();
                if (flag == 1) {
                    Log.e(TAG, "555555555");
                    editor = pref.edit();
                    if (rememberPass.isChecked()) {
                        editor.putBoolean("remember_password", true);
                        editor.putString("account", accountinput);
                        editor.putString("password", passwordinput);
                    } else {
                        editor.clear();
                    }
                    editor.apply();
                    Intent intent = new Intent(loadActivity.this, downloadpicActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(loadActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(loadActivity.this, registerActivity.class);
                startActivity(intent);
                /*
                int flag = 0;
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                Cursor cursor = db.query("Book", null, null, null, null, null, null);
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                if (cursor.moveToFirst()) {
                    do {
                        String acc = cursor.getString(cursor.getColumnIndex("account"));
                        Log.e(TAG, "3333" );
                        if (account.equals(acc)) {
                            flag = 1;
                            Log.e(TAG, "22222 " );
                            break;
                        }
                    } while (cursor.moveToNext());
                }
                cursor.close();
                if (flag == 1) {
                    Toast.makeText(view.getContext(), "账号存在", Toast.LENGTH_SHORT).show();
                } else {
                    values.put("account", account);
                    values.put("password", password);
                    db.insert("Book", null, values);
                    Log.e(TAG, "1111" );
                }
                */
            }
        });

    }

}
