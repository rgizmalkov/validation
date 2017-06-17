package main.validation.example.main;

import main.validation.BuildInstruction;
import main.validation.ValidationTree;
import main.validation.example.ClassA;

/**
 * Created by romanizmalkov on 14.06.17.
 */
public class Main {
    public static void main(String[] args){
        ValidationTree validationTree = new ValidationTree(new ClassA(), new BuildInstruction[]{BuildInstruction.ADD_CIRCLING});
        System.out.println(validationTree);
    }
}
