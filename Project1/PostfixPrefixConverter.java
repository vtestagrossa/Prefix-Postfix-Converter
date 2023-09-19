/*
Vincent Testagrossa
Project 1: Expression Converter
28May2020

Requirements: Needs ProjectFrame, SwitchFix, and SyntaxError to function.

Contains the main class that creates an instance of the ProjectFrame class.
*/
package Project1;

class PostfixPrefixConverter{
    public static void main(String args[]) {
        ProjectFrame frame = new ProjectFrame();
        frame.toFront();
    }
}