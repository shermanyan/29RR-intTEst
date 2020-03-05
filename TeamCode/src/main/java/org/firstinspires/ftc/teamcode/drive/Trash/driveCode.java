//package org.firstinspires.ftc.teamcode.drive.opmode;
//
//
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import org.firstinspires.ftc.teamcode.drive.Trash.RobotHardwareMap;
//import org.firstinspires.ftc.teamcode.drive.Trash.TeleOpMethods;
//
//
//@TeleOp(name = "Drive Code ILT")
//
//
//public class driveCode extends OpMode {
//
//    RobotHardwareMap hardware = new RobotHardwareMap(hardwareMap);
//    TeleOpMethods teleOpMethods = new TeleOpMethods();
//
//
//
//    @Override
//    public void init() {
//
//        hardware.lift.setPower(0.1);
//        hardware.lifthelper.setPower(0.1);
//
//    }
//
//    @Override
//    public void loop() {
//
//        teleOpMethods.Driving();
//        teleOpMethods.LiftArm();
//        String gripperstatus = teleOpMethods.GripperIntake();
//        teleOpMethods.PlatformGrabber();
//        String DrivingSpeed = teleOpMethods.returndrivevalue();
//        teleOpMethods.Capstone();
//        String SideArmValue = teleOpMethods.SideArm();
//
//
//        telemetry.addData("DrivingSpeed " , DrivingSpeed);
//        telemetry.addData("liftstatus", teleOpMethods.LiftArm());
//        telemetry.addData("liftpower", hardware.lift.getPower());
//        telemetry.addData("gripperstatus", gripperstatus, hardware.GripRight.getPosition(), hardware.GripLeft.getPosition());
////        telemetry.addData("platform", platformstatus);
//        telemetry.addData("Brake", hardware.lift.getZeroPowerBehavior());
//        telemetry.addData("Captone", teleOpMethods.Capstone());
//        telemetry.addData("SideArm Value",SideArmValue);
//
//    }
//
//
//
//    @Override
//    public void stop() {
//
//    }
//}
