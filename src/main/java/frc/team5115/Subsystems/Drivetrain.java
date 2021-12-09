package frc.team5115.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.team5115.Robot.RobotContainer;

import static frc.team5115.Constants.*;

public class Drivetrain extends SubsystemBase{

    private TalonSRX frontLeft;
    private TalonSRX frontRight;
    private TalonSRX backLeft;
    private TalonSRX backRight;

    private double FrontrightSpd;
    private double FrontleftSpd;

    public Drivetrain(RobotContainer x) {
        frontLeft = new TalonSRX(FRONT_LEFT_MOTOR_ID);
        frontRight = new TalonSRX(FRONT_RIGHT_MOTOR_ID);
        backLeft = new TalonSRX(BACK_LEFT_MOTOR_ID);
        backRight = new TalonSRX(BACK_RIGHT_MOTOR_ID);

        frontLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        frontRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        backLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        backRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    }

        public void stop() {
        drive(0, 0, 0);
    }

    //@Override
    public void drive(double x, double y, double z) { //Change the drive output


        

       y = -y;

        fix(x,y);

        real(x,y);

        
        frontLeft.set(ControlMode.PercentOutput, (FrontleftSpd+z));
        frontRight.set(ControlMode.PercentOutput, -(FrontrightSpd+z));
        backLeft.set(ControlMode.PercentOutput, (FrontrightSpd-z));
        backRight.set(ControlMode.PercentOutput, -(FrontleftSpd-z));
        }
    


    public void autodrive(){
        System.out.println("STARTING AUTO DRIVE");
        frontLeft.set(ControlMode.PercentOutput, 0.5);
        frontRight.set(ControlMode.PercentOutput, 0.5);
        backLeft.set(ControlMode.PercentOutput, 0.5);
        backRight.set(ControlMode.PercentOutput, 0.5);
    }

        private void fix(double x, double y){
            while(Math.hypot(x,y)>1){
                if(x>y){
                    x = x-0.01;
                }
                else{
                    if(y>x){
                    y = y-0.01;
                    }
                    else{
                        x=x-0.01;
                        y=y-0.01;
                    }
                }
            }
        }

        private void normalize(double x, double y) {

        double d  = (Math.hypot(x, y)); //gets magnitude and sets it on a scale of 0,1

        x = x*d;

        y = y*d;
/*multiples the x on a scale of 0,1 by the length of the vector(again on a scale of 0,1) so that the x and the y result
         in the proper direction and relative speed */ 

    }


    
        private void real(double x, double y){
            double angle = Math.atan(Math.abs(y)/Math.abs(x));
            if(x>0 && y>0){
            FrontleftSpd = 1;
            angle = angle;
            }
            if(0>x && y>0){
                FrontrightSpd = 1;
            angle = Math.PI - angle;
            }
            if(0>x && 0>y){
                FrontrightSpd = -1;
                angle = Math.PI + angle;
                }
            if(x>0 && 0>y){
                FrontleftSpd = -1;
                angle = (2*Math.PI) - angle;
            }



        }
}

