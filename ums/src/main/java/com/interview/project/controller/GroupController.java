package com.interview.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.interview.project.dao.GroupMapper;
import com.interview.project.model.Group;

@RestController
public class GroupController {
	@Autowired
	private GroupMapper groupMapper;

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(value = "api/groups")
	public List<Group> getGroups() {
		return groupMapper.findAllGroups();
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "api/addGroup")
	public boolean addGroup(@RequestBody Group group) {
		try {
			groupMapper.insertGroup(group);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "api/deleteGroup/{id}", method=RequestMethod.GET)
	public boolean deleteGroup(@PathVariable("id") int id) {
		try {
			groupMapper.deleteGroup(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
