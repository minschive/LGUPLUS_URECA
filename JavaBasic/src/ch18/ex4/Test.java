package ch18.ex4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Test {
	// File
	public static void main(String[] args) {
		// File 객체 생성
		// throws Exception 필요
//		{
//			// folder
//			File dir = new File("C:/Temp/images"); // dir
//			System.out.println(dir.exists());
//			if(!dir.exists()) { // 없다면 만들어라
//				dir.mkdir();
//			}
//			
//			// file
//			File file = new File("C:/Temp/myFile.txt");
//			System.out.println(file.exists());
//			if(!file.exists()) {
//				file.createNewFile(); // throw IOException 필요
//			}
//		}
		
		// file write (char 기반)
		{
			File file = new File("C:/Temp/myfile.txt");
			
//			try {
//				FileWriter fw = new FileWriter(file); // close() 필요
//			} catch(IOException e) {
//				e.printStackTrace();
//			}
			
			// try - resource
			try(FileWriter fw = new FileWriter(file);) { // close() 필요 X
				fw.write("안녕하세요!");
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		// file read (char 기반)
		{
			File file = new File("C:/Temp/myfile.txt");
			
			// try - resource
			try(FileReader fr = new FileReader(file);) { // close() 필요 X
				// char 1개씩 처리
				int ch;
				StringBuilder sb = new StringBuilder();
				
				while( (ch = fr.read()) != -1 ) {
					sb.append( (char)ch ); // 정수를 문자로 형변환
				}
				System.out.println(sb);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		// file copy (binary 기반 image)
		{
			String dir = "C:" + File.separator + "Temp";
			String srcFileName = "snow.jpeg";
			String tgtFileName = "copy_snow.jpeg";
			
			File src = new File(dir, srcFileName); // 폴더, 파일명
			File tgt = new File(dir, tgtFileName); // 폴더, 파일명
			
			try (
				FileInputStream fis = new FileInputStream(src);
				FileOutputStream fos = new FileOutputStream(tgt);
			){
				int read;
				
				while( (read = fis.read()) != -1 ) {
					fos.write(read);
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}

	}

}
