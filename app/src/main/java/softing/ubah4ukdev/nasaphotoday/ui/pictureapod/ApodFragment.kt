package softing.ubah4ukdev.nasaphotoday.ui.pictureapod

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.snackbar.Snackbar
import softing.ubah4ukdev.nasaphotoday.MainActivity
import softing.ubah4ukdev.nasaphotoday.R
import softing.ubah4ukdev.nasaphotoday.databinding.FragmentApodBinding
import softing.ubah4ukdev.nasaphotoday.domain.RepositoryImpl
import softing.ubah4ukdev.nasaphotoday.ui.extensions.visible
import softing.ubah4ukdev.nasaphotoday.viewBinding

class ApodFragment : Fragment(R.layout.fragment_apod) {

    companion object {
        const val MAX_LINES = 5
    }

    private val viewBinding: FragmentApodBinding by viewBinding(
        FragmentApodBinding::bind
    )

    private val apodViewModel: ApodViewModel by viewModels {
        ApodViewModelFactory(RepositoryImpl)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

        (activity as MainActivity).findViewById<CollapsingToolbarLayout>(R.id.toolbarLayout)?.let {
            it.title = getString(R.string.apod_title)
        }
    }

    private fun init() {
        apodViewModel.errorLiveData.observe(viewLifecycleOwner) {
            val error = it ?: return@observe
            viewBinding.progress.visible { false }

            Snackbar
                .make(
                    viewBinding.root,
                    error,
                    Snackbar.LENGTH_INDEFINITE
                )
                .setAction(getString(R.string.repeat_text)) { apodViewModel.getPhoto() }
                .also {
                    it.view.also {
                        (it.findViewById(com.google.android.material.R.id.snackbar_text) as TextView?)?.maxLines =
                            MAX_LINES
                    }
                }
                .show()
        }

        apodViewModel.loadingLiveData.observe(viewLifecycleOwner) {
            viewBinding.progress.visible { it }
        }

        apodViewModel.apodLiveData.observe(viewLifecycleOwner) {
            it?.title?.let {
                viewBinding.title.text = it
            }

            it?.photoUrl?.let { url ->
                Glide.with(viewBinding.photo)
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                    .error(R.drawable.ic_no_image)
                    .into(viewBinding.photo)
            }

            it?.explanation?.let {
                viewBinding.description.text = it
            }

            it?.copyright?.let {
                "${getString(R.string.copyright)} $it".also { viewBinding.copyright.text = it }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            apodViewModel.getPhoto()
        }
    }
}