package picker.image.android.com.library.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;
import com.github.clans.fab.FloatingActionMenu;
import picker.image.android.com.library.R;

public class SingleImagePickerFragment extends Fragment {

  private Toolbar mSingleToolbar;
  private RecyclerView mSingleRecycler;
  private FloatingActionMenu mSingleFAM;
  private View mSingleView;

  public static SingleImagePickerFragment newInstance() {
    return new SingleImagePickerFragment();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    mSingleView = inflater.inflate(R.layout.fragment_single_image_picker, container, false);
    mSingleToolbar = (Toolbar) mSingleView.findViewById(R.id.single_toolbar);
    mSingleRecycler = (RecyclerView) mSingleView.findViewById(R.id.ui_single_recycler);
    mSingleFAM = (FloatingActionMenu) mSingleView.findViewById(R.id.ui_single_menu_labels);
    return mSingleView;
  }
}
