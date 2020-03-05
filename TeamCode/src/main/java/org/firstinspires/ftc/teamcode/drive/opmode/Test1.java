package org.firstinspires.ftc.teamcode.drive.opmode;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.drive.MecanumDrive;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.profile.MotionProfile;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.acmerobotics.roadrunner.trajectory.constraints.DriveConstraints;
import com.acmerobotics.roadrunner.util.Angle;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.robot.Robot;
import org.firstinspires.ftc.teamcode.drive.RR_Tuners.SampleTankDrive;
import org.firstinspires.ftc.teamcode.drive.RobotHardware.AutonMethods;
import org.firstinspires.ftc.teamcode.drive.RobotHardware.DriveConstants;

import org.firstinspires.ftc.teamcode.drive.RobotHardware.MecanumDriveBasePID;
@Config
@Autonomous(group = "drive")
public class Test1 extends AutonMethods {
    @Override
    public void runOpMode() throws InterruptedException {

        robotStartup();
        Platform(platformup);
        MecanumDriveBasePID drive = new MecanumDriveBasePID(hardwareMap);

        drive.setPoseEstimate( new Pose2d(0,0,0));
        Trajectory trajectory1 = new TrajectoryBuilder(drive.getPoseEstimate(), false, DriveConstants.BASE_CONSTRAINTS)
                .splineToConstantHeading(new Pose2d(-4,-34))
                .build();
        Trajectory trajectory2 = new TrajectoryBuilder(trajectory1.end(), false, DriveConstants.BASE_CONSTRAINTS)
                .splineToConstantHeading(new Pose2d(-5,-32))
                .build();
        Trajectory trajectory3 = new TrajectoryBuilder(trajectory2.end(), false, DriveConstants.BASE_CONSTRAINTS)
                .lineTo(new Vector2d(72,-32))
                .build();
        Trajectory trajectory4 = new TrajectoryBuilder(trajectory3.end(), false, DriveConstants.BASE_CONSTRAINTS)
                .splineToConstantHeading(new Pose2d(72,-40))
//                .back(48)
                .build();
        Trajectory trajectory5 = new TrajectoryBuilder(trajectory4.end(), false, DriveConstants.BASE_CONSTRAINTS)
                .splineToConstantHeading(new Pose2d(72,-25))
                .build();
        Trajectory trajectory6 = new TrajectoryBuilder(trajectory5.end(), false, DriveConstants.BASE_CONSTRAINTS)
                .lineTo(new Vector2d(72,-40))
                .build();
        Trajectory trajectory7 = new TrajectoryBuilder(trajectory6.end(), false, DriveConstants.BASE_CONSTRAINTS)
                .lineToConstantHeading(new Vector2d(72,-38))
                .build();
        Trajectory trajectory8 = new TrajectoryBuilder(trajectory7.end(), false, DriveConstants.BASE_CONSTRAINTS)
                .lineToConstantHeading(new Vector2d(72,-40))
                .build();
        waitForStart();
        if (isStopRequested()) return;
        while (opModeIsActive()) {

            drive.followTrajectory(trajectory1);
            sleep(1000);
            drive.followTrajectory(trajectory2);
            sleep(0);
            drive.followTrajectory(trajectory3);
            sleep(0);
            drive.followTrajectory(trajectory4);
            sleep(0);
            drive.followTrajectory(trajectory5);
            sleep(0);
            drive.turn(Math.toRadians(-90));
            LiftArm(1000,1);
            StopLiftArm();
            drive.followTrajectory(trajectory6);
            Platform(platformdown);
            drive.followTrajectory(trajectory7);
            sleep(0);
            drive.followTrajectory(trajectory8);
            sleep(1000);
            stop();
        }
    }
}
