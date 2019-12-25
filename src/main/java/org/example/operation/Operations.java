package org.example.operation;

public class Operations {

    public double multiply(double[] numbers){
        double inefficient = 1;
        for (int i = 0; i < numbers.length ; i++) {

            inefficient *= numbers[i];
        }
        return inefficient;
    }

    public double divide(double[] numbers){
        double inefficient = 1;
        try {
            double temp = 0;

            for (int i = 0; i < numbers.length ; i++) {
                temp += numbers[i];
            }

            inefficient = temp / numbers[numbers.length - 1];

        }catch (ArithmeticException e){
            System.out.println("SIFIRA BÖLME HATASI!");
        }

        return inefficient;
    }

    public double add(double[] numbers){
        double inefficient = 0;
        for (int i = 0; i < numbers.length ; i++) {
            inefficient += numbers[i];
        }

        return inefficient;
    }

    public double subtract(double[] numbers){
        double inefficient = 0;
        for (int i = 0; i < numbers.length ; i++) {
            inefficient -= numbers[i];
        }

        return inefficient;
    }

    public double module(double[] numbers){
        double inefficient = 1;
        double temp = 0;

        for (int i = 0; i < numbers.length ; i++) {
            temp += numbers[i];
        }

        inefficient = temp % numbers[numbers.length - 1];

        return inefficient;
    }
}
