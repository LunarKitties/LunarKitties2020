/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drivetrain;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveWithController extends Command {
  public DriveWithController() {
    requires(Robot.mDrivetrainConfig);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    //Get Controller 1
    XboxController xbox = Robot.m_oi.getXboxController1();

    //Speed is based on the triggers. Left Trigger is reverse, Right Trigger is forward
    double lt = xbox.getTriggerAxis(Hand.kLeft);
    double rt = xbox.getTriggerAxis(Hand.kRight);
    double speed = rt - lt;

    //To Rotate you use the Right Joystick X Axis
    double rotate = xbox.getX(Hand.kRight);

    //Drive the Robot
    Robot.mDrivetrainConfig.run(-speed,rotate);
  }  

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.mDrivetrainConfig.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.mDrivetrainConfig.stop();
  }
}
