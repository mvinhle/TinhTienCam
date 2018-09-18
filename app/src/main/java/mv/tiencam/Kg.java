package mv.tiencam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class Kg extends AppCompatActivity {
    // nhóm add, edit
    EditText inputKg;
    Button add;
    ListView listView;
    ArrayList<String> listKG = new ArrayList<>();
    ArrayAdapter adt;
    TextView sumSoCap, tP;
    double sumSC = 0d, sumA = 0d;
    double pT = 0d, bT = 0d;
    // hệ thống phao
    RadioGroup cachPhao;
    RadioButton p1, p2, p3, p4, p5;
    EditText phaoSetE;
    Button phaoSetB;
    //hệ thống bì
    TextView tB;
    EditText soL, soK;
    Button oBi;
    //reset
    Button rB, rP;
    TextView ssc;
    //fix gấp ko màn hậu quả :D
    int id = 0;
    boolean b = true;
    //xong
    Button xong;

    boolean bool = true;
    int vt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kg);
//nhóm add, edit
        inputKg = (EditText) findViewById(R.id.EditTextKg);
        add = (Button) findViewById(R.id.ButtonAdd);
        listView = (ListView) findViewById(R.id.ListViewKg);
        adt = new ArrayAdapter(
                Kg.this, android.R.layout.simple_list_item_1, listKG);
        listView.setAdapter(adt);
        sumSoCap = (TextView) findViewById(R.id.TextViewSum);

        cachPhao = (RadioGroup) findViewById(R.id.RadioGroupPhao);
        p1 = (RadioButton) findViewById(R.id.id1);
        p2 = (RadioButton) findViewById(R.id.id2);
        p3 = (RadioButton) findViewById(R.id.id3);
        p4 = (RadioButton) findViewById(R.id.id4);
        p5 = (RadioButton) findViewById(R.id.id5);
        phaoSetB = (Button) findViewById(R.id.ButtonPhao);
        phaoSetE = (EditText) findViewById(R.id.EditTextPhao);
        tP = (TextView) findViewById(R.id.TextViewTP);

        oBi = (Button) findViewById(R.id.ButtonBi);
        soK = (EditText) findViewById(R.id.EditTextBi);
        soL = (EditText) findViewById(R.id.EditTextBi2);
        tB = (TextView) findViewById(R.id.TextViewTB);

        rB = (Button) findViewById(R.id.ButtonRB);
        rP = (Button) findViewById(R.id.ButtonRP);
        ssc = (TextView) findViewById(R.id.TextViewSC);

        xong = (Button) findViewById(R.id.ButtonBack);

        enabled(false);

        xong.setVisibility(View.INVISIBLE);
        xong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                Intent end = new Intent(Kg.this, End.class);
                end.putExtra("kgSC",String.valueOf(sumSC));
                end.putExtra("phao",String.valueOf(pT));
                end.putExtra("bi",String.valueOf(bT));
                end.putExtra("kgAll",String.valueOf(sumA));
                end.putExtra("soMoneyDefault", intent.getStringExtra("soMoneyDefault"));
                startActivity(end);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click(bool, vt);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                add.setText("Sửa");
                bool = false;
                vt = i;
                inputKg.setText(listKG.get(i));
                inputKg.setSelection(inputKg.length());
                adt.notifyDataSetChanged();
                setSum();
            }
        });

        cachPhao.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                id = i;
                idP(id);
            }
        });

        oBi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bT += (HelpData.dataInput(soL.getText().toString())
                        *HelpData.dataInput(soK.getText().toString()));
                tB.setText("Bì: ".concat(String.valueOf(bT)).concat(" Kg."));
                soL.setHint(soL.getText().toString());
                soL.setText(null);
                soK.setHint(soK.getText().toString());
                soK.setText(null);
                setSum();
            }
        });

        rP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pT = 0d;
                phaoSetE.setText(null);
                phaoSetE.setHint("Số Kg phải phao");
                setSum2();
            }
        });
        rB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bT = 0d;
                soL.setText(null);
                soL.setHint("Số Kg / Bì");
                soK.setText(null);
                soK.setHint("Số lần cân / Bì");
                setSum();
            }
        });
    }

    void click(boolean b, int vt) { // click add sẽ là add hay edit
        add.setText("Thêm");
        if (b) {
            listKG.add(0, String.valueOf(HelpData.dataInput(inputKg.getText().toString())));
        } else {
            listKG.set(vt, String.valueOf(HelpData.dataInput(inputKg.getText().toString())));
        }
        for (int i = 0; i < listKG.size(); i++) { //tổng sơ cấp và xóa số 0
            if (listKG.get(i).equals("0.0")) {
                listKG.remove(i);
            }
        }
        sumSC = 0d;
        for (int i = 0; i < listKG.size(); i++) {
            sumSC += HelpData.dataInput(listKG.get(i));
        }
        bool = true;
        inputKg.setText(null);
        setSum();
        adt.notifyDataSetChanged();
    }

    void enabled(Boolean b) {
        phaoSetE.setEnabled(b);
        phaoSetB.setEnabled(b);
    }
    void setSum(){
        idP(id);
        sumA = sumSC - bT - pT;
        sumSoCap.setText("Tổng: ".concat(String.valueOf(sumA)).concat(" Kg."));
        tP.setText("Trừ Phao: ".concat(String.valueOf(pT)).concat(" Kg."));
        tB.setText("Trừ Bì: ".concat(String.valueOf(bT)).concat(" Kg."));
        ssc.setText("Tổng cơ bản: ".concat(String.valueOf(sumSC)).concat(" Kg."));
        tB.setText("Bì: ".concat(String.valueOf(bT)).concat(" Kg."));

        xong.setVisibility(View.VISIBLE);
    }
    void setSum2(){
        sumA = sumSC - bT - pT;
        sumSoCap.setText("Tổng: ".concat(String.valueOf(sumA)).concat(" Kg."));
        tP.setText("Trừ Phao: ".concat(String.valueOf(pT)).concat(" Kg."));
        tB.setText("Trừ Bì: ".concat(String.valueOf(bT)).concat(" Kg."));
        ssc.setText("Tổng cơ bản: ".concat(String.valueOf(sumSC)).concat(" Kg."));
        tB.setText("Bì: ".concat(String.valueOf(bT)).concat(" Kg."));

        xong.setVisibility(View.VISIBLE);
    }
    void idP(int i){
        Intent intent = getIntent();
        double pDefault = Double.parseDouble(intent.getStringExtra("soPhaoDefault"));
        switch (i) {
            case R.id.id1:
                pT = pDefault * (sumSC / 100);
                phaoSetE.setHint(String.valueOf(pT));
                enabled(false);
                setSum2();
                break;
            case R.id.id2:
                pT = Math.round(pDefault * (sumSC / 100));
                phaoSetE.setHint(String.valueOf(pT));
                enabled(false);
                setSum2();
                break;
            case R.id.id3:
                pT = Math.ceil(pDefault * (sumSC / 100));
                phaoSetE.setHint(String.valueOf(pT));
                enabled(false);
                setSum2();
                break;
            case R.id.id4:
                pT = Math.floor(pDefault * (sumSC / 100));
                phaoSetE.setHint(String.valueOf(pT));
                enabled(false);
                setSum2();
                break;
            case R.id.id5:
                phaoSetE.setHint(String.valueOf(pT));
                enabled(true);
                setSum2();
                phaoSetB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pT = HelpData.dataInput(phaoSetE.getText().toString());
                        phaoSetE.setHint(String.valueOf(pT));
                        phaoSetE.setText(null);
                        setSum2();
                    }
                });
                break;
        }
    }
}
