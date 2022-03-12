package com.muscu.carnetMusculation.controllers;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.muscu.carnetMusculation.dto.MapperAPI;
import com.muscu.carnetMusculation.dto.SerieAPI;
import com.muscu.carnetMusculation.entities.Serie;
import com.muscu.carnetMusculation.services.impl.SerieServiceImpl;


@RestController
@CrossOrigin(origins = "*", allowedHeaders={"*"})
@RequestMapping("/series")
public class SerieController {

}
