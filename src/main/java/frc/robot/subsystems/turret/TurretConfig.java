package frc.robot.subsystems.turret;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.ConfigMap;
import edu.wpi.first.wpilibj.Encoder;

/**
 * Configuration and basic commands for the shooter.
 */
public class TurretConfig extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  //Create Motor Controller Objects based on CAN IDs
    TalonSRX turretMotor = new TalonSRX(ConfigMap.CAN_TALON_TURRET_MOTOR);

    Encoder turretEnc = new Encoder(ConfigMap.DIO_TURRET_A_ENCODER, ConfigMap.DIO_TURRET_B_ENCODER);
    
    
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new TurretOperate());
  }

  public TurretConfig()
  {
  }

  public int currEncode(){
    return turretEnc.get();
  }

  public void resetEncoder(){
    turretEnc.reset();
  }

  public void run(double speed){
    turretMotor.set(ControlMode.PercentOutput, speed);
  }
  public void turn(){
    turretMotor.set(ControlMode.PercentOutput, .5);
  }

  public void stop(){
    turretMotor.set(ControlMode.PercentOutput, 0);
  }

  public void log(){
    SmartDashboard.putNumber("Turret Encoder", currEncode());
    SmartDashboard.putNumber("Turret Motor Power", turretMotor.getMotorOutputPercent());
  }


}
