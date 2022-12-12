package com.controller;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.model.Photo;

@Controller
@RequestMapping("/image/upload.do")
public class ImageController {

   @GetMapping
   public String form() {
      return "image/image";
   }

   @PostMapping
   public String submit(Photo photo, HttpServletRequest request) {

      /*
      1. Photo DTO 타입으로 데이터 받기
         1.1. 자동화 > name 속성값이 Photo 클래스의 member field명과 동일해야 한다. *** 
      2. public String submit(Photo photo) 내부적으로 코딩할 필요 없이 자동적으로 주입
         >> Photo photo = new Photo();
         >> photo.setName("홍길동"); 
         >> photo.setAge(20);
         ***** photo.setImage() 자동 주입 XXX 유일하게 수동 작업
         * CommonsMultipartFile에서 이름 추출
         >> photo.setFile(CommonsMultipartFile file) 파일이 자동으로 들어감
      */
      
      System.out.println(photo.toString());

      // 업로드한 파일 정보
      CommonsMultipartFile imagefile = photo.getFile();
      System.out.println("imagefile name: " + imagefile.getName());
      System.out.println("imagefile getContentType: " + imagefile.getContentType());
      System.out.println("imagefile getOriginalFilename: " + imagefile.getOriginalFilename());
      System.out.println("imagefile getBytes: " + imagefile.getBytes().length);

      // Point DB에 들어갈 파일명 추출
      photo.setImage(imagefile.getName());

      // cos.jar 자동 파일 업로드
      // 실제 파일 업로드 구현 (upload 업로드)
      String filename = imagefile.getOriginalFilename();
      String path = request.getServletContext().getRealPath("/upload"); // 배포된 서버 경로
      String fpath = path + "\\" + filename;
      System.out.println(fpath);

      FileOutputStream fs = null;
      try {
         fs = new FileOutputStream(fpath);
         fs.write(imagefile.getBytes());

      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         try {
            fs.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
      // DB작업 .... 파일 업로드 완료
      return "image/image";
   }

}