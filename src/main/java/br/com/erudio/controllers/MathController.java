package br.com.erudio.controllers;

import br.com.erudio.exception.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    // http://localhost:8080/math/sum/3/5

    @RequestMapping("/sum/{NumberOne}/{NumberTwo}")
    public Double sum(
            @PathVariable("NumberOne") String NumberOne,
            @PathVariable("NumberTwo") String NumberTwo
    )throws Exception{
        if (!isNumeric(NumberOne) || !isNumeric(NumberTwo))
            throw new UnsupportedMathOperationException("please set a numeric value!");
        return convertToDouble(NumberOne) + convertToDouble(NumberTwo);
    }

    private Double convertToDouble(String strNumber)
            throws IllegalArgumentException {
        if (strNumber == null || strNumber.isEmpty())
            throw new UnsupportedMathOperationException("please set a numeric value!");
        String number = strNumber.replace(",",".");
        return Double.parseDouble(number);
    }

    private boolean isNumeric(String strNumber) {
        if (strNumber == null || strNumber.isEmpty()) return false;
        String number = strNumber.replace(",",".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
    //http://localhost:8080/math/subtraction/3/5
  //  http://localhost:8080/math/division/3/5
}
