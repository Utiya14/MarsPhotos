    package com.example.android.marsphotos.overview

    import androidx.lifecycle.LiveData
    import androidx.lifecycle.MutableLiveData
    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.viewModelScope
    import com.example.android.marsphotos.network.MarsApi
    import com.example.android.marsphotos.network.MarsApiService
    import com.example.android.marsphotos.network.MarsPhoto
    import kotlinx.coroutines.launch

    enum class MarsApiStatus { LOADING, ERROR, DONE }

    class OverviewViewModel : ViewModel() {

        //MutableLiveData yang digunakan untuk menyimpan status permintaan terbaru
        private val _status = MutableLiveData<MarsApiStatus>()
        //LiveData yang diakses dari luar kelas ViewModel
        val status: LiveData<MarsApiStatus> = _status
        //MutableLiveData yang digunakan untuk menyimpan daftar foto Mars
        private val _photos = MutableLiveData<List<MarsPhoto>>()
        val photos: LiveData<List<MarsPhoto>> = _photos

        init {
            getMarsPhotos()
        }

        //mengambil foto dari Mars API yang kemudian disimpan ke variabel listResult
        // lalu nilainya di masukkan ke variabel _status.value yang sebuah LiveData
        private fun getMarsPhotos() {
            viewModelScope.launch {
                _status.value = MarsApiStatus.LOADING
                //membuat antisipasi force close aplikasi
                //dan memunculkan pesan eror
                try {
                    _photos.value = MarsApi.retrofitService.getPhotos()
                    _status.value = MarsApiStatus.DONE
                } catch (e: Exception) {
                    _status.value = MarsApiStatus.ERROR
                    _photos.value = listOf()
                }
            }
        }
    }
