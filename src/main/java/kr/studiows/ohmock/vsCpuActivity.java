package kr.studiows.ohmock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

public class vsCpuActivity extends AppCompatActivity {
    private String Tag = "ohMock vsCpuActivity";
    long m_exitPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_vs_cpu);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void onBackPressed()
    {
        long currentTime = System.currentTimeMillis();
        if(currentTime - m_exitPressedTime <= 2000)
        {
            exit();
        }
        else
        {
            m_exitPressedTime = currentTime;
            Toast.makeText(this, "게임을 끝내려면 뒤로가기를 두번 눌러주세요", Toast.LENGTH_SHORT).show();
        }
    }
    public void exit()
    {
        finish();
    }

}
