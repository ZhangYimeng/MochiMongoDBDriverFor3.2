package test;

public class NotAFileException extends Exception {

	private static final long serialVersionUID = 1521117824072301589L;
	private static final String info = "path指定的路径不是一个文件。";
	
	public NotAFileException() {
		super(info);
	}

}
