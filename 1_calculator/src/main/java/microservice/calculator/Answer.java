package microservice.calculator;

public class Answer {
  private long id;
  private double answer;

  public Answer(long id, double answer /*,String calculation*/) {
    this.id = id;
    this.answer = answer;
  }

  public long getId() {
    return id;
  }

  public double getAnswer() {
    return answer;
  }

}
