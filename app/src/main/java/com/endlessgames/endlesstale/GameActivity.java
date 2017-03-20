package com.endlessgames.endlesstale;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.endlessgames.endlesstale.GContent.GL_SurfaceView;
import com.endlessgames.endlesstale.renderEngine.Loader;

public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final GL_SurfaceView mGLView = new GL_SurfaceView(this);
        setContentView(mGLView);

        hideSystemUI(mGLView);
        handle(mGLView);
    }

    private void handle(final View view){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hideSystemUI(view);
                handle(view);
            }
        }, 6000);
    }

    // This snippet hides the system bars.
    private void hideSystemUI(View view) {
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        view.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    @Override
    protected void onDestroy() {
       //TODO  Loader.getLoader().cleanUp();
        super.onDestroy();
    }
}
