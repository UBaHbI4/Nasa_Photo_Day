package softing.ubah4ukdev.nasaphotoday.ui.pictureepic

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import softing.ubah4ukdev.nasaphotoday.R
import softing.ubah4ukdev.nasaphotoday.databinding.FragmentEarthBinding
import softing.ubah4ukdev.nasaphotoday.domain.RepositoryImpl
import softing.ubah4ukdev.nasaphotoday.ui.extensions.visible
import softing.ubah4ukdev.nasaphotoday.viewBinding

class EpicFragment : Fragment(R.layout.fragment_earth) {

    companion object {
        const val MAX_LINES = 5
    }

    private val adapter by lazy { EpicAdapter() }

    private val viewBinding: FragmentEarthBinding by viewBinding(
        FragmentEarthBinding::bind
    )

    private val epicViewModel: EpicViewModel by viewModels {
        EpicViewModelFactory(RepositoryImpl)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val epicRV: RecyclerView = viewBinding.earthList
        epicRV.adapter = adapter

        epicViewModel.errorLiveData.observe(viewLifecycleOwner) {
            val error = it ?: return@observe
            viewBinding.progress.visible { false }

            Snackbar
                .make(
                    viewBinding.root,
                    error,
                    Snackbar.LENGTH_INDEFINITE
                )
                .setAction(getString(R.string.repeat_text)) { epicViewModel.getPhoto() }
                .also {
                    it.view.also {
                        (it.findViewById(com.google.android.material.R.id.snackbar_text) as TextView?)?.maxLines =
                            MAX_LINES
                    }
                }
                .show()
        }

        epicViewModel.loadingLiveData.observe(viewLifecycleOwner) {
            viewBinding.progress.visible { it }
        }

        epicViewModel.epicLiveData.observe(viewLifecycleOwner) {
            with(adapter) {
                clear()
                val earths = it ?: return@observe
                addItems(earths)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            epicViewModel.getPhoto()
        }
    }
}