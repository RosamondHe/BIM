package com.swufestu.bim;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText input1,input2;
    TextView out1,out2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Calendar calendar = Calendar.getInstance();
        //SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        //System.out.println(formatter.format(calendar.getTime()));
        //自动显示当前日期时间
        //TextView showtime = findViewById(R.id.datetime);
        //showtime.setText(formatter.format(calendar.getTime()));

        //获取用户输入
        input1 = (EditText)findViewById(R.id.height);
        input2 = (EditText)findViewById(R.id.weight);
        //结果输出
        out1 = (TextView)findViewById(R.id.result);
        out2 = (TextView)findViewById(R.id.advice);
        //计算按钮监听事件
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        //日志
        Log.i("main","onClick msg...");
        //获取身高体重
        double height = Double.parseDouble(input1.getText().toString());
        double weight = Double.parseDouble(input2.getText().toString());
        //计算
        double bim = weight/(Math.pow(height, 2));
        NumberFormat nf = NumberFormat.getNumberInstance();
        // 保留两位小数
        nf.setMaximumFractionDigits(2);
        // 四舍五入
        nf.setRoundingMode(RoundingMode.UP);
        String bim2 = nf.format(bim);
        out1.setText("结果是："+bim2);

        //性别，分男女比较bim，给出建议
        RadioGroup radioGroup = findViewById(R.id.radioGroupSex);
        String sex = "";
        String ad = "";
        if (radioGroup.getCheckedRadioButtonId() == R.id.radioMale) {
            sex = "男";
        } else {
            sex = "女";
        }
        if (sex.equals("男") ) {
            if(bim<20){
                ad = "体重过轻，适当增重";
            }
            if(bim>=20&&bim<25){
                ad = "体重适中，继续保持";
            }
            if(bim>=25&&bim<30){
                ad = "体重过重，适当减肥";
            }
            if(bim>=30&&bim<35){
                ad = "肥胖，抓紧减肥";
            }
            if(bim>=35){
                ad = "非常肥胖，抓紧减肥";
            }
        }
        if (sex.equals("女") ) {
            if(bim<19){
                ad = "体重过轻，适当增重";
            }
            if(bim>=19&&bim<24){
                ad = "体重适中，继续保持";
            }
            if(bim>=24&&bim<29){
                ad = "体重过重，适当减肥";
            }
            if(bim>=29&&bim<34){
                ad = "肥胖，抓紧减肥";
            }
            if(bim>=34){
                ad = "非常肥胖，抓紧减肥";
            }
        }
        out2.setText("健康建议：\n\t"+ad+"。\n\t最理想的体重指数是22。");
    }
}