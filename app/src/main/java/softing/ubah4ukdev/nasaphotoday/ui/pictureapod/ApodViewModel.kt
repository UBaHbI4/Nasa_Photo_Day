package softing.ubah4ukdev.nasaphotoday.ui.pictureapod

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import softing.ubah4ukdev.moviesinfosearcher.domain.Error
import softing.ubah4ukdev.moviesinfosearcher.domain.Success
import softing.ubah4ukdev.nasaphotoday.domain.IRepository
import softing.ubah4ukdev.nasaphotoday.domain.model.Apod

class ApodViewModel(private val repository: IRepository) : ViewModel() {

    private val _loadingLiveData = MutableLiveData(false)
    private val _errorLiveData = MutableLiveData<String?>()
    private val _apodLiveData = MutableLiveData<Apod>()

    val loadingLiveData: LiveData<Boolean> = _loadingLiveData
    val errorLiveData: LiveData<String?> = _errorLiveData
    val apodLiveData: LiveData<Apod> = _apodLiveData

    fun getPhoto() {
        _loadingLiveData.value = true
        repository.getAstronomyPictureDay() {
            when (it) {
                is Success -> {
                    _apodLiveData.value = it.value ?: null
                    _errorLiveData.value = null
                    _loadingLiveData.value = false
                }
                is Error -> {
                    _errorLiveData.value = it.value.message.toString()
                    _loadingLiveData.value = false
                }
            }
        }
    }
}