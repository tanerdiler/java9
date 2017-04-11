import java.io.*;

/**
 * Created by taner on 06.04.2017.
 */
public class Main
{
	public static void main(String[] args) throws IOException
	{
		InputStream is = new BufferedInputStream(
				new FileInputStream(new File("/tmp/test.txt")));

		byte[] allBytes = is.readAllBytes();



		is = new BufferedInputStream(
				new FileInputStream(new File("/tmp/test.txt")));

		is.transferTo(System.out);
	}

}


