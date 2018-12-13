package kr.studiows.ohmock;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;


public class pvpActivity extends AppCompatActivity {

    private String Tag = "ohMock PlayPvPActivity";
    public PlayView ohMockPan;

    public DefaultButton BTN_btm_launch;
    public DefaultButton BTN_top_launch;
    public DefaultButton BTN_btm_undo;
    public DefaultButton BTN_top_undo;
    public DefaultButton BTN_btm_giveUp;
    public DefaultButton BTN_top_giveUp;

    long m_exitPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pvp);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        //        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        ohMockPan = (PlayView) findViewById(R.id.PlayView);
        //버튼객체 등록
        BTN_btm_launch = (DefaultButton)findViewById(R.id.p2Launch);
        BTN_btm_undo = (DefaultButton)findViewById(R.id.p2Undo);
        BTN_btm_giveUp = (DefaultButton)findViewById(R.id.p2GiveUp);

        BTN_btm_launch.setText("착수");
        BTN_btm_undo.setText("무르기");
        BTN_btm_giveUp.setText("기권");

        BTN_top_launch = (DefaultButton)findViewById(R.id.p1Launch);
        BTN_top_undo = (DefaultButton)findViewById(R.id.p1Undo);
        BTN_top_giveUp = (DefaultButton)findViewById(R.id.p1GiveUp);

        BTN_top_launch.setText("착수");
        BTN_top_undo.setText("무르기");
        BTN_top_giveUp.setText("기권");

        BTN_top_launch.setEnabled(false);
        BTN_top_giveUp.setEnabled(false);
        BTN_top_undo.setEnabled(false);
        BTN_btm_undo.setEnabled(false);

        BTN_top_launch.setRotated();
        BTN_top_giveUp.setRotated();
        BTN_top_undo.setRotated();
        //클릭 리스너등록
            // 하단
        BTN_btm_launch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ohMockPan.BTN_launch_event())
                {
                    invertButton();
                }
            }
        });
        BTN_btm_undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ohMockPan.mController.undo())
                {
                    invertButton();
                }

            }
        });
        BTN_btm_giveUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
            //상단
        BTN_top_launch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ohMockPan.BTN_launch_event())
                {
                    invertButton();
                }
            }
        });
        BTN_top_undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ohMockPan.mController.undo())
                {
                    invertButton();
                }
            }
        });
        BTN_top_giveUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    public void invertButton()
    {
        boolean c = BTN_btm_launch.isEnabled();

        BTN_top_launch.setEnabled(c);
        BTN_top_giveUp.setEnabled(c);
        BTN_top_undo.setEnabled(!c);

        BTN_btm_launch.setEnabled(!c);
        BTN_btm_giveUp.setEnabled(!c);
        BTN_btm_undo.setEnabled(c);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed()
    {
        long currentTime = System.currentTimeMillis();
        if(currentTime - m_exitPressedTime <= 2000)
        {
            ohMockPan.stop();
            exit();
        }
        else
        {
            m_exitPressedTime = currentTime;
            Toast.makeText(this, "게임을 끝내려면 뒤로가기를 한번 더 눌러주세요", Toast.LENGTH_SHORT).show();
        }
    }
    public void exit()
    {
        finish();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        ohMockPan.stop();
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        ohMockPan.start();
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        ohMockPan.stop();
    }
}
