package com.example.android.databinding.basicsample.ui.observers;

import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;
import com.example.android.databinding.basicsample.data.Popularity;
import com.example.android.databinding.basicsample.data.ProfileLiveDataViewModel;
import com.example.android.databinding.basicsample.databinding.ViewmodelProfileXiaoyuBinding;

/**
 * Created by xiaoxiaoyu on 2019-08-08.
 */
public class LikeInfoMgrObserver extends BaseLifeCycleObserver {

    public LikeInfoMgrObserver(AppCompatActivity activity,
                               ViewmodelProfileXiaoyuBinding binding,
                               ProfileLiveDataViewModel viewModel) {
        super(activity, binding, viewModel);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private void init() {
        binding.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.onLike();
                viewModel.changeHomeTown();
            }
        });

        viewModel.getPopularity().observe(activity, new Observer<Popularity>() {
            @Override
            public void onChanged(Popularity popularity) {
                Toast.makeText(activity,"xiaoxiaoyu test", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void onResume() {
        viewModel.changeName();
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private void onPause() {

    }
}

