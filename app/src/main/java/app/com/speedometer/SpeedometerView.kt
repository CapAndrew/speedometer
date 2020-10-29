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

    private companion object {
        const val VIEW_STATE_KEY = "state"
        const val SUPER_STATE = "super_state"
    }

    private var size = 320
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var angleDeg = 125
    private var needleColor = Color.WHITE
    private var needleWidth = 5.0f

    fun changeAngle(angle: Int) {
        this.angleDeg = angle
        invalidate()
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

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val radius = size / 3f

        this.setBackgroundResource(R.drawable.speedometer)




        paint.color = needleColor
        paint.style = Paint.Style.FILL
        paint.strokeWidth = needleWidth


        val angleRad = angleDeg * PI / 180
        val endLinePositionX = size / 2f + radius * cos(angleRad).toFloat()
        val endLinePositionY = size / 2f + radius * sin(angleRad).toFloat()

        canvas.drawLine(
            size / 2f,
            size / 2f,
            endLinePositionX,
            endLinePositionY,
            paint
        )

        canvas.drawCircle(size / 2f, size / 2f, radius / 12, paint)
    }


}