package org.firstinspires.ftc.teamcode.drive.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.drive.RobotHardware.AutonMethods;
import org.firstinspires.ftc.teamcode.drive.RobotHardware.MecanumDriveBasePID;
import org.firstinspires.ftc.teamcode.drive.RobotHardware.skystoneDetectorService;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;

@Autonomous(group = "drive")

public class camINT extends AutonMethods {


    int[][] skyposArray = {{5,0},{10,0},{20,0},{30,0},{40,0},{50,0}};

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

    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDriveBasePID drive = new MecanumDriveBasePID(hardwareMap);
        robotStartup();
        cam2detectorstartup();
        telemetry.addData("pos1",sky1Pos());
        telemetry.addData("pos2",sky2Pos());
        telemetry.addData("FinalPos",new Pose2d(skyposArray[sky1Pos()][0],skyposArray[sky1Pos()][1]));
        telemetry.update();

        waitForStart();


    }



    public void cam2detectorstartup() {
        robotStartup();
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Logitech2"), cameraMonitorViewId);

        skystoneDetectorService skystoneDetectorService = new skystoneDetectorService(webcam);

    }
    public void cam1detectorstartup() {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Logitech1"), cameraMonitorViewId);
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
    public int sky1Pos(){

        int pos = 0;
        if (opModeIsActive() && findSkystonePosActive() == 1) {
            pos = 0;

        } else if (opModeIsActive() && findSkystonePosActive() == 2) {
            pos  = 1;

        } else if (opModeIsActive() && findSkystonePosActive() == 3) {
            pos = 2;

        }
        return pos;

    }
    public int sky2Pos(){

        int pos = 0;

        if (opModeIsActive() && findSkystonePosActive() == 1) {
            pos = 3;

        } else if (opModeIsActive() && findSkystonePosActive() == 2) {
            pos  = 4;

        } else if (opModeIsActive() && findSkystonePosActive() == 3) {
            pos = 5;
        }
        return pos;

    }






}
