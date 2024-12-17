package pack;

import javax.swing.*; //자바에서 GUI(Graphic User Interface)를 구현하기 위해 JDK에서 기본적으로 제공하는 개발 툴킷
import java.awt.*;		//GUI 프로그램을 위한 도구(OS 컴포넌트를 그대로 사용)
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TypingArchiveGUI extends JFrame {						// Jframe을 상속받는 gui 클래스

	// field

	private JTextArea sampleTextArea; 								// 예문 표시 영역
	private JTextArea inputTextArea;								// 사용자 입력 영역
	private JButton submitButton; 									// 결과 제출 버튼
	private JLabel resultLabel; 									// 결과 표시 레이블
	
    private String sampleText = "동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리 나라 만세.";
    
    
    public TypingArchiveGUI() {
    	setTitle("TypingArchive");									// 타이틀
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);				// X버튼을 눌러 창을 닫고, 프로세스를 완전히 종료
    	setSize(600,400);											// 창의 크기
    	setLayout(new BorderLayout());								// 영역 사이에 간격이 없는 BorderLayout을 생성.
    	
    	// 예문 표시 영역
        sampleTextArea = new JTextArea(sampleText);					// 예문표시 영역에 예문을 표시
        sampleTextArea.setEditable(false);							// 입력불가 설정
        sampleTextArea.setFont(new Font("Dialog", Font.PLAIN, 18));	// 폰트설정 (폰트, 스타일, 크기)
        add(new JScrollPane(sampleTextArea), BorderLayout.NORTH);	// 스크롤바를 생성
        
        // 사용자 입력 영역
        inputTextArea = new JTextArea();							// 입력 영역에 입력값 표시
        inputTextArea.setFont(new Font("Dialog", Font.PLAIN, 18));	// 폰트 설정
        add(new JScrollPane(inputTextArea), BorderLayout.CENTER);	// 스크롤바를 생성
        
        // 버튼 및 결과 레이블
        JPanel bottomPanel = new JPanel();							// Panel 생성
        submitButton = new JButton("결과 확인");						// 결과 확인을 위한 button 생성
        resultLabel = new JLabel("결과가 여기에 표시됩니다.");				// 라벨 생성
        bottomPanel.add(submitButton);								// Panel에 버튼 추가
        bottomPanel.add(resultLabel);								// Panel에 라벨 추가
        add(bottomPanel, BorderLayout.SOUTH);						// 레이아웃 하단에 패널 배치

        // 버튼 클릭 이벤트
        submitButton.addActionListener(new ActionListener() {		// 버튼에 이벤트 메소드 걸기
            @Override												// actionPerformed을 오버라이드 
            public void actionPerformed(ActionEvent e) {			// parameter ActionEvent 타입
                checkTypingResult();								// 결과를 보여주는 메소드를 호출
            }
        });

        setVisible(true);	
    }
    
    	// 타이핑 결과 비교 메서드
    private void checkTypingResult() {
        String userInput = inputTextArea.getText();					// private field 값 inputTextAread을 getText로 접근	
        char[] sampleTextArr = sampleText.toCharArray();			// 예문을 char 배열로 sampleTextArr에 저장
        char[] userInputArr = userInput.toCharArray();				// userInput 값을 char 배열로 userInputArr에 저장

        int correctCount = 0;										// 올바르게 타이핑한 개수를 카운팅하는 counter
        int wrongCount = 0;											// 오타 개수를 카운팅하는 wrongCounter
        int minLen = Math.min(sampleTextArr.length, userInputArr.length);	//  둘 중 길이가 더 짧은 것을 minLen에 저장

        // 비교 logic
        for (int i = 0; i < minLen; i++) {							// minLen만큼 반복 수행
            if (sampleTextArr[i] == userInputArr[i]) {				// 예문의 i번째가 입력한 값의 i번째 값과 일치한다면(올바르게 쳤다면)
                correctCount++;										// correctCounter의 값을 1 증가
            } else {												// 오타를 쳤다면
                wrongCount++;										// wrongCounter의 값을 1 증가
            }
        }
        wrongCount += Math.abs(sampleTextArr.length - userInputArr.length); // 남은 문자 수 추가
        // Math.abs 메소드 : ()안의 값의 절대값을 반환한다.

        double errorRate = ((double) wrongCount / sampleTextArr.length) * 100;	// 오타율을 계산 (double 오타율 / 예문의 길이) * 100
        

        // 결과 표시
        resultLabel.setText(String.format("맞은 글자 수: %d, 틀린 글자 수: %d, 오타율: %.2f%%", 
                                         correctCount, wrongCount, errorRate));
    }
        
    }
    
    


