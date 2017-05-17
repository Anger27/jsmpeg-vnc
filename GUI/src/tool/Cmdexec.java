package tool;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
 
 
public class Cmdexec {
 
	
	private InputStream is = null;
	private InputStreamReader isr= null;
	private BufferedReader br= null;

	public Cmdexec() {
		try {
			Runtime rt = Runtime.getRuntime();
			Process proc = rt
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

	}

}