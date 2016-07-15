package picker.image.android.com.library.ui.activity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import java.util.ArrayList;
import java.util.List;
import picker.image.android.com.library.R;
import picker.image.android.com.library.option.Constant;
import picker.image.android.com.library.view.ViewPagerFixed;

/**
 * Zoom ImageView
 */
public class ImageZoomActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

  private ViewPagerFixed mUiViewViewPager;
  private List<Uri> mItemLists;
  private int position;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_image_zoom);
    initialization();
    initializationItemList();
    setUpViewPagerAdapter();
  }

  private void initialization() {
    mUiViewViewPager = (ViewPagerFixed) findViewById(R.id.ui_view_view_pager);
  }

  private void initializationItemList() {
    mItemLists = getIntent().getParcelableArrayListExtra(Constant.CONSTANT_IMAGE_LIST);
    position = getIntent().getIntExtra(Constant.CONSTANT_IMAGE_POSTION, 0);
    Uri image = getIntent().getParcelableExtra(Constant.CONSTANT_IMAGE);
    if (image != null) {
      mItemLists = new ArrayList<>();
      mItemLists.add(image);
    }
  }

  @SuppressLint("SetTextI18n") private void setUpViewPagerAdapter() {
    ImageViewPagerAdapter adapter = new ImageViewPagerAdapter();
    mUiViewViewPager.setAdapter(adapter);
    mUiViewViewPager.setCurrentItem(position);
    mUiViewViewPager.setOnPageChangeListener(this);
  }

  @Override
  public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

  }

  @Override public void onPageSelected(int position) {

  }

  @Override public void onPageScrollStateChanged(int state) {

  }

  class ImageViewPagerAdapter extends PagerAdapter {

    @Override public int getCount() {
      if (mItemLists != null && mItemLists.size() != 0) {
        return mItemLists.size();
      }
      return 0;
    }

    @Override public boolean isViewFromObject(View view, Object object) {
      return view == object;
    }

    @Override public void destroyItem(ViewGroup container, int position, Object object) {
      View v = (View) object;
      container.removeView(v);
    }

    @Override public Object instantiateItem(ViewGroup container, int position) {
      View v = LayoutInflater.from(ImageZoomActivity.this)
          .inflate(R.layout.list_item_zoom_imageview, container, false);
      ImageView imageView = (ImageView) v.findViewById(R.id.ui_view_iv);
      Uri uri = mItemLists.get(position);
      container.addView(imageView);
      Glide.with(ImageZoomActivity.this)
          .load(uri)
          .centerCrop()
          .into(imageView);
      return imageView;
    }
  }
}
