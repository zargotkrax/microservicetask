package microservice.calculator;

public class calc {
    private int operand1;
    private String operator;
    private int operand2;

    public calc(int operand1,String operator,int operand2){
        this.operand1=operand1;
        this.operator=operator;
        this.operand2=operand2;
    }

    public String getString(){
        System.out.println("getstring+:" +String.valueOf(operand1)+operator+String.valueOf(operand2));
        return String.valueOf(operand1)+operator+String.valueOf(operand2);
    }

    public Double doMath(){
        Double answer=0.0;
        switch(operator){
            case "+":
                answer=Double.valueOf(operand1 + operand2);
                break;
            case "-":
                answer=Double.valueOf(operand1 - operand2);
                break;
            case "*":
                answer=Double.valueOf(operand1 * operand2);
                break;
            case "/":
                answer=Double.valueOf(Double.valueOf(operand1) / Double.valueOf(operand2));
                break;
            default:
                break;
        }
        return answer;
    }

}
