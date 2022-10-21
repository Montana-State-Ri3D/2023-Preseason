// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
//SparkMax
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

//Shuffleboard
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.networktables.NetworkTableEntry;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TestMotor extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */

  private CANSparkMax motor;
  private static final int MAX_CURRENT = 5;

  private final ShuffleboardTab tab = Shuffleboard.getTab("Testing");
  private final NetworkTableEntry RPM;

  public TestMotor(int CANid) {
    motor = new CANSparkMax(CANid, MotorType.kBrushless);
    motor.setInverted(true);
    motor.setIdleMode(IdleMode.kCoast);
    motor.setSmartCurrentLimit(MAX_CURRENT);
    motor.set(0);

    RPM = tab.add("Curent Motor RPM", 0)
      .withPosition(0, 0)
      .withSize(1, 1)
      .getEntry();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    RPM.setNumber(motor.getEncoder().getVelocity());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
  public void setPower(double power)
  {
    motor.set(power);
  }
}
