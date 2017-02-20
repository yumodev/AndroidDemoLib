package com.yumo.demo.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.yumo.demo.R;
import com.yumo.demo.base.YmDemoBaseFragment;


public class YmTestFragment extends YmDemoBaseFragment {
    protected static final String TEST_TAG = "TestFragment";

    @Override
    protected View getContainerView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String tag = getTag();
        if (TextUtils.isEmpty(tag)){
            mToolbar.setTitle(TEST_TAG);
        }else{
            mToolbar.setTitle(tag);
        }

        mToolbar.setNavigationIcon(R.drawable.head_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        YmTestView testView = new YmTestView(getContext());
        testView.init(this, getClass());
        return testView;
    }

    public Context getContext() {
        return getActivity();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return false;
    }

    public void showTestView(View view, FrameLayout.LayoutParams layoutParams) {
        TestCaseFragment testCase = new TestCaseFragment();
        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.addView(view, layoutParams);
        testCase.setContentView(frameLayout);

        FragmentTransaction ft = getActivity().getSupportFragmentManager()
                .beginTransaction();
        ft.addToBackStack(null);
        ft.replace(R.id.test_fragment_id, testCase).commit();
    }

    public void showFragment(Fragment fragment) {
        FragmentTransaction ft = getActivity().getSupportFragmentManager()
                .beginTransaction();
        ft.replace(R.id.test_fragment_id, fragment).commit();
    }

    public void showDialogFragment(DialogFragment fragment, String tag) {
        fragment.show(getActivity().getSupportFragmentManager(), tag);
    }

    public void showTestView(View view) {
        showTestView(view, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
    }

    public void showTestView(int resid) {
        View view = View.inflate(getActivity(), resid, null);
        showTestView(view);
    }

    public void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    public void showToastMessageOnUiThread(final String message) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static class TestCaseFragment extends Fragment {
        private View mContent;

        public void setContentView(View v) {
            mContent = v;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            FrameLayout root = new FrameLayout(getActivity());
            root.addView(mContent, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
            return root;
        }
    }

    public void printLog(String logTag, String info) {
        System.out.println(logTag + " " + info);
    }
}