package softing.ubah4ukdev.nasaphotoday.ui.start

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import softing.ubah4ukdev.nasaphotoday.MainActivity
import softing.ubah4ukdev.nasaphotoday.R
import softing.ubah4ukdev.nasaphotoday.databinding.FragmentStartBinding
import softing.ubah4ukdev.nasaphotoday.viewBinding

class StartFragment : Fragment(R.layout.fragment_start), View.OnClickListener {

    private val viewBinding: FragmentStartBinding by viewBinding(
        FragmentStartBinding::bind
    )

    private val startViewModel: StartViewModel by viewModels {
        StartViewModelFactory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        with(viewBinding) {
            btnEarth.setOnClickListener(this@StartFragment)
            btnMars.setOnClickListener(this@StartFragment)
            btnPictureOfTheDay.setOnClickListener(this@StartFragment)
            btnSettings.setOnClickListener(this@StartFragment)
        }

        (activity as MainActivity).findViewById<CollapsingToolbarLayout>(R.id.toolbarLayout)
            ?.apply {
                title = getString(R.string.start_title)
                val img: AppCompatImageView = findViewById(R.id.toolbar_image)
                img.setImageResource(R.drawable.img_start)
            }

        (activity as MainActivity).findViewById<AppBarLayout>(R.id.app_layout_bar)?.apply {
            setExpanded(true, true)
        }
    }

    private fun navigateTo(target: Int) =
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main).also {
            it.navigate(target)
        }

    override fun onClick(v: View?) {
        v?.let {
            when (it.id) {
                R.id.btn_picture_of_the_day -> {
                    navigateTo(R.id.nav_picture_apod)
                }
                R.id.btn_earth -> {
                    navigateTo(R.id.nav_picture_earth)
                }
                R.id.btn_mars -> {
                    navigateTo(R.id.nav_picture_mars)
                }
                R.id.btn_settings -> {
                    navigateTo(R.id.nav_settings)
                }
                else -> {
                }
            }
        }
    }

}