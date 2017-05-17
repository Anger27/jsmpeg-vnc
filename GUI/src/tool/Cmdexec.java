package tool;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
 
 
public class Cmdexec {

	// Singleton 클래스로 만들기 위해서 생성자를 private로 선언
	private static Cmdexec obj;

	// 선언한 static변수에 객체를 생성해주는 메서드 선언
	public static Cmdexec sharedInstance() {
		if (obj == null) {
			obj = new Cmdexec();
		}
		return obj;
	}
	
	private InputStream is = null;
	private InputStreamReader isr= null;
	private BufferedReader br= null;
	private Runtime rt = null;
	private Process proc = null;
	
	private Cmdexec() {

	}
	
	//
	public boolean execute(){
		boolean result = false;
		
		try {
			rt = Runtime.getRuntime();
			proc = rt
					.exec("cmd /c E:\\PYJ\\오픈소스프로젝트\\jsmpeg-vnc-v0.2\\jsmpeg-vnc.exe -s 1920x1080 -b 19440 desktop"); // 시스템
																														// 명령어

			is = proc.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);

			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				System.out.flush();
			}
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