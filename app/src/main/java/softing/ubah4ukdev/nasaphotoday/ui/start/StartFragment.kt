package softing.ubah4ukdev.nasaphotoday.ui.start

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewAnimationUtils
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.transition.*
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import softing.ubah4ukdev.nasaphotoday.MainActivity
import softing.ubah4ukdev.nasaphotoday.R
import softing.ubah4ukdev.nasaphotoday.databinding.FragmentStartContainerBinding
import softing.ubah4ukdev.nasaphotoday.ui.extensions.visible
import softing.ubah4ukdev.nasaphotoday.viewBinding

class StartFragment : Fragment(R.layout.fragment_start_container), View.OnClickListener {
    //Флаг для закомментированной анимации
    //var bool = false

    companion object {
        //Задержка анимации
        const val ANIMATION_START_DELAY = 500L
        const val ANIMATION_DURATION = 350L

        const val TWO = 2
        const val RADIUS = 0.5f
    }

    private lateinit var sceneStart: Scene
    private lateinit var sceneFinish: Scene

    private val viewBinding: FragmentStartContainerBinding by viewBinding(
        FragmentStartContainerBinding::bind
    )

    private val startViewModel: StartViewModel by viewModels {
        StartViewModelFactory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {

        (activity as MainActivity).findViewById<CollapsingToolbarLayout>(R.id.toolbarLayout)
            ?.apply {
                title = getString(R.string.start_title)
                val img: AppCompatImageView = findViewById(R.id.toolbar_image)
                img.setImageResource(R.drawable.img_start)
            }

        (activity as MainActivity).findViewById<AppBarLayout>(R.id.app_layout_bar)?.apply {
            setExpanded(true, true)
        }

        (activity as MainActivity).findViewById<FloatingActionButton>(R.id.fab)
            ?.apply {
                visible { false }
            }

        initScene()

        //Запустим анимацию с задержкой, чтобы стартануть после отображения фрагмента
        Thread {
            Handler(Looper.getMainLooper()).postDelayed({
                TransitionManager.go(
                    sceneFinish,
                    TransitionInflater.from(requireContext())
                        .inflateTransition(R.transition.transition)
                )
                /*
                Навешаем кликлистенеры тоже позже, после того как загрузится фрагмент, в котором есть эти кнопки
                 */
                setButtonListener()
            }, ANIMATION_START_DELAY)
        }.start()
    }

    //Инициализация сцен для анимации
    private fun initScene() {
        sceneStart = Scene.getSceneForLayout(
            viewBinding.sceneRootFrameLayout,
            R.layout.fragment_start,
            requireContext()
        )

        sceneFinish = Scene.getSceneForLayout(
            viewBinding.sceneRootFrameLayout,
            R.layout.fragment_start_finish,
            requireContext()
        )

        sceneStart.enter()
    }

    //Навешаем кликлистенеры
    private fun setButtonListener() {
        val btnEarth: AppCompatImageButton? =
            view?.findViewById(R.id.btn_earth)
        btnEarth?.setOnClickListener(this@StartFragment)

        val btnMars: AppCompatImageButton? =
            view?.findViewById(R.id.btn_mars)
        btnMars?.setOnClickListener(this@StartFragment)

        val btnPictureOfTheDay: AppCompatImageButton? =
            view?.findViewById(R.id.btn_picture_of_the_day)
        btnPictureOfTheDay?.setOnClickListener(this@StartFragment)

        val btnSettings: AppCompatImageButton? =
            view?.findViewById(R.id.btn_settings)
        btnSettings?.setOnClickListener(this@StartFragment)
    }

    private fun navigateTo(target: Int) =
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main).also {
            it.navigate(target)
        }

    override fun onClick(v: View?) {
        v?.let {
            /* Эксперименты. Комменты не удаляю, чтобы тут можно было подсмотреть :)
            Анимация, кнопка уходит влево (Gravity.START)
            TransitionManager.beginDelayedTransition(viewBinding.fragmentStartRoot, Slide(Gravity.START))
            it.visible { bool }
            bool = !bool
             */

            animateWithCircularReveal(it)

            when (it.id) {
                R.id.btn_picture_of_the_day -> {
                    Thread {
                        Handler(Looper.getMainLooper()).postDelayed({
                            navigateTo(R.id.nav_picture_apod)
                        }, ANIMATION_DURATION)
                    }.start()
                }
                R.id.btn_earth -> {
                    Thread {
                        Handler(Looper.getMainLooper()).postDelayed({
                            navigateTo(R.id.nav_picture_earth)
                        }, ANIMATION_DURATION)
                    }.start()
                }
                R.id.btn_mars -> {
                    Thread {
                        Handler(Looper.getMainLooper()).postDelayed({
                            navigateTo(R.id.nav_picture_mars)
                        }, ANIMATION_DURATION)
                    }.start()
                }
                R.id.btn_settings -> {
                    Thread {
                        Handler(Looper.getMainLooper()).postDelayed({
                            navigateTo(R.id.nav_settings)
                        }, ANIMATION_DURATION)
                    }.start()
                }
                else -> {
                    return@let
                }
            }
        }
    }

    private fun animateWithCircularReveal(target: View) {
        val view: AppCompatImageButton = target as AppCompatImageButton
        val cx = view.width / TWO
        val cy = view.height / TWO
        val finalRadius = Math.hypot(cx.toDouble(), cy.toDouble()).toFloat()
        val anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, finalRadius, RADIUS)
        anim.duration = ANIMATION_START_DELAY
        anim.start()
    }
}