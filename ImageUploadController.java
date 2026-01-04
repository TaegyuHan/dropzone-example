import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/images")
public class ImageUploadController {

    /**
     * 이미지 업로드 데이터를 받고 처리하는 엔드포인트
     * @param items 프론트엔드에서 전송한 이미지 정보 배열
     * @return 처리 결과
     */
    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> uploadImages(@RequestBody List<Map<String, Object>> items) {
        System.out.println("=== 이미지 업로드 요청 받음 ===");
        System.out.println("총 개수: " + items.size());
        
        // 응답 맵 생성
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 각 아이템 처리
            for (int i = 0; i < items.size(); i++) {
                Map<String, Object> item = items.get(i);
                
                System.out.println("\n[" + (i + 1) + "] 처리 중...");
                System.out.println("  - UUID: " + item.get("uuid"));
                System.out.println("  - 파일명: " + item.get("name"));
                System.out.println("  - 파일크기: " + item.get("size") + " bytes");
                System.out.println("  - 파일타입: " + item.get("type"));
                System.out.println("  - 선택옵션: " + item.get("option"));
                System.out.println("  - 회전각도: " + item.get("rotateDeg") + "deg");
                System.out.println("  - X축반전: " + item.get("flipX"));
                System.out.println("  - Y축반전: " + item.get("flipY"));
                System.out.println("  - DataUrl 길이: " + 
                    (item.get("dataUrl") != null ? item.get("dataUrl").toString().length() : 0) + " chars");
                
                // 여기서 실제 파일 저장, DB 저장 등의 로직 추가 가능
                // 예시:
                // - dataUrl을 파일로 변환해서 저장
                // - DB에 메타데이터 저장
                // - 썸네일 생성 등
            }
            
            System.out.println("\n=== 모든 이미지 처리 완료 ===");
            
            // 성공 응답
            response.put("success", true);
            response.put("count", items.size());
            response.put("message", items.size() + "개의 이미지가 성공적으로 처리되었습니다.");
            
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            System.err.println("이미지 처리 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
            
            // 오류 응답
            response.put("success", false);
            response.put("message", "이미지 처리 실패: " + e.getMessage());
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
