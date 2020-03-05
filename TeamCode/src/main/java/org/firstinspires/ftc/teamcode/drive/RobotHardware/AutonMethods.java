package org.firstinspires.ftc.teamcode.drive.RobotHardware;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Hardware;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;

import java.security.PublicKey;

import static java.lang.Math.sqrt;


public class AutonMethods extends LinearOpMode {

    public DcMotor motorFrontRight;
    public DcMotor motorFrontLeft;
    public DcMotor motorBackRight;
    public DcMotor motorBackLeft;
    public DcMotor lift;
    public DcMotor lifthelper;
    public DcMotor tapemeasure;
    public Servo GripLeft;
    public Servo GripRight;
    public Servo platformR;
    public Servo platformL;
    public CRServo spinR;
    public CRServo spinL;
    public Servo cap;
    public Servo Arm1;
    public Servo Arm2;
    public Servo Arm3;
    public Servo Arm4;

    @Override
    public void runOpMode() throws InterruptedException {

    }

//    public RobotHardwareMap hardware = new RobotHardwareMap();
//
//
//    public void setHardware(RobotHardwareMap hardware) {
//        this.hardware = hardware;
//    }

    public static final double s_on = .3;
    public static final double d_on = .4;
    public static final double off = 0;
    public static double grabMax = .9;
    public static final double grabMin = .5;
    public static final double platformdown = 1;
    public static final double platformup = 0;
    public static final int i_off = 0;

    public static final double sarmup = 0.7;
    public static final double sarmdown = 0.1;
    public static final double sarmmid = 0.35;

    public static final double sarmopen = 1;
    public static final double sarmclose = 0;

    //Skystone Detector
    private int valLeft = 10;
    private int valMid = 10;
    private int valRight = 10;

    public int getValLeft() {
        return valLeft;
    }

    public int getValMid() {
        return valMid;
    }

    public int getValRight() {
        return valRight;
    }

    public int getiSkystonePos() {
        return iSkystonePos;
    }

    private int iSkystonePos = 1;
    OpenCvCamera webcam;

    public int cam1 = 1;
    public int cam2 = 2;




    public void robotStartup(){
        motorFrontRight = hardwareMap.get(DcMotor.class, "frontRight");
        motorFrontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        motorBackRight = hardwareMap.get(DcMotor.class, "backRight");
        motorBackLeft = hardwareMap.get(DcMotor.class, "backLeft");


        GripLeft = hardwareMap.get(Servo.class, "GripLeft");
        GripRight = hardwareMap.get(Servo.class, "GripRight");

        spinL = hardwareMap.get(CRServo.class, "spinL");
        spinR = hardwareMap.get(CRServo.class, "spinR");

        spinL = hardwareMap.get(CRServo.class, "spinL");
        spinR = hardwareMap.get(CRServo.class, "spinR");

        cap = hardwareMap.get(Servo.class, "cap");
        Arm1 = hardwareMap.get(Servo.class, "arm1");
        Arm2 = hardwareMap.get(Servo.class, "arm2");
        Arm3 = hardwareMap.get(Servo.class, "arm3");
        Arm4 = hardwareMap.get(Servo.class, "arm4");

        cap.setDirection(Servo.Direction.REVERSE);


        motorFrontLeft.setDirection(DcMotor.Direction.FORWARD);
        motorFrontRight.setDirection(DcMotor.Direction.REVERSE);
        motorBackLeft.setDirection(DcMotor.Direction.FORWARD);
        motorBackRight.setDirection(DcMotor.Direction.REVERSE);

        spinR.setDirection(CRServo.Direction.FORWARD);
        spinL.setDirection(CRServo.Direction.REVERSE);
        GripRight.setDirection(Servo.Direction.FORWARD);
        GripLeft.setDirection(Servo.Direction.REVERSE);
        Arm1.setDirection(Servo.Direction.REVERSE);
        Arm2.setDirection(Servo.Direction.REVERSE);
        Arm3.setDirection(Servo.Direction.REVERSE);
        Arm4.setDirection(Servo.Direction.REVERSE);

        platformR = hardwareMap.get(Servo.class, "platformR");
        platformL = hardwareMap.get(Servo.class, "platformL");

        platformL.setDirection(Servo.Direction.REVERSE);
        platformR.setDirection(Servo.Direction.FORWARD);

        lift = hardwareMap.get(DcMotor.class, "lift");
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setDirection(DcMotor.Direction.REVERSE);
        lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        lifthelper = hardwareMap.get(DcMotor.class, "lifthelper");
        lifthelper.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        lifthelper.setDirection(DcMotor.Direction.REVERSE);

        tapemeasure = hardwareMap.get(DcMotor.class, "tape");

    }




    //Detector Methods

    public void detectorstartup(int cam) {

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        if (cam == 1){
            webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Logitech1"), cameraMonitorViewId);
        }
        else if (cam == 2){
            webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Logitech2"), cameraMonitorViewId);

        }

        skystoneDetectorService skystoneDetectorService = new skystoneDetectorService(webcam);

    }

    public int findSkystonePosActive() {

//        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
//       webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Logitech"), cameraMonitorViewId);

        skystoneDetectorService skystoneDetectorService = new skystoneDetectorService(webcam);
        valLeft = skystoneDetectorService.getValLeft();
        valMid = skystoneDetectorService.getValMid();
        valRight = skystoneDetectorService.getValRight();
        int pos = 1;

        // determine which one has skystone

        if (opModeIsActive() && valLeft <= 0) {
            pos = 1;
        } else if (opModeIsActive() && valMid <= 0) {
            pos = 2;
        } else
            pos = 3;
        return pos;
    }


    //LiftArm Methods
    public void getLift(){
        lift = hardwareMap.get(DcMotor.class, "lift");
    }
    public void LiftArm(long time, double power) {
        getLift();
        lift.setPower(power);
        sleep(time);
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void StopLiftArm() {
        lift.setPower(0.1);
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        sleep(10);
    }
    public void Platform(double position) {

        platformL.scaleRange(.3, 1);
        platformR.scaleRange(0, 0.72);

        platformL.setPosition(position);
        platformR.setPosition(position);
        sleep(10);
    }

    public double liftdownvalue() {
        lift = hardwareMap.get(DcMotor.class, "lift");
        double distance = lift.getCurrentPosition();

        double power = 0;
        if (distance <= 1000 && distance > 200) {
            power = sqrt(distance / 1000);
        } else {
            power = -0.1;
        }

        return power;
    }

    //SideArm Methods

    public void SideArm(double aposition) {
        Arm2.setPosition(aposition);
        sleep(10);
    }

    public void SideArmGrip(long time, int stime, double gposition) {
        Arm1.setPosition(gposition);
        sleep(stime);
    }

    public void SideArm1(long time, int stime, double aposition) {
        Arm3.setPosition(aposition);
        sleep(stime);
    }

    public void SideArmGrip2(long time, int stime, double gposition) {
        Arm4.setPosition(gposition);
        sleep(stime);
    }



}
