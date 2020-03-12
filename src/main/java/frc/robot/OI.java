/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.subsystems.drivetrain.ShiftHigh;
import frc.robot.subsystems.drivetrain.ShiftLow;
import frc.robot.subsystems.indexor.DumpBalls;
import frc.robot.subsystems.lift.BrakeOff;
import frc.robot.subsystems.lift.BrakeOn;
import frc.robot.subsystems.accumulator.RaiseAccum;
import frc.robot.subsystems.accumulator.LowerAccum;

import frc.robot.Robot;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  public static final int A = 1;
	public static final int B = 2;
	public static final int X = 3;
	public static final int Y = 4;
	public static final int LB = 5;
	public static final int RB = 6;
	public static final int BACK = 7;
	public static final int START = 8;
	public static final int LEFT_STICK_BUTTON = 9;
	public static final int RIGHT_STICK_BUTTON = 10;


  public XboxController xbox1 = new XboxController(0);
  public XboxController xbox2 = new XboxController(1);

  Button btnDisengageBrake = new JoystickButton(xbox2, BACK);
  Button btnEngageBrake = new JoystickButton(xbox2, LEFT_STICK_BUTTON);
  Button btnShiftWheelsHigh = new JoystickButton(xbox1, RB);
  Button btnShiftWheelsLow = new JoystickButton(xbox1, LB);

  //Button btnRaiseAccum = new JoystickButton(xbox2, Y);
  //Button btnLowerAccum = new JoystickButton(xbox2, X);
  //Button btnDumpBalls = new JoystickButton(xbox2, RB);
  //SET SPEED ON LB?

  public OI()
  {
    btnDisengageBrake.whenPressed(new BrakeOff());
    btnEngageBrake.whenPressed(new BrakeOn());
    btnShiftWheelsHigh.whenPressed(new ShiftHigh());
    btnShiftWheelsLow.whenPressed(new ShiftLow());
    
    //btnRaiseAccum.whenPressed(new RaiseAccum());
    //btnLowerAccum.whenPressed(new LowerAccum());
    //btnDumpBalls.whileHeld(new DumpBalls());
  }
  /**
   * Get the Xbox Controller plugged into port 0
   * @return An instance of "Controller 1"
   */
  public XboxController getXboxController1() {
    return xbox1;
  }

  /**
   * Get the Xbox Controller Plugged into port 1
   * @return An instance of "Controller 2"
   */
  public XboxController getXboxController2() {
    return xbox2;
  }
}

