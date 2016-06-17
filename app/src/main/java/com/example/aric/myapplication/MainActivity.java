package com.example.aric.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CompoundBarcodeView;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private EditText searchView;
    private CompoundBarcodeView barcodeView;
    private boolean isOpened;
    private TextView tv_content;
    private boolean flight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         initView();
        barcodeView.decodeContinuous(callback);
        searchView.addTextChangedListener(new InputTextChangeListener());
        barcodeView.setStatusText("Please put barcode into camera area--haifeng");

    }


    private class InputTextChangeListener implements TextWatcher{
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String inputString = searchView.getText().toString();
            boolean empty = inputString.isEmpty();
            if (!empty &&isOpened){
                barcodeView.setVisibility(View.GONE);
                isOpened=false;
            }

            if(empty&&!isOpened){
                barcodeView.setVisibility(View.VISIBLE);
                isOpened=true;
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    private void initView() {
        flight=false;
        isOpened = true;
        tv_content=(TextView)findViewById(R.id.tv_content);
        barcodeView = (CompoundBarcodeView) findViewById(R.id.barcode_scanner);
        searchView=(EditText)findViewById(R.id.seach_text);
    }

    public void bt_flight(View view){

        if(flight==false){
            barcodeView.setTorchOn();
        }else {
            barcodeView.setTorchOff();
        }
        flight=!flight;

    }


    private BarcodeCallback callback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
            if (result.getText() != null) {
                tv_content.setText(result.getText());
            }
        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {
        }
    };

    @Override
    public void onResume() {
        barcodeView.resume();
        super.onResume();
    }

    @Override
    public void onPause() {
        barcodeView.pause();
        super.onPause();
    }


}

