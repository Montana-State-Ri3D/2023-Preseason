// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    // Xbox Controllers Port Indexes
	public static final int DRIVE_CONTROLLER_PORT = 0;
	public static final int OPERATOR_CONTROLLER_PORT = 1;
	public static final int TEST_CONTROLLER_PORT = 2;

    public static final int PDP_CAN_ID = 0;

    // CAN IDs for our motor controllers
    public static final int TEST_MOTOR_CAN_ID = 1;

    // Drive Motors
    public static final int LEFT_FRONT_MOTOR = 4;
    public static final int LEFT_BACK_MOTOR = 5;
    public static final int RIGHT_FRONT_MOTOR = 6;
    public static final int RIGHT_BACK_MOTOR = 7;
    
}
