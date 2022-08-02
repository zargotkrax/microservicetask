package com.secondtask;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import javax.validation.constraints.NotBlank;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.view.RedirectView;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
public class FileUploadController {

	String key;

	@RequestMapping(value="json",method=RequestMethod.GET)
	@ResponseBody
	public RedirectView key(@RequestParam("key") String realkey){
		key=realkey;
		return new RedirectView("");
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public void upload(@RequestPart("file") @Valid @NotNull @NotBlank MultipartFile file) {
		printFileDetails(file);
		JSONParser jsonParser = new JSONParser();
		Path path= Paths.get("C:\\Users\\krax\\Desktop\\file-upload-ajax-sample-master\\src\\main\\java\\com\\gsswain\\file\\");
		saveFile(file,path);
		try (FileReader reader = new FileReader("C:\\Users\\krax\\Desktop\\file-upload-ajax-sample-master\\src\\main\\java\\com\\gsswain\\file\\employee.json"))
		{
			Object obj = jsonParser.parse(reader);
			JSONArray employeeList = (JSONArray) obj;
			System.out.println(employeeList);


			employeeList.forEach( emp -> parseEmployeeObject( (JSONObject) emp,key) );

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}



	private void printFileDetails(MultipartFile file) {
		System.out.println("Uploaded File: ");
		System.out.println("Name : " + file.getName());
		System.out.println("Type : " + file.getContentType());
		System.out.println("Name : " + file.getOriginalFilename());
		System.out.println("Size : " + file.getSize());

	}

	public void saveFile(MultipartFile multipartFile, Path path) {
		try {
			File directory=new File(path.toString());
			if(!directory.exists())
				directory.mkdirs();
			Files.write(path.resolve(multipartFile.getOriginalFilename()),
					multipartFile.getBytes());
		} catch (IOException e) {
			System.out.println("Error while storing file "+e);
		}
	}

	private static void parseEmployeeObject(JSONObject employee,String key)
	{

		List<? extends Comparable> object=new ArrayList<>();
		ArrayList<Object> objects =new ArrayList<>();
		Set<String> keys = employee.keySet();
		JSONArray jsonArray=new JSONArray();
		if(keys.iterator().next().equals(key)){

		}else{
			jsonArray = (JSONArray) employee.get(keys.iterator().next());
			Iterator<String> iteratorObj = jsonArray.iterator();
			while (iteratorObj.hasNext()) {
				objects.add(iteratorObj.next());
				objects.sort(null);
			}
		}

		for(int i=0;i<objects.size();i++){
			System.out.println(String.valueOf(objects.get(i)));
		}



	}
}
