package org.firstinspires.ftc.teamcode.drive.RobotHardware;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static java.lang.Math.sqrt;


public class AutonMethods extends LinearOpMode{

    RobotMechBase mechanisms = new RobotMechBase(hardwareMap);

    public static final double s_on = .3;
    public static final double d_on = .4;
    public static final double off = 0;
    public static double grabMax = .9;
    public static final double grabMin = .5;
    public static final double platformMax = 1;
    public static final double platformMin = 0;
    public static final int i_off = 0;

    public static final double sarmup = 0.7;
    public static final double sarmdown = 0.1;
    public static final double sarmmid = 0.35;

    public static final double sarmopen = 1;
    public static final double sarmclose = 0;



    @Override
    public void runOpMode() throws InterruptedException {

    }

    //LiftArm Methods
    public void LiftArm(long time, double power) {
        mechanisms.lift.setPower(power);
        sleep(time);
        mechanisms.lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void StopLiftArm() {
        mechanisms.lift.setPower(0.1);
        mechanisms.lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        sleep(10);
    }

    public double liftdownvalue() {
        mechanisms.lift = hardwareMap.get(DcMotor.class, "lift");
        double distance = mechanisms.lift.getCurrentPosition();

        double power = 0;
        if (distance <= 1000 && distance > 200) {
            power = sqrt(distance / 1000);
        } else {
            power = -0.1;
        }

        return power;
    }

    //SideArm Methods

    public void SideArm(long time, int stime, double aposition) {
        mechanisms.Arm2.setPosition(aposition);
        sleep(stime);
    }

    public void SideArmGrip(long time, int stime, double gposition) {
        mechanisms.Arm1.setPosition(gposition);
        sleep(stime);
    }


}
