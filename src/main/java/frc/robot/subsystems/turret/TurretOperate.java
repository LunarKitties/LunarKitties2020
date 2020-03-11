/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.turret;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TurretOperate extends Command {
  //Main control vars
    public static int maxCWPos = 236;
    public static int maxCCWPos = -270;
    public static int goalCWPos = 220;
    public static int goalCCWPos = -220;
    public static double minSpeed = 0.06;
    public static double kPGoal = 0.035;
    public static double kPEnc = 0.005;
    double currErr, goalEnc, currAngle, speed;
    boolean targetFound;
  
    public TurretOperate() {
    requires(Robot.mTurretConfig);
    requires(Robot.mNavX);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    XboxController xbox = Robot.m_oi.getXboxController2();
    double rStickX = xbox.getX(Hand.kRight);
    /*
    targetFound = Robot.mLimelight.targetAcquired();
    if(targetFound && Robot.mNavX.getAngle() < 55 && Robot.mNavX.getAngle() > -55){
      //use limelight
      currErr = Robot.mLimelight.crosshairX();
      if (currErr > 0.5){
        speed = -kPGoal*currErr - minSpeed;
      }else if (currErr < -0.5){
        speed = -kPGoal*currErr + minSpeed;
      }
      if(Robot.mTurretConfig.currEncode() >= goalCWPos && speed > 0){
        speed = 0;
      }else if (Robot.mTurretConfig.currEncode() <= goalCCWPos && speed < 0){
        speed = 0;          }
    }else{
      currErr = Robot.mTurretConfig.currEncode();
      if(currErr > 3){
        speed = kPEnc * currErr + minSpeed;
      }else if(currErr < -3){
        speed = kPEnc * currErr - minSpeed;
      }else{
        speed = 0;
      }
    }
    if (rStickX > 0.7){
        speed = -0.3;
    }else if (rStickX < -0.7){
        speed = 0.3;
    }
        */
    speed = rStickX;
    if (speed > 0.6){
      speed = 0.6;
    }else if (speed < -0.6){
      speed = -0.6;
    }
    Robot.mTurretConfig.run(speed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.mTurretConfig.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
