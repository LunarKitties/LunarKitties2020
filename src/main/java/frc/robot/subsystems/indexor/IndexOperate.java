/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.indexor;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class IndexOperate extends Command {

  double currEncDist, targetEncDist;

  static double spacedEncDist = 3475;
  boolean currBall = false;

  public IndexOperate() {
    requires(Robot.mIndexConfig);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    XboxController xbox = Robot.m_oi.getXboxController2();

    boolean a = xbox.getAButtonPressed();
    boolean b = xbox.getXButtonPressed();
    boolean ballHere = Robot.mIndexConfig.ballHere();
    currEncDist = Math.abs(Robot.mIndexConfig.indexEncoder());

    if(ballHere && !currBall){ //
      targetEncDist = currEncDist + spacedEncDist;
      currBall = true;
    }

    if (currBall){
      if(currEncDist < targetEncDist){
        //run the intake belts
        Robot.mIndexConfig.runBelts();
      }else{
        currBall = false;
      }
    }else{
      Robot.mIndexConfig.stop();
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
