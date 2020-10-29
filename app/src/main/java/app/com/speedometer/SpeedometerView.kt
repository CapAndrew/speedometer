package app.com.speedometer

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin


class SpeedometerView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attributeSet, defStyleAttr, defStyleRes) {

    private var size = 320
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    var speed = 20
    private var angleDeg = convertSpeedIntoDegrees(speed)

    private var needleColor = Color.WHITE
    private var needleWidth = 5.0f

    private var minSpeed = 20
    private var maxSpeed = 300

    fun changeSpeed(speed: Int) {
        this.angleDeg = convertSpeedIntoDegrees(speed)
        if (speed in minSpeed..maxSpeed) {
            invalidate()
        }
    }

    private fun convertSpeedIntoDegrees(speed: Int): Int {
        var angleFromSpeed = 0
        if (speed in 20..249) angleFromSpeed =
            speed + 109 else if (speed in 250..300) angleFromSpeed = speed - 251
        return angleFromSpeed
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = measureDimension(size, widthMeasureSpec)
        val height = measureDimension(size, heightMeasureSpec)

        size = min(width, height)

        setMeasuredDimension(width, height)
    }

    private fun measureDimension(minSize: Int, measureSpec: Int): Int {
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)

        return when (specMode) {
            MeasureSpec.EXACTLY -> specSize
            MeasureSpec.AT_MOST -> minSize.coerceAtMost(specSize)
            else -> minSize
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val radius = size / 3f

        this.setBackgroundResource(R.drawable.speedometer)

        paint.color = needleColor
        paint.style = Paint.Style.FILL
        paint.strokeWidth = needleWidth

        val angleRad = angleDeg * PI / 180
        val centerPosition = size / 2f
        val endLinePositionX = centerPosition + radius * cos(angleRad).toFloat()
        val endLinePositionY = centerPosition + radius * sin(angleRad).toFloat()

        canvas.drawLine(
            centerPosition,
            centerPosition,
            endLinePositionX,
            endLinePositionY,
            paint
        )

        canvas.drawCircle(centerPosition, centerPosition, radius / 12, paint)
    }


}