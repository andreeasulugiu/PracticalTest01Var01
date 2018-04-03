package practicaltest01var01.eim.systems.cs.pub.ro.practicaltest01var01;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by student on 03.04.2018.
 */

public class PracticalTest01Var01SecondaryActivity extends AppCompatActivity {
    Button saveButton;
    Button cancelButton;
    TextView text;

    ButtonOnClickListener listener = new ButtonOnClickListener();

    private class ButtonOnClickListener implements Button.OnClickListener{
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.save){
                setResult(RESULT_OK, null);
            }
            if (view.getId() == R.id.cancel){
                setResult(RESULT_CANCELED, null);
            }
            finish();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_text01_var01_secondary);
        saveButton = (Button) findViewById(R.id.save);

        saveButton.setOnClickListener(listener);
        cancelButton = (Button) findViewById(R.id.cancel);
        cancelButton.setOnClickListener(listener);
        text = (TextView) findViewById(R.id.text_2);
        Intent intent = getIntent();
        if (intent != null){
            if (intent.getExtras().containsKey("text")){
                text.setText(intent.getExtras().get("text").toString());
            }
        }
    }

}
