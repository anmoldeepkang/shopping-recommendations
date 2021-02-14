/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rbc.shopping.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rbc.shopping.service.RecommendationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

/**
 * @author Anmoldeep Singh Kang
 * This class contains the controller for returning the recommendations for User.
 */

@Api(description = "Endpoints for getting recommendations for items on sale.", tags = { "recommendations" })
@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

	@Autowired
	RecommendationService recommendationService;
	
	/**
	 * @param username
	 * @return A Map representing the list of products under hot deals, past orders & wish list
	 */
	@ApiOperation(value = "Get Item recommendations by username", notes = "Item recommendations by username", tags = {
			"recommendations" }, authorizations = { @Authorization(value = "basicAuth") })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation", response = Map.class),
			@ApiResponse(code = 403, message = "User is Forbidden.") })
	@GetMapping("/{username}")
	public ResponseEntity<Object> getRecommendations(
			@ApiParam("Username for the user whose recommendations need to be suggested.") @PathVariable String username) {
		return new ResponseEntity<Object>(recommendationService.getItemsOnSale(username), HttpStatus.OK);
	}
}
