/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.drivetrain;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.controller.PIDController;
import frc.robot.ConfigMap;
import frc.robot.subsystems.drivetrain.DriveWithController;;

/**
 * Pretty Simple. It is the Drivetrain.
 * Control the Wheels as well as the shifters
 */
public class DrivetrainConfig extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  //Create Motor Controller Objects based on CAN IDs
    CANSparkMax lfMotor = new CANSparkMax(ConfigMap.CAN_LF_DRIVE_MOTOR, MotorType.kBrushless);
    CANSparkMax lmMotor = new CANSparkMax(ConfigMap.CAN_LM_DRIVE_MOTOR, MotorType.kBrushless);
    CANSparkMax lbMotor = new CANSparkMax(ConfigMap.CAN_LB_DRIVE_MOTOR, MotorType.kBrushless);
    
    CANSparkMax rfMotor = new CANSparkMax(ConfigMap.CAN_RF_DRIVE_MOTOR, MotorType.kBrushless);
    CANSparkMax rmMotor = new CANSparkMax(ConfigMap.CAN_RM_DRIVE_MOTOR, MotorType.kBrushless);
    CANSparkMax rbMotor = new CANSparkMax(ConfigMap.CAN_RB_DRIVE_MOTOR, MotorType.kBrushless);

  
  //Group the Left and Right Motors together
    public SpeedControllerGroup leftWheels = new SpeedControllerGroup(lfMotor, lmMotor, lbMotor);
    public SpeedControllerGroup rightWheels = new SpeedControllerGroup(rfMotor, rmMotor, rbMotor);
  
  //Create Differential Drive Object allowing us to drive the robot
    DifferentialDrive dd = new DifferentialDrive(leftWheels, rightWheels);

  //Wheel Shifters
    DoubleSolenoid shifters = new DoubleSolenoid(ConfigMap.PCM_DRIVE_S_IN, ConfigMap.PCM_DRIVE_S_OUT);

  //Rotation Control
    static final double kP = 0.03;
    static final double kToleranceDegrees = 2.0f;    
    PIDController turnController;
    
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new DriveWithController());
  }

  public DrivetrainConfig()
  {
    dd.setSafetyEnabled(false);
  }
  /**
   * Drive Robot using Arcade Drive
   * @param speed Power for moving the robot robot -1 to 1
   * @param rotate Power for Rotating -1 to 1
   */
  public void run(double speed, double rotate) {
    dd.arcadeDrive(speed, -rotate);
  }

  public void rotateTo(double kTargetAngleDegrees){
    
  }

   /**
   * Shift to high gear
   */
  public void shiftHigh()
  {
    shifters.set(Value.kForward);
  }

  /**
   * Shift to low gear
   */
  public void shiftLow()
  {
    shifters.set(Value.kReverse);
  }

  /**
   * Stop the wheels on the robot
   */
  public void stop()
  {
    dd.stopMotor();
  }

  public boolean isHighGear()
  {
    return shifters.get() == Value.kForward;
  }
}
