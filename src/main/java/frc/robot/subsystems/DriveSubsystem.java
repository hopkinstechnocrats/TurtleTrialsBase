// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  TalonFX leftLeader;
  TalonFX leftFollower;
  TalonFX rightLeader;
  TalonFX rightFollower;
  DifferentialDrive drive;
  public DigitalInput limitSwitch;
  

  public DriveSubsystem() {
    leftLeader = new TalonFX(Constants.leftLeaderCANID);
    leftFollower = new TalonFX(Constants.leftFollowerCANID);
    rightLeader = new TalonFX(Constants.rightLeaderCANID);
    rightFollower = new TalonFX(Constants.rightFollowerCANID);
    leftLeader.getConfigurator().apply(new TalonFXConfiguration());
    leftFollower.getConfigurator().apply(new TalonFXConfiguration());
    rightLeader.getConfigurator().apply(new TalonFXConfiguration());
    rightFollower.getConfigurator().apply(new TalonFXConfiguration());
    leftLeader.setNeutralMode(NeutralModeValue.Brake);
    rightLeader.setNeutralMode(NeutralModeValue.Brake);
    leftFollower.setNeutralMode(NeutralModeValue.Brake);
    rightFollower.setNeutralMode(NeutralModeValue.Brake);

    drive = new DifferentialDrive(
      leftLeader,
      rightLeader
    );
    leftFollower.setControl(new Follower(leftLeader.getDeviceID(), false));
    rightFollower.setControl(new Follower(leftLeader.getDeviceID(), false));
    
    
  }

  public void drive(double left, double right) {
    drive.tankDrive(left, right);
    System.out.println("left: "+ left+ ", right: "+ right);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
