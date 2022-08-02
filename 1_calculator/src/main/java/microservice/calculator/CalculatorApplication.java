package microservice.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
@RestController
@Service
public class CalculatorApplication {

  public static void main(String[] args) {
    SpringApplication.run(CalculatorApplication.class, args);
  }

  private final AtomicLong counter = new AtomicLong();

  @GetMapping("/calculate")
  @ResponseBody
   public ResponseEntity<Answer> calculate(@RequestBody calc calcc,
      @RequestParam(value = "id", defaultValue = "") String id) {



    try {
      String calculation=calcc.getString();
      if (!calculation.equals(null) && !calculation.equals("")) {
        double result = calcc.doMath();
        long resultId = counter.incrementAndGet();
        Answer answer = new Answer(resultId, result /*,newCalc*/);
        return new ResponseEntity<>(answer, HttpStatus.OK);
      } else {
        String history = JsonUtil.getAll();
        Answer answer = new Answer(0, 0 /*,history*/);
        return new ResponseEntity<>(answer, HttpStatus.BAD_REQUEST);
      }
    }  catch (Exception ex) {
      System.out.print(ex);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
