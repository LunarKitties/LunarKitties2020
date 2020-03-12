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

  static double spacedEncDist = 3500;
  int ballCount;
  boolean currBall = false, moveBall = false;

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
    //Speed is based on the triggers. Left Trigger is reverse, Right Trigger is forward
    boolean a = xbox.getAButton();
    boolean b = xbox.getBButton();

    boolean ballHere = Robot.mIndexConfig.ballHere();
    boolean colorSeesBall = Robot.mIndexConfig.colorSeesBall();
    currEncDist = Math.abs(Robot.mIndexConfig.indexEncoder());

    
    if(colorSeesBall){ //
      if(!currBall){
        currBall = true;
      }
    }
    if (currBall && !colorSeesBall){
      targetEncDist = currEncDist + spacedEncDist;
      moveBall = true;
      currBall = false;
    }
    if(moveBall){
      if(currEncDist < targetEncDist){
        //run the intake belts
        Robot.mIndexConfig.runBelts(-0.8);
      }else{
        moveBall = false;
      }
    }else{
      Robot.mIndexConfig.stop();
    }
    
    if(a){
      Robot.mIndexConfig.dumpBalls();
    }else if(b){
      Robot.mIndexConfig.unShoot();
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
    Robot.mIndexConfig.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
