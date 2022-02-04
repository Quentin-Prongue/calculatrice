package com.pronque.calculatrice;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Déclare toutes les variables dont on aura besoin
    Button btnNum0, btnNum1, btnNum2, btnNum3, btnNum4, btnNum5, btnNum6, btnNum7, btnNum8, btnNum9,
            btnPlus, btnMinus, btnDiv, btnTimes, btnClear, btnDelete, btnEqual, btnDot;
    EditText ecran;

    private double chiffre;
    private boolean clickOperator = false;
    private boolean update = false;
    private String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupère tous les éléments
        btnNum0 = findViewById(R.id.num0);
        btnNum1 = findViewById(R.id.num1);
        btnNum2 = findViewById(R.id.num2);
        btnNum3 = findViewById(R.id.num3);
        btnNum4 = findViewById(R.id.num4);
        btnNum5 = findViewById(R.id.num5);
        btnNum6 = findViewById(R.id.num6);
        btnNum7 = findViewById(R.id.num7);
        btnNum8 = findViewById(R.id.num8);
        btnNum9 = findViewById(R.id.num9);
        btnDot = findViewById(R.id.dot);
        btnPlus = findViewById(R.id.addition);
        btnMinus = findViewById(R.id.substraction);
        btnDiv = findViewById(R.id.division);
        btnTimes = findViewById(R.id.multiply);
        btnClear = findViewById(R.id.clear);
        btnDelete = findViewById(R.id.delete);
        btnEqual = findViewById(R.id.equal);
        ecran = findViewById(R.id.main_result);


        // Attribue un on click listener à tous les boutons
        btnPlus.setOnClickListener(v -> plusClick());
        btnMinus.setOnClickListener(v -> minusClick());
        btnTimes.setOnClickListener(v -> mulClick());
        btnDiv.setOnClickListener(v -> divClick());
        btnClear.setOnClickListener(v -> resetClick());
        btnDelete.setOnClickListener(v -> deleteClick());
        btnEqual.setOnClickListener(v -> equalClick());
        btnDot.setOnClickListener(v -> numberClick("."));
        btnNum0.setOnClickListener(v -> numberClick("0"));
        btnNum1.setOnClickListener(v -> numberClick("1"));
        btnNum2.setOnClickListener(v -> numberClick("2"));
        btnNum3.setOnClickListener(v -> numberClick("3"));
        btnNum4.setOnClickListener(v -> numberClick("4"));
        btnNum5.setOnClickListener(v -> numberClick("5"));
        btnNum6.setOnClickListener(v -> numberClick("6"));
        btnNum7.setOnClickListener(v -> numberClick("7"));
        btnNum8.setOnClickListener(v -> numberClick("8"));
        btnNum9.setOnClickListener(v -> numberClick("9"));
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Désactive les boutons d'opérations au départ de l'application
        btnPlus.setEnabled(false);
        btnMinus.setEnabled(false);
        btnTimes.setEnabled(false);
        btnDiv.setEnabled(false);

        // Si aucun chiffre n'est tapé, les boutons d'opérations sont désactivés
        ecran.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!ecran.getText().toString().equals("")) {
                    btnPlus.setEnabled(true);
                    btnMinus.setEnabled(true);
                    btnTimes.setEnabled(true);
                    btnDiv.setEnabled(true);
                } else {
                    btnPlus.setEnabled(false);
                    btnMinus.setEnabled(false);
                    btnTimes.setEnabled(false);
                    btnDiv.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }


    /**
     * Méthode qui est exécutée lorsqu'on clique sur un chiffre
     * @param str le nombre en string
     */
    public void numberClick(String str) {
        if (update) {
            update = false;
        } else {
            if (!ecran.getText().toString().equals("0"))
                str = ecran.getText() + str;
        }
        ecran.setText(str);
    }

    /**
     * Méthode qui est exécutée lorsqu’on clique sur le bouton +
     */
    public void plusClick() {
        if (clickOperator) {
            calculate();
            ecran.setText(String.valueOf(chiffre));
        } else {
            chiffre = Double.parseDouble(ecran.getText().toString());
            clickOperator = true;
        }
        operator = "+";
        update = true;
    }

    /**
     * Méthode qui est exécutée lorsqu’on clique sur le bouton -
     */
    public void minusClick() {
        if (clickOperator) {
            calculate();
            ecran.setText(String.valueOf(chiffre));
        } else {
            chiffre = Double.parseDouble(ecran.getText().toString());
            clickOperator = true;
        }
        operator = "-";
        update = true;
    }

    /**
     * Méthode qui est exécutée lorsqu’on clique sur le bouton *
     */
    public void mulClick() {
        if (clickOperator) {
            calculate();
            ecran.setText(String.valueOf(chiffre));
        } else {
            chiffre = Double.parseDouble(ecran.getText().toString());
            clickOperator = true;
        }
        operator = "*";
        update = true;
    }

    /**
     * Méthode qui est exécutée lorsqu’on clique sur le bouton /
     */
    public void divClick() {
        operator = "/";

        if (clickOperator) {
            calculate();
            ecran.setText(String.valueOf(chiffre));
        } else {
            chiffre = Double.parseDouble(ecran.getText().toString());
            clickOperator = true;
        }
        update = true;
    }

    /**
     * Méthode qui est exécutée lorsqu’on clique sur le bouton =
     */
    public void equalClick() {
        calculate();
        update = true;
        clickOperator = false;
    }

    /**
     * Méthode qui est exécutée lorsqu’on clique sur le bouton clear
     */
    public void resetClick() {
        clickOperator = false;
        update = true;
        chiffre = 0;
        operator = "";
        ecran.setText("");
    }

    /**
     * Méthode qui est exécutée lorsqu’on clique sur le bouton delete
     */
    public void deleteClick() {
        if (!ecran.getText().toString().equals("")) {
            String s = ecran.getText().toString();
            s = s.substring(0, s.length() - 1);
            ecran.setText(s);
        }
    }

    /**
     * Méthode qui est faite en fonction de l'opérateur cliqué par l'utilisateur
     */
    private void calculate() {
        switch (operator) {
            case "+":
                chiffre += Double.parseDouble(ecran.getText().toString());
                ecran.setText(String.valueOf(chiffre));
                break;
            case "-":
                chiffre -= Double.parseDouble(ecran.getText().toString());
                ecran.setText(String.valueOf(chiffre));
                break;
            case "*":
                chiffre *= Double.parseDouble(ecran.getText().toString());
                ecran.setText(String.valueOf(chiffre));
                break;
            case "/":
                chiffre /= Double.parseDouble(ecran.getText().toString());
                ecran.setText(String.valueOf(chiffre));
        }

    }
}