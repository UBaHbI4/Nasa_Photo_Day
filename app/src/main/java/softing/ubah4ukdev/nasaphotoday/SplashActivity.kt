package softing.ubah4ukdev.nasaphotoday

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.BounceInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.google.android.material.textview.MaterialTextView

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide();

        this.findViewById<AppCompatImageView>(R.id.splash_image)
            .animate()
            .rotationBy(1440f)
            .alpha(1f)
            .scaleX(2f)
            .scaleY(2f)
            .setInterpolator(AccelerateDecelerateInterpolator()).setDuration(2500)

            .setListener(object : Animator.AnimatorListener {
                override fun onAnimationEnd(animation: Animator?) {
                }

                override fun onAnimationRepeat(animation: Animator?) {}
                override fun onAnimationCancel(animation: Animator?) {}
                override fun onAnimationStart(animation: Animator?) {
                    findViewById<MaterialTextView>(R.id.title)
                        .animate()
                        .setStartDelay(1500)
                        .alpha(1f)
                        .scaleX(0.9f)
                        .scaleY(0.9f)
                        .setInterpolator(BounceInterpolator()).setDuration(1000)
                        .setListener(object : Animator.AnimatorListener {
                            override fun onAnimationEnd(animation: Animator?) {
                                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                                finish()
                            }

                            override fun onAnimationRepeat(animation: Animator?) {}
                            override fun onAnimationCancel(animation: Animator?) {}
                            override fun onAnimationStart(animation: Animator?) {}
                        })
                }
            })
    }
}