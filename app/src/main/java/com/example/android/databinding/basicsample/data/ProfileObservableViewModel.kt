/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.databinding.basicsample.data

import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.android.databinding.basicsample.BR
import com.example.android.databinding.basicsample.data.XyTestData.XyInnerData
import com.example.android.databinding.basicsample.util.ObservableViewModel


/**
 * This class is used as a variable in the XML layout and it's fully observable, meaning that
 * changes to any of the exposed observables automatically refresh the UI. *
 */
class ProfileLiveDataViewModel : ViewModel() {
    private val _name = MutableLiveData("Ada")
    private val _lastName = MutableLiveData("Lovelace")
    private val _likes =  MutableLiveData(0)
    private val _xytestData = MutableLiveData(XyTestData())

    val name: LiveData<String> = _name
    val lastName: LiveData<String> = _lastName
    val likes: LiveData<Int> = _likes
    val xyTestData: LiveData<XyTestData> = _xytestData

    // popularity is exposed as LiveData using a Transformation instead of a @Bindable property.
    val popularity: LiveData<Popularity> = Transformations.map(_likes) {
        when {
            it > 9 -> Popularity.STAR
            it > 4 -> Popularity.POPULAR
            else -> Popularity.NORMAL
        }
    }

    fun onLike() {
//        Thread(Runnable {
            Log.d("xiaoyu_ViewModel",Thread.currentThread().name + Thread.currentThread().id)
            _likes.value = (_likes.value ?: 0) + 1
//        }).start()
    }

    fun changeHomeTown() {
        _xytestData.value?.apply {
            innerData.homeTown = innerData.homeTown + "——湖南"
        }
        _xytestData.postValue(_xytestData.value) // 最后会调用到LiveData的setValue

//        _xytestData.value = XyTestData()
        //        _xytestData.value?.innerData?.homeTown = _xytestData.value?.innerData?.homeTown + "——湖南"

    }
}

/**
 * As an alternative to LiveData, you can use Observable Fields and binding properties.
 *
 * `Popularity` is exposed here as a `@Bindable` property so it's necessary to call
 * `notifyPropertyChanged` when any of the dependent properties change (`likes` in this case).
 */
class ProfileObservableViewModel : ObservableViewModel() {
    val name = ObservableField("Ada")
    val lastName = ObservableField("Lovelace")
    val likes =  ObservableInt(0)

    fun onLike() {
        likes.increment()
        // You control when the @Bindable properties are updated using `notifyPropertyChanged()`.
        notifyPropertyChanged(BR.popularity)
    }

    @Bindable
    fun getPopularity(): Popularity {
        return likes.get().let {
            when {
                it > 9 -> Popularity.STAR
                it > 4 -> Popularity.POPULAR
                else -> Popularity.NORMAL
            }
        }
    }
}

enum class Popularity {
    NORMAL,
    POPULAR,
    STAR
}

private fun ObservableInt.increment() {
    set(get() + 1)
}
