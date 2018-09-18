package mv.tiencam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class End extends AppCompatActivity {
    TextView all, all2, all3;
    Double moneyD = 0d, money = 0d, kgSC = 0d, kgAll = 0d, phao = 0d, Bi = 0d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        all = (TextView) findViewById(R.id.TextViewAll);
        all2 = (TextView) findViewById(R.id.TextViewAll2);
        all3 = (TextView) findViewById(R.id.TextViewAll3);

        Intent vKg = getIntent();
        kgSC  = Double.parseDouble(vKg.getStringExtra("kgSC"));
        phao  = Double.parseDouble(vKg.getStringExtra("phao"));
        Bi    = Double.parseDouble(vKg.getStringExtra("bi"));
        kgAll = Double.parseDouble(vKg.getStringExtra("kgAll"));
        moneyD = Double.parseDouble(vKg.getStringExtra("soMoneyDefault"));
        money = moneyD * kgAll;

        String sSC = String.valueOf(kgSC);
        String sAll = String.valueOf(kgAll);
        String sPhao = String.valueOf(phao);
        String sBi = String.valueOf(Bi);
        String sMoney = String.valueOf(money);
        String smoney = String.valueOf(moneyD);
        all.setText("Tổng lúc đầu: "+sSC+" Kg.\nTrừ phao: "+sPhao+"Kg.\nTrừ bì: "+sBi
                +"\nGiá cam: "+smoney+" / Kg.\n");
        all2.setText("Tổng số Kg còn lại:\n\t"+sAll+"Kg.\nThành tiền: \n\t"+sMoney+"\n");
        all3.setText("Số tiền đọc là: \n\t"+"chức năng chưa ra mắt.");
    }
}