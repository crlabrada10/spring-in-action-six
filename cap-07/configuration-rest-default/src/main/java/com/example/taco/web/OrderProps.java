/**
 * 
 */
package com.example.taco.web;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

/**
 * @author clabrada
 *
 */
@Component
@Data
@ConfigurationProperties(prefix = "taco.orders")
@Validated
public class OrderProps {
	
	@Min(value = 5, message = "El valor debe de ser mayor que 4.")
	@Max(value = 25, message = "El valor debe de ser menor que 26.")
	private int pageSize = 20;
}
