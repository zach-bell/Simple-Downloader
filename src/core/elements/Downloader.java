package core.elements;

import java.io.PrintWriter;
import tools.SyncPipe;

public class Downloader {

	public void Download(String url, String location) {
		String[] command = {"cmd", };
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
			new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
			PrintWriter stdin = new PrintWriter(p.getOutputStream());
			stdin.println("c:");
			stdin.println("cd \"\\sd\"");
			stdin.println("youtube-dl " + url);
			stdin.close();
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
