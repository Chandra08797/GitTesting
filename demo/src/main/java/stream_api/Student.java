package stream_api;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {
    private int studentId;
    private String studentName;
    private Map<String, Integer> subjectsWithMarks;
}
