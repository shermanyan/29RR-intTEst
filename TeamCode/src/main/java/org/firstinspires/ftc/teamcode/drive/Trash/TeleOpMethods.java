//package org.firstinspires.ftc.teamcode.drive.Trash;
//
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.hardware.CRServo;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.qualcomm.robotcore.hardware.Servo;
//import com.qualcomm.robotcore.util.Range;
//
//import org.firstinspires.ftc.teamcode.drive.Trash.RobotHardwareMap;
//
//import static java.lang.Math.sqrt;
//
//public class TeleOpMethods extends OpMode {
//
//    RobotHardwareMap hardware = new RobotHardwareMap();
//
//
//    @Override
//    public void init() {
//    }
//    @Override
//    public void loop() {
//    }
//
//
//
//
////    public void getlift() {
////
////        lift.setDirection(DcMotor.Direction.REVERSE);
//
//
//        //lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        //lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
////    }
//
//
//    //Methods for Driving
//
//    public void Driving() {
//
//
//        float Y2 = gamepad1.left_stick_y;
//        float X2 = gamepad1.left_stick_x;
//        float R2 = gamepad1.right_stick_x;
//
//        float Y = (float) scaleInput(Y2);
//        float X = (float) scaleInput(X2);
//        float R = (float) scaleInput(R2);
//
//        float FrontLeft = +X - Y + R;
//        float FrontRight = - X - Y - R;
//        float BackLeft = - X - Y + R;
//        float BackRight = +X - Y - R;
//
//
//        if (gamepad1.x) {
//            FrontRight = Range.clip(FrontRight, -1, 1);
//            FrontLeft = Range.clip(FrontLeft, -1, 1);
//            BackLeft = Range.clip(BackLeft, -1, 1);
//            BackRight = Range.clip(BackRight, -1, 1);
//
//            telemetry.addData("SPEEDY BOY", "ON");
//
//        } else if (gamepad1.right_bumper) {
//            FrontRight = (float) Range.clip(FrontRight, -0.3, 0.3);
//            FrontLeft = (float) Range.clip(FrontLeft, -0.3, 0.3);
//            BackLeft = (float) Range.clip(BackLeft, -0.3, 0.3);
//            BackRight = (float) Range.clip(BackRight, -0.3, 0.3);
//
//
//            telemetry.addData("PRECISE BOY", "ON");
//        } else {
//            FrontRight = (float) Range.clip(FrontRight, -0.55, 0.55);
//            FrontLeft = (float) Range.clip(FrontLeft, -0.55, 0.55);
//            BackLeft = (float) Range.clip(BackLeft, -0.55, 0.55);
//            BackRight = (float) Range.clip(BackRight, -0.55, 0.55);
//
//
//        }
//        if (X + Y + R == 0) {
//            hardware.motorFrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//            hardware.motorFrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//            hardware.motorBackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//            hardware.motorBackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//
//        }
//
//        hardware.motorFrontLeft.setPower(FrontLeft);
//        hardware.motorFrontRight.setPower(FrontRight);
//        hardware.motorBackLeft.setPower(BackLeft);
//        hardware.motorBackRight.setPower(BackRight);
//
//    }
//
//    public String returndrivevalue() {
////        getlift();
//        String Speed;
//
//        double FL = hardware.motorFrontLeft.getPower();
//        double FR = hardware.motorFrontRight.getPower();
//        double BL = hardware.motorBackLeft.getPower();
//        double BR = hardware.motorBackRight.getPower();
//
//        Speed = FL + "," + FR + "," + BL + "," + BR;
//
//        return Speed;
//
//    }
//
//    private double scaleInput(double dVal) {
//        double[] scaleArray = {0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
//                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00};
//
//        // get the corresponding index for the scaleInput array.
//        int index = (int) (dVal * 16.0);
//
//        // index should be positive.
//        if (index < 0) {
//            index = -index;
//        }
//
//        // index cannot exceed size of array minus 1.
//        if (index > 16) {
//            index = 16;
//        }
//
//        // get value from the array.
//        double dScale = 0.0;
//        if (dVal < 0) {
//            dScale = -scaleArray[index];
//        } else {
//            dScale = scaleArray[index];
//        }
//
//        // return scaled value.
//        return dScale;
//    }
//
//    //Methods for Main Servos
//
//    public String GripperIntake() {
//
//        String power = "na";
//
//        hardware.GripLeft.scaleRange(.2, .5);
//        hardware.GripRight.scaleRange(.51, .8);
//        hardware.GripLeft.setPosition(gamepad1.right_trigger);
//        hardware.GripRight.setPosition(gamepad1.right_trigger);
//
//        if (gamepad2.right_bumper) {
//            hardware.spinL.setPower(1);
//            hardware.spinR.setPower(1);
//
//            power = String.valueOf(hardware.spinL.getPower());
//        }
//        if (gamepad2.left_bumper) {
//            hardware.spinL.setPower(0);
//            hardware.spinR.setPower(0);
//
//            power = String.valueOf(hardware.spinL.getPower());
//        }
//
//        return (power + "," + "L" + hardware.GripLeft.getPosition() + "," + "R" + hardware.GripRight.getPosition());
//    }
//
//    public void PlatformGrabber() {
//
//        hardware.platformL.scaleRange(.3, 1);
//        hardware.platformR.scaleRange(0, 0.7);
//
//        hardware.platformL.setPosition(gamepad2.left_trigger);
//        hardware.platformR.setPosition(gamepad2.right_trigger);
//
//        return;
//
//    }
//
//    //Methods for Lift
//
//    public String LiftArm() {
////        getlift();
//
//        int min = 10;
//        int max = 1800;
//
//        String status = "na";
//
//        if (liftencodervalue() > min && liftencodervalue() < max) {
//            if (gamepad2.dpad_up) {
//
//                hardware.lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//                hardware.lifthelper.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//                hardware.lift.setPower(1);
//                hardware.lifthelper.setPower(1);
//            } else if (gamepad2.dpad_down) {
//                hardware.lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//                hardware.lifthelper.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//                hardware.lift.setPower(downvalue());
//                hardware.lifthelper.setPower(downvalue());
//            } else {
//                hardware.lift.setPower(0.1);
//                hardware.lifthelper.setPower(0.1);
//                hardware.lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//                hardware.lifthelper.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//                // status = "brake";
//
//            }
//            status = String.valueOf(liftencodervalue());
//        } else if (liftencodervalue() >= max) {
//            if (gamepad2.dpad_down) {
//                hardware.lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//                hardware.lifthelper.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//                hardware.lift.setPower(downvalue());
//                hardware.lifthelper.setPower(downvalue());
//            } else {
//                hardware.lift.setPower(0.1);
//                hardware.lifthelper.setPower(0.1);
//                hardware.lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//                hardware.lifthelper.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//                //status = "maxbrake";
//            }
//
//            status = "MAX";
//        } else if (liftencodervalue() <= min) {
//            if (gamepad2.dpad_up) {
//                hardware.lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//                hardware.lifthelper.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
//                hardware.lift.setPower(1);
//                hardware.lifthelper.setPower(1);
//            } else {
//                hardware.lift.setPower(0.1);
//                hardware.lifthelper.setPower(0.1);
//                hardware.lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//                hardware.lifthelper.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//                //status = "minbrake";
//            }
//            status = "MIN";
//        }
//
//
//        return status;
//
//    }
//
//    private double liftencodervalue() {
////        getlift();
//        double value = -hardware.lift.getCurrentPosition();
//
//        return value;
//    }
//
//    private double upvalue() {
////        getlift();
//        double distance = hardware.lift.getCurrentPosition();
//        double power = 0;
//        if (distance >= 1500) {
//            power = 0.35;
//        }
//        return power;
//    }
//
//    private double downvalue() {
////        getlift();
//        double distance = hardware.lift.getCurrentPosition();
//
//        double power = 0;
//        if (distance <= 1000 && distance > 200) {
//            power = sqrt(distance / 1000);
//        } else {
//            power = -0.1;
//        }
//
//        return power;
//    }
//
//    //Other
//
//    public String Capstone() {
//
//        String status = "Locked";
//        if (gamepad2.x) {
//            hardware.cap.setPosition(0);
//            status = "Released";
//        } else  {
//            hardware.cap.setPosition(0.8);
//        }
//        return status;
//    }
//
//    public String SideArm (){
//
//        String ArmValue = (hardware.Arm1.getPosition()+ ","+hardware.Arm2.getPosition());
//        hardware.Arm1.scaleRange(0.1, 0.7);
//        hardware.Arm2.scaleRange(0, 1);
//        if(gamepad1.y){
//            hardware.Arm1.setPosition(0);
//        }
//        if(gamepad1.x){
//            hardware.Arm1.setPosition(1);
//        }
//        if (gamepad1.b){
//            hardware.Arm2.setPosition(0);
//        }
//        else if(gamepad1.a){
//            hardware.Arm2.setPosition(1);
//        }
//
//        return ArmValue;
//    }
//
//    public void Tape (){
//        hardware.tapemeasure.setPower(gamepad2.left_stick_y);
//    }
//
//}
//
