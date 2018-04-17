package test;

public class CanNotWriteException extends Exception {

	private static final long serialVersionUID = 3445406156351814839L;
	private static final String info = "文件不能写或者无写权限";

	public CanNotWriteException() {
		super(info);
	}
	
}
