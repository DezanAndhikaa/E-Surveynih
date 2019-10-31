package com.example.e_survey;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import com.example.e_survey.DatabaseLokal.DataHelper;

import java.util.List;

public class Historia extends AppCompatActivity {

    public RecyclerView recyclerView;
    private TextView tv_toolbar;
    private List list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historia);

        DataHelper dbh = new DataHelper(getApplicationContext());
        list = dbh.getAllRecord();


    }
}
