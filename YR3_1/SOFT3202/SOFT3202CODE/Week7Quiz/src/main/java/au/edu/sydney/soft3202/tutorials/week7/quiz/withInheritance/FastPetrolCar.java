package au.edu.sydney.soft3202.tutorials.week7.quiz.withInheritance;

public class FastPetrolCar extends FastPetrolVehicle {
    @Override
    public void drive() {
        System.out.println("Car is driving.");
        System.out.println(super.getSpeedOutput());
        System.out.println(super.getEngineOutput());
    }
}
