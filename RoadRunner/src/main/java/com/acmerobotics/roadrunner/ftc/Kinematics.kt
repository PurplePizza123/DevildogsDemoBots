package com.acmerobotics.roadrunner.ftc

import com.acmerobotics.roadrunner.Arclength
import com.acmerobotics.roadrunner.DualNum
import com.acmerobotics.roadrunner.Pose2dDual
import com.acmerobotics.roadrunner.PosePath
import com.acmerobotics.roadrunner.PoseVelocity2dDual
import com.acmerobotics.roadrunner.Twist2dDual
import com.acmerobotics.roadrunner.Vector2dDual
import com.acmerobotics.roadrunner.VelConstraint
import kotlin.math.abs

/**
 * @param[trackWidth] distance between wheels on opposite sides; see the diagram below
 * ![Wheelbase and track width diagram](https://upload.wikimedia.org/wikipedia/commons/5/52/Wheelbase_and_Track.png)
 * @param[lateralMultiplier] factor that multiplies strafe velocity to compensate for slip; increase it to boost the
 * distance traveled in the strafe direction
 */
data class OmniKinematics @JvmOverloads constructor(
        @JvmField
        val trackWidth: Double,
        @JvmField
        val lateralMultiplier: Double = 1.0
) {
    /**
     * @param[wheelbase] distance between wheels on the same side; see the diagram in [OmniKinematics]
     */
    constructor(
            trackWidth: Double,
            wheelbase: Double,
            lateralMultiplier: Double = 1.0
    ) : this((trackWidth + wheelbase) / 2, lateralMultiplier)

    data class WheelIncrements<Param>(
            @JvmField
            val leftFront: DualNum<Param>,
            @JvmField
            val leftBack: DualNum<Param>,
            @JvmField
            val rightBack: DualNum<Param>,
            @JvmField
            val rightFront: DualNum<Param>,
    )

    fun <Param> forward(w: WheelIncrements<Param>) = Twist2dDual(
            Vector2dDual(
                (w.leftBack + w.rightFront) * 0.5,
                (w.leftFront + w.rightBack) * (0.5 / lateralMultiplier),
            ),
            (w.leftFront - w.leftBack - w.rightBack + w.rightFront) * (0.25 / trackWidth),
    )

    data class WheelVelocities<Param>(
            @JvmField
            val leftFront: DualNum<Param>,
            @JvmField
            val leftBack: DualNum<Param>,
            @JvmField
            val rightBack: DualNum<Param>,
            @JvmField
            val rightFront: DualNum<Param>,
    ) {
        fun all() = listOf(leftFront, leftBack, rightBack, rightFront)
    }

    fun <Param> inverse(t: PoseVelocity2dDual<Param>) = WheelVelocities(
            t.linearVel.y + t.angVel * trackWidth,
            t.linearVel.x - t.angVel * trackWidth,
            t.linearVel.y - t.angVel * trackWidth,
            t.linearVel.x + t.angVel * trackWidth,
    )

    inner class WheelVelConstraint(@JvmField val maxWheelVel: Double) : VelConstraint {
        override fun maxRobotVel(robotPose: Pose2dDual<Arclength>, path: PosePath, s: Double): Double {
            val txRobotWorld = robotPose.value().inverse()
            val robotVelWorld = robotPose.velocity().value()
            val robotVelRobot = txRobotWorld * robotVelWorld
            return inverse(PoseVelocity2dDual.constant<Arclength>(robotVelRobot, 1))
                    .all()
                    .minOf { abs(maxWheelVel / it.value()) }
        }
    }
}