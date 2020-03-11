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

  public int 
    a = 1,
	b = 2,
	x = 3,
	y = 4,
	rb = 6,
	lb = 5,
	start = 8,
	back = 7,
	rt = 3,
	lt = 2,
	dpad_up = 0,
	dpad_right = 90,
	dpad_down = 180,
  dpad_left = 270;

  public XboxController xbox1 = new XboxController(0);
  public XboxController xbox2 = new XboxController(1);

  Button btnDisengageBrake = new JoystickButton(xbox2, rb);
  Button btnEngageBrake = new JoystickButton(xbox2, lb);
  Button btnShiftWheelsHigh = new JoystickButton(xbox1, rb);
  Button btnShiftWheelsLow = new JoystickButton(xbox1, lb);

  Button btnRaiseAccum = new JoystickButton(xbox2, y);
  Button btnLowerAccum = new JoystickButton(xbox2, x);
  Button btnDumpBalls = new JoystickButton(xbox2, rb);

  public OI()
  {
    btnDisengageBrake.whenPressed(new BrakeOff());
    btnEngageBrake.whenPressed(new BrakeOn());
    btnShiftWheelsHigh.whenPressed(new ShiftHigh());
    btnShiftWheelsLow.whenPressed(new ShiftLow());
    
    btnRaiseAccum.whenPressed(new RaiseAccum());
    btnLowerAccum.whenPressed(new LowerAccum());
    btnDumpBalls.whileHeld(new DumpBalls());
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

