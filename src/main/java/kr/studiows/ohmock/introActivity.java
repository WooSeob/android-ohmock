package kr.studiows.ohmock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public class introActivity extends AppCompatActivity {
    private String Tag = "ohMock PlayPvPActivity";
    long m_exitPressedTime = 0;
    public DefaultButton BTN_pvp;
    public DefaultButton BTN_vsCpu;
    public DefaultButton BTN_setting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        //창 전환 에니메이션
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        //버튼객체 등록
        BTN_pvp = (DefaultButton)findViewById(R.id.BTN_pvp);
        BTN_vsCpu = (DefaultButton)findViewById(R.id.BTN_vsCpu);
        BTN_setting = (DefaultButton)findViewById(R.id.BTN_setting);
        BTN_pvp.setText("vsPvP");
        BTN_vsCpu.setText("vsCpu");
        BTN_setting.setText("setting");

        //클릭 리스너등록
        BTN_pvp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), pvpActivity.class);
                startActivity(i); //pvp 시작
            }
        });
        BTN_vsCpu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "준비중입니다.", Toast.LENGTH_SHORT).show();
            }
        });
        BTN_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "준비중입니다.", Toast.LENGTH_SHORT).show();
            }
        });

        Log.i(Tag, "onCreate");
    }

    public void onBackPressed()
    {
        long currentTime = System.currentTimeMillis();
        if(currentTime - m_exitPressedTime <= 2000)
        {
            finish();
        }
        else
        {
            m_exitPressedTime = currentTime;
            Toast.makeText(this, "게임을 끝내려면 뒤로가기를 두번 눌러주세요", Toast.LENGTH_SHORT).show();

        }
    }
    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }
}
