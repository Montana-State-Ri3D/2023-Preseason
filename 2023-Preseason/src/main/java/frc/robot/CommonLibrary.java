// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;

/** Add your docs here. */
public class CommonLibrary {
    public static double JoystickInput(double value, double power, double deadband, boolean clamp) {
        if (clamp) {
            value = MathUtil.clamp(value, -1.0, 1.0);
        }
        if (power != 0) {
            value = Math.copySign(Math.pow(value, power), value);
        }
        if (deadband != 0) {
            if (Math.abs(value) > deadband) {
                if (value > 0) {
                    value = (value - deadband) / (1.0 - deadband);
                } else {
                    value = (value + deadband) / (1.0 - deadband);
                }
            } else {
                value = 0;
            }
        }
        return value;
    }
    public static double JoystickInput(double value){
        return JoystickInput(value,2,0.02,true);
    }
}
