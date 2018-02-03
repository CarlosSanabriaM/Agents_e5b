package uo.asw.agents.controller.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import uo.asw.agents.AgentsService;
import uo.asw.agents.controller.AgentController;
import uo.asw.agents.util.AgentMin;

import java.util.Map;

@RestController
public class AgentControllerImpl implements AgentController {

	@Autowired
	private AgentsService agentsService;

	@Override
	@RequestMapping(value = "/user", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<AgentMin> getCitzen(@RequestBody Map<String, Object> payload) {
		//if(!payload.keySet().containsAll(Arrays.asList("login", "password"))){
		//	return new ResponseEntity<CitizenMin>(HttpStatus.BAD_REQUEST);
		//}

		String login, password;
		login = (String) payload.get("login");
		password = (String) payload.get("password");
		AgentMin c = agentsService.getAgentInfo(login, password);
		if(c == null){
			return new ResponseEntity<AgentMin>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<AgentMin>(c, HttpStatus.OK);

	}
	
}