package com.example.demo.stt;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class SttJsonLoader {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<SttSegment> loadSegments() {
        try {
            ClassPathResource resource =
                    new ClassPathResource("diarized_raw.json");

            InputStream is = resource.getInputStream();
            JsonNode root = objectMapper.readTree(is);
            JsonNode segmentsNode = root.get("segments");

            List<SttSegment> segments = new ArrayList<>();

            for (JsonNode s : segmentsNode) {
                SttSegment segment = new SttSegment();
                segment.setSpeaker(s.path("speaker").asText());
                segment.setStart(s.path("start").asDouble());
                segment.setEnd(s.path("end").asDouble());
                segment.setText(s.path("text").asText());
                segments.add(segment);
            }

            return segments;
        } catch (Exception e) {
            throw new IllegalStateException("STT JSON 로딩 실패", e);
        }
    }
}
