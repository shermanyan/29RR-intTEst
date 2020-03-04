package org.firstinspires.ftc.teamcode.drive.opmode;

import android.graphics.Paint;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.drive.RobotHardware.RobotMechBase;

import static java.lang.Math.sqrt;


@TeleOp(name = "Drive Code ILT")


public class driveCode extends OpMode {

    RobotMechBase mechanisms = new RobotMechBase(hardwareMap);



    @Override
    public void init() {

        mechanisms.lift.setPower(0.1);
        mechanisms.lifthelper.setPower(0.1);

    }

    @Override
    public void loop() {

        mechanisms.Driving();
        mechanisms.LiftArm();
        String gripperstatus = mechanisms.GripperIntake();
        mechanisms.PlatformGrabber();
        String DrivingSpeed = mechanisms.returndrivevalue();
        mechanisms.Capstone();
        String SideArmValue = mechanisms.SideArm();


        telemetry.addData("DrivingSpeed " , DrivingSpeed);
        telemetry.addData("liftstatus", mechanisms.LiftArm());
        telemetry.addData("liftpower", mechanisms.lift.getPower());
        telemetry.addData("gripperstatus", gripperstatus, mechanisms.GripRight.getPosition(), mechanisms.GripLeft.getPosition());
//        telemetry.addData("platform", platformstatus);
        telemetry.addData("Brake", mechanisms.lift.getZeroPowerBehavior());
        telemetry.addData("Captone", mechanisms.Capstone());
        telemetry.addData("SideArm Value",SideArmValue);

    }



    @Override
    public void stop() {

    }
}
