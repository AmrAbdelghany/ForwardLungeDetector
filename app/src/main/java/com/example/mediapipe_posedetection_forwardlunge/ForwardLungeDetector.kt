package com.example.mediapipe_posedetection_forwardlunge

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.example.mediapipe_posedetection_forwardlunge.databinding.CameraBinding
import com.google.mediapipe.tasks.components.containers.NormalizedLandmark
import kotlin.math.abs
import kotlin.math.atan2

class ForwardLungeDetector(context: Context?, attrs: AttributeSet): View(context, attrs) {

    private var binding: CameraBinding? = null
    private val maxAngle = 95
    private val minAngle = 170
    private var previousLeftAngle = minAngle
    private var previousRightAngle = minAngle
    private var leftReps = 0
    private var rightReps = 0
    private val kneeYThreshold = 0.15
    private var progress = 0

    @SuppressLint("SetTextI18n")
    fun detectForwardLunge(landmarks: List<NormalizedLandmark>){

        val leftLegAngle = calculateAngle(landmarks[23], landmarks[25], landmarks[27]).toInt()
        val rightLegAngle = calculateAngle(landmarks[24], landmarks[26], landmarks[28]).toInt()

        val rightKneeY = (landmarks[26].y())
        val leftKneeY = (landmarks[25].y())

        if(rightLegAngle <= maxAngle || rightLegAngle > minAngle)
            rightRepCounter(rightLegAngle,rightKneeY - leftKneeY)

        if (leftLegAngle <= maxAngle || leftLegAngle > minAngle)
            leftRepCounter(leftLegAngle, leftKneeY - rightKneeY)


        val leftLegCounter = binding!!.leftLegCounter
        leftLegCounter.text = "Left Leg Count: $leftReps"

        val rightLegCounter = binding!!.rightLegCounter
        rightLegCounter.text = "Right Leg Count: $rightReps"

        val angleProgressBar = binding!!.angleProgressbar

        if(leftLegAngle < rightLegAngle) {
            progress = ((leftLegAngle - minAngle) / (maxAngle - minAngle) * 100)
            angleProgressBar.progress = progress
        } else{
            progress = ((rightLegAngle - minAngle) / (maxAngle - minAngle) * 100)
            angleProgressBar.progress = progress
        }
    }

    private fun calculateAngle(hip: NormalizedLandmark, knee: NormalizedLandmark, ankle: NormalizedLandmark):Double{
        var angle = Math.toDegrees(
            atan2((ankle.y()-knee.y()).toDouble(), (ankle.x()-knee.x().toDouble())) -
                    atan2((hip.y()-knee.y()).toDouble(), (hip.x()-knee.x()).toDouble()))

        angle = abs(angle)

        if(angle>180)
            angle = 360.0 - angle

        return angle
    }

    private fun leftRepCounter(leftAngle: Int, kneeYDifference: Float){
        if(leftAngle <= maxAngle && previousLeftAngle >= minAngle && kneeYDifference >= kneeYThreshold){
            leftReps++
            previousLeftAngle = leftAngle
        } else if(leftAngle >= minAngle){
            previousLeftAngle = leftAngle
        }
    }

    private fun rightRepCounter(rightAngle: Int, kneeYDifference: Float){
        if(rightAngle <= maxAngle && previousRightAngle >= minAngle && kneeYDifference >= kneeYThreshold){
            rightReps++
            previousRightAngle = rightAngle
        } else if(rightAngle >= minAngle ){
            previousRightAngle = rightAngle
        }
    }

    fun setBinding(cameraBinding: CameraBinding){
        binding = cameraBinding
    }
}