/*
 * Copyright 2012 the original author or authors. 
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
package com.forum.utils; 
 
import static org.imgscalr.Scalr.OP_ANTIALIAS;
import static org.imgscalr.Scalr.OP_BRIGHTER;
import static org.imgscalr.Scalr.pad;
import static org.imgscalr.Scalr.resize;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile; 
 
/**
 * @author Carlo Micieli
 */ 
@Component
public class ImageUtils {
	
	
    public static byte[] createThumbnail(MultipartFile file, int targetSize) throws IOException { 
        validateFile(file); 
 
        final BufferedImage image = convertToImage(file); 
        final BufferedImage thumb = pad( 
                resize(image, Scalr.Method.SPEED, targetSize, OP_ANTIALIAS, OP_BRIGHTER), 2); 
 
        return convertToArray(thumb, file.getContentType()); 
    }
    
    
    public static InputStream createResize(MultipartFile file, int targetWidth, int targetHeight) throws IOException { 
        validateFile(file); 
        final BufferedImage image = convertToImage(file); 
        //final BufferedImage finalImage = resize(image, targetWidth, targetHeight);
        final BufferedImage finalImage = ImageResizer.resize(image, targetWidth, targetHeight);
        return convertToInputStream(finalImage, file.getContentType());
         
    }
    
    public static InputStream createResize(File file, int targetWidth, int targetHeight) throws IOException { 
        //validateFile(file);
    	
        final BufferedImage image = convertToImage(file); 
        //final BufferedImage finalImage = resize(image, targetWidth, targetHeight);
        final BufferedImage finalImage = ImageResizer.resize(image, targetWidth, targetHeight);
        return convertToInputStream(finalImage, "jpg");
         
    }
    
 
    public static byte[] convert(MultipartFile file) throws IOException { 
        validateFile(file);
        return file.getBytes(); 
    } 
 
    private static void validateFile(MultipartFile file) throws IOException { 
        String contentType = file.getContentType(); 
        if (!contentType.equals(MediaType.IMAGE_JPEG.toString()) && !contentType.equals(MediaType.IMAGE_PNG.toString())) 
            throw new IOException("Invalid media type"); 
    } 
 
    private static BufferedImage convertToImage(MultipartFile file) throws IOException { 
        InputStream in = new ByteArrayInputStream(file.getBytes()); 
        return ImageIO.read(in); 
    }
    
    private static BufferedImage convertToImage(File file) throws IOException {
    	
        //InputStream in = new ByteArrayInputStream(file.getBytes()); 
        return ImageIO.read(file); 
    }
 
    private static byte[] convertToArray(BufferedImage image, String contentType) throws IOException { 
        byte[] imageInByte; 
 
        String typeName = "jpg"; 
        if (contentType.equals(MediaType.IMAGE_PNG_VALUE)) 
            typeName = "png"; 
 
        ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
        ImageIO.write(image, typeName, baos); 
        baos.flush(); 
        imageInByte = baos.toByteArray(); 
        baos.close(); 
 
        return imageInByte; 
    } 
    
    private static InputStream convertToInputStream(BufferedImage image, String contentType) throws IOException { 
    	InputStream inStream; 
 
        String typeName = "jpg"; 
        if (contentType.equals(MediaType.IMAGE_PNG_VALUE)) 
            typeName = "png"; 
 
        ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
        ImageIO.write(image, typeName, baos); 
        baos.flush(); 
        inStream = new ByteArrayInputStream(baos.toByteArray());
        baos.close(); 
        
        return inStream; 
    }
    
    
    
    
    
    
}