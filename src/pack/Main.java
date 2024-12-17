package pack;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		String sampleText = "동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리 나라 만세.";
		char sampleTextArr[] = sampleText.toCharArray();

		System.out.println("예문을 따라 입력하세요.");
		System.out.println(sampleText);

		long startTime = System.currentTimeMillis(); // 입력 시작 시간 기록

		// 사용자 입력 받기
		String uTyped = scan.nextLine();
		
		long endTime = System.currentTimeMillis();  // 입력 종료 시간 기록
		
		double timeTaken = (endTime - startTime) / 1000.0; // 초 단위로 시간 변환
		double typingSpeed = uTyped.length() / timeTaken; // 초당 글자 수
		System.out.printf("타이핑 속도: %.2f 글자/초\n", typingSpeed);
		
		char uTypedArr[] = uTyped.toCharArray(); // 입력값을 배열로 변환

		int correctCounter = 0;
		int wrongCounter = 0;

		System.out.println("\n입력 비교 결과:");

		// 최소한의 길이를 기준으로 비교
		int minLen = Math.min(sampleTextArr.length, uTypedArr.length);

		for (int i = 0; i < minLen; i++) {
			if (sampleTextArr[i] == uTypedArr[i]) {
				System.out.print(uTypedArr[i]); // 맞은 글자는 그대로 출력
				correctCounter++;
			} else {
				System.out.print("^" + uTypedArr[i] + "^"); // 틀린 글자는 ^로 감싸서 출력
				wrongCounter++;
			}
		}
		System.out.println(); // 줄바꿈

	

		// 남은 부분 출력 (길이가 다를 경우)
		if (uTypedArr.length > sampleTextArr.length) {
			for (int i = sampleTextArr.length; i < uTypedArr.length; i++) {
				System.out.print("^" + uTypedArr[i] + "^"); // 초과 입력된 부분 강조
				wrongCounter++;
			}
		}

		System.out.println(); // 줄바꿈

		// 오타율 계산
		double errorRate = ((double) wrongCounter / sampleTextArr.length) * 100;

		// 결과 출력
		System.out.printf("\n총 글자 수: %d\n", sampleTextArr.length);
		System.out.printf("맞은 글자 수: %d\n", correctCounter);
		System.out.printf("틀린 글자 수: %d\n", wrongCounter);
		System.out.printf("오타율: %.2f%%\n", errorRate);
		
		if (errorRate <= 5) {
		    System.out.println("훌륭해요! 거의 완벽한 타이핑입니다.");
		} else if (errorRate <= 15) {
		    System.out.println("좋아요! 연습하면 더 좋아질 거예요.");
		} else {
		    System.out.println("조금 더 연습이 필요해요! 계속 도전해 보세요.");
		}

		scan.close();
	}
}
