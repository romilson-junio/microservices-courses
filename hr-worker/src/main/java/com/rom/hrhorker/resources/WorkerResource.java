package com.rom.hrhorker.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rom.hrhorker.entities.Worker;
import com.rom.hrhorker.repositories.WorkerRepository;

@RefreshScope
@RestController
@RequestMapping(value = "/workers")
public class WorkerResource {
	
	private static Logger logger = LoggerFactory.getLogger(WorkerResource.class);
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private WorkerRepository workerRepository;

	
	@GetMapping(value = "/configs")
	public ResponseEntity<Void> configs(){
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@GetMapping
	public ResponseEntity<List<Worker>> listAll(){
		List<Worker> list = workerRepository.findAll();
		return ResponseEntity.ok(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Worker> findById(@PathVariable Long id){
		logger.info("Port = " + environment.getProperty("local.server.port"));
		Worker worker = workerRepository.findById(id).get();
		return ResponseEntity.ok(worker);
	}
	
}
