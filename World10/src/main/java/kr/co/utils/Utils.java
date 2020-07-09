package kr.co.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class Utils {

   public static String makeDir(String uploadPath) {
      String[] paths = Utils.makeDirName();

      File f = new File(uploadPath + paths[2]);
      if (f.exists()) {
         // 폴더가 존재한다면 그냥 빠져나오면 된다.
         return paths[2]; // dathPath
      }

      for (String path : paths) {
         // for문을 돌려서 계속 path를 붙이고, 혹시 그 폴더가 없으면 만들어라.
         File dirPath = new File(uploadPath + path);
         if (!dirPath.exists()) {
            dirPath.mkdir();
         }
      }
      return paths[2]; //upload 파일저장
   }

   public static String[] makeDirName() {
      int[] arr = Utils.getDateInfo();
      String yearPath = File.separator + arr[0]; // \2020으로 들어감
      // 해당 년도 아래에 폴더가 있어야 하므로 yearPath를 붙여준다.
      String monthPath = yearPath + File.separator + String.format("%02d", arr[1]); // 2자리 정수를 넣을건데, 비어있으면 0을 넣자.
      String datePath = monthPath + File.separator + String.format("%02d", arr[2]);
      String[] paths = { yearPath, monthPath, datePath };
      return paths;
   }

   // 반환을 배열로 잡아서 값을 여러개 반환한다.
   public static int[] getDateInfo() {
      Calendar cal = Calendar.getInstance();
      // 파라미터로 스태틱 상수를 받기 때문에 Calender.~~~~로 받는다.
      int year = cal.get(Calendar.YEAR);
      // 월도 0부터 시작한다. 그러므로 1을 더해준다.
      int month = cal.get(Calendar.MONTH) + 1;
      int date = cal.get(Calendar.DATE);
      int[] arr = { year, month, date };
      return arr;
   }

   // uploadPath를 파라미터로 하나 더 주어서 프로젝트마다 원하는 경로에 파일을 업로드하도록 한다.
   public static String saveFile(String originalName, MultipartFile file, String uploadPath) throws Exception {

      String newName = Utils.makeNewName(originalName);
      String datePath = Utils.makeDir(uploadPath);
      // 파일이 저장될 곳, 새로운 이름으로 넣어준다. datePath에는 폴더명만 있으므로 C~~를 위해 붙여주어야 한다.
      File target = new File(uploadPath + datePath, newName);
      // 파일을 업로드하는 코드
      FileCopyUtils.copy(file.getBytes(), target);

      boolean isimgFile = isImg(originalName);
      if (isimgFile) {
         // 실제로 값을 반환하도록 한다. 바뀐 썸네일 path값
         return makeThumbnail(uploadPath, datePath, newName);
      } else {
         String beforeChangeName = datePath + File.separator + newName;
         // 이미지 파일이 아닐 경우 그냥 \만 /로 바꾼다.
         return beforeChangeName.replace(File.separatorChar, '/');
      }
      
   }

   public static String makeThumbnail(String uploadPath, String datePath, String newName) throws Exception {
      // 경로와 파일명
      File f1 = new File(uploadPath + datePath, newName);
      BufferedImage sourceImg = ImageIO.read(f1);
      // sourceImg를 리사이징 할건데, 가로세로 100크기로 만들어라.
      BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_EXACT, 100);
     //썸네일이름 과위치
      String thumbnailName = uploadPath + datePath + File.separator + "s_" + newName;
      File newFile = new File(thumbnailName);

      int idx = newName.lastIndexOf(".");
      String format = newName.substring(idx+1).toUpperCase();
      // destImg를 format의 형태로 newFile이라는 이름으로 저장해라
      ImageIO.write(destImg, format, newFile);
	//문자열의 최대 인덱스  =문자열의 길이 -1 // c:upload떼어냄
		// /2020/07/09/a_asfasdfsafadfad_aaa.png
      return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/'); // File.separator는 String, separatorchar 사용해야 함
   }

   // 이미지파일인지 검수
   public static boolean isImg(String filename) {

      // 확장자는 뒤에서부터 .이후에 존재
      int idx = filename.lastIndexOf(".");
      String format = filename.substring(idx + 1);
      Map<String, MediaType> map = new HashMap<String, MediaType>();
      map.put("JPG", MediaType.IMAGE_JPEG);
      map.put("JPEG", MediaType.IMAGE_JPEG);
      map.put("PNG", MediaType.IMAGE_PNG);
      map.put("GIF", MediaType.IMAGE_GIF);

      // 소문자, 대문자를 대문자로 통일하여 값을 뽑는다.
      MediaType mType = map.get(format.toUpperCase());
      // 미디어타입이 맞는가 아닌가
      if (mType != null) {
         return true;
      } else {
         return false;
      }
   }

   public static String makeNewName(String originalName) {
      // 중복되지 않는 새로운 이름이 만들어진다.
      UUID uid = UUID.randomUUID();
      String newName = uid.toString() + "_" + originalName;
      return newName;
   }

   public static String toKor(String msg) {

      try {
         return new String(msg.getBytes("8859_1"), "UTF-8");
      } catch (UnsupportedEncodingException e) {

         e.printStackTrace();
         // 데이터가 없으면 nullpointer가 발생하므로 null을 반환하도록 해준다.
         return null;
      }
   }
}