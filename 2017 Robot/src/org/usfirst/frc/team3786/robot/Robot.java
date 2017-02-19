
package org.usfirst.frc.team3786.robot;

import org.usfirst.frc.team3786.robot.commands.auto.CrossBaseline;
import org.usfirst.frc.team3786.robot.commands.auto.DoNothing;
import org.usfirst.frc.team3786.robot.commands.climber.WinchMove;
import org.usfirst.frc.team3786.robot.commands.display.DisplayData;
import org.usfirst.frc.team3786.robot.commands.drive.Drive;
import org.usfirst.frc.team3786.robot.commands.grabber.GearArmBottomPosition;
import org.usfirst.frc.team3786.robot.commands.grabber.GearArmTopPosition;
import org.usfirst.frc.team3786.robot.commands.grabber.MoveGearArmPosition;
import org.usfirst.frc.team3786.robot.commands.grabber.ServoMove;
import org.usfirst.frc.team3786.robot.config.Camera;
import org.usfirst.frc.team3786.robot.config.UIConfig;
import org.usfirst.frc.team3786.robot.subsystems.BNO055;
import org.usfirst.frc.team3786.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3786.robot.subsystems.GearArm;
import org.usfirst.frc.team3786.robot.subsystems.Rangefinders;
import org.usfirst.frc.team3786.robot.vision.FinderOfRange;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public Camera camera;
	public static DisplayData displayData;
	private static BNO055 imu;
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		Drive.getInstance();
		UIConfig.getInstance().getServoMoveButton().whenPressed(ServoMove.getInstance());
		UIConfig.getInstance().getPegPositionButton().whenPressed(MoveGearArmPosition.getInstance());
		UIConfig.getInstance().getGearArmTopButton().whenPressed(GearArmTopPosition.getInstance());
		UIConfig.getInstance().getGearArmBottomButton().whenPressed(GearArmBottomPosition.getInstance());
		UIConfig.getInstance().getWinchDownButton().whileHeld(WinchMove.getDownInstance());
		UIConfig.getInstance().getWinchDownButton().whenReleased(WinchMove.getStopInstance());
		UIConfig.getInstance().getWinchUpButton().whileHeld(WinchMove.getUpInstance());
		UIConfig.getInstance().getWinchUpButton().whenReleased(WinchMove.getStopInstance());
		
		chooser.addDefault("Do Nothing", new DoNothing());
		chooser.addObject("Autonomous baseline crosser", new CrossBaseline());
		
		imu = BNO055.getInstance(BNO055.opmode_t.OPERATION_MODE_IMUPLUS,
			  BNO055.vector_type_t.VECTOR_EULER);
		
		//UsbCamera cam = CameraServer.getInstance().startAutomaticCapture();
		//cam.setResolution(1280, 720);
		//chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
		displayData = new DisplayData();
		SmartDashboard.putData("Display Data", displayData);
		//SmartDashboard.putBoolean("Connected", !Gyroscope.getInstance().isConnected());
		
		SmartDashboard.putData(Scheduler.getInstance());
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Left Encoder", DriveTrain.getInstance().getLeftEncoder());
		SmartDashboard.putNumber("Left Velocity", DriveTrain.getInstance().getLeftVelocity());
		SmartDashboard.putNumber("Left Voltage", DriveTrain.getInstance().getLeftMotorOutput());
		
		SmartDashboard.putNumber("Right Encoder", DriveTrain.getInstance().getRightEncoder());
		SmartDashboard.putNumber("Right Velocity", DriveTrain.getInstance().getRightVelocity());
		SmartDashboard.putNumber("Right Voltage", DriveTrain.getInstance().getRightMotorOutput());

	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		DriveTrain.getInstance().setSpeedDrive();
		
		

		
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Potentiometer", GearArm.getInstance().getPosition());
		//Camera.getInstance().pollCamera();
		SmartDashboard.putNumber("Gyro X", imu.getVector()[0]);
		SmartDashboard.putNumber("Gyro Y", imu.getVector()[1]);
		SmartDashboard.putNumber("Gyro Z", imu.getVector()[2]);
		
		SmartDashboard.putBoolean("Gyro Calibration", imu.isCalibrated());
		
		SmartDashboard.putNumber("Left Encoder", DriveTrain.getInstance().getLeftEncoder());
		SmartDashboard.putNumber("Left Velocity", DriveTrain.getInstance().getLeftVelocity());
		SmartDashboard.putNumber("Left Voltage", DriveTrain.getInstance().getLeftMotorOutput());
		
		SmartDashboard.putNumber("Right Encoder", DriveTrain.getInstance().getRightEncoder());
		SmartDashboard.putNumber("Right Velocity", DriveTrain.getInstance().getRightVelocity());
		SmartDashboard.putNumber("Right Voltage", DriveTrain.getInstance().getRightMotorOutput());

		
		
		SmartDashboard.putString("Drive Train Mode:", DriveTrain.getInstance().getDriveType());
		SmartDashboard.putBoolean("Servo is closed", GearArm.getInstance().getIsClosed());
		SmartDashboard.putString("Gear Arm Drive Mode", GearArm.getInstance().getMode());
		SmartDashboard.putNumber("Test Rangefinder Voltage", Rangefinders.getInstance().getTestVoltage());
		SmartDashboard.putNumber("Test Rangefinder Distance", FinderOfRange.rangeForVoltage(Rangefinders.getInstance().getTestVoltage()));
		SmartDashboard.putNumber("Window Motor Voltage", GearArm.getInstance().getVoltage());
		SmartDashboard.putBoolean("Top Limit", GearArm.getInstance().getTopLimitSwitch());
		SmartDashboard.putBoolean("Bottom Limit", GearArm.getInstance().getBottomLimitSwitch());
		
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
