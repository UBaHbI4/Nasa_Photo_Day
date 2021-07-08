package softing.ubah4ukdev.nasaphotoday.ui.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import softing.ubah4ukdev.nasaphotoday.R
import softing.ubah4ukdev.nasaphotoday.databinding.FragmentSettingsBinding
import softing.ubah4ukdev.nasaphotoday.domain.storage.AppThemeStorage
import softing.ubah4ukdev.nasaphotoday.viewBinding

class SettingsFragment : Fragment(R.layout.fragment_settings), View.OnClickListener {

    val THEME_GRAY = 0
    val THEME_ORANGE = 1

    private val viewBinding: FragmentSettingsBinding by viewBinding(
        FragmentSettingsBinding::bind
    )

    private val settingsViewModel: SettingsViewModel by viewModels {
        SettingsViewModelFactory(
            AppThemeStorage(requireActivity().application)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        //Навешали ClickListener на RadioButton-ы
        viewBinding.optionGreyTheme.setOnClickListener(this)
        viewBinding.optionOrangeTheme.setOnClickListener(this)

        /**
         * Подписались на изменение ID темы, для установки Checked
         * сохраненной темы при создании вьюхи
         */
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            settingsViewModel.theme.collect {
                when (it) {
                    THEME_GRAY -> {
                        viewBinding.optionGreyTheme.isChecked = true
                    }
                    THEME_ORANGE -> {
                        viewBinding.optionOrangeTheme.isChecked = true
                    }
                }
            }
        }
    }

    /**
     * Обработка клика по RadioButton-у для установки
     * выбранной темы
     */
    override fun onClick(v: View?) {
        v?.let {
            when (it.id) {
                R.id.option_grey_theme -> {
                    settingsViewModel.setTheme(THEME_GRAY)
                    requireActivity().recreate()
                }
                R.id.option_orange_theme -> {
                    settingsViewModel.setTheme(THEME_ORANGE)
                    requireActivity().recreate()
                }
            }
        }
    }

}