package frc.robot.subsystems.lift;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.ConfigMap;
import edu.wpi.first.wpilibj.Encoder;
/**
 * Configuration and basic commands for the shooter.
 */
public class LiftConfig extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  //Create Motor Controller Objects based on CAN IDs
    TalonSRX skywalker = new TalonSRX(ConfigMap.CAN_TALON_SKYWALKER_MOTOR);
    VictorSPX liftMotor = new VictorSPX(ConfigMap.CAN_VICTOR_LIFT_MOTOR);
    
    DoubleSolenoid brake = new DoubleSolenoid(ConfigMap.PCM_BRAKE_IN, ConfigMap.PCM_BRAKE_OUT);

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new LiftOperate());
  }

  public LiftConfig()
  {
  }

  public void runLift (double speed){
    liftMotor.set(ControlMode.PercentOutput, speed);
  }

  public void walkSky (double speed){
    skywalker.set(ControlMode.PercentOutput, speed);
  }

  public void brakeOn()
  {
    brake.set(Value.kForward);
  }

  public void brakeOff()
  {
    brake.set(Value.kReverse);
  }

  public boolean brakeEngaged(){
    return brake.get() == Value.kForward;
  }

  public void stop(){
    liftMotor.set(ControlMode.PercentOutput, 0);
  }

  public void log(){
    //SmartDashboard.putNumber("Intake Speed", shootEncoder.getVelocity());
  }


}
