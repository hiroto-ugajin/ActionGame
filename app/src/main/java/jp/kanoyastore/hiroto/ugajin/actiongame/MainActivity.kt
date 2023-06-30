package jp.kanoyastore.hiroto.ugajin.actiongame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.FrameLayout
import jp.kanoyastore.hiroto.ugajin.actiongame.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // 追加
        val view = binding.root // 追加
        setContentView(view) // 変更

        val frameLayout = binding.frameLayout

        val squareView = binding.squareView

        val image1 = binding.image1
        val image2 = binding.image2
        val image3 = binding.image3

        val button1 = binding.button1
        val button2 = binding.button2
        val button3 = binding.button3
        val button4 = binding.button4
        val button5 = binding.button5
        val button6 = binding.button6

        val displayMetrics = DisplayMetrics()
//        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val screenWidth = displayMetrics.widthPixels


// 画面上の座標をピクセル単位で指定
        val xPosition = 200 // x座標
        val yPosition = 300 // y座標
// LayoutParamsオブジェクトを作成し、位置情報を設定
        val layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
        layoutParams.leftMargin = xPosition
        layoutParams.topMargin = yPosition
// ImageViewにLayoutParamsを設定
//    image2.layoutParams = layoutParams

        val moveRight = 100
        val moveLeft = -100// 移動距離（ピクセル単位）

        button1.setOnClickListener {
            val currentX = image1.translationX // 現在のX座標
            val newX = currentX + moveLeft // 移動後のX座標
            image1.translationX = newX // ImageViewのX座標を更新
        }

        button2.setOnClickListener {
            val currentX = image1.translationX // 現在のX座標
            val newX = currentX + moveRight // 移動後のX座標
            image1.translationX = newX // ImageViewのX座標を更新
        }

        button3.setOnClickListener {
            val currentX = image2.translationX // 現在のX座標
            val newX = currentX + moveLeft // 移動後のX座標
            image2.translationX = newX // ImageViewのX座標を更新
        }

        button4.setOnClickListener {
            val currentX = image2.translationX // 現在のX座標
            val newX = currentX + moveRight // 移動後のX座標
            image2.translationX = newX // ImageViewのX座標を更新
        }

        button5.setOnClickListener {
            val currentX = image3.translationX // 現在のX座標
            val newX = currentX + moveLeft // 移動後のX座標
            image3.translationX = newX // ImageViewのX座標を更新
        }

        button6.setOnClickListener {
            val currentX = image3.translationX // 現在のX座標
            val newX = currentX + moveRight // 移動後のX座標
            image3.translationX = newX // ImageViewのX座標を更新
        }


        val random = Random()

        val animation = TranslateAnimation(0f, 0f, 0f, 1000f)
        animation.duration = 3000
        animation.fillAfter = true

        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                // アニメーションが開始されたときの処理
                val number = random.nextInt(100)
                squareView.text = number.toString()
            }

            override fun onAnimationEnd(animation: Animation?) {
                // アニメーションが終了したときの処理
                CoroutineScope(Dispatchers.Main).launch {
                    delay(3000)
                    squareView.startAnimation(animation)
                }
            }

            override fun onAnimationRepeat(animation: Animation?) {
                // アニメーションが繰り返されたときの処理

            }
        })

        squareView.startAnimation(animation)

    }
}
