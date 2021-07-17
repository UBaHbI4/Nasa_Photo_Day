package softing.ubah4ukdev.nasaphotoday.ui.picturemars

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.snackbar.Snackbar
import softing.ubah4ukdev.nasaphotoday.MainActivity
import softing.ubah4ukdev.nasaphotoday.R
import softing.ubah4ukdev.nasaphotoday.databinding.FragmentMarsBinding
import softing.ubah4ukdev.nasaphotoday.domain.RepositoryImpl
import softing.ubah4ukdev.nasaphotoday.ui.extensions.visible
import softing.ubah4ukdev.nasaphotoday.viewBinding

class MarsFragment : Fragment(R.layout.fragment_mars) {

    companion object {
        const val MAX_LINES = 5
    }

    private val adapter by lazy { MarsAdapter() }

    private val viewBinding: FragmentMarsBinding by viewBinding(
        FragmentMarsBinding::bind
    )

    private val marsViewModel: MarsViewModel by viewModels {
        MarsViewModelFactory(RepositoryImpl)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

        (activity as MainActivity).findViewById<CollapsingToolbarLayout>(R.id.toolbarLayout)?.let {
            it.title = getString(R.string.mars_title)
            val img: AppCompatImageView = it.findViewById(R.id.toolbar_image)
            img.setImageResource(R.drawable.img_mars)
        }
    }

    private fun init() {
        val marsRV: RecyclerView = viewBinding.marsList
        marsRV.adapter = adapter

        marsViewModel.errorLiveData.observe(viewLifecycleOwner) {
            val error = it ?: return@observe
            viewBinding.progress.visible { false }

            Snackbar
                .make(
                    viewBinding.root,
                    error,
                    Snackbar.LENGTH_INDEFINITE
                )
                .setAction(getString(R.string.repeat_text)) { marsViewModel.getPhoto() }
                .also {
                    it.view.also {
                        (it.findViewById(com.google.android.material.R.id.snackbar_text) as TextView?)?.maxLines =
                            MAX_LINES
                    }
                }
                .show()
        }

        marsViewModel.loadingLiveData.observe(viewLifecycleOwner) {
            viewBinding.progress.visible { it }
        }

        marsViewModel.marsLiveData.observe(viewLifecycleOwner) {
            with(adapter) {
                clear()
                val marses = it ?: return@observe
                addItems(marses)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            marsViewModel.getPhoto()
        }
    }
}