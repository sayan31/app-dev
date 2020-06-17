package com.sayan.fullstack.demospringbootangular.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author S
 * @RestController combines 2 annotations:
 * @Controller & @ResponseBody
 * @Controller - allows impl classes to be autodetected through classpath scanning.
 * @ResponseBody - placed on each request handling method to enable automatic 
 * serialization of the returned object into HttpResponse.
 */
@RestController

/**
 * @RequestMapping - is used to map incoming requests to correct controllers
 * The mapping can be done in various ways:
 * 1. by path value: put the exact path to the resource in the "value" attribute.
 * 2. specify the HTTP method type to use for the request in the "method" attribute.
 * 3. specify the produces and consumes attributes to restrict media types produced
 *    & consumed by a controller.
 */
@RequestMapping
public class ReservationResource {
	
}
