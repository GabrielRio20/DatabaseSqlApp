package com.example.databasesqlapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText nameEditText;
    EditText priceEditText;
    Button insertBtn;
    EditText keywordEditText;
    Button searchButton;
    TextView priceResult;
    Button getAllBtn;

    //instantiate dbHelper
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.et_name);
        priceEditText = findViewById(R.id.et_price);
        insertBtn = findViewById(R.id.btn_insert);
        keywordEditText = findViewById(R.id.et_search);
        searchButton = findViewById(R.id.btn_search);
        priceResult = findViewById(R.id.price_result);
        getAllBtn = findViewById(R.id.btn_getAll);

        databaseHelper = new DatabaseHelper(this);

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPriceFromName(keywordEditText.getText().toString());
            }
        });

        getAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAllProducts();
            }
        });

    }

    public void showAllProducts(){
        List<Product> products = databaseHelper.getAllProducts();
        String result = "";
        for (int i=0; i<products.size(); i++){
            Product product = products.get(i);
            result = result + product.getId() + ",";
        }
    }

    public void insertData(){
        Product product = new Product();
        product.setName(nameEditText.getText().toString());
        product.setPrice(Integer.valueOf(priceEditText.getText().toString()));

        databaseHelper.insertRecord(product);
        Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
    }

    public void showPriceFromName(String keyword){
        Product product = databaseHelper.getProductFromName(keyword);
        priceResult.setText(String.valueOf(product.getPrice()));
    }
}