// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class MediumShelfSubsystem extends SubsystemBase {
  /** Creates a new HighShelfSubsystem. */
  WPI_TalonSRX motor;

  public MediumShelfSubsystem() {
    motor = new WPI_TalonSRX(Constants.mediumShelfMotorPort);
    motor.setNeutralMode(NeutralMode.Brake);
  }

  public void spin(double speed) {
    motor.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}