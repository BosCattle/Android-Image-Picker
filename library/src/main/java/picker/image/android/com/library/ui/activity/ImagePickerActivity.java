package picker.image.android.com.library.ui.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import picker.image.android.com.library.R;
import picker.image.android.com.library.option.Constant;
import picker.image.android.com.library.option.MediaOptions;
import picker.image.android.com.library.ui.fragment.MutiImagePickerFragment;

/**
 * Picker image by kevin
 */
public class ImagePickerActivity extends AppCompatActivity {

  private static MediaOptions mMediaOptions;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_image_picker);
    issueObject();
  }

  /**
   * Open image picker activity {@link MediaOptions}
   */
  public static void openFromActivity(AppCompatActivity activity, MediaOptions option, int code) {
    Intent intent = new Intent(activity, ImagePickerActivity.class);
    mMediaOptions = option;
    activity.startActivityForResult(intent, code);
  }

  /**
   * Open image picker activity from {@link Fragment} {@link MediaOptions}
   */
  @TargetApi(Build.VERSION_CODES.M) public static void openFromFragment(Fragment fragment,
      MediaOptions option, int code) {
    Intent intent = new Intent(fragment.getContext(), ImagePickerActivity.class);
    intent.putExtra(Constant.CODE_KEY, code);
    intent.putExtra(Constant.MEDIA_KEY, option);
    fragment.startActivityForResult(intent, code);
  }

  /**
   * Get FragmentTransaction Instance
   *
   * @return {@link FragmentTransaction}
   */
  @SuppressLint("CommitTransaction") public FragmentTransaction getFragmentTransaction() {
    FragmentManager fragmentManager = getFragmentManager();
    return fragmentManager.beginTransaction();
  }

  /**
   * Issue MediaOptions
   */
  public void issueObject() {
    FragmentTransaction transaction = getFragmentTransaction();
    if (mMediaOptions.canSelectMultiPhoto()) {
      // Switching Fragment
      Fragment mutiFragment = MutiImagePickerFragment.newInstance();
      Bundle bundle = new Bundle();
      bundle.putParcelable(Constant.MEDIA_KEY, mMediaOptions);
      mutiFragment.setArguments(bundle);
      transaction.replace(R.id.ui_container_index, mutiFragment);
    } else {
      Fragment singleFragment = MutiImagePickerFragment.newInstance();
      Bundle bundle = new Bundle();
      bundle.putParcelable(Constant.MEDIA_KEY, mMediaOptions);
      singleFragment.setArguments(bundle);
      transaction.replace(R.id.ui_container_index, singleFragment);
    }
    transaction.commit();
  }
}
