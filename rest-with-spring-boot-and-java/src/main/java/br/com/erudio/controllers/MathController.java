package br.com.erudio.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.erudio.helper.MathUtils;

import br.com.erudio.exceptions.UnsupportedMathOperationException;
import br.com.erudio.helper.Utils;

@RestController
public class MathController {

	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/sum/{numberOne}/{numberTwo}")
	public Double sum(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		
		if(!Utils.isNumeric(numberOne) || !Utils.isNumeric(numberTwo))
			throw new UnsupportedMathOperationException("Please set a numeric value");
		
		return MathUtils.sum(numberOne, numberTwo);

	}
	
	@GetMapping("/sub/{numberOne}/{numberTwo}")
	public Double sub(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		
		if(!Utils.isNumeric(numberOne) || !Utils.isNumeric(numberTwo))
			throw new UnsupportedMathOperationException("Please set a numeric value");
		
		return MathUtils.sub(numberOne, numberTwo);
		
	}
	
	@GetMapping("/mut/{numberOne}/{numberTwo}")
	public Double mut(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		
		if(!Utils.isNumeric(numberOne) || !Utils.isNumeric(numberTwo))
			throw new UnsupportedMathOperationException("Please set a numeric value");
		
		return MathUtils.mut(numberOne, numberTwo);
		
	}
	
	@GetMapping("/div/{numberOne}/{numberTwo}")
	public Double div(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		
		if(!Utils.isNumeric(numberOne) || !Utils.isNumeric(numberTwo))
			throw new UnsupportedMathOperationException("Please set a numeric value");
		
		return MathUtils.div(numberOne, numberTwo);
		
	}
	
	@GetMapping("/med/{numberOne}/{numberTwo}")
	public Double med(
			@PathVariable(value = "numberOne") String numberOne,
			@PathVariable(value = "numberTwo") String numberTwo) throws Exception {
		
		if(!Utils.isNumeric(numberOne) || !Utils.isNumeric(numberTwo))
			throw new UnsupportedMathOperationException("Please set a numeric value");
		
		return MathUtils.med(numberOne, numberTwo);
		
	}
	
	@GetMapping("/raz/{numberOne}")
	public Double raz(
			@PathVariable(value = "numberOne") String numberOne) throws Exception {
		
		if(!Utils.isNumeric(numberOne))
			throw new UnsupportedMathOperationException("Please set a numeric value");
		
		return MathUtils.raz(numberOne);
		
	}

	

}
