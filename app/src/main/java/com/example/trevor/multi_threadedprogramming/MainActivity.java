package com.example.trevor.multi_threadedprogramming;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProgressBar simpleBar =(ProgressBar) findViewById(R.id.progressBar1);
        simpleBar.setProgress(0);

    }

    public void onClickCreate(View v) {
        String fileName = "numbers.txt";
        ProgressBar simpleBar =(ProgressBar) findViewById(R.id.progressBar1);
        simpleBar.setProgress(0);
        try {
            File file = new File(getFilesDir(), fileName);
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            int progress = 10;
            for (int i = 1; i < 11; i++) {
                bw.write(Integer.toString(i));
                bw.newLine();
                if (progress <= 100)
                   simpleBar.setProgress(progress);
                Thread.sleep(250);
                progress+= 10;
            }
            bw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }



    public void onClickLoad(View v) {
        ProgressBar simpleBar =(ProgressBar) findViewById(R.id.progressBar1);
        simpleBar.setProgress(0);
        String fileName = "numbers.txt";
        File file = new File(getFilesDir(), fileName);
        List<String> list = new ArrayList<String>();
        try {
            Scanner scanner = new Scanner(file);
            int progress = 1;
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine());
                simpleBar.setProgress(progress * 10);
                Thread.sleep(250);
                progress++;
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        lv = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> arrayAdapter;
        arrayAdapter = new ArrayAdapter<String>
                (MainActivity.this, android.R.layout.simple_list_item_1, list);
        arrayAdapter.notifyDataSetChanged();

        lv.setAdapter(arrayAdapter);
    }
    public void onClickClear(View v) {
        ProgressBar simpleBar =(ProgressBar) findViewById(R.id.progressBar1);
        simpleBar.setProgress(0);
        lv.setAdapter(null);
    }
}

