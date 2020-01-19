package com.example.pesanjus;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.NumberFormat;

import static android.R.string.no;
import static android.os.Build.VERSION_CODES.N;


public class MainActivity extends AppCompatActivity {
    int quantity=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Image Gallery");
    }


    public void increment(View view){
        if(quantity==100){
            Toast.makeText(this,"pesanan maximal 100",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity+1 ;
        display(quantity);
    }
    public void decrement(View view){
        if (quantity==1){
            Toast.makeText(this,"pesanan minimal 1",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity -1;
        display(quantity);
    }


    public void Submitorder(View view) {
        EditText nameEditText=(EditText)findViewById(R.id.edt_name);
        String name=nameEditText.getText().toString();
        Log.v("MainActivity","Nama:"+name);

        CheckBox whippedmanggaChekBox= (CheckBox) findViewById(R.id.WhippedMangga_checkbox);
        boolean haswhippedmangga=whippedmanggaChekBox.isChecked();
        Log.v("MainActivity","has whippedmangga:"+haswhippedmangga);


        CheckBox AlpukatChekBox= (CheckBox) findViewById(R.id.Alpukat_checkbox);
        boolean hasAlpukat=AlpukatChekBox.isChecked();
        Log.v("MainActivity","has whippedAlpukat:"+hasAlpukat);

        CheckBox JambuChekBox= (CheckBox) findViewById(R.id.Jambu_checkbox);
        boolean hasJambu=JambuChekBox.isChecked();
        Log.v("MainActivity","has whippedJambu:"+hasJambu);

        CheckBox ApelChekBox= (CheckBox) findViewById(R.id.Apel_checkbox);
        boolean hasApel=ApelChekBox.isChecked();
        Log.v("MainActivity","has whippedApel:"+hasApel);

        int price=calculateprice(haswhippedmangga,hasAlpukat,hasJambu,hasApel);
        String pricemessage=createOrderSummary(price,name,haswhippedmangga,hasAlpukat,hasJambu,hasApel);


        displayMessage(pricemessage);

    }
    private int calculateprice(boolean hasJambu, boolean hasApel, boolean haswhippedmangga, boolean hasAlpukat)
    {
        int harga=0;

        if(haswhippedmangga){
            harga=harga+11000;
        }

        if (hasAlpukat){
            harga=harga+13000;
        }
        if (hasJambu){
            harga=harga+11000;
        }
        if (hasApel){
            harga=harga+12000;
        }
        return quantity * harga;
    }
    private String createOrderSummary(int price, String name, boolean haswhippedmangga, boolean hasAlpukat, boolean hasJambu, boolean hasApel) {
        String pricemessage=" Nama = "+name;
        pricemessage+="\n Jus Mangga =" +haswhippedmangga;
        pricemessage+="\n Jus Alpukat =" +hasAlpukat;
        pricemessage+="\n Jus Jambu =" +hasJambu;
        pricemessage+="\n Jus Apel =" +hasApel;
        pricemessage+="\n Jumlah Pemesanan =" +quantity;
        pricemessage+="\n Total = Rp " +price;
        pricemessage+="\n Terimakasih";
        return  pricemessage;
    }


    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_textview);
        priceTextView.setText(message);
    }
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_textview);
        quantityTextView.setText("" + number);
    }
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_textview);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

}