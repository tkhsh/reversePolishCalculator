package reversePolishCalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class RPC_sample {
	// TODO 再利用できるようクラスに分割する。
	private static final int EMPTY = -1;

	public static void main(String[] args) throws IOException {
		PrintStream printStream = System.out;
		InputStream inputStream = System.in;
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		// BufferedReaderのクラスを調べる。

		while (true) {// FIXME とりあえずtrueを入れている。後で修正する。
			String input = bufferedReader.readLine();
			input = input.trim();
			char[] array = new char[input.length()];

			for (int i = 0; i < input.length(); i++) {
				array[i] = input.charAt(i);
			}
			int tmp1 = EMPTY;
			int tmp2 = EMPTY;

			for (int i = 0; i < array.length; i++) {

				String s = Character.toString(array[i]);

				if (tmp1 == EMPTY && s.matches("[0-9]")) {
					tmp1 = Integer.parseInt(s);
				} else if (tmp2 == EMPTY && s.matches("[0-9]")) {
					tmp2 = Integer.parseInt(s);
				} else {
					switch (array[i]) {
					case '+':
						tmp1 += tmp2;
						tmp2 = EMPTY;
						break;
					case '-':
						tmp1 -= tmp2;
						tmp2 = EMPTY;
						break;
					case '*':
						tmp1 *= tmp2;
						tmp2 = EMPTY;
						break;
					case '/':
						tmp1 /= tmp2;
						tmp2 = EMPTY;
						break;
					case ' ':
						break;
					default:
						printStream.println("エラーです。");
					}
				}
			}
			if (tmp2 == EMPTY) {
				printStream.println(input + " = " + tmp1);
			} else {
				printStream.println("エラーです");
			}
		}
	}
}
