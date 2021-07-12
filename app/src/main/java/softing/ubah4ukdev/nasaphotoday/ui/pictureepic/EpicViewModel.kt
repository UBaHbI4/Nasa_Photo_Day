package softing.ubah4ukdev.nasaphotoday.ui.pictureepic

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import softing.ubah4ukdev.moviesinfosearcher.domain.Error
import softing.ubah4ukdev.moviesinfosearcher.domain.Success
import softing.ubah4ukdev.nasaphotoday.domain.IRepository
import softing.ubah4ukdev.nasaphotoday.domain.model.Epic

class EpicViewModel(private val repository: IRepository) : ViewModel() {

    private val _loadingLiveData = MutableLiveData(false)
    private val _errorLiveData = MutableLiveData<String?>()
    private val _earthLiveData = MutableLiveData<ArrayList<Epic>>()

    val loadingLiveData: LiveData<Boolean> = _loadingLiveData
    val errorLiveData: LiveData<String?> = _errorLiveData
    val epicLiveData: LiveData<ArrayList<Epic>> = _earthLiveData

    fun getPhoto() {
        _loadingLiveData.value = true
        repository.getEarthPictureDay() {
            when (it) {
                is Success -> {
                    _earthLiveData.value = it.value ?: arrayListOf()
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