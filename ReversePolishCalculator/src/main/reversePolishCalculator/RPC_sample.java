package reversePolishCalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

public class RPC_sample {

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
			char[] array = input.toCharArray();

			// 終了時の判定
			if (input.equalsIgnoreCase("exit")) {
				printStream.println("終了します");
				// whileループからぬける
				break;
			}

			ComputationResult answer = calculateRPN(array);

			printAnswer(printStream, input, answer);
		}
	}

	private static void printAnswer(PrintStream printStream, String input,
			ComputationResult answer) {
		if (answer.isSuccess() != false) {
			printStream.println(input + " = " + answer.getValue());
		} else {
			printStream.println(answer.getErrorMessage());
		}
	}

	private static ComputationResult calculateRPN(char[] array) {
		int tmp;
		int tmpAnswer;
		ArrayList<Integer> stack = new ArrayList<Integer>();

		for (int i = 0; i < array.length; i++) {
			String s = Character.toString(array[i]);
			if (s.matches("[0-9]")) {
				tmp = Integer.parseInt(s);
				stack.add(tmp);
			} else {
				switch (array[i]) {
				case '+':
					if (stack.size() >= 2) {
						tmpAnswer = stack.get(stack.size() - 2)
								+ stack.get(stack.size() - 1);
						stack.remove(stack.size() - 1);
						stack.set(stack.size() - 1, tmpAnswer);
					} else {
						ComputationResult result = new ComputationResult(
								"逆ポーランド記法が正しくありません。\nもう一度入力してください。");
						return result;
					}
					break;
				case '-':
					if (stack.size() >= 2) {
						tmpAnswer = stack.get(stack.size() - 2)
								- stack.get(stack.size() - 1);
						stack.remove(stack.size() - 1);
						stack.set(stack.size() - 1, tmpAnswer);
					} else {
						ComputationResult result = new ComputationResult(
								"逆ポーランド記法が正しくありません。\nもう一度入力してください。");
						return result;
					}
					break;
				case '*':
					if (stack.size() >= 2) {
						tmpAnswer = stack.get(stack.size() - 2)
								* stack.get(stack.size() - 1);
						stack.remove(stack.size() - 1);
						stack.set(stack.size() - 1, tmpAnswer);
					} else {
						ComputationResult result = new ComputationResult(
								"逆ポーランド記法が正しくありません。\nもう一度入力してください。");
						return result;
					}
					break;
				case '/':
					if (stack.size() >= 2) {
						tmpAnswer = stack.get(stack.size() - 2)
								/ stack.get(stack.size() - 1);
						stack.remove(stack.size() - 1);
						stack.set(stack.size() - 1, tmpAnswer);
					} else {
						ComputationResult result = new ComputationResult(
								"逆ポーランド記法が正しくありません。\nもう一度入力してください。");
						return result;
					}
					break;
				case ' ':
					break;
				default:
					ComputationResult result = new ComputationResult(
							array[i]
									+ "という文字が不正です。数値か演算子を入力してください。利用できる演算子は + - * / です\nもう一度入力してください。");
					return result;
				}
			}
		}

		if (stack.size() != 1) {
			ComputationResult result = new ComputationResult(
					"逆ポーランド記法が正しくありません。\nもう一度入力してください。");
			return result;
		} else {
			ComputationResult result = new ComputationResult(stack.get(0));
			return result;
		}
	}
}
