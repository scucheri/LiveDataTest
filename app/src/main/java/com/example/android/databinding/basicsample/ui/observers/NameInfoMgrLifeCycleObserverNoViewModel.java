package com.example.android.databinding.basicsample.ui.observers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;
import com.example.android.databinding.basicsample.data.ProfileLiveDataViewModel;
import com.example.android.databinding.basicsample.databinding.ViewmodelProfileXiaoyuNoViewmodelBinding;
import com.example.android.databinding.basicsample.ui.ViewModelActivityXiaoyuBindingActivity;

public class NameInfoMgrLifeCycleObserverNoViewModel implements LifecycleObserver {
    protected AppCompatActivity activity;
    protected ViewmodelProfileXiaoyuNoViewmodelBinding binding;
    protected ProfileLiveDataViewModel viewModel;

    public NameInfoMgrLifeCycleObserverNoViewModel(ViewModelActivityXiaoyuBindingActivity activity,
                                                   ViewmodelProfileXiaoyuNoViewmodelBinding binding,
                                                   ProfileLiveDataViewModel viewModel) {
        this.activity = activity;
        this.binding = binding;
        this.viewModel = viewModel;
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