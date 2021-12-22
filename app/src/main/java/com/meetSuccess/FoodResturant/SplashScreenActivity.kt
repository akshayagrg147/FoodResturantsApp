package com.meetSuccess.FoodResturant

import android.animation.Animator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import com.airbnb.lottie.LottieAnimationView

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar()?.hide();
        setContentView(R.layout.activity_splash_screen)


        var lottieAnimationView: LottieAnimationView = findViewById(R.id.lootie)
        var backgroundimage: LottieAnimationView = findViewById(R.id.backgroundimg)

        backgroundimage.animate().translationY(-3500f).setDuration(1000).startDelay = 2000
        lottieAnimationView.animate().translationY(3500f).setDuration(1000).setStartDelay(2000)
            .setListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {}
                override fun onAnimationEnd(animation: Animator) {
                    val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent)
                }

                override fun onAnimationCancel(animation: Animator) {}
                override fun onAnimationRepeat(animation: Animator) {}
            })

    }
}