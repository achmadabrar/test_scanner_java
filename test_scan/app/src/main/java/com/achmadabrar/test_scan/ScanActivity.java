package com.achmadabrar.test_scan;

import androidx.appcompat.app.AppCompatActivity;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.zxing.Result;

public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    ZXingScannerView zXingScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        zXingScannerView = new ZXingScannerView(this);
        FrameLayout frameLayout = findViewById(R.id.fl_camera);

        frameLayout.addView(zXingScannerView);
    }

    @Override
    protected void onResume() {
        zXingScannerView.setAutoFocus(true);
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
        super.onResume();
    }

    @Override
    protected void onPause() {
        zXingScannerView.stopCamera();
        super.onPause();
    }

    @Override
    public void handleResult(Result result) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_camera, ResultFragment.newInstance(result.getText())).commit();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}