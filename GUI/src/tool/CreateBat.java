package tool;

/*
 * vnc ����� ���Ḧ ���� Ŭ����
 */

public class CreateBat {
	String Path = null;		//���� ���
	String Start_cmd = null; //start jsmpeg_vnc.exe ���� bat���� ��ɾ�
	String Exit_cmd = null; //taskkill ��ɾ�
	
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
