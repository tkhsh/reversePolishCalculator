package reversePolishCalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.LinkedList;

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
			String[] symbols = analyzeInput(input);

			// 終了時の判定
			if (input.equalsIgnoreCase("exit")) {
				printStream.println("終了します");
				// whileループからぬける
				break;
			}

			ComputationResult answer = calculateRPN(symbols);

			printAnswer(printStream, input, answer);
		}
	}

	private static String[] analyzeInput(String input) {
		input = input.trim();
		return input.split(" ");
	}

	private static void printAnswer(PrintStream printStream, String input,
			ComputationResult answer) {
		if (answer.isSuccess() != false) {
			printStream.println(input + " = " + answer.getValue());
		} else {
			printStream.println(answer.getErrorMessage());
		}
	}

	private static ComputationResult calculateRPN(String[] symbols) {
		LinkedList<Integer> stack = new LinkedList<Integer>();

		for (int i = 0; i < symbols.length; i++) {
			if (symbols[i].matches("[0-9]*")) {
				stack.push(Integer.parseInt(symbols[i]));
			} else {
				char[] c = symbols[i].toCharArray();
				if (symbols[i].length() > 1) {
					return new ComputationResult(
							"逆ポーランド記法が正しくありません。\nもう一度入力してください。");
				}
				switch (c[0]) {
				case '+':
					if (stack.size() >= 2) {
						int right = stack.pop();
						int left = stack.pop();
						stack.push(left + right);
					} else {
						return new ComputationResult(
								"逆ポーランド記法が正しくありません。\nもう一度入力してください。");
					}
					break;
				case '-':
					if (stack.size() >= 2) {
						int right = stack.pop();
						int left = stack.pop();
						stack.push(left - right);
					} else {
						return new ComputationResult(
								"逆ポーランド記法が正しくありません。\nもう一度入力してください。");
					}
					break;
				case '*':
					if (stack.size() >= 2) {
						int right = stack.pop();
						int left = stack.pop();
						stack.push(left * right);
					} else {
						return new ComputationResult(
								"逆ポーランド記法が正しくありません。\nもう一度入力してください。");
					}
					break;
				case '/':
					if (stack.size() >= 2) {
						int right = stack.pop();
						int left = stack.pop();
						stack.push(left / right);
					} else {
						return new ComputationResult(
								"逆ポーランド記法が正しくありません。\nもう一度入力してください。");
					}
					break;
				case ' ':
					break;
				default:
					return new ComputationResult(
							symbols[i]
									+ "という文字が不正です。数値か演算子を入力してください。利用できる演算子は + - * / です\nもう一度入力してください。");
				}
			}
		}

		if (stack.size() != 1) {
			return new ComputationResult("逆ポーランド記法が正しくありません。\nもう一度入力してください。");
		} else {
			return new ComputationResult(stack.peek());
		}
	}
}
