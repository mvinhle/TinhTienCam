package mv.tiencam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button mvStart;
    EditText mvMoney, mvPhao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mvPhao = (EditText) findViewById(R.id.EditTextPhao);
        mvMoney = (EditText) findViewById(R.id.EditTextMoney);
        mvStart = (Button) findViewById(R.id.ButtonStart);

        mvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent start = new Intent(MainActivity.this, Kg.class);
                start.putExtra(
                        "soPhaoDefault",
                        String.valueOf(HelpData.dataInput(mvPhao.getText().toString())));
                start.putExtra(
                        "soMoneyDefault",
                        String.valueOf(HelpData.dataInput(mvMoney.getText().toString())));
                startActivity(start);
            }
        });
    }
}
