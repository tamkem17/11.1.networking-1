package com.example.tam.a111networking_1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {
    private String mHost= "time-b.nist.gov";
    private int mPort = 13;
    private TextView mTxt_quote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTxt_quote = (TextView)findViewById(R.id.txt_quote);
        Button btn_quote = (Button)findViewById(R.id.btn_quote);
        btn_quote.setOnClickListener(new displayQuote());
    }

    private class displayQuote implements View.OnClickListener{

        @Override
        public void onClick(View v) {
         new Quote().execute();
        }
    }

    class Quote extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            Socket socket = null;
            try {
                socket = new Socket(mHost, mPort);
            } catch (IOException e) {
                e.printStackTrace();
            }
            BufferedReader in = null;
            try {
                in = SocketUtils.getReader(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String lineQuote = null;
            try {
                lineQuote = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return lineQuote;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mTxt_quote.setText(s);
        }
    }
}
