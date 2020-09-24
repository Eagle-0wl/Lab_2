package com.example.lab_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private View txtInputBox;
    private TextView tvMain;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner userChoice = findViewById(R.id.spinWordSymbol);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.UserChoice, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userChoice.setAdapter(adapter);
        userChoice.setOnItemSelectedListener(this);
        this.tvMain = findViewById(R.id.tvMain);
        // this.txtInputBox = findViewById(R.id.txtInputBox);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    text = parent.getItemAtPosition(position).toString();
    Toast.makeText(parent.getContext(),text, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void btnCounter(View view) {
        EditText txtInputBox=findViewById(R.id.txtInputBox);
        String userInput=txtInputBox.getText().toString();
        int userInputLength=userInput.length();
        Context context = getApplicationContext();
        if(userInputLength>0) {
            if (text.equals("Words")) {
                int wordCount = 0;
                char[] userInputCharacters = new char[userInputLength];
                for (int i = 0; i < userInputLength; i++) {
                    userInputCharacters[i] = userInput.charAt(i);
                }
                int i = 0;
                while (userInputLength - 1 > i) {
                    if (userInputCharacters[i] == ',' && userInputCharacters[i + 1] != ' ') {
                        wordCount++;
                    } else if (userInputCharacters[i] == ',' && userInputCharacters[i + 1] == ' ') {
                        wordCount++;
                        i++;
                    } else if (userInputCharacters[i] == '.' && userInputCharacters[i + 1] != ' ') {
                        wordCount++;
                    } else if (userInputCharacters[i] == '.' && userInputCharacters[i + 1] == ' ') {
                        wordCount++;
                        i++;
                    } else if (userInputCharacters[i] == ' ') {
                        wordCount++;
                    }
                    i++;
                }
                if (userInputCharacters[userInputLength - 1] != ',' || userInputCharacters[userInputLength - 1] != '.' || userInputCharacters[userInputLength - 1] != ' ')
                    wordCount++;
                this.tvMain.setText(Integer.toString(wordCount));
            } else if (text.equals("Symbols")) {
                this.tvMain.setText(Integer.toString(userInput.length()));
            }
        }
        else  Toast.makeText(context, "The field is emty", Toast.LENGTH_SHORT).show();
            //this.tvMain.setText("0");

    }
}
