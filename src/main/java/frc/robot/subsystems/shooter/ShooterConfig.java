package frc.robot.subsystems.shooter;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.ConfigMap;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.Encoder;
/**
 * Configuration and basic commands for the shooter.
 */

public class ShooterConfig extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  //Create Motor Controller Objects based on CAN IDs
    CANSparkMax shootMotor = new CANSparkMax(ConfigMap.CAN_L_SHOOTER_MOTOR, MotorType.kBrushless);
    CANSparkMax followMotor = new CANSparkMax(ConfigMap.CAN_R_SHOOTER_MOTOR, MotorType.kBrushless);

    double kP = 5e-5; 
    double kI = 1e-6;
    double kD = 1e-4; 
    double kIz = 0; 
    double kFF = 0.000156; 
    double kMaxOutput = 1; 
    double kMinOutput = -1;
    //double maxRPM = 5700;
    // Smart Motion Coefficients
    double maxVel = 5701; // rpm
    double minVel = 3500;
    double maxAcc = 1500;


  //Encoder for tracking shooter wheel rotation speed
    CANEncoder shootEncoder = shootMotor.getEncoder();
    CANEncoder followEncoder = followMotor.getEncoder();

    CANPIDController m_pidController = shootMotor.getPIDController();
  
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ShooterOperate());
  }

  public ShooterConfig()
  {
    followMotor.follow(shootMotor, true);
  }

  public void run(double speed){
    shootMotor.set(-speed);
  }

  public void setSpeed(double rate){
    m_pidController.setP(kP);
    m_pidController.setI(kI);
    m_pidController.setD(kD);
    m_pidController.setIZone(kIz);
    m_pidController.setFF(kFF);
    m_pidController.setOutputRange(kMinOutput, kMaxOutput);

    m_pidController.setSmartMotionMaxVelocity(maxVel, 0);
    m_pidController.setSmartMotionMinOutputVelocity(minVel, 0);
    m_pidController.setSmartMotionMaxAccel(maxAcc, 0);
    m_pidController.setSmartMotionAllowedClosedLoopError(0.1, 0);

    m_pidController.setReference(rate, ControlType.kVelocity);
  }

  public double getDist(){
    double a2 = Robot.mLimelight.crosshairY();
    double h1, h2, a1;
    h1 = 33; //in
    h2 = 98; //in
    a1 = 34.5; //degrees

    a1 = Math.toRadians(a1);
    a2 = Math.toRadians(a2);

    return (h2 - h1)/(Math.tan(a1 + a2));
  }

  public double getSpeed(){
    double minDist = 85;
    double maxDist = 310;
    double minSpeed = -3675;
    double maxSpeed = -5700;

    double mappedSlope = -9.01;
    double mappedIntercept = -2907.565;

    return mappedSlope * getDist() + mappedIntercept;
  }
  
  public void stop(){
    shootMotor.stopMotor();
  }

  public void log(){
    SmartDashboard.putNumber("Shooter Angular Velocity", shootEncoder.getVelocity());
    SmartDashboard.putNumber("follower pwr", followMotor.getOutputCurrent());
    SmartDashboard.putNumber("master pwr", shootMotor.getOutputCurrent());
    SmartDashboard.putNumber("Dist from goal", getDist());
    SmartDashboard.putNumber("Intended Speed", getSpeed());
  }
}
