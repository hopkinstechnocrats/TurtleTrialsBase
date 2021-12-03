// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.HighShelfSubsystem;
import frc.robot.subsystems.MediumShelfSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.DriveToWall;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem driveSubsystem = new DriveSubsystem();
  private final HighShelfSubsystem highShelfSubsystem = new HighShelfSubsystem();
  private final MediumShelfSubsystem mediumShelfSubsystem = new MediumShelfSubsystem();

  private final XboxController driveController = new XboxController(Constants.XboxControllerPort);
  
  private final XboxController operatorController = new XboxController(1);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    driveSubsystem.setDefaultCommand(
            new RunCommand(
                    () -> {
                      driveSubsystem.drive(-0.55*driveController.getY(GenericHID.Hand.kLeft), -0.55*driveController.getY(GenericHID.Hand.kRight));
                    }
            , driveSubsystem)
    );
    highShelfSubsystem.setDefaultCommand(new RunCommand(() -> highShelfSubsystem.spin(0), highShelfSubsystem));
  
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton aButton = new JoystickButton(operatorController, 1);
    aButton.whileHeld(new RunCommand(
      () -> {
        mediumShelfSubsystem.spin(-0.15);
      }, 
      mediumShelfSubsystem));
      JoystickButton bButton = new JoystickButton(operatorController, 2);
      bButton.whileHeld(new RunCommand(
        () -> {
          mediumShelfSubsystem.spin(-0.3);
        }, 
        mediumShelfSubsystem));
        mediumShelfSubsystem.setDefaultCommand(new RunCommand(
          () -> {
            mediumShelfSubsystem.spin(0);
          }, 
          mediumShelfSubsystem));
          JoystickButton aDriverButton = new JoystickButton(driveController, 1);
    aDriverButton.whileHeld(new RunCommand(
      () -> {
        driveSubsystem.drive(-.5, -.5);
      }, 
      driveSubsystem));
      JoystickButton bDriverButton = new JoystickButton(driveController, 2
      );
    bDriverButton.whileHeld(new RunCommand(
      () -> {
        driveSubsystem.drive(0.6 , 0.7);

      }, 
      driveSubsystem));

  }

  

  public DriveSubsystem getDriveSubsystem() {
    return driveSubsystem;
  }
  
  
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new SequentialCommandGroup(
      new DriveToWall(driveSubsystem),
      new RunCommand(
        () -> {
          highShelfSubsystem.spin(-0.5);
        }
        , highShelfSubsystem).withTimeout(1)
    );
  }
}
