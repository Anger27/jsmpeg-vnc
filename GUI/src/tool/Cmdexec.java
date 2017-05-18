package tool;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
 
 
public class Cmdexec {
	
	private InputStream is = null;
	private InputStreamReader isr= null;
	private BufferedReader br= null;
	private Runtime rt = null;
	private Process proc = null;
	private String[] cmd = null;
	
	public Cmdexec(String[] cmd) {
		this.cmd = cmd;
	}
	
	//
	public boolean execute(){
		boolean result = false;
		
		//cmd = new String[]{"cmd","/c","vnc_start.bat jsmpeg-vnc.exe -p 80"}
		try {
			rt = Runtime.getRuntime();
			proc = rt.exec(cmd,null,new File(".\\jsmpeg-vnc")); // ╫ц╫╨еш
			is = proc.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			
			
			/*String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				System.out.flush();
			}*/
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public BufferedReader getReader(){
		return br;
	}
	
	public boolean undo(){
		try{
			br.close();
			isr.close();
			is.close();
			proc.destroy();
		}catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	
	
	

}