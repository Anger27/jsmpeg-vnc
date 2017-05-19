package tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileCreater {

	public FileCreater() {
		
	}
	
	public void CreateFile(String FilePath)  
	{  
	    try  
	    {  
	        System.out.println(FilePath);  
	          
	        int nLast = FilePath.lastIndexOf("\\");  
	        String strDir = FilePath.substring(0, nLast);  
	        String strFile = FilePath.substring(nLast+1, FilePath.length());  
	          
	        File dirFolder = new File(strDir);  
	        dirFolder.mkdirs();  
	        File f = new File(dirFolder, strFile);  
	        f.createNewFile();  
	    }  
	    catch (Exception ex)  
	    {  
	        System.out.println(ex.getMessage());  
	    }  
	}  
	  
	// 파일 테스트 읽기  
	public String ReadFileText(File file)  
	{  
	    String strText = "";  
	    int nBuffer;  
	    try   
	    {  
	        BufferedReader buffRead = new BufferedReader(new FileReader(file));  
	        while ((nBuffer = buffRead.read()) != -1)  
	        {  
	            strText += (char)nBuffer;  
	        }  
	        buffRead.close();  
	    }  
	    catch (Exception ex)  
	    {  
	        System.out.println(ex.getMessage());  
	    }  
	      
	    return strText;  
	}  
	  
	// 파일 수정  
	public boolean UpdateFile(String FilePath, String Text)  
	{  
	    try   
	    {  
	        File f = new File(FilePath);  
	        if (f.exists() == true)  
	        	f.delete();
	              
	        
	        CreateFile(FilePath);  
	        // 파일 읽기  
	        String fileText = ReadFileText(f);  
	        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(f));  
	        Text = fileText + "\r\n" + Text;  
	        // 파일 쓰기  
	        buffWrite.write(Text, 0, Text.length());  
	        // 파일 닫기  
	        buffWrite.flush();  
	        buffWrite.close();
	        
	        return true;
	    }  
	    catch (Exception ex)  
	    {  
	        System.out.println(ex.getMessage());  
	    } 
	    return false;
	}  
}
