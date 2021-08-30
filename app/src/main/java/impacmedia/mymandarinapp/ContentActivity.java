package impacmedia.mymandarinapp;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.InvocationTargetException;

public class ContentActivity extends AppCompatActivity {

    WebView wVContent1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        onResume();
    }

    private void initialise() {
        String url = "file:///android_asset/index.html";
        wVContent1 = findViewById(R.id.wVContent);

        wVContent1.getSettings().setJavaScriptEnabled(true);
        wVContent1.getSettings().setLoadWithOverviewMode(true);
        wVContent1.getSettings().setUseWideViewPort(true);
        wVContent1.setBackgroundResource(R.drawable.splash_bg);

        wVContent1.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        wVContent1.loadUrl(url);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    @Override
    public void onBackPressed() {
        if(wVContent1.getVisibility()==View.VISIBLE){
            // dont pass back button action
            if(wVContent1.canGoBack()){
                wVContent1.goBack();
            }
            return;
        }else{
            // pass back button action
            super.onBackPressed();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        wVContent1.loadUrl("about:blank");
//        wVContent1.onPause();
//        wVContent1.pauseTimers();
    }

    @Override
    public void onResume() {
        super.onResume();
        initialise();
    }


    @Override
    protected void onDestroy() {
        wVContent1.destroy();
        super.onDestroy();
    }
}
