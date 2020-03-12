/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.accumulator;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RunIntake extends Command {
  public RunIntake() {
    requires(Robot.mAccumConfig);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double posSpeed, negSpeed;

    //Get Controller 1
    XboxController xbox = Robot.m_oi.getXboxController2();
    //Speed is based on the triggers. Left Trigger is reverse, Right Trigger is forward
    double rTrig = xbox.getTriggerAxis(Hand.kRight);
    double lTrig = xbox.getTriggerAxis(Hand.kLeft);
    boolean x = xbox.getXButton();
    boolean y = xbox.getYButton();
    double speed = 0;
    int AccumUp = 30;
    int AccumDown = 500;

    posSpeed = rTrig;
    negSpeed = -lTrig;
    if(rTrig > lTrig){
      Robot.mAccumConfig.runIntake(posSpeed);
    }else{
      Robot.mAccumConfig.runIntake(negSpeed);
    }

    if(y && Robot.mAccumConfig.accumEncoder() > AccumUp){
      //go up
      speed = -0.8;
    }else if(x && Robot.mAccumConfig.accumEncoder() < AccumDown){
      speed = 0.8;
    }else{
      speed = 0;
    }
    Robot.mAccumConfig.adjustAccum(speed);

    
  }  

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.mAccumConfig.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
