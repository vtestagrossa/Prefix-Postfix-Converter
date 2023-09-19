
/*
Vincent Testagrossa
Project 1: Expression Converter
28May2020

Requirements: None

Custom Exception class for SwitchFix. Thrown when either stack is empty, or stack is still full at the completion of getPrefix() or getPostfix().
*/
package Project1;

public class SyntaxError extends Exception {
    public SyntaxError(String errorMessage){
        super(errorMessage);
    }
}
