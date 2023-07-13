package com.knf.dev.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {

	@Autowired
	FileService fileService;

	@GetMapping("/")
	public String index(Model model) {
		List<String> list = new ArrayList<String>();
		List<String> fileList = fileService.listOfFiles();
		list.addAll(fileList);
		model.addAttribute("list", list);
		return "upload";
	}

	@PostMapping("/upload")
	public String singleFileUpload
	(@RequestParam("file") MultipartFile file, Model model) {

		if (file.isEmpty()) {
			model.addAttribute("warning",
					"Please select a file to upload");
			return "upload";
		}

		try {

			fileService.uploadFile(file);

		} catch (IOException e) {
			model.addAttribute("error", "Error");
			return "upload";
		}

		List<String> list = new ArrayList<String>();
		List<String> fileList = fileService.listOfFiles();
		list.addAll(fileList);
		model.addAttribute("list", list);
		return "upload";
	}

	@GetMapping(path = "/download/{name}")
	public ResponseEntity<Resource> download
	  (@PathVariable("name") String name) throws IOException {

		ByteArrayResource resource = fileService.downloadFile(name);

		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + name + "\"");

		return ResponseEntity.ok().
				contentType(MediaType.APPLICATION_OCTET_STREAM).
				headers(headers).body(resource);
	}

	@PostMapping(path = "/delete")
	public String delete(@RequestParam("name") String name)
			throws IOException {

		try {
			fileService.deleteFile(name);;
		}

		catch (Exception e) {
			return "redirect:/";
		}
		return "redirect:/";
	}

	private HttpHeaders headers(String name) {

		HttpHeaders header = new HttpHeaders();
		header.add(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=" + name);
		header.add("Cache-Control", "no-cache, no-store,"
				+ " must-revalidate");
		header.add("Pragma", "no-cache");
		header.add("Expires", "0");
		return header;

	}
}
