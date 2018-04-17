package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MochiTxtFile {

	private File path;
	private File file;
	private BufferedReader br;
	private BufferedWriter bw;
	
	public MochiTxtFile(String path, boolean appendOrOverwrite) throws IOException, NotAFileException {
		file = new File(path);
		this.path = new File(file.getParent());
		if(!this.path.exists()) {
			this.path.mkdir();
		} else {
			System.out.println("文件路径正常！");
		}
		if(!file.isDirectory()) {
			if(!file.exists()) {
				file.createNewFile();
			} else {
				System.out.println("文件存在！");
			}
		} else {
			throw new NotAFileException();
		}
		br = new BufferedReader(new FileReader(file));
		bw = new BufferedWriter(new FileWriter(file, appendOrOverwrite));
	}
	
	public void recreateNewFile() throws IOException, NotAFileException {
		if(file.isFile()) {
			file.delete();
			file.createNewFile();
		} else {
			throw new NotAFileException();
		}
	}
	
	public void write(String content) throws IOException, CanNotWriteException {
		if(file.canWrite()) {
			bw.write(content);
			bw.flush();
		} else {
			throw new CanNotWriteException();
		}
	}
	
	public String readLine() throws IOException {
		return br.readLine();
	}
	
	public boolean ready() throws IOException {
		return br.ready();
	}
	
	public static void main(String[] args) throws IOException, CanNotWriteException, NotAFileException {
		MochiTxtFile mf = new MochiTxtFile("/Users/zhangyimeng/iPhone X.txt", true);
		mf.write("你好呀！");
		mf.write("哦！你也好！");
		mf.write("============");
		System.out.println(mf.readLine());
	}
	
}
