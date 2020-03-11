package frc.robot.subsystems.limelight;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight extends Subsystem {

  @Override
  public void initDefaultCommand() {}

  public Limelight(){}

    //looks at 'tv'
  public boolean targetAcquired(){
    double target = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);

    if (target == 1){
      return true;
    }else{
      return false;
    }
  }

  public double crosshairX(){
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
  }

  public double crosshairY(){
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
  }

  public double targetArea(){
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
  }

  public boolean inScope(){
    double currXDiff = crosshairX();
    
    if(currXDiff < 3 && currXDiff > -3){
      return true;
    }else{
      return false;
    }
  }

  public boolean withinRange(){
    double goalHeight = crosshairY();
    double goalArea = targetArea();
    boolean withinHeight, withinArea;

    if(goalHeight < 100 && goalHeight > 50){
      withinHeight = true;
    }else{
      withinHeight = false;
    }

    if(goalArea < 5000 && goalArea > 1500){
      withinArea = true;
    }else{
      withinArea = false;
    }

    //TODO: Add support for tshort, tlong, thor, and tver?

    return withinArea && withinHeight;
  }

  public void LEDsControl(boolean state){
    int LEDs; //1 will force off, 3 forces on
    if(state){
      LEDs = 3;
    }else{
      LEDs = 1;
    }
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("<variablename>").setNumber(LEDs);
  }

  public void log(){
    
  }
    
    
     
  }