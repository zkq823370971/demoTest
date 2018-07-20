
package com.example.a.demotest.ui.activity;

import android.accounts.Account;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.util.LogTime;
import com.example.a.demotest.R;
import com.example.a.demotest.bean.MyDatabaseHelper;
import com.example.a.demotest.bean.test;

public class registerActivity extends BaseActivity {
    private static final String TAG = "registerActivity";
    private Button register1;
    private TextView text;
    private Context context;
    private static int flag2 = 0;
    private EditText account1;
    private MyDatabaseHelper dbHelper;
    private String account11;
    public static final int UPDATE_TEXT = 1;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_TEXT:
                    text.setText("请输入密码");
                    register1.setText("确认密码");
                    account1.getText().clear();
                    account1.setHint("请输入密码");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        account1 = findViewById(R.id.account1);
        text = findViewById(R.id.text_input);
        register1 = findViewById(R.id.register1);
        dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 4);
        register1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int flag = 0;
                        test abc = new test();
                        Cursor cursor = abc.crea(context);
                        /*
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        Cursor cursor = db.query("Book", null, null, null, null, null, null);
                        */
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        String account = account1.getText().toString();
                        if (flag2 == 0) {
                            if (cursor.moveToFirst()) {
                                do {
                                    String acc = cursor.getString(cursor.getColumnIndex("account"));
                                    if (account.equals(acc)) {
                                        flag = 1;
                                        break;
                                    }
                                } while (cursor.moveToNext());
                            }
                            cursor.close();
                            if (flag == 1) {
                                Looper.prepare();
                                Log.e(TAG, "errr ");
                                Intent intent = new Intent("com.example.a.demotest.MY_BROADCAST");
                                sendBroadcast(intent);
                                //Toast.makeText(view.getContext(), "账号存在", Toast.LENGTH_SHORT).show();
                                Looper.loop();
                            } else {
                                flag2 = 1;
                                account11 = account;
                                Message message = new Message();
                                message.what = UPDATE_TEXT;
                                handler.sendMessage(message);
                            }
                        } else {
                            values.put("account", account11);
                            values.put("password", account);
                            db.insert("Book", null, values);
                            if (cursor.moveToFirst()) {
                                do {
                                    String acc = cursor.getString(cursor.getColumnIndex("account"));
                                    String pass = cursor.getString(cursor.getColumnIndex("password"));
                                    Log.e(TAG, "账号" + acc);
                                    Log.e(TAG, "密码" + pass);
                                } while (cursor.moveToNext());
                            }
                            cursor.close();
                            Intent intent = new Intent(registerActivity.this, loadActivity.class);
                            flag2 = 0;
                            startActivity(intent);
                        }
                    }
                }).start();
            }
        });
    }

}
