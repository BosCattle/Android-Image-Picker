package com.android.image.picker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import picker.image.android.com.library.option.Constant;
import picker.image.android.com.library.option.MediaOptions;
import picker.image.android.com.library.ui.activity.ImagePickerActivity;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    findViewById(R.id.btn_image_picker).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        MediaOptions mediaOptions = new MediaOptions.Builder().canSelectMultiPhoto(true).setImageSize(2).build();
        ImagePickerActivity.openFromActivity(MainActivity.this,mediaOptions, Constant.CONSTANT_REQUEST_CODE);
      }
    });
  }
}
