package softing.ubah4ukdev.nasaphotoday.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import softing.ubah4ukdev.nasaphotoday.R
import softing.ubah4ukdev.nasaphotoday.databinding.FragmentHomeBinding
import softing.ubah4ukdev.nasaphotoday.viewBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewBinding: FragmentHomeBinding by viewBinding(
        FragmentHomeBinding::bind
    )

    private val homeViewModel: HomeViewModel by viewModels {
        HomeViewModelFactory()
    }

}