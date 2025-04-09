package complaint;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping("/complaints")
public class ComplaintController {

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ComplaintResponseDto> addComplaint(@RequestBody ComplaintRequestDto complaintRequestDto) {
        return null;
    }

    @PutMapping(value = "/{complaintId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ComplaintResponseDto> editComplaint(@PathVariable UUID complaintId,
                                                              @RequestBody ComplaintRequestDto complaintRequestDto) {
        return null;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComplaintResponseDto>> listAll() {
        return ResponseEntity.ok(Collections.emptyList());
    }

}
