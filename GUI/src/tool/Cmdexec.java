package tool;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
 
 
public class Cmdexec {

	// Singleton Ŭ������ ����� ���ؼ� �����ڸ� private�� ����
	private static Cmdexec obj;

	// ������ static������ ��ü�� �������ִ� �޼��� ����
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
					.exec("cmd /c E:\\PYJ\\���¼ҽ�������Ʈ\\jsmpeg-vnc-v0.2\\jsmpeg-vnc.exe -s 1920x1080 -b 19440 desktop"); // �ý���
																														// ��ɾ�

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