// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.Map;

//SparkMax
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
//Shuffleboard
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.networktables.GenericEntry;

public class TestMotorSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  private CANSparkMax motor;

  private SparkMaxPIDController pidController;

  private RelativeEncoder encoder;

  private static final int MAX_CURRENT = 5;

  private final ShuffleboardTab tab = Shuffleboard.getTab("PID Tuning");

  private final GenericEntry  RPM;
  private final GenericEntry  PowerDraw;
  private final GenericEntry  AppliedPower;

  private GenericEntry  SetPoint, kP, kI, kD, kIz, kFF;

  public double kMaxOutput, kMinOutput, maxRPM, maxVel, minVel, maxAcc, allowedErr;

  public TestMotorSubsystem(int CANid) {
    motor = new CANSparkMax(CANid, MotorType.kBrushless);
    motor.setInverted(true);
    motor.setIdleMode(IdleMode.kCoast);
    motor.setSmartCurrentLimit(MAX_CURRENT);

    pidController = motor.getPIDController();
    encoder = motor.getEncoder();

    kMaxOutput = 1;
    kMinOutput = -1;
    maxRPM = 5700;
    maxVel = 2000;
    maxAcc = 1500;

    RPM = tab.add("Motor RPM", 0)
        .withPosition(0, 0)
        .withSize(3, 3)
        .withWidget(BuiltInWidgets.kGraph)
        .withProperties(Map.of("min", 0, "max", 5800))
        .getEntry();

    PowerDraw = tab.add("Power Draw", 0)
        .withPosition(0, 3)
        .withSize(3, 3)
        .withWidget(BuiltInWidgets.kGraph)
        .withProperties(Map.of("min", 0, "max", 40))
        .getEntry();

    AppliedPower = tab.add("Applied Power", 0)
        .withPosition(3, 3)
        .withSize(3, 3)
        .withWidget(BuiltInWidgets.kGraph)
        .withProperties(Map.of("min", -1, "max", 1))
        .getEntry();

    SetPoint = tab.add("Set Point", 1000)
        .withPosition(3, 0)
        .withSize(1, 1)
        .getEntry();

    kP = tab.add("kP", 0.0001)
        .withPosition(3, 1)
        .withSize(1, 1)
        .getEntry();

    kI = tab.add("kI", 0.000001)
        .withPosition(4, 1)
        .withSize(1, 1)
        .getEntry();

    kD = tab.add("kD", 0)
        .withPosition(5, 1)
        .withSize(1, 1)
        .getEntry();

    kIz = tab.add("kIz", 0)
        .withPosition(4, 0)
        .withSize(1, 1)
        .getEntry();

    kFF = tab.add("kFF", 0)
        .withPosition(5, 0)
        .withSize(1, 1)
        .getEntry();

    pidController.setP(kP.getDouble(0));
    pidController.setI(kI.getDouble(0));
    pidController.setD(kD.getDouble(0));
    pidController.setIZone(kIz.getDouble(0));
    pidController.setFF(kFF.getDouble(0));
    pidController.setOutputRange(kMinOutput, kMaxOutput);

    motor.setSmartCurrentLimit(15);

    int smartMotionSlot = 0;
    pidController.setSmartMotionMaxVelocity(maxVel, smartMotionSlot);
    pidController.setSmartMotionMinOutputVelocity(minVel, smartMotionSlot);
    pidController.setSmartMotionMaxAccel(maxAcc, smartMotionSlot);
    pidController.setSmartMotionAllowedClosedLoopError(allowedErr, smartMotionSlot);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    RPM.setDouble(encoder.getVelocity());
    PowerDraw.setDouble(motor.getOutputCurrent());
    AppliedPower.setDouble(motor.getAppliedOutput());

  }

  public void updatePID() {
    pidController.setReference(SetPoint.getDouble(0), CANSparkMax.ControlType.kVelocity);

    pidController.setP(kP.getDouble(0));
    pidController.setI(kI.getDouble(0));
    pidController.setD(kD.getDouble(0));
    pidController.setIZone(kIz.getDouble(0));
    pidController.setFF(kFF.getDouble(0));
  }
  public void setPower(double speed){
    motor.set(speed);
  }
}
