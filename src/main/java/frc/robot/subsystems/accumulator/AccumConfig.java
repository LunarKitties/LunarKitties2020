package frc.robot.subsystems.accumulator;

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
public class AccumConfig extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  //Create Motor Controller Objects based on CAN IDs
    TalonSRX intakeMotor = new TalonSRX(ConfigMap.CAN_TALON_INTAKE_MOTOR);
    VictorSPX adjustMotor = new VictorSPX(ConfigMap.CAN_VICTOR_ADJUST_MOTOR);
    
    Encoder accumEnc = new Encoder(ConfigMap.DIO_ACCUM_A_ENCODER, ConfigMap.DIO_ACCUM_B_ENCODER);

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new RunIntake());
  }

  public AccumConfig()
  {
  }

  public void adjustAccum(final double speed) {
    adjustMotor.set(ControlMode.PercentOutput, speed);
  }

  public void runIntake(final double speed){
    intakeMotor.set(ControlMode.PercentOutput, -speed);
  }

  public void resetEncoder(){
    accumEnc.reset();
  }
  public int accumEncoder(){
        return accumEnc.get();
  }

  public void stop(){
    intakeMotor.set(ControlMode.PercentOutput, 0);
    adjustMotor.set(ControlMode.PercentOutput, 0);
  }

  public void log(){
    SmartDashboard.putNumber("Accum Raising Encoder", accumEncoder());
  }


}
