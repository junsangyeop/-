package com.image.mlcplanet.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ImageRecogController {
	
	private static String UPLOADED_FOLDER = "/home/miruware/temp/img/";
	
	@GetMapping("/imageRecog")
	public String welcome() {
		return "imageRecog";
	}

	@PostMapping("/fileUpload")
	public String fileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, Map<String, Object> model) {
		
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }
        
//        model.put("fileName", file.getOriginalFilename());
        redirectAttributes.addFlashAttribute("fileName", file.getOriginalFilename());
        
		return "redirect:resultRecog";
	}
	
	@GetMapping("/resultRecog")
	public String resultRecog(Map<String, Object> model) {
		
//		System.out.println("fileName : " + model.get("fileName"));
		
		String fileName = (String) model.get("fileName");
		
		String result = null;
		
		try{
			Socket s = new Socket("localhost",5000);

			OutputStream os = s.getOutputStream();
			InputStream is = s.getInputStream();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			bw.write(fileName);
			bw.flush();
			
			result = br.readLine();
//			System.out.println("[서버 메세지] " + result);
			
			s.close();
		}catch(UnknownHostException e){
			System.out.println("[경고] 서버를 찾을 수 없습니다.");
		}catch(IOException e){
			System.out.println("[경고] 사용되지 않는 PORT 번호 입니다.");
		}
		 
		model.put("resultRecog", result);
        
		return "result";
	}
	
}
