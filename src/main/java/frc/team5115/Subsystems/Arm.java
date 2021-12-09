package frc.team5115.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team5115.Robot.RobotContainer;
import edu.wpi.first.wpilibj.DigitalInput; 

import static frc.team5115.Constants.*;

public class Arm {
        TalonSRX Talon1;
        TalonSRX Talon2;
        double DCMOTORSPEED;
        DigitalInput ArmStopper1;
        DigitalInput ArmStopper2;
        int n;


    public Arm(){
        DCMOTORSPEED = 0.5;
        ArmStopper1 = new DigitalInput(0);
        ArmStopper2 = new DigitalInput(1);
        Talon2 = new TalonSRX(2);
        n = 0;
    }

    public void Master(){

        Open();
        Set();
    }

    public void Open(){
        if(n==0){
        Talon2.set(ControlMode.PercentOutput, DCMOTORSPEED);
        if(ArmStopper1.get()|| ArmStopper2.get()){
         Talon2.set(ControlMode.PercentOutput, 0);
         System.out.println("THE CODE WORKS");
        }
        n=1;
    }
    }

    public void Set(){
        if(n==1){
        Talon2.set(ControlMode.PercentOutput, -DCMOTORSPEED);
        if(ArmStopper1.get()|| ArmStopper2.get()){
            Talon2.set(ControlMode.PercentOutput, 0);
            System.out.println("I DID IT");
           }
        n=0;
        }
    }

}
