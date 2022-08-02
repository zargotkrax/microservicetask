package microservice.calculator;



public class Math {

  public static double calculate(String calculation){
    String operators[]=calculation.split("[0-9]+");
    String operands[]=calculation.split("[-+*/]");
    Double agregate = Double.parseDouble(operands[0]);
    for(int i=1;i<operands.length;i++){
      System.out.println(operators[i]);
      switch(operators[i]){
        case "+":
          agregate += Double.parseDouble(operands[i]);
          break;
        case "-":
          agregate -= Double.parseDouble(operands[i]);
          break;
        case "*":
          agregate *= Double.parseDouble(operands[i]);
          break;
        case "/":
          agregate /= Double.parseDouble(operands[i]);
          break;
        default:
          break;
      }
    }
    return agregate;
  }
}
