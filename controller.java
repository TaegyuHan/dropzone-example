import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

// ===== DTO 클래스 =====
class ImageUploadRequest {
    private String uuid;
    private String name;
    private Long size;
    private String type;
    private String dataUrl;
    private String option;
    private Integer rotateDeg;
    private Integer flipX;
    private Integer flipY;

    // Getter
    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public Long getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public String getDataUrl() {
        return dataUrl;
    }

    public String getOption() {
        return option;
    }

    public Integer getRotateDeg() {
        return rotateDeg;
    }

    public Integer getFlipX() {
        return flipX;
    }

    public Integer getFlipY() {
        return flipY;
    }

    // Setter
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public void setRotateDeg(Integer rotateDeg) {
        this.rotateDeg = rotateDeg;
    }

    public void setFlipX(Integer flipX) {
        this.flipX = flipX;
    }

    public void setFlipY(Integer flipY) {
        this.flipY = flipY;
    }

    @Override
    public String toString() {
        return "ImageUploadRequest{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", type='" + type + '\'' +
                ", option='" + option + '\'' +
                ", rotateDeg=" + rotateDeg +
                ", flipX=" + flipX +
                ", flipY=" + flipY +
                ", dataUrl길이=" + (dataUrl != null ? dataUrl.length() : 0) +
                '}';
    }
}

// ===== 컨트롤러 =====
@RestController
@RequestMapping("/api/images")
public class ImageUploadController {

    /**
     * 이미지 업로드 데이터를 DTO 객체로 받고 처리하는 엔드포인트
     * @param items 프론트엔드에서 전송한 이미지 정보 배열 (DTO 객체 리스트)
     * @return 처리 결과
     */
    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> uploadImages(@RequestBody List<ImageUploadRequest> items) {
        System.out.println("=== 이미지 업로드 요청 받음 ===");
        System.out.println("총 개수: " + items.size());
        
        // 응답 맵 생성
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 각 아이템 처리
            for (int i = 0; i < items.size(); i++) {
                ImageUploadRequest item = items.get(i);
                
                System.out.println("\n[" + (i + 1) + "] 처리 중...");
                System.out.println("  - UUID: " + item.getUuid());
                System.out.println("  - 파일명: " + item.getName());
                System.out.println("  - 파일크기: " + item.getSize() + " bytes");
                System.out.println("  - 파일타입: " + item.getType());
                System.out.println("  - 선택옵션: " + item.getOption());
                System.out.println("  - 회전각도: " + item.getRotateDeg() + "deg");
                System.out.println("  - X축반전: " + item.getFlipX());
                System.out.println("  - Y축반전: " + item.getFlipY());
                System.out.println("  - DataUrl 길이: " + 
                    (item.getDataUrl() != null ? item.getDataUrl().length() : 0) + " chars");
                
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
