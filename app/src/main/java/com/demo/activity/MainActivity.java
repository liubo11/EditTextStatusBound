package com.demo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import cn.lb.utils.text.EditTextStatusBoundUtils;

public class MainActivity extends AppCompatActivity {

    private LinearLayout lilContainer;
    private Button btnAdd;
    private Button btnRemove;
    private Button btnConfirm;

    private EditTextStatusBoundUtils boundUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lilContainer = (LinearLayout) findViewById(R.id.container);

        btnAdd = (Button) findViewById(R.id.btn1);
        btnRemove = (Button) findViewById(R.id.btn2);
        btnConfirm = (Button) findViewById(R.id.btn_confirm);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEdit(lilContainer);
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //removeEdit(lilContainer);
            }
        });

        boundUtils = new EditTextStatusBoundUtils(btnConfirm);
        boundUtils.setStatusListner(new EditTextStatusBoundUtils.StatusChangeListner() {
            @Override
            public void statusChange(View v, boolean unable) {
                v.setEnabled(!unable);
            }
        });
    }

    private void addEdit(LinearLayout lil) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1,-2);
        lp.topMargin = getResources().getDimensionPixelOffset(R.dimen.edit_top_margin);

        LayoutInflater inflater = LayoutInflater.from(this);
        EditText et = (EditText) inflater.inflate(R.layout.edittext, lil, false);

        boundUtils.addEditText(et);

        lil.addView(et, lp);
    }

    private void removeEdit(LinearLayout lil) {
        if (lil.getChildCount() == 1) {
            return;
        }
        lil.removeViewAt(lil.getChildCount()-1);
    }

}
