package org.firstinspires.ftc.teamcode.drive.RobotHardware;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
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

    MecanumDriveBasePID drive = new MecanumDriveBasePID(hardwareMap);

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

        Arm1.setDirection(Servo.Direction.FORWARD);
        Arm2.setDirection(Servo.Direction.REVERSE);
        Arm3.setDirection(Servo.Direction.REVERSE);
        Arm4.setDirection(Servo.Direction.FORWARD);

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

        skystoneDetectorService DetectorService = new skystoneDetectorService(webcam);

    }

    public int findSkystonePosActive() {

//        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
//       webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Logitech"), cameraMonitorViewId);

        skystoneDetectorService DetectorService = new skystoneDetectorService(webcam);
        valLeft = DetectorService.getValLeft();
        valMid = DetectorService.getValMid();
        valRight = DetectorService.getValRight();
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

    public int sky1Pos(){
        int pos = 0;
        if (opModeIsActive() && findSkystonePosActive() == 1) {
            pos = 1;

        } else if (opModeIsActive() && findSkystonePosActive() == 2) {
            pos  = 2;

        } else if (opModeIsActive() && findSkystonePosActive() == 3) {
            pos = 3;

        }
        return pos;

    }

    public int sky2Pos(){
        int pos = 0;

     if (opModeIsActive() && findSkystonePosActive() == 1) {
        pos = 4;

    } else if (opModeIsActive() && findSkystonePosActive() == 2) {
        pos  = 5;

    } else if (opModeIsActive() && findSkystonePosActive() == 3) {
         pos = 6;
     }
        return pos;

    }

    //Position Calling Methods
// put the x,y, for each of the skystone positions

//    public String[] skyPosDet(){
//        String [] skypos;
//        if (opModeIsActive() && findSkystonePosActive() == 1) {
//            skypos = ((new Pose2d(0,0,0)));
//        } else if (opModeIsActive() && findSkystonePosActive() == 2) {
//            skypos = String.valueOf((new Pose2d(0,0,0)));
//        } else if (opModeIsActive() && findSkystonePosActive() == 3) {
//            skypos = String.valueOf((new Pose2d(0,0,0)));
//        }
//        return skypos;
//    }



//    {
//        // declaring and initializing 2D array
//        int arr[][] = { {2,7,9},{3,6,1},{7,4,2} };
//
//        // printing 2D array
//        for (int i=0; i< 3 ; i++)
//        {
//            for (int j=0; j < 3 ; j++)
//                System.out.print(arr[i][j] + " ");
//
//            System.out.println();
//        }
//    }


//    public int[] skyPosDet2(){
//        // set below array with the position of the 3 skystone
//        int[] posArray1 = {2,1};
//        int[] posArray2 = {2,1};
//        int[] posArray3 = {2,1};
//
//        if (opModeIsActive() && findSkystonePosActive() == 1) {
//            posArray1[0] = 0;
//            //   skypos = ((new Pose2d(0,0,0)));
//        } else if (opModeIsActive() && findSkystonePosActive() == 2) {
//            posArray2[1] = 0;
////            skypos = String.valueOf((new Pose2d(0,0,0)));
//        } else if (opModeIsActive() && findSkystonePosActive() == 3) {
//            posArray3[0] = 0;
//            //          skypos = String.valueOf((new Pose2d(0,0,0)));
//        }
//
//
//        return skyposArray;
//    }

//    public int[] sky1Pos(){
//        // : set below arrays with the position of the First 3 skystone XY Positons
//        int[] skyPos1 = {2,0};
//        int[] skyPos2 = {3,0};
//        int[] skyPos3 = {4,0};
//
//        int [] pos = {0,0};
//
//        if (opModeIsActive() && findSkystonePosActive() == 1) {
//            pos = skyPos1;
//
//        } else if (opModeIsActive() && findSkystonePosActive() == 2) {
//            pos  = skyPos2;
//
//        } else if (opModeIsActive() && findSkystonePosActive() == 3) {
//            pos = skyPos3;
//
//        }
//
//
//        return pos ;
//    }
//    public int sky1Pos(){
//        // : set below arrays with the position of the First 3 skystone XY Positons
//        int skyPos1x = 1;
//        int skyPos1y = 1;
//        int skyPos2x = 2;
//        int skyPos2y = 2;
//        int skyPos3x = 3;
//        int skyPos3y = 3;
//
//        int posx = 0 ;
//        int posy = 0 ;
//
//        if (opModeIsActive() && findSkystonePosActive() == 1) {
//            posx = skyPos1x;
//            posy = skyPos1y;
//
//        } else if (opModeIsActive() && findSkystonePosActive() == 2) {
//            posx = skyPos2x;
//            posy = skyPos2y;
//
//        } else if (opModeIsActive() && findSkystonePosActive() == 3) {
//            posx = skyPos3x;
//            posy = skyPos3y;
//        }
//
//
//        return posx + posy;
//    }

//    public int[] sky2Pos(){
//        // : set below arrays with the position of the SECOND 3 skystone XY Positons
//
//        int[] skyPos4 = {2,0};
//        int[] skyPos5 = {3,0};
//        int[] skyPos6 = {4,0};
//
//        int [] pos2 = {0,0};
//
//        if (opModeIsActive() && findSkystonePosActive() == 1) {
//            pos2 = skyPos4;
//
//        } else if (opModeIsActive() && findSkystonePosActive() == 2) {
//            pos2  = skyPos5;
//
//        } else if (opModeIsActive() && findSkystonePosActive() == 3) {
//            pos2 = skyPos6;
//
//        }
//
//
//        return pos2 ;
//    }

//    public int sky2Pos(){
//
//        if (sky1Pos()==1)
//        // : set below arrays with the position of the SECOND 3 skystone XY Positons
//        int skyPos4x = 1;
//        int skyPos4y = 1;
//        int skyPos5x = 2;
//        int skyPos5y = 2;
//        int skyPos6x = 3;
//        int skyPos6y = 3;
//
//        int posx = 0 ;
//        int posy = 0 ;
//
//        if (opModeIsActive() && findSkystonePosActive() == 1) {
//            posx = skyPos4x;
//            posy = skyPos4y;
//
//        } else if (opModeIsActive() && findSkystonePosActive() == 2) {
//            posx = skyPos5x;
//            posy = skyPos5y;
//
//        } else if (opModeIsActive() && findSkystonePosActive() == 3) {
//            posx = skyPos6x;
//            posy = skyPos6y;
//        }
//
//
//        return posx + posy;
//    }


//    public static void main(String args[])
//    {
//        // declaring and initializing 2D array
//        int arr[][] = { {2,7,9},{3,6,1},{7,4,2} };
//
//        // printing 2D array
//        for (int i=0; i< 3 ; i++)
//        {
//            for (int j=0; j < 3 ; j++)
//                System.out.print(arr[i][j] + " ");
//
//            System.out.println();
//        }
//    }

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
