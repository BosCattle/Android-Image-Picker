package picker.image.android.com.library.ui.fragment;

import android.Manifest;
import android.annotation.TargetApi;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.tbruyelle.rxpermissions.RxPermissions;

import picker.image.android.com.library.R;
import picker.image.android.com.library.callback.ItemTouchListener;
import picker.image.android.com.library.option.Constant;
import picker.image.android.com.library.option.MediaOptions;
import picker.image.android.com.library.option.MediaUtils;
import picker.image.android.com.library.utils.GridSpacingItemDecoration;
import picker.image.android.com.library.ui.adapter.ImagePickerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MutiImagePickerFragment extends android.app.Fragment
        implements android.app.LoaderManager.LoaderCallbacks<Cursor>, ItemTouchListener {

    private RecyclerView mMutiRecyclerView;
    private FloatingActionMenu mFloatingActionMenu;
    private View mView;
    private Toolbar mToolbar;

    public static MutiImagePickerFragment newInstance() {
        return new MutiImagePickerFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_muti_image_picker, container, false);
        initialization();
        setUpToolbar();
        requestReadPermissions();
        return mView;
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void requestReadPermissions() {
        RxPermissions.getInstance(getActivity())
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) {
                        requestPhotos(false);
                    } else {
                        Toast.makeText(getContext(), "我们需要读SD卡权限来查询图片。", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initialization() {
        mMutiRecyclerView = (RecyclerView) mView.findViewById(R.id.ui_muti_recycler);
        mFloatingActionMenu = (FloatingActionMenu) mView.findViewById(R.id.ui_menu_labels);
        mToolbar = (Toolbar) mView.findViewById(R.id.muti_toolbar);
    }

    public void setUpToolbar() {
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setTitle(getResources().getString(R.string.label_toolbar));
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
    }

    private void requestPhotos(boolean isRestart) {
        requestMedia(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, MediaUtils.PROJECT_PHOTO, isRestart);
    }

    private void requestMedia(Uri uri, String[] projects, boolean isRestart) {
        Bundle bundle = new Bundle();
        bundle.putStringArray(Constant.LOADER_EXTRA_PROJECT, projects);
        bundle.putString(Constant.LOADER_EXTRA_URI, uri.toString());
        if (isRestart) {
            getLoaderManager().restartLoader(0, bundle, this);
        } else {
            getLoaderManager().initLoader(0, bundle, this);
        }
    }

    @Override
    public android.content.Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
        Uri uri = Uri.parse(bundle.getString(Constant.LOADER_EXTRA_URI));
        String[] projects = bundle.getStringArray(Constant.LOADER_EXTRA_PROJECT);
        String order = MediaStore.MediaColumns.DATE_ADDED + " DESC";
        return new android.content.CursorLoader(getActivity(), uri, projects, null, null, order);
    }

    @Override
    public void onLoadFinished(android.content.Loader<Cursor> loader, Cursor cursor) {
        MediaOptions mediaOptions = getArguments().getParcelable(Constant.MEDIA_KEY);
        ImagePickerAdapter imagePickerAdapter =
                new ImagePickerAdapter(getActivity(), cursor, null, mediaOptions);
        mMutiRecyclerView.setHasFixedSize(true);
        mMutiRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        int spanCount = 3; // 3 columns
        int spacing = 4; // 50px
        boolean includeEdge = true;
        mMutiRecyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        mMutiRecyclerView.setAdapter(imagePickerAdapter);
    }

    @Override
    public void onLoaderReset(android.content.Loader<Cursor> loader) {

    }

    @Override
    public void bindOnTouchListener(int position) {

    }
}
