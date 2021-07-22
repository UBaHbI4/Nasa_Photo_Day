package softing.ubah4ukdev.nasaphotoday.ui.pictureapod

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import softing.ubah4ukdev.nasaphotoday.domain.repository.nasa.Error
import softing.ubah4ukdev.nasaphotoday.domain.repository.nasa.Success
import softing.ubah4ukdev.nasaphotoday.domain.repository.nasa.INasaRepository
import softing.ubah4ukdev.nasaphotoday.domain.model.nasa.Apod

class ApodViewModel(private val nasaRepository: INasaRepository) : ViewModel() {

    private val _loadingLiveData = MutableLiveData(false)
    private val _errorLiveData = MutableLiveData<String?>()
    private val _apodLiveData = MutableLiveData<Apod>()

    val loadingLiveData: LiveData<Boolean> = _loadingLiveData
    val errorLiveData: LiveData<String?> = _errorLiveData
    val apodLiveData: LiveData<Apod> = _apodLiveData

    fun getPhoto() {
        _loadingLiveData.value = true
        nasaRepository.getAstronomyPictureDay() {
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