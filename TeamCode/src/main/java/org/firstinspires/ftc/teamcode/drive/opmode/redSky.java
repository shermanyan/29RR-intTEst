package org.firstinspires.ftc.teamcode.drive.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.drive.RobotHardware.AutonMethods;
import org.firstinspires.ftc.teamcode.drive.RobotHardware.DriveConstants;
import org.firstinspires.ftc.teamcode.drive.RobotHardware.MecanumDriveBasePID;
import org.firstinspires.ftc.teamcode.drive.RobotHardware.skystoneDetectorService;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;

import java.nio.file.Path;

@Autonomous(group = "drive")

public class redSky extends AutonMethods {

    double[][] skyposArray = {{-1,37},{-10, 37.5},{-19,37.9},{-18.5,34.5},{-25.75,36},{-36,36}};

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
        robotStartup();
        cam1detectorstartup();
        sleep(2000);
        telemetry.addLine("Robot 好了");
        telemetry.update();
        waitForStart();
        PathChooser();

    }

    public void pos1(){
        telemetry.addLine("11");
        telemetry.update();
            MecanumDriveBasePID drive = new MecanumDriveBasePID(hardwareMap);

            drive.setPoseEstimate(new Pose2d(0, 0, 0));
            Trajectory trajectory1 = new TrajectoryBuilder(drive.getPoseEstimate(), false, DriveConstants.BASE_CONSTRAINTS)
                    .splineToConstantHeading(new Pose2d(skyposArray[0][0],skyposArray[0][1]))
                    .build();
            Trajectory trajectory2 = new TrajectoryBuilder(trajectory1.end(), false, DriveConstants.BASE_CONSTRAINTS)
                    .lineToConstantHeading(new Vector2d(-5, 30))
                    .build();
            Trajectory trajectory3 = new TrajectoryBuilder(trajectory2.end(), false, DriveConstants.BASE_CONSTRAINTS)
                    .lineToConstantHeading(new Vector2d(72, 36))
                    .build();
            Trajectory trajectory4 = new TrajectoryBuilder(trajectory3.end(), false, DriveConstants.BASE_CONSTRAINTS)
                    .lineToConstantHeading(new Vector2d(21, 33))
                    .build();
            Trajectory trajectory5 = new TrajectoryBuilder(trajectory4.end(), false, DriveConstants.BASE_CONSTRAINTS)
                    .lineToConstantHeading(new Vector2d(skyposArray[3][0],skyposArray[3][1]))
                    .build();
            Trajectory trajectory6 = new TrajectoryBuilder(trajectory5.end(), false, DriveConstants.BASE_CONSTRAINTS)
                    .lineToConstantHeading(new Vector2d(-26, 32))
                    .build();
            Trajectory trajectory7 = new TrajectoryBuilder(trajectory6.end(), false, DriveConstants.BASE_CONSTRAINTS)
                    .lineToConstantHeading(new Vector2d(82, 36))
                    .build();
            Trajectory trajectory8 = new TrajectoryBuilder(trajectory7.end(), false, DriveConstants.BASE_CONSTRAINTS)
                    .lineToConstantHeading(new Vector2d(30, 35))
                    .build();
            telemetry.addLine("ready");
            telemetry.update();
            waitForStart();

                Grabbers(0, grabMin, 0);
                SideArmGrip1(500, sarmopen);
                SideArm1(500, sarmdown);
                drive.followTrajectory(trajectory1);
                sleep(0);
                SideArmGrip1(500, sarmclose);
                SideArm1(500, sarmmid) ;
                drive.followTrajectory(trajectory2);
                sleep(0);
                drive.followTrajectory(trajectory3);
                sleep(0);
                SideArm1(500, sarmdown);
                SideArmGrip1(0, sarmopen);
                SideArm1(0, sarmup);
//                drive.followTrajectory(trajectory4);
//                sleep(0);
//                SideArm1(500, sarmdown);
//                drive.followTrajectory(trajectory5);
//                SideArmGrip1(500, sarmclose);
//                SideArm1(500, sarmmid);
//                drive.followTrajectory(trajectory6);
//                sleep(0);
//                drive.followTrajectory(trajectory7);
//                SideArm1(500, sarmdown);
//                SideArmGrip1(10, sarmopen);
//                SideArm1(0, sarmup);
//                sleep(0);
                drive.followTrajectory(trajectory8);
                stop();
            }

    public void pos2(){
        telemetry.addLine("22");
        telemetry.update();
        MecanumDriveBasePID drive = new MecanumDriveBasePID(hardwareMap);

        drive.setPoseEstimate(new Pose2d(0, 0, 0));
        Trajectory trajectory1 = new TrajectoryBuilder(drive.getPoseEstimate(), false, DriveConstants.BASE_CONSTRAINTS)
                .splineToConstantHeading(new Pose2d(skyposArray[1][0],skyposArray[1][1]))
                .build();
        Trajectory trajectory2 = new TrajectoryBuilder(trajectory1.end(), false, DriveConstants.BASE_CONSTRAINTS)
                .lineToConstantHeading(new Vector2d(-5, 30))
                .build();
        Trajectory trajectory3 = new TrajectoryBuilder(trajectory2.end(), false, DriveConstants.BASE_CONSTRAINTS)
                .lineToConstantHeading(new Vector2d(72, 38))
                .build();
        Trajectory trajectory4 = new TrajectoryBuilder(trajectory3.end(), false, DriveConstants.BASE_CONSTRAINTS)
                .lineToConstantHeading(new Vector2d(21, 33))
                .build();
        Trajectory trajectory5 = new TrajectoryBuilder(trajectory4.end(), false, DriveConstants.BASE_CONSTRAINTS)
                .lineToConstantHeading(new Vector2d(skyposArray[4][0],skyposArray[4][1]))
                .build();
        Trajectory trajectory6 = new TrajectoryBuilder(trajectory5.end(), false, DriveConstants.BASE_CONSTRAINTS)
                .lineToConstantHeading(new Vector2d(-26, 32))
                .build();
        Trajectory trajectory7 = new TrajectoryBuilder(trajectory6.end(), false, DriveConstants.BASE_CONSTRAINTS)
                .lineToConstantHeading(new Vector2d(82, 36))
                .build();
        Trajectory trajectory8 = new TrajectoryBuilder(trajectory7.end(), false, DriveConstants.BASE_CONSTRAINTS)
                .lineToConstantHeading(new Vector2d(30, 35))
                .build();
        telemetry.addLine("ready");
        telemetry.update();
        waitForStart();

        Grabbers(0, grabMin, 0);
        SideArmGrip1(500, sarmopen);
        SideArm1(500, sarmdown);
        drive.followTrajectory(trajectory1);
        sleep(0);
        SideArmGrip1(500, sarmclose);
        SideArm1(500, sarmmid) ;
        drive.followTrajectory(trajectory2);
        sleep(0);
        drive.followTrajectory(trajectory3);
        sleep(0);
        SideArm1(500, sarmdown);
        SideArmGrip1(0, sarmopen);
        SideArm1(0, sarmup);
//        drive.followTrajectory(trajectory4);
//        sleep(0);
//        SideArm1(500, sarmdown);
//        drive.followTrajectory(trajectory5);
//        SideArmGrip1(500, sarmclose);
//        SideArm1(500, sarmmid);
//        drive.followTrajectory(trajectory6);
//        sleep(0);
//        drive.followTrajectory(trajectory7);
//        SideArm1(500, sarmdown);
//        SideArmGrip1(10, sarmopen);
//        SideArm1(0, sarmup);
//        sleep(0);
        drive.followTrajectory(trajectory8);
        stop();
    }

    public void pos3(){
        telemetry.addLine("33");
        telemetry.update();
        MecanumDriveBasePID drive = new MecanumDriveBasePID(hardwareMap);

        drive.setPoseEstimate(new Pose2d(0, 0, 0));
        Trajectory trajectory1 = new TrajectoryBuilder(drive.getPoseEstimate(), false, DriveConstants.BASE_CONSTRAINTS)
                .splineToConstantHeading(new Pose2d(skyposArray[2][0],skyposArray[2][1]))
                .build();
        Trajectory trajectory2 = new TrajectoryBuilder(trajectory1.end(), false, DriveConstants.BASE_CONSTRAINTS)
                .lineToConstantHeading(new Vector2d(-5, 30))
                .build();
        Trajectory trajectory3 = new TrajectoryBuilder(trajectory2.end(), false, DriveConstants.BASE_CONSTRAINTS)
                .lineToConstantHeading(new Vector2d(72, 36))
                .build();
        Trajectory trajectory4 = new TrajectoryBuilder(trajectory3.end(), false, DriveConstants.BASE_CONSTRAINTS)
                .lineToConstantHeading(new Vector2d(21, 33))
                .build();
        Trajectory trajectory5 = new TrajectoryBuilder(trajectory4.end(), false, DriveConstants.BASE_CONSTRAINTS)
                .lineToConstantHeading(new Vector2d(skyposArray[5][0],skyposArray[5][1]))
                .build();
        Trajectory trajectory6 = new TrajectoryBuilder(trajectory5.end(), false, DriveConstants.BASE_CONSTRAINTS)
                .lineToConstantHeading(new Vector2d(-26, 32))
                .build();
        Trajectory trajectory7 = new TrajectoryBuilder(trajectory6.end(), false, DriveConstants.BASE_CONSTRAINTS)
                .lineToConstantHeading(new Vector2d(82, 36))
                .build();
        Trajectory trajectory8 = new TrajectoryBuilder(trajectory7.end(), false, DriveConstants.BASE_CONSTRAINTS)
                .lineToConstantHeading(new Vector2d(30, 35))
                .build();
        telemetry.addLine("ready");
        telemetry.update();
        waitForStart();

        Grabbers(0, grabMin, 0);
        SideArmGrip1(500, sarmopen);
        SideArm1(500, sarmdown);
        drive.followTrajectory(trajectory1);
        sleep(0);
        SideArmGrip1(500, sarmclose);
        SideArm1(500, sarmmid) ;
        drive.followTrajectory(trajectory2);
        sleep(0);
        drive.followTrajectory(trajectory3);
        sleep(0);
        SideArm1(500, sarmdown);
        SideArmGrip1(0, sarmopen);
        SideArm1(0, sarmup);
//        drive.followTrajectory(trajectory4);
//        sleep(0);
//        SideArm1(500, sarmdown);
//        drive.followTrajectory(trajectory5);
//        SideArmGrip1(500, sarmclose);
//        SideArm1(500, sarmmid);
//        drive.followTrajectory(trajectory6);
//        sleep(0);
//        drive.followTrajectory(trajectory7);
//        SideArm1(500, sarmdown);
//        SideArmGrip1(10, sarmopen);
//        SideArm1(0, sarmup);
//        sleep(0);
        drive.followTrajectory(trajectory8);
        stop();
    }

    public void PathChooser(){
        if (opModeIsActive() && findSkystonePosActive() == 1){
            pos3();
        } else if (opModeIsActive() && findSkystonePosActive() == 2){
            pos2();
        } else if (opModeIsActive() && findSkystonePosActive() == 3){
            pos1();
        }
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
    public int  sky1Pos(){

        int pos = 0;

        if (opModeIsActive() && findSkystonePosActive() == 1) {
            pos = 0;

        } else if (opModeIsActive() && findSkystonePosActive() == 2) {
            pos  = 1;

        } else if (opModeIsActive() && findSkystonePosActive() == 3) {
            pos = 2;

        }
        sleep(5000);
        return pos;

    }

    public int sky2Pos(){

        int pos = 1;

        if (opModeIsActive() && sky1Pos() == 0) {
            pos = 3;
            return pos;

        } else if (opModeIsActive() && sky2Pos() == 1) {
            pos  = 4;
            return pos;

        } else if (opModeIsActive() && sky2Pos() == 2) {
            pos = 5;
            return pos;
        }
        sleep(5000);
        return pos;
    }






}
