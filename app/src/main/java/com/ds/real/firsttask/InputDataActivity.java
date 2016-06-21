package com.ds.real.firsttask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputDataActivity extends AppCompatActivity implements View.OnClickListener {


    /*
    * UI elements variable
    */
    EditText mproductName,mproductPrice;
    Button mbuttonSave,mbuttonNext;
    /*
    * Database Helper
    */

    DbHelperUtil dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);
        dbHelper =  new DbHelperUtil(this);
        initialiseUIItems();
        setListener();
    }

    private void setListener() {
        mbuttonSave.setOnClickListener(this);
        mbuttonNext.setOnClickListener(this);
    }

    private void initialiseUIItems() {
        mproductName=(EditText) findViewById(R.id.product_name);
        mproductPrice=(EditText) findViewById(R.id.product_price);
        mbuttonSave=(Button) findViewById(R.id.button_save);
        mbuttonNext=(Button) findViewById(R.id.button_next);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_save:
                saveData();
                break;
            case R.id.button_next:
                Intent i=new Intent(InputDataActivity.this,ProductListActivity.class);
                startActivity(i);
                break;
        }
    }

    private void saveData() {
        ProductModel myModel=new ProductModel();
        myModel.setName(mproductName.getText().toString());
        myModel.setPrice(mproductPrice.getText().toString());
        mproductName.setText("");
        mproductPrice.setText("");
        boolean isInserted= dbHelper.insertData(myModel);
        if(isInserted){
            Toast.makeText(this,"Data inserted",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Data not inserted",Toast.LENGTH_SHORT).show();
        }
    }
}

