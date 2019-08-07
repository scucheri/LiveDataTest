package com.example.android.databinding.basicsample.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.example.android.databinding.basicsample.R;
import com.example.android.databinding.basicsample.data.Popularity;
import com.example.android.databinding.basicsample.data.ProfileLiveDataViewModel;
import com.example.android.databinding.basicsample.data.XyTestData;
import com.example.android.databinding.basicsample.databinding.ViewmodelProfileXiaoyuBinding;
import com.example.android.databinding.basicsample.util.BindingAdapters;

/**
 * Created by xiaoxiaoyu on 2019-08-01.
 */
public class ViewModelActivityXiaoyu extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Obtain ViewModel from ViewModelProviders
        final ViewModel viewModel = ViewModelProviders.of(this).get(ProfileLiveDataViewModel.class);

        // An alternative ViewModel using Observable fields and @Bindable properties can be used:
        // val viewModel = ViewModelProviders.of(this).get(ProfileObservableViewModel::class.java)
        // Obtain binding
        final ViewmodelProfileXiaoyuBinding binding =
            DataBindingUtil.setContentView(this, R.layout.viewmodel_profile_xiaoyu);
        // Bind layout with ViewModel
        binding.setViewmodel((ProfileLiveDataViewModel) viewModel);
        // LiveData needs the lifecycle owner
        binding.setLifecycleOwner(this);

        binding.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ProfileLiveDataViewModel) viewModel).onLike();
                ((ProfileLiveDataViewModel) viewModel).changeHomeTown();
            }
        });
        (((ProfileLiveDataViewModel) viewModel).getPopularity()).observe(this, new Observer<Popularity>() {
            @Override
            public void onChanged(Popularity popularity) {
                BindingAdapters.popularityIcon(binding.imageView, popularity);
            }
        });

        (((ProfileLiveDataViewModel) viewModel).getPopularity()).observe(this, new Observer<Popularity>() {
            @Override
            public void onChanged(Popularity popularity) {
                Toast.makeText(ViewModelActivityXiaoyu.this,"xiaoxiaoyu test", Toast.LENGTH_SHORT).show();
            }
        });

        (((ProfileLiveDataViewModel) viewModel).getXyTestData()).observe(this, new Observer<XyTestData>() {
            @Override
            public void onChanged(XyTestData xyTestData) {
                Log.d("xiaoyu_activity home : ",xyTestData.innerData.homeTown);
                binding.lastname.setText(xyTestData.innerData.homeTown);
            }
        });
    }
}
