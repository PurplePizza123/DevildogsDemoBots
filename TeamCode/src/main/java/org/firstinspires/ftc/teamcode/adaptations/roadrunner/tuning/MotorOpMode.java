package org.firstinspires.ftc.teamcode.adaptations.roadrunner.tuning;

import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.RUN_WITHOUT_ENCODER;
import static com.qualcomm.robotcore.hardware.DcMotor.RunMode.STOP_AND_RESET_ENCODER;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.ftc.OverflowEncoder;
import com.acmerobotics.roadrunner.ftc.RawEncoder;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Config
@TeleOp
@Disabled
public class MotorOpMode extends LinearOpMode {
    public static double POWER = 0.5;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(super.telemetry, FtcDashboard.getInstance().getTelemetry());

        DcMotor driveFrontLeft = hardwareMap.get(DcMotor.class, "driveFrontLeft");
        DcMotor driveFrontRight = hardwareMap.get(DcMotor.class, "driveFrontRight");
        DcMotor driveBackLeft = hardwareMap.get(DcMotor.class, "driveBackLeft");
        DcMotor driveBackRight = hardwareMap.get(DcMotor.class, "driveBackRight");

        driveBackLeft.setDirection(REVERSE);
        driveBackRight.setDirection(REVERSE);

        driveFrontLeft.setMode(STOP_AND_RESET_ENCODER);
        driveFrontLeft.setMode(RUN_WITHOUT_ENCODER);
        driveBackRight.setMode(STOP_AND_RESET_ENCODER);
        driveBackRight.setMode(RUN_WITHOUT_ENCODER);

        OverflowEncoder odometryRight = new OverflowEncoder(new RawEncoder(hardwareMap.get(DcMotorEx.class, "odometryRight")));
        OverflowEncoder odometryCenter = new OverflowEncoder(new RawEncoder(hardwareMap.get(DcMotorEx.class, "odometryCenterAndIntake")));

        odometryRight.setDirection(REVERSE);

        telemetry.addData(">", "Ready. Press Play.");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            driveFrontLeft.setPower(gamepad1.x ? POWER : 0);
            driveFrontRight.setPower(gamepad1.y ? POWER : 0);
            driveBackLeft.setPower(gamepad1.a ? POWER : 0);
            driveBackRight.setPower(gamepad1.b ? POWER : 0);

            telemetry.addData("driveFrontLeft",  "%d", driveFrontLeft.getCurrentPosition());
            telemetry.addData("driveFrontRight",  "%d", driveFrontRight.getCurrentPosition());
            telemetry.addData("driveBackLeft",  "%d", driveBackLeft.getCurrentPosition());
            telemetry.addData("driveBackRight",  "%d", driveBackRight.getCurrentPosition());

            telemetry.addData("odometryRight",  "%d", odometryRight.getPositionAndVelocity().position);
            telemetry.addData("odometryCenter",  "%d", odometryCenter.getPositionAndVelocity().position);

            telemetry.update();

            sleep(50);
        }
    }
}
