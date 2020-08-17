/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
//blake was here

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/*
import frc.robot.subsystems.Accumulator;
import frc.robot.subsystems.CameraHandler;

import frc.robot.subsystems.FloorJack;
import frc.robot.subsystems.HABClimber;

import frc.robot.subsystems.Lift;
import frc.robot.subsystems.Tilt;
*/
import frc.robot.subsystems.drivetrain.DrivetrainConfig;
import frc.robot.subsystems.limelight.Limelight;
import frc.robot.subsystems.shooter.ShooterConfig;
import frc.robot.subsystems.accumulator.AccumConfig;
import frc.robot.subsystems.turret.TurretConfig;
import frc.robot.subsystems.lift.LiftConfig;
import frc.robot.subsystems.indexor.IndexConfig;
import frc.robot.subsystems.LEDs.LEDsConfig;
import frc.robot.subsystems.navx.NavX;
import frc.robot.OI;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  
  public static OI m_oi;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  private final Timer m_timer = new Timer();

  /**
   * Subsystems
   */
  public static ShooterConfig mShooterConfig;
  public static DrivetrainConfig mDrivetrainConfig;
  public static LEDsConfig mLeds;
  public static AccumConfig mAccumConfig;
  public static Limelight mLimelight;
  public static TurretConfig mTurretConfig;
  public static IndexConfig mIndexConfig;
  public static LiftConfig mLiftConfig;
  public static NavX mNavX;
  /*
  public static Accumulator mAccumulator;
  public static CameraHandler mCameraHandler;
  
  public static HABClimber mClimber;
  public static FloorJack mFloorJack;
  public static Lift mLift;
  
  public static Tilt mTilt;
	Compressor c;

  public static boolean processingCargo = false;
  */

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {

    //Initialize Subsystems
    /*
    
    mAccumulator = new Accumulator();
    mCameraHandler = new CameraHandler();
    
    mFloorJack = new FloorJack();
    mClimber = new HABClimber();
    mLift = new Lift();
    mTilt = new Tilt();
    */
    mAccumConfig = new AccumConfig();
    mShooterConfig = new ShooterConfig();
    mDrivetrainConfig = new DrivetrainConfig();
    mLeds = new LEDsConfig();
    mLimelight = new Limelight();
    mTurretConfig = new TurretConfig();
    mIndexConfig = new IndexConfig();
    mLiftConfig = new LiftConfig();
    mNavX = new NavX();
    //Make sure OI is last
    m_oi = new OI();
   
    /*
    c = new Compressor(0);
    c.setClosedLoopControl(true);

    processingCargo = false;
    */
    // mHatchManipulator.releaseHatch();
    // mHatchManipulator.retract();
    
    /*
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setBrightness(50);
    camera.setVideoMode(VideoMode.PixelFormat.kMJPEG, 320, 240, 15);
    mCameraHandler.setCameraPosition(ConfigMap.BOTTOM_ANGLE);
    */
    mTurretConfig.resetEncoder();
    mAccumConfig.resetEncoder();
    mNavX.calibrate();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
   // m_autonomousCommand = m_chooser.getSelected();
    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // // schedule the autonomous command (example)
    // if (m_autonomousCommand != null) {
    //   m_autonomousCommand.start();
    // }

    // mHatchManipulator.clampHatch();
    //Robot.mTilt.latch();
    m_timer.reset();
    /*
    mTurretConfig.resetGyro();
    mTurretConfig.resetEncoder();
    mAccumConfig.resetEncoder();
    */
    m_timer.start();
    teleopInit();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    // if (m_autonomousCommand != null) {
    //   m_autonomousCommand.cancel();
    // }
    /*
    mClimber.pullStepOneIn();
    mClimber.pullStepTwoIn();
    */
    mTurretConfig.resetEncoder();
    mAccumConfig.resetEncoder();
    mNavX.calibrate();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

    /*
    mAccumulator.log();
    // mHatchManipulator.log();
    mTilt.log();
     mFloorJack.log();
    //mLift.log();
    //mClimber.log();
    
    mLeds.setColor(LEDS.RED);

    */
    mShooterConfig.log();
    mTurretConfig.log();
    mIndexConfig.log();
    mAccumConfig.log();
    mNavX.log();

    if (mLiftConfig.brakeEngaged()){
      mLeds.setColor(LEDsConfig.RED);
    } else {
      mLeds.setColor(LEDsConfig.RAINBOW);
    }

    if (mDrivetrainConfig.isHighGear()){
      mLeds.setColor(LEDsConfig.RAINBOW);
    } else {
      mLeds.setColor(LEDsConfig.RAINBOW);
    }
    
    if (mDrivetrainConfig.isLowGear()){
      mLeds.setColor(LEDsConfig.GOLD_PATTERN);
    } else {
      mLeds.setColor(LEDsConfig.RAINBOW);
    }

  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    // mHatchManipulator.releaseHatch();
    //SmartDashboard.putData(mCameraHandler);
    
  }
}
