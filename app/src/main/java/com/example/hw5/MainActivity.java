package com.example.hw5;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    private String operation;
    private TextView textView;
    private Integer first, second;
    private Boolean isOperationClick;
    private MaterialButton resultButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        textView = findViewById(R.id.text_view);
        resultButton = findViewById(R.id.result_button);
        resultButton.setVisibility(View.GONE);

        findViewById(R.id.btn_equal).setOnClickListener(view -> {
            second = Integer.valueOf(textView.getText().toString());
            Integer result;

            switch (operation) {
                case "+":
                    result = first + second;
                    break;
                case "-":
                    result = first - second;
                    break;
                case "*":
                    result = first * second;
                    break;
                case "/":
                    result = second != 0 ? first / second : 0;
                    break;
                default:
                    result = 0;
            }

            textView.setText(result.toString());
            resultButton.setVisibility(View.VISIBLE);

            resultButton.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("key", result.toString());
                startActivity(intent);
            });
        });
    }

    public void onNumberClick(View view) {
        String text = ((MaterialButton) view).getText().toString();
        if (text.equals("AC")) {
            textView.setText("0");
            first = 0;
            second = 0;
            resultButton.setVisibility(View.GONE); // Скрыть кнопку
        } else if (textView.getText().toString().equals("0") || isOperationClick) {
            textView.setText(text);
        } else {
            textView.append(text);
        }
        isOperationClick = false;
    }

    public void onOperationClick(View view) {
        first = Integer.valueOf(textView.getText().toString());
        if (view.getId() == R.id.btn_plus) {
            operation = "+";
        } else if (view.getId() == R.id.btn_minus) {
            operation = "-";
        } else if (view.getId() == R.id.btn_multiply) {
            operation = "*";
        } else if (view.getId() == R.id.btn_divide) {
            operation = "/";
        }
        isOperationClick = true;
        resultButton.setVisibility(View.GONE);
    }
}