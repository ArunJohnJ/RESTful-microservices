package com.microservice.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
	// a)URI versioning:
	@GetMapping("/v1/name")
	public PersonV1 getNameURIV1() {
		return new PersonV1("Arun Johnson");
	}

	@GetMapping("v2/name")
	public PersonV2 getNameURIV2() {
		return new PersonV2(new Name("Arun", "Johnson"));
	}

	// b)Request parameter versioning
	@GetMapping(value = "/name", params = "v1")
	public PersonV1 getNameParamV1() {
		return new PersonV1("Arun Johnson");
	}

	@GetMapping(value = "/name", params = "v2") // we call using http://localhost:8080/name?v2
	public PersonV2 getNameParamV2() {
		return new PersonV2(new Name("Arun", "Johnson"));
	}

	// c)CONTENT Negotiation/Accept Header versioning:
	@GetMapping(value = "/name", produces = "application/v1+json") // in head pass 'Accept' as key and
																	// 'application/v1+json' as value
	public PersonV1 getNameAcceptHeaderV1() {
		return new PersonV1("Arun Johnson");
	}

	@GetMapping(value = "/name", produces = "application/v2+json")
	public PersonV2 getNameAcceptHeaderV2() {
		return new PersonV2(new Name("Arun", "Johnson"));
	}

	// d)HEADER versioning:
	@GetMapping(value = "/name", headers = "version=v1") // in header pass 'version' as key and 'v1' as value
	public PersonV1 getNameHeaderV1() {
		return new PersonV1("Arun Johnson");
	}

	@GetMapping(value = "/name", headers = "version=v2")
	public PersonV2 getNameHeaderV2() {
		return new PersonV2(new Name("Arun", "Johnson"));
	}

}
