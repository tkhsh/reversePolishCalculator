package reversePolishCalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class RPC_sample {

	private static final int EMPTY = -1;

	public static void main(String[] args) throws IOException {
		PrintStream printStream = System.out;

		InputStream inputStream = System.in;
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		printStream
				.println("逆ポーランド記法の数式を入力してください。利用できる演算子は + - * / です。\nまた、終了するときはexitと入力してください。");
		while (true) {
			String input = bufferedReader.readLine();
			input = input.trim();
			char[] array = new char[input.length()];

			// 終了時の判定
			if (input.equalsIgnoreCase("exit")) {
				printStream.println("終了します");
				// whileループからぬける
				break;
			}

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
						printStream
								.println(array[i]
										+ "という文字が不正です。数値か演算子を入力してください。利用できる演算子は + - * / です。\nもう一度入力してください。");
					}
				}
			}
			if (tmp2 == EMPTY) {
				printStream.println(input + " = " + tmp1);
			} else {
				printStream.println("逆ポーランド記法が正しくありません。\nもう一度入力してください。");
			}
		}
	}
}
