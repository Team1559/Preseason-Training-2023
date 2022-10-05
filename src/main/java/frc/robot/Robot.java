// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    private static final double SLOW_MODE_MOTOR_SPEED_RATIO = 0.25;
    private static final double MAX_MOTOR_SPEED             = 0.75;

    private DTXboxController controller;
    private TalonFX          motor1;

    private int autoStepCounter;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        this.controller = new DTXboxController(0);
        this.motor1 = new TalonFX(0);

        this.motor1.setNeutralMode(NeutralMode.Coast);
    }

    /**
     * This function is called every robot packet, no matter the mode. Use this
     * for items like diagnostics that you want ran during disabled, autonomous,
     * teleoperated and test.
     * <p>
     * This runs after the mode specific periodic functions, but before
     * LiveWindow and SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {}

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString line to get the auto name from the text box below the Gyro
     * <p>
     * You can add additional auto modes by adding additional comparisons to the
     * switch structure below with additional strings. If using the
     * SendableChooser make sure to add them to the chooser code above as well.
     */
    @Override
    public void autonomousInit() {
        this.motor1.set(TalonFXControlMode.PercentOutput, 0);
        this.autoStepCounter = 0;
    }

    /** This function is called periodically during autonomous. */
    @Override
    public void autonomousPeriodic() {
        // Fluctuating speed, period = 4 seconds
        double speed = 0.5 * Math.sqrt(Math.sin(this.autoStepCounter / 100D * Math.PI));
        this.motor1.set(TalonFXControlMode.PercentOutput, speed);
    }

    /** This function is called once when teleop is enabled. */
    @Override
    public void teleopInit() {}

    /** This function is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {
        //motor1.set(ControlMode.PercentOutput, .5);
        double yVal = controller.getLeftStickY();
        
            
            //motor1.set(TalonFXControlMode.PercentOutput,controller.getLeftStickY());
            if(controller.getLeftBumperPressed()){
                motor1.set(TalonFXControlMode.PercentOutput,yVal*.15);
            }
            else{
                motor1.set(TalonFXControlMode.PercentOutput,yVal);
            }
        
    }

    /** This function is called once when the robot is disabled. */
    @Override
    public void disabledInit() {}

    /** This function is called periodically when disabled. */
    @Override
    public void disabledPeriodic() {}

    /** This function is called once when test mode is enabled. */
    @Override
    public void testInit() {}

    /** This function is called periodically during test mode. */
    @Override
    public void testPeriodic() {}
}
