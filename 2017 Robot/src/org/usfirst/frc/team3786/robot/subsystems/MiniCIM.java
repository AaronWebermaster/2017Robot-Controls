package org.usfirst.frc.team3786.robot.subsystems;

import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class MiniCIM extends Subsystem {
	public static MiniCIM instance;
	public static MiniCIM getInstance() {
		if(instance == null)
			instance = new MiniCIM();
		return instance;
	}
	TalonSRX miniDriver = new TalonSRX(1);
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setSpeed(double speed) {
    	miniDriver.setSpeed(speed);
    }
}
