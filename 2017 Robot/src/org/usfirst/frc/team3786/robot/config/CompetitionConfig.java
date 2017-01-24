package org.usfirst.frc.team3786.robot.config;

public class CompetitionConfig extends RobotConfig {

	private static final int LEFT_DRIVE_MOTOR = 1;
	
	private static final int RIGHT_DRIVE_MOTOR = 8;
	
	
	@Override
	public int getLeftDriveMotor() {
		return LEFT_DRIVE_MOTOR;
	}


	@Override
	public int getRightDriveMotor() {
		return RIGHT_DRIVE_MOTOR;
	}

}
