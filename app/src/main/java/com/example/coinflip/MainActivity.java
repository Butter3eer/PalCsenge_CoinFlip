package com.example.coinflip;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView imageViewCoin;
    private Button buttonFej;
    private Button buttonIras;
    private TextView textViewEredmeny;
    private Random random;
    private AlertDialog.Builder jatekVege;
    private int dobasok;
    private int gyozelem;
    private int vereseg;
    private String eredmeny;
    private int tipp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        buttonFej.setOnClickListener(view -> {
            tipp = 0;
            int gepTipp = random.nextInt(2);

            ellenorzes(gepTipp, tipp);
        });

        buttonIras.setOnClickListener(view -> {
            tipp = 1;
            int gepTipp = random.nextInt(2);

            ellenorzes(gepTipp, tipp);
        });
    }

    public void init() {
        imageViewCoin = findViewById(R.id.imageViewCoin);
        buttonFej = findViewById(R.id.buttonFej);
        buttonIras = findViewById(R.id.buttonIras);
        textViewEredmeny = findViewById(R.id.textViewEredmeny);
        random = new Random();
        dobasok = 0;
        gyozelem = 0;
        vereseg = 0;
        tipp = 0;
        eredmeny = "";
        jatekVege = new AlertDialog.Builder(this);

        jatekVege.setMessage("Szeretne új játékot játszani?")
                .setNegativeButton("NEM", (dialogInterface, i) -> {
                    finish();
                })
                .setPositiveButton("IGEN", (dialogInterface, i) -> {
                    newGame();
                })
                .create();
    }

    public void newGame() {
        imageViewCoin.setImageResource(R.drawable.heads);
        dobasok = 0;
        gyozelem = 0;
        vereseg = 0;
        eredmeny = "Dobások: 0\nGyőzelem: 0\nVereség: 0";
        textViewEredmeny.setText(eredmeny);
    }

    public void gepTipp(int szam) {
        switch (szam) {
            case 0:
                imageViewCoin.startAnimation(animation);
                imageViewCoin.setImageResource(R.drawable.heads);
                Toast.makeText(this, "FEJ.", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                imageViewCoin.startAnimation(animation);
                imageViewCoin.setImageResource(R.drawable.tails);
                Toast.makeText(this, "ÍRÁS.", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void ellenorzes(int geptipp, int tipp) {
        if (dobasok == 5) {

            if (gyozelem > vereseg) {
                jatekVege.setTitle("Győzelem").create();
                jatekVege.show();
            }
            else {
                jatekVege.setTitle("Vereség").create();
                jatekVege.show();
            }
        }
        else if (gyozelem == 3 || vereseg == 3) {

            if (gyozelem > vereseg) {
                jatekVege.setTitle("Győzelem").create();
                jatekVege.show();
            }
            else {
                jatekVege.setTitle("Vereség").create();
                jatekVege.show();
            }
        }
        else {
            gepTipp(geptipp);
            dobasok++;
            if (tipp == geptipp) {
                gyozelem++;
            }
            else {
                vereseg++;
            }

            eredmeny = "Dobások: " + dobasok + "\nGyőzelem: " + gyozelem + "\nVereség: " + vereseg;

            textViewEredmeny.setText(eredmeny);
        }
    }

}