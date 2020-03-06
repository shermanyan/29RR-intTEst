package org.firstinspires.ftc.teamcode.drive.Trash.RR_Tuners;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.RobotHardware.MecanumDriveBasePID;

/*
 * This is a simple routine to test translational drive capabilities.
 */
@Config
@Disabled
@Autonomous(group = "drive")
public class StraightTest extends LinearOpMode {
    public static double DISTANCE = 48;

    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDriveBasePID drive = new MecanumDriveBasePID(hardwareMap);

        Trajectory trajectory = drive.trajectoryBuilder(new Pose2d())
                .forward(DISTANCE)
                .build();

        waitForStart();

        if (isStopRequested()) return;

        drive.followTrajectory(trajectory);

        sleep(5000);
    }
}
