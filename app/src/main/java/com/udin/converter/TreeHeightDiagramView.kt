package com.udin.converter

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import kotlin.math.atan2

class TreeHeightDiagramView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private val density = resources.displayMetrics.density
    private fun dp(value: Float) = value * density

    private val colorGreenDark = Color.parseColor("#1B5E20")
    private val colorGreen = Color.parseColor("#2E7D32")
    private val colorAmber = Color.parseColor("#F57F17")
    private val colorGray = Color.parseColor("#757575")
    private val colorTrunk = Color.parseColor("#795548")

    private val dashPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = dp(1.5f)
        color = colorGray
        pathEffect = DashPathEffect(floatArrayOf(dp(6f), dp(4f)), 0f)
    }

    private val sightPuncakPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = dp(2f)
        color = colorGreenDark
    }

    private val sightPangkalPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = dp(2f)
        color = colorAmber
    }

    private val trunkPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = colorTrunk
    }

    private val canopyPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = colorGreen
    }

    private val observerPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.BLACK
    }

    private val bracketPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = dp(1.5f)
        color = colorGreenDark
    }

    private val labelGray = textPaint(colorGray, 11f)
    private val labelPuncak = textPaint(colorGreenDark, 12f, bold = true)
    private val labelPangkal = textPaint(colorAmber, 12f, bold = true)
    private val labelHeight = textPaint(colorGreenDark, 12f, bold = true)
    private val labelObserver = textPaint(colorGray, 11f)

    private fun textPaint(paintColor: Int, sizeSp: Float, bold: Boolean = false) =
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = paintColor
            textSize = dp(sizeSp)
            isFakeBoldText = bold
        }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val left = paddingLeft.toFloat()
        val top = paddingTop.toFloat()
        val right = width - paddingRight.toFloat()
        val bottom = height - paddingBottom.toFloat()

        // Reserve safe margins so the canopy circle, angle labels, observer
        // label and height-bracket text never get clipped by the view bounds.
        val observerX = left + dp(8f)
        val observerY = bottom - dp(24f)
        val treeX = right - dp(70f)
        val topY = top + dp(40f)
        val baseY = observerY - (observerY - topY) * 0.3f

        // horizontal distance line (dashed)
        canvas.drawLine(observerX, observerY, treeX, observerY, dashPaint)
        canvas.drawText("Jarak", (observerX + treeX) / 2 - dp(14f), observerY + dp(16f), labelGray)

        // sighting lines from observer to top and base of tree
        canvas.drawLine(observerX, observerY, treeX, topY, sightPuncakPaint)
        canvas.drawLine(observerX, observerY, treeX, baseY, sightPangkalPaint)

        // angle arcs at observer position
        val anglePuncak = Math.toDegrees(
            atan2((observerY - topY).toDouble(), (treeX - observerX).toDouble())
        ).toFloat()
        val anglePangkal = Math.toDegrees(
            atan2((observerY - baseY).toDouble(), (treeX - observerX).toDouble())
        ).toFloat()

        val radiusOuter = dp(44f)
        val radiusInner = dp(28f)
        val rectOuter = RectF(
            observerX - radiusOuter, observerY - radiusOuter,
            observerX + radiusOuter, observerY + radiusOuter
        )
        val rectInner = RectF(
            observerX - radiusInner, observerY - radiusInner,
            observerX + radiusInner, observerY + radiusInner
        )
        canvas.drawArc(rectOuter, -anglePuncak, anglePuncak, false, sightPuncakPaint)
        canvas.drawArc(rectInner, -anglePangkal, anglePangkal, false, sightPangkalPaint)

        canvas.drawText(
            "θ puncak",
            observerX + radiusOuter * 0.5f,
            observerY - radiusOuter * 0.55f,
            labelPuncak
        )
        canvas.drawText(
            "θ pangkal",
            observerX + radiusInner * 0.55f,
            observerY - radiusInner * 0.05f,
            labelPangkal
        )

        // observer point + label
        canvas.drawCircle(observerX, observerY, dp(4f), observerPaint)
        canvas.drawText("Anda", observerX - dp(6f), observerY + dp(30f), labelObserver)

        // tree trunk + canopy
        val trunkWidth = dp(6f)
        canvas.drawRect(treeX - trunkWidth / 2, baseY, treeX + trunkWidth / 2, topY, trunkPaint)
        canvas.drawCircle(treeX, topY - dp(12f), dp(20f), canopyPaint)

        // height bracket for total tree height
        val bracketX = treeX + dp(20f)
        canvas.drawLine(bracketX, topY, bracketX, baseY, bracketPaint)
        canvas.drawLine(bracketX - dp(5f), topY, bracketX + dp(5f), topY, bracketPaint)
        canvas.drawLine(bracketX - dp(5f), baseY, bracketX + dp(5f), baseY, bracketPaint)
        canvas.drawText("Tinggi", bracketX + dp(8f), (topY + baseY) / 2 - dp(2f), labelHeight)
        canvas.drawText("Pohon", bracketX + dp(8f), (topY + baseY) / 2 + dp(14f), labelHeight)
    }
}
