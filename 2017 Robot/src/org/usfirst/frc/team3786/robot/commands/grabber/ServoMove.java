package org.usfirst.frc.team3786.robot.commands.grabber;

import org.usfirst.frc.team3786.robot.subsystems.GearArm;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ServoMove extends Command {
	public static ServoMove instance;
	
	public static ServoMove getInstance() {
		if(instance == null)
			instance = new ServoMove();
		return instance;
	}

	
    public ServoMove() {
		requires(GearArm.getInstance());
		setTimeout(.01);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(GearArm.getInstance().getIsClosed())
    		GearArm.getInstance().open();
    	else
    		GearArm.getInstance().close();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
