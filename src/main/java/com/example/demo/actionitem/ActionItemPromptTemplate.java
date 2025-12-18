package com.example.demo.actionitem;

import org.springframework.stereotype.Component;

@Component
public class ActionItemPromptTemplate {
    public String build(String summary) {
        return """
        너는 대학생 개발팀의 프로젝트 매니저다.
        입력으로 주어지는 회의 요약본에서
        1. 결정 사항
        2. 요청 또는 지시
        에 해당하는 내용만을 바탕으로,
        1주 이내 실행 가능한 액션 아이템만 추출하라.

        논의, 아이디어, 검토 수준의 내용은 제외한다.
        출력은 반드시 JSON 배열 형식으로만 제공한다.
        모든 날짜는 반드시 YYYY-MM-DD 형식으로만 출력하라.
        자연어 표현(오늘, 내일, 오후 등)은 절대 사용하지 마라.
  

        형식:
        [
          {
            "task": "...",
            "assignee": "... or null",
            "dueDate": "YYYY-MM-DD or null"
          }
        ]

        회의 요약본:
        %s
        """.formatted(summary);
    }

}
