package frc.robot.subsystems.navx;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.kauailabs.navx.frc.AHRS;

public class NavX extends Subsystem {

  AHRS navX = new AHRS();

  @Override
  public void initDefaultCommand() {}

  public NavX(){}

  public void calibrate(){
    navX.calibrate();
  }  

  public void reset(){
    navX.reset();
  }

  public double getAngle(){
      return navX.getAngle() % 360;
  }

  public void log(){
    SmartDashboard.putNumber("NavX Gyro", getAngle());
  }
    
    
     
  }