package com.example.android.databinding.basicsample.ui.observers;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;
import com.example.android.databinding.basicsample.data.ProfileLiveDataViewModel;
import com.example.android.databinding.basicsample.data.XyTestData;
import com.example.android.databinding.basicsample.databinding.ViewmodelProfileXiaoyuBinding;

/**
 * Created by xiaoxiaoyu on 2019-08-08.
 */
public class XyTestDataObserver extends BaseLifeCycleObserver {

    public XyTestDataObserver(AppCompatActivity activity,
                              ViewmodelProfileXiaoyuBinding binding,
                              ProfileLiveDataViewModel viewModel) {
        super(activity, binding, viewModel);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private void init() {
        viewModel.getXyTestData().observe(activity, new Observer<XyTestData>() {
            @Override
            public void onChanged(XyTestData xyTestData) {
                Log.d("xiaoyu_activity home : ",xyTestData.innerData.homeTown);
                binding.lastname.setText(xyTestData.innerData.homeTown);
            }
        });

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void onResume() {
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private void onPause() {

    }
}
