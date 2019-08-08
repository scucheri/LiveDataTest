package com.example.android.databinding.basicsample.ui;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import com.example.android.databinding.basicsample.R;
import com.example.android.databinding.basicsample.data.ProfileLiveDataViewModel;
import com.example.android.databinding.basicsample.databinding.ViewmodelProfileXiaoyuBinding;
import com.example.android.databinding.basicsample.ui.observers.LikeInfoMgrObserver;
import com.example.android.databinding.basicsample.ui.observers.NameInfoMgrLifeCycleObserver;
import com.example.android.databinding.basicsample.ui.observers.XyTestDataObserver;

/**
 * Created by xiaoxiaoyu on 2019-08-01.
 */
public class ViewModelActivityXiaoyu extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Obtain ViewModel from ViewModelProviders
        final ProfileLiveDataViewModel viewModel = ViewModelProviders.of(this).get(ProfileLiveDataViewModel.class);

        // An alternative ViewModel using Observable fields and @Bindable properties can be used:
        // val viewModel = ViewModelProviders.of(this).get(ProfileObservableViewModel::class.java)
        // Obtain binding
        final ViewmodelProfileXiaoyuBinding binding =
            DataBindingUtil.setContentView(this, R.layout.viewmodel_profile_xiaoyu);
        // Bind layout with ViewModel
        binding.setViewmodel(viewModel);
        // LiveData needs the lifecycle owner
        binding.setLifecycleOwner(this);



        getLifecycle().addObserver(new NameInfoMgrLifeCycleObserver(this, binding, viewModel));
        getLifecycle().addObserver(new LikeInfoMgrObserver(this,binding, viewModel));
        getLifecycle().addObserver(new XyTestDataObserver(this,binding, viewModel));

    }
}
