/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ShooterOperate extends Command {
  public ShooterOperate() {
    requires(Robot.mShooterConfig);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    XboxController xbox = Robot.m_oi.getXboxController2();
    int dPad = xbox.getPOV();

    double spdFromDist = Robot.mShooterConfig.getSpeed();

    
    //DPAD UP
    if (dPad < 45 && dPad > 315){
      Robot.mShooterConfig.setSpeed(-5000); 
    }
    //DPAD RIGHT
    if (dPad < 135 && dPad > 45){
      Robot.mShooterConfig.setSpeed(-4200); 
    }
    //
    if (dPad < 240 && dPad > 135){
      Robot.mShooterConfig.stop(); 
    }

    if (dPad < 315 && dPad > 245){
      Robot.mShooterConfig.setSpeed(-3675); 
    }
    
  }  

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.mShooterConfig.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
