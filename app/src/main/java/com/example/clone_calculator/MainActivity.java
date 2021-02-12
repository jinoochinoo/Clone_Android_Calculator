package com.example.clone_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.faendir.rhino_android.RhinoAndroidHelper;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ast.Scope;

public class MainActivity extends AppCompatActivity {

    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnPercent, btnPlus,
            btnMinus, btnMultiply, btnDivision, btnEqual, btnClear, btnDot, btnBracket;
    TextView tvInput, tvOutput;
    String process;
    boolean checkBracket = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnDivision = findViewById(R.id.btnDivision);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnEqual = findViewById(R.id.btnEqual);
        btnClear = findViewById(R.id.btnClear);
        btnDot = findViewById(R.id.btnDot);
        btnPercent = findViewById(R.id.btnPercent);
        btnBracket = findViewById(R.id.btnBracket);

        tvInput = findViewById(R.id.tvInput);
        tvOutput = findViewById(R.id.tvOutput);

        // Clear 버튼 누르면 input, output 초기화
        btnClear.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                tvInput.setText("");
                tvOutput.setText("");
            }
        });

        // 0 버튼 누르면 기존 텍스트에 0 더함
        btn0.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                process = tvInput.getText().toString();
                tvInput.setText(process + "0");
            }
        });

        btn1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                process = tvInput.getText().toString();
                tvInput.setText(process + "1");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                process = tvInput.getText().toString();
                tvInput.setText(process + "2");
            }
        });

        btn3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                process = tvInput.getText().toString();
                tvInput.setText(process + "3");
            }
        });

        btn4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                process = tvInput.getText().toString();
                tvInput.setText(process + "4");
            }
        });

        btn5.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                process = tvInput.getText().toString();
                tvInput.setText(process + "5");
            }
        });

        btn6.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                process = tvInput.getText().toString();
                tvInput.setText(process + "6");
            }
        });

        btn7.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                process = tvInput.getText().toString();
                tvInput.setText(process + "7");
            }
        });

        btn8.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                process = tvInput.getText().toString();
                tvInput.setText(process + "8");
            }
        });

        btn9.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                process = tvInput.getText().toString();
                tvInput.setText(process + "9");
            }
        });

        // +
        btnPlus.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                process = tvInput.getText().toString();
                tvInput.setText(process + "+");
            }
        });

        // -
        btnMinus.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                process = tvInput.getText().toString();
                tvInput.setText(process + '-');
            }
        });

        // *
        btnMultiply.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                process = tvInput.getText().toString();
                tvInput.setText(process + '*');
            }
        });

        // /
        btnDivision.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                process = tvInput.getText().toString();
                tvInput.setText(process + '/');
            }
        });

        // .
        btnDot.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                process = tvInput.getText().toString();
                tvInput.setText(process + '.');
            }
        });

        // %
        btnPercent.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                process = tvInput.getText().toString();
                tvInput.setText(process + '%');
            }
        });

        // 괄호 버튼 눌렀을 때 () 설정
        btnBracket.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(checkBracket){
                    process = tvInput.getText().toString();
                    tvInput.setText(process + ")");
                    checkBracket = false;
                } else{
                    process = tvInput.getText().toString();
                    tvInput.setText(process + "(");
                    checkBracket = true;
                }
            }
        });

        // = 버튼 눌렀을 때 계산 -> 결과 출력
        btnEqual.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                process = tvInput.getText().toString();
                // % -> /100 바꿈
                process = process.replaceAll("%", "/100");

                Context rhino = Context.enter();
                rhino.setOptimizationLevel(-1);

                String finalResult = ""; // 최종결과 변수 선언 및 초기화

                try{
                    Scriptable scriptable = rhino.initStandardObjects();
                    // 프로세스 수식 계산해서 finalResult 저장
                    finalResult = rhino.evaluateString(scriptable, process,
                            "javascript",1, null).toString();
                } catch(Exception e){
                    finalResult = "0";
                }
                tvOutput.setText(finalResult);
            }
        });
    }
}