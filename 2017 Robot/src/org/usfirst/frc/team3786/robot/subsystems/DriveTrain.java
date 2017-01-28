package org.usfirst.frc.team3786.robot.subsystems;

import org.usfirst.frc.team3786.robot.commands.drive.Drive;
import org.usfirst.frc.team3786.robot.config.RobotConfig;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
	
	public static DriveTrain instance;
	
	public static DriveTrain getInstance() {
		if(instance == null)
			instance = new DriveTrain();
		return instance;
	}
	
	private CANTalon leftDriveMotor;
	private CANTalon rightDriveMotor;
	
	public DriveTrain() {
		leftDriveMotor = new CANTalon(RobotConfig.getInstance().getLeftDriveMotor());
		rightDriveMotor = new CANTalon(RobotConfig.getInstance().getRightDriveMotor());
	}
	
    
    
    public void setSpeed(double leftSpeed, double rightSpeed) {
    	leftDriveMotor.set(leftSpeed);
    	rightDriveMotor.set(rightSpeed);
    }
    
    public int getLeftEncoder() {
    	return leftDriveMotor.getEncPosition();
    }
    
    public int getLeftVelocity() {
    	return leftDriveMotor.getEncVelocity();
    }
    
    public void initDefaultCommand() {
    	setDefaultCommand(Drive.getInstance());  	
    }
}

