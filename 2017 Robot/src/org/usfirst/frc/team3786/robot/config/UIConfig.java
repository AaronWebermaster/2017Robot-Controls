package org.usfirst.frc.team3786.robot.config;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Abstract class to store all the controls.
 * @author Aaron Weber 2017
 *
 */
public abstract class UIConfig {
	private static UIConfig instance;
	
	public static UIConfig getInstance() {
		if(instance == null)
			instance = new ArcadeDrive();
		return instance;
	}
	
	
	
	public abstract Joystick getLeftStick();
	
	public abstract Joystick getRightStick();
	
	public abstract Joystick getXbox();
			
	public abstract double getLeftDrive();
	
	public abstract double getRightDrive();
			
	public abstract JoystickButton getServoMoveButton();
		
	public abstract JoystickButton getPegPositionButton();
	
	public abstract double getPegPosition();
	
	public abstract JoystickButton getGearArmTopButton();
	
	public abstract JoystickButton getGearArmBottomButton();
	
	public abstract JoystickButton getWinchUpButton();
	
	public abstract JoystickButton getWinchDownButton();
	
	public abstract JoystickButton getWinchDeployMoveButton();
	
	public abstract JoystickButton getWinchDeployEnableButton();
	
	public abstract JoystickButton getWinchDeployReverseButton();

		
	
}
