package softing.ubah4ukdev.nasaphotoday.ui.picturemars

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import softing.ubah4ukdev.moviesinfosearcher.domain.Error
import softing.ubah4ukdev.moviesinfosearcher.domain.Success
import softing.ubah4ukdev.nasaphotoday.domain.IRepository
import softing.ubah4ukdev.nasaphotoday.domain.model.Mars
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class MarsViewModel(private val repository: IRepository) : ViewModel() {

    private val _loadingLiveData = MutableLiveData(false)
    private val _errorLiveData = MutableLiveData<String?>()
    private val _marsLiveData = MutableLiveData<ArrayList<Mars>>()

    val loadingLiveData: LiveData<Boolean> = _loadingLiveData
    val errorLiveData: LiveData<String?> = _errorLiveData
    val marsLiveData: LiveData<ArrayList<Mars>> = _marsLiveData

    private fun getDateYesterday(): String {
        var currentDate = Date()
        val calendar = Calendar.getInstance()
        calendar.time = currentDate
        calendar.add(Calendar.DATE, -1)
        currentDate = calendar.time
        val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(currentDate)
    }

    fun getPhoto() {
        _loadingLiveData.value = true
        repository.getMarsPictureDay(getDateYesterday()) {
            when (it) {
                is Success -> {
                    _marsLiveData.value = it.value ?: arrayListOf()
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