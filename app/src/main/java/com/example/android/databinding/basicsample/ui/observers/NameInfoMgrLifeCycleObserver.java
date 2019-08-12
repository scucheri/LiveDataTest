package com.example.android.databinding.basicsample.ui.observers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;
import com.example.android.databinding.basicsample.data.ProfileLiveDataViewModel;
import com.example.android.databinding.basicsample.databinding.ViewmodelProfileXiaoyuBinding;

/**
 * Created by xiaoxiaoyu on 2019-08-08.
 */
public class NameInfoMgrLifeCycleObserver extends BaseLifeCycleObserver {

    public NameInfoMgrLifeCycleObserver(@NonNull  AppCompatActivity activity,
                                        @NonNull ViewmodelProfileXiaoyuBinding binding,
                                        @NonNull ProfileLiveDataViewModel viewModel) {
        super(activity, binding, viewModel);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private void init() {
        viewModel.getName().observe(activity, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.name.setText(s);
            }
        });
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void connectListener() {
        viewModel.changeName();
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private void disconnectListener() {

    }
}
