package picker.image.android.com.library.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import picker.image.android.com.library.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageCropFragment extends Fragment {

  public static ImageCropFragment newInstance() {
    return new ImageCropFragment();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_image_crop, container, false);
  }
}
