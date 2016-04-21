package ex01.pyrmont;

import java.net.Socket;
import java.net.ServerSocket;
import java.net.InetAddress;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.File;

public class HttpServer {

	/**
	 * WEB_ROOT is the directory where our HTML and other files reside.<br>
	 * 
	 * System.getProperty("user.dir")：获取用户当前的工作路径。<br>
	 * 
	 * File.separator：在 UNIX 系统上，此字段的值为 '/'；在 Microsoft Windows 系统上，它为 '\\'。<br>
	 * 
	 * For this package,WEB_ROOT is the "webroot" directory under the working
	 * directory. The working directory is the location in the file system from
	 * where the java command was invoked.
	 */
	public static final String WEB_ROOT = System.getProperty("user.dir")
			+ File.separator + "webroot";

	// shutdown command
	private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
	// the shutdown command received
	private boolean shutdown = false;

	public static void main(String[] args) {
		HttpServer server = new HttpServer();
		server.await();
	}

	public void await() {
		ServerSocket serverSocket = null;
		int port = 8080;
		try {
			serverSocket = new ServerSocket(port, 1,
					InetAddress.getByName("127.0.0.1"));
			System.out.print("启动成功！！！");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		// Loop waiting for a request
		while (!shutdown) {
			Socket socket = null;
			InputStream input = null;
			OutputStream output = null;
			try {
				socket = serverSocket.accept();
				input = socket.getInputStream();
				output = socket.getOutputStream();

				// create Request object and parse
				Request request = new Request(input);
				//request.setInput(input);
				request.parse();

				// create Response object
				Response response = new Response(output);
				response.setRequest(request);
				response.sendStaticResource();

				// Close the socket
				socket.close();

				// check if the previous URI is a shutdown command
				shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
	}
}
