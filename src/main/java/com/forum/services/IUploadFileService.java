package com.forum.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {

	public Resource load(String filename) throws MalformedURLException;

	public String copy(MultipartFile file) throws IOException;

	public boolean delete(String filename);

	public void deleteAll();

	public void init() throws IOException;

	public  String copy(MultipartFile file, int width, int height) throws IOException;

	File downloadImageFromUrl(String uri) throws Exception;

	String copy(File file, int width, int height) throws IOException;
}
