// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.Map;

//TalonSRX
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
//Shuffleboard
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.networktables.GenericEntry;

public class DriveTrainSubsystem extends SubsystemBase {
  /** Creates a new DriveTrainSubsystem. */

  // Creating the Tab in Shuffleboard
  private final ShuffleboardTab tab = Shuffleboard.getTab("DriveBase");

  // Declaring the PowerDraw NetworkTable Entry
  private final GenericEntry PowerDraw;

  // Declaring the motor controler Member variable
  private TalonSRX leftMotor_1;
  private TalonSRX leftMotor_2;
  private TalonSRX rightMotor_1;
  private TalonSRX rightMotor_2;

  public DriveTrainSubsystem(int IDleftMotor_1, int IDleftMotor_2, int IDrightMotor_1, int IDrightMotor_2) {
    leftMotor_1 = new TalonSRX(IDleftMotor_1);
    leftMotor_2 = new TalonSRX(IDleftMotor_2);
    rightMotor_1 = new TalonSRX(IDrightMotor_1);
    rightMotor_2 = new TalonSRX(IDrightMotor_2);

    TalonSRXConfiguration config = new TalonSRXConfiguration();
    config.peakCurrentLimit = 15;
    config.peakCurrentDuration = 1500;
    config.continuousCurrentLimit = 10;

    leftMotor_1.configAllSettings(config);
    leftMotor_2.configAllSettings(config);
    rightMotor_1.configAllSettings(config);
    rightMotor_2.configAllSettings(config);

    leftMotor_1.setNeutralMode(NeutralMode.Coast);
    leftMotor_2.setNeutralMode(NeutralMode.Coast);
    rightMotor_1.setNeutralMode(NeutralMode.Coast);
    rightMotor_2.setNeutralMode(NeutralMode.Coast);

    leftMotor_1.setInverted(true);
    leftMotor_2.setInverted(true);

    leftMotor_2.follow(leftMotor_1);
    rightMotor_2.follow(rightMotor_1);

    // Shuffleboard setup
    PowerDraw = tab.add("Curent Power Draw of Full Drive Base", 0)
        .withPosition(0, 0)
        .withSize(3, 2)
        .withProperties(Map.of("min", 0, "max", 100))
        .withWidget(BuiltInWidgets.kGraph)
        .getEntry();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    PowerDraw.setDouble(leftMotor_1.getSupplyCurrent() + leftMotor_2.getSupplyCurrent()
        + rightMotor_1.getSupplyCurrent() + rightMotor_2.getSupplyCurrent());
  }

  public void drive(double leftPower, double rightPower) {
    leftMotor_1.set(TalonSRXControlMode.PercentOutput, leftPower);
    rightMotor_1.set(TalonSRXControlMode.PercentOutput, rightPower);
  }
}
