package com.example.android.databinding.basicsample.ui;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ViewModelProviders;
import com.example.android.databinding.basicsample.R;
import com.example.android.databinding.basicsample.data.ProfileLiveDataViewModel;
import com.example.android.databinding.basicsample.databinding.ViewmodelProfileXiaoyuBinding;
import com.example.android.databinding.basicsample.databinding.ViewmodelProfileXiaoyuNoViewmodelBinding;
import com.example.android.databinding.basicsample.ui.observers.NameInfoMgrLifeCycleObserverNoViewModel;

/**
 * Created by xiaoxiaoyu on 2019-08-01.
 */
public class ViewModelActivityXiaoyuBindingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Obtain ViewModel from ViewModelProviders
        final ProfileLiveDataViewModel viewModel = ViewModelProviders.of(this).get(ProfileLiveDataViewModel.class);

        // An alternative ViewModel using Observable fields and @Bindable properties can be used:
        // val viewModel = ViewModelProviders.of(this).get(ProfileObservableViewModel::class.java)
        // Obtain binding
        final ViewmodelProfileXiaoyuNoViewmodelBinding binding =
            DataBindingUtil.setContentView(this, R.layout.viewmodel_profile_xiaoyu_no_viewmodel);
        // Bind layout with ViewModel
        // LiveData needs the lifecycle owner
        binding.setLifecycleOwner(this);

        binding.lastname.setText("xiaoxiaoyu test binding without viewmodel");
        getLifecycle().addObserver(new NameInfoMgrLifeCycleObserverNoViewModel(ViewModelActivityXiaoyuBindingActivity.this, binding, viewModel));
    }


}
