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
	  
	// ���� �׽�Ʈ �б�  
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
	  
	// ���� ����  
	public boolean UpdateFile(String FilePath, String Text)  
	{  
	    try   
	    {  
	        File f = new File(FilePath);  
	        if (f.exists() == true)  
	        	f.delete();
	              
	        
	        CreateFile(FilePath);  
	        // ���� �б�  
	        String fileText = ReadFileText(f);  
	        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(f));  
	        Text = fileText + "\r\n" + Text;  
	        // ���� ����  
	        buffWrite.write(Text, 0, Text.length());  
	        // ���� �ݱ�  
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
