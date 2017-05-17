package tool;

/*
 * vnc 실행과 종료를 위한 클래스
 */

public class CreateBat {
	String Path = null;		//파일 경로
	String Start_cmd = null; //start jsmpeg_vnc.exe 실행 bat파일 명령어
	String Exit_cmd = null; //taskkill 명령어
	
	FileCreater fc = null;
	
	
	
	public CreateBat(String Path) {
		fc = new FileCreater();
		this.Path = Path;
	}
	
	public boolean MakeStartBat(){
		Start_cmd = "@echo off\r\n"
				+ "if exist %cd%\\jsmpeg-vnc.exe (\r\n"
				+ " echo exist %cd%\\%* desktop\r\n"
				+ " start %cd%\\%* desktop\r\n"
				+ ") else (\r\necho not exist\r\n)";
		
		return fc.UpdateFile(Path+"vnc_start.bat", Start_cmd);
	}

	public boolean MakeExitBat() {
		Exit_cmd = "@echo off\r\n\r\ntaskkill /f /im %1";
		return fc.UpdateFile(Path+"vnc_exit.bat", Exit_cmd);
	}
	
}
