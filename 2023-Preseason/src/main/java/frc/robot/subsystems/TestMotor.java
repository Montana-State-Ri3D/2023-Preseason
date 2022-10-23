// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//SparkMax
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

//Shuffleboard
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.networktables.NetworkTableEntry;

public class TestMotor extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  private CANSparkMax motor;
  private RelativeEncoder encoder;

  private static final int MAX_CURRENT = 5;

  private final ShuffleboardTab tab = Shuffleboard.getTab("Testing");
  private final NetworkTableEntry RPM;
  private final NetworkTableEntry PowerDraw;

  public TestMotor(int CANid) {
    motor = new CANSparkMax(CANid, MotorType.kBrushless);
    motor.setInverted(true);
    motor.setIdleMode(IdleMode.kCoast);
    motor.setSmartCurrentLimit(MAX_CURRENT);
    motor.set(0);

    encoder = motor.getEncoder();

    RPM = tab.add("Curent Motor RPM", 0)
        .withPosition(0, 0)
        .withSize(1, 1)
        .getEntry();
    PowerDraw = tab.add("Curent Motor Power Draw", 0)
        .withPosition(1, 0)
        .withSize(1, 1)
        .getEntry();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    RPM.setNumber(encoder.getVelocity());
    PowerDraw.setNumber(motor.getOutputCurrent());
  }

  public void setPower(double power) {
    motor.set(power);
  }
}
