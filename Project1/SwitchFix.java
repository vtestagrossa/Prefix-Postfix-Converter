/*
Vincent Testagrossa
Project 1: Expression Converter
28May2020

Requirements: An input string consisting of either prefix or post-fix notation, separating each operand
with a space/one or more operators. 

This class tokenizes an input string as an arraylist of tokens (operands and operators only), then uses the tokenized
arraylist and a stack to convert between prefix and postfix notation, then return a formatted string. 
*/
package Project1;

import java.util.EmptyStackException;
import java.util.Stack;
import java.util.StringTokenizer;

public class SwitchFix{
    private String inputText = "", outputText = "", delimiters = " +-*/^";
    SwitchFix(String input){
        inputText = input;
    }
    public String getPostfix() throws SyntaxError{
        //Buffer for reading from the tokenized string and pushing to the reversal stack.
        String buffer = "", operand = "^[a-zA-Z0-9]*";
        //Define the stacks needed
        Stack<String> reversalStack = new Stack<String>();
        Stack<String> operandStack = new Stack<String>();
        //tokenize the input
        StringTokenizer tokenized = new StringTokenizer(inputText, delimiters, true);
        //Process the tokenized input
        try{
            while (tokenized.hasMoreTokens()){
                //while there are more tokens
                //clear the token buffer and assign it the next token
                buffer = "";
                buffer = tokenized.nextToken();
                if (!buffer.equals(" ")){
                    //Push the token onto a reversal stack if it is not space
                    reversalStack.push(buffer);
                }
            }
            while(!reversalStack.empty()){
                //While the reversal stack is not empty
                if(reversalStack.peek().matches(operand)){
                    //if it's an operand
                    //push it onto the operand stack
                    operandStack.push(reversalStack.pop());
                }
                else{
                    //else it is an operator
                    //pop two operands off of the operand stack
                    //create a string with the two operands followed by
                    buffer = operandStack.pop() + " " + operandStack.pop();
                    //the operator
                    buffer += " " + reversalStack.pop();
                    //push that string onto the operand stack
                    operandStack.push(buffer);
                }
            }
            //pop the postfix expression off the stack
            outputText = operandStack.pop();
        }
        catch (EmptyStackException ex){
            String message = "Error: Incorrect syntax." + 
            "\nMust be in prefix notation with spaces between consecutive operands.";
            throw new SyntaxError(message);
        }
       if (!operandStack.empty() || !reversalStack.empty()){
            String message = "Error: There appear to be too many operands." +
            "\nPlease use valid prefix notation with spaces between consecutive operands.";
            throw new SyntaxError(message);
        }
        return outputText;
    }
    public String getPrefix() throws SyntaxError{
        String tokBuffer = "", opBuffer="", operand = "^[a-zA-Z0-9]*";
        Stack <String> operandStack = new Stack<String>();
        //Tokenize the input
        StringTokenizer tokenized = new StringTokenizer(inputText, delimiters, true);
        //Get the next token
        try{
            while (tokenized.hasMoreTokens()){
                //clear the buffers then assign tokBuffer the next token.
                tokBuffer = "";
                opBuffer = "";
                tokBuffer = tokenized.nextToken();
                //if it is a space, skip it
                if (tokBuffer.equals(" ")){
                    continue;
                }
                //else if it is an operand
                else if (tokBuffer.matches(operand)){
                    //push it onto the operand stack
                    operandStack.push(tokBuffer);
                }
                //else it is an operator
                else{
                    //pop two operands off the operand stack
                    //create a string with the operator followed by
                    //two operands
                    opBuffer = " " + operandStack.pop(); //pop the second operand into opBuffer and prepend a space
                    opBuffer = " " + operandStack.pop() + opBuffer; //pop the first operand into the buffer, prepend a space, and append the second operand
                    tokBuffer += opBuffer; //append the operand buffer to the token buffer that contains the operator.
                    //push that string onto the operand stack
                    operandStack.push(tokBuffer);
                }
            }
            //Pop the prefix expression off the stack
            outputText = operandStack.pop();
        }
        catch (EmptyStackException ex){
            String message = "Error: Incorrect syntax." + 
            "\nMust be in postfix notation with spaces between consecutive operands.";
            throw new SyntaxError(message);
        }
        if (!operandStack.empty()){
            String message = "Error: There appear to be too many operands." +
            "\nPlease use valid postfix notation with spaces between consecutive operands.";
            throw new SyntaxError(message);
        }
        return outputText;
    }
}