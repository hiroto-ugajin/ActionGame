package jp.kanoyastore.hiroto.ugajin.actiongame

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.DisplayMetrics
import android.util.Log
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

        val screenWidth = resources.displayMetrics.widthPixels
        val screenHeight = resources.displayMetrics.widthPixels

        val timer = Timer()
        val task = object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    dropSquareView()
                }
            }

            var squareViewY: Float = 0f
            val gravity: Float = 600f // 落下速度（ピクセル/秒）
            val collisionThreshold: Int = 30 // 衝突判定のしきい値（ピクセル）


            fun checkCollision() {
                val squareViewRect = Rect()
                squareView.getGlobalVisibleRect(squareViewRect) // squareViewの位置とサイズを取得

                val image1Rect = Rect()
                image1.getGlobalVisibleRect(image1Rect) // image1の位置とサイズを取得

                // 衝突判定
                if (Rect.intersects(squareViewRect, image1Rect)) {
                    // 衝突した場合の処理をここに記述します
                    // 例えば、ログの出力や別のアクションの実行など
                    Log.d("Collision", "衝突しました☺️")
                }
            }

            // squareViewの落下処理を行う関数
            fun dropSquareView() {
                val deltaTime = 16 // 1フレームの時間（ミリ秒）
                val newY = squareViewY + gravity * deltaTime / 1000 // 新しいY座標を計算（単位はピクセル）
                squareViewY = newY
                squareView.translationY = newY

                checkCollision() // 衝突判定を実行

                // 1秒間に60回（約16msごと）の更新を繰り返す

                if (newY < screenHeight*2) {
                    // 画面内にいる場合は次の更新をスケジュール
                    Handler(Looper.getMainLooper()).postDelayed({
                        dropSquareView()
                    }, deltaTime.toLong())
                }
            }
        }

        timer.scheduleAtFixedRate(task, 0, 3000)

            val moveRight = 100
            val moveLeft = -100// 移動距離（ピクセル単位）

        button1.setOnClickListener {
            val currentX = image1.translationX // 現在のX座標
            val newX = currentX + moveLeft // 移動後のX座標
            image1.translationX = newX // ImageViewのX座標を更新

            val minLimit = 0f // 画面の左端の制限
            val maxLimit = binding.root.width - image1.width // 画面の右端の制限

            // 移動後のX座標を制限範囲内に修正
            val clampedX = newX.coerceIn(minLimit, maxLimit.toFloat())
            image1.translationX = clampedX // ImageViewのX座標を更新
        }

        button2.setOnClickListener {
            val currentX = image1.translationX // 現在のX座標
            val newX = currentX + moveRight // 移動後のX座標
            image1.translationX = newX // ImageViewのX座標を更新

            val minLimit = 0f // 画面の左端の制限
            val maxLimit = binding.root.width - image1.width // 画面の右端の制限

            // 移動後のX座標を制限範囲内に修正
            val clampedX = newX.coerceIn(minLimit, maxLimit.toFloat())
            image1.translationX = clampedX // ImageViewのX座標を更新
        }

        button3.setOnClickListener {
            val currentX = image2.translationX // 現在のX座標
            val newX = currentX + moveLeft // 移動後のX座標
            image1.translationX = newX // ImageViewのX座標を更新

            val minLimit = 0f // 画面の左端の制限
            val maxLimit = binding.root.width - image2.width // 画面の右端の制限

            // 移動後のX座標を制限範囲内に修正
            val clampedX = newX.coerceIn(minLimit, maxLimit.toFloat())
            image1.translationX = clampedX // ImageViewのX座標を更新
        }

        button4.setOnClickListener {
            val currentX = image2.translationX // 現在のX座標
            val newX = currentX + moveRight // 移動後のX座標
            image1.translationX = newX // ImageViewのX座標を更新

            val minLimit = 0f // 画面の左端の制限
            val maxLimit = binding.root.width - image2.width // 画面の右端の制限

            // 移動後のX座標を制限範囲内に修正
            val clampedX = newX.coerceIn(minLimit, maxLimit.toFloat())
            image1.translationX = clampedX // ImageViewのX座標を更新
        }

        button5.setOnClickListener {
            val currentX = image3.translationX // 現在のX座標
            val newX = currentX + moveLeft // 移動後のX座標
            image1.translationX = newX // ImageViewのX座標を更新

            val minLimit = 0f // 画面の左端の制限
            val maxLimit = binding.root.width - image3.width // 画面の右端の制限

            // 移動後のX座標を制限範囲内に修正
            val clampedX = newX.coerceIn(minLimit, maxLimit.toFloat())
            image1.translationX = clampedX // ImageViewのX座標を更新
        }

        button6.setOnClickListener {
            val currentX = image3.translationX // 現在のX座標
            val newX = currentX + moveRight // 移動後のX座標
            image1.translationX = newX // ImageViewのX座標を更新

            val minLimit = 0f // 画面の左端の制限
            val maxLimit = binding.root.width - image3.width // 画面の右端の制限

            // 移動後のX座標を制限範囲内に修正
            val clampedX = newX.coerceIn(minLimit, maxLimit.toFloat())
            image1.translationX = clampedX // ImageViewのX座標を更新
        }
    }
}








//            button1.setOnClickListener
//            {
//
//
//                val currentX = image1.translationX // 現在のX座標
//                val newX = currentX + moveLeft // 移動後のX座標
//                image1.translationX = newX // ImageViewのX座標を更新
//
//                val minLimit = 0f // 画面の左端の制限
//                val maxLimit = binding.root.width - image1.width // 画面の右端の制限
//
//                // 移動後のX座標を制限範囲内に修正
//                val clampedX = newX.coerceIn(minLimit, maxLimit.toFloat())
//                image1.translationX = clampedX // ImageViewのX座標を更新
//            }

//            button2.setOnClickListener
//            {
//                val currentX = image1.translationX // 現在のX座標
//                val newX = currentX + moveRight // 移動後のX座標
//                image1.translationX = newX // ImageViewのX座標を更新
//
//                val minLimit = 0f // 画面の左端の制限
//                val maxLimit = binding.root.width - image1.width // 画面の右端の制限
//
//                // 移動後のX座標を制限範囲内に修正
//                val clampedX = newX.coerceIn(minLimit, maxLimit.toFloat())
//                image1.translationX = clampedX // ImageViewのX座標を更新
//
//
//            }

//            button3.setOnClickListener
//            {
//                val currentX = image2.translationX // 現在のX座標
//                val newX = currentX + moveLeft // 移動後のX座標
//                image2.translationX = newX // ImageViewのX座標を更新
//                val minLimit = 0f // 画面の左端の制限
//                val maxLimit = binding.root.width - image2.width // 画面の右端の制限
//                val clampedX = newX.coerceIn(minLimit, maxLimit.toFloat())
//                image2.translationX = clampedX // ImageViewのX座標を更新
//            }

//            button4.setOnClickListener
//            {
//                val currentX = image2.translationX // 現在のX座標
//                val newX = currentX + moveRight // 移動後のX座標
//                image2.translationX = newX // ImageViewのX座標を更新
//                val minLimit = 0f // 画面の左端の制限
//                val maxLimit = binding.root.width - image2.width // 画面の右端の制限
//                val clampedX = newX.coerceIn(minLimit, maxLimit.toFloat())
//                image2.translationX = clampedX // ImageViewのX座標を更新
//            }

//            button5.setOnClickListener
//            {
//                val currentX = image3.translationX // 現在のX座標
//                val newX = currentX + moveLeft // 移動後のX座標
//                image3.translationX = newX // ImageViewのX座標を更新
//                val minLimit = 0f // 画面の左端の制限
//                val maxLimit = binding.root.width - image3.width // 画面の右端の制限
//                val clampedX = newX.coerceIn(minLimit, maxLimit.toFloat())
//                image3.translationX = clampedX // ImageViewのX座標を更新
//            }

//            button6.setOnClickListener
//            {
//                val currentX = image3.translationX // 現在のX座標
//                val newX = currentX + moveRight // 移動後のX座標
//                image3.translationX = newX // ImageViewのX座標を更新
//                val minLimit = 0f // 画面の左端の制限
//                val maxLimit = binding.root.width - image3.width // 画面の右端の制限
//                val clampedX = newX.coerceIn(minLimit, maxLimit.toFloat())
//                image3.translationX = clampedX // ImageViewのX座標を更新
//            }

//        val random = Random()
//
//        var animation1 = TranslateAnimation(200f, 200f, 0f, 1600f)
//        animation1.duration = 2800
//        animation1.fillAfter = true
//
//        var animation2 = TranslateAnimation(500f, 500f, 0f, 1600f)
//        animation2.duration = 2800
//        animation2.fillAfter = true
//
//        var animation3 = TranslateAnimation(800f, 800f, 0f, 1600f)
//        animation3.duration = 2800
//        animation3.fillAfter = true
//
//        val animations = listOf(animation1, animation2, animation3)
//
//        CoroutineScope(Dispatchers.Main).launch {
//            var counter = 0
//            while (counter < 10) {
//                val randomAnimation = animations.random()
//                val randomNumber = random.nextInt(100)
//                squareView.text = randomNumber.toString()
//                squareView.startAnimation(randomAnimation)
//                checkCollision()
//                delay(3000)
//                counter++
//            }
//        }

