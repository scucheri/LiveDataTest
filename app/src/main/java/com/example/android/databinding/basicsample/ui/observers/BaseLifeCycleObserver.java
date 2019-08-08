package com.example.android.databinding.basicsample.ui.observers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleObserver;
import com.example.android.databinding.basicsample.data.ProfileLiveDataViewModel;
import com.example.android.databinding.basicsample.databinding.ViewmodelProfileXiaoyuBinding;

/**
 * Created by xiaoxiaoyu on 2019-08-08.
 */
public class BaseLifeCycleObserver implements LifecycleObserver {
    protected AppCompatActivity activity;
    protected ViewmodelProfileXiaoyuBinding binding;
    protected ProfileLiveDataViewModel viewModel;

    public BaseLifeCycleObserver(AppCompatActivity activity,
                                 ViewmodelProfileXiaoyuBinding binding,
                                 ProfileLiveDataViewModel viewModel){
        this.activity = activity;
        this.binding = binding;
        this.viewModel = viewModel;
    }



}
