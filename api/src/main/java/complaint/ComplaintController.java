package complaint;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping("/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;

    protected ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ComplaintResponseDto> addComplaint(HttpServletRequest httpServletRequest,
                                                             @RequestBody ComplaintRequestDto complaintRequestDto) {
        String ip = getIpFromRequest(httpServletRequest);
        AddComplaintCommand command = AddComplaintCommandFactory.fromRequest(complaintRequestDto, ip);
        Complaint complaint = complaintService.addOrBumpUpComplaint(command);
        ComplaintResponseDto response = ComplaintResponseDto.of(complaint);
        return ResponseEntity.ofNullable(response);
    }

    @PatchMapping(value = "/{complaintId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ComplaintResponseDto> editComplaint(@PathVariable UUID complaintId,
                                                              @RequestParam(value = "description") String description) {
        EditComplaintCommand command = EditComplaintCommand.of(complaintId, description);
        Complaint complaint = complaintService.editComplaint(command);
        ComplaintResponseDto response = ComplaintResponseDto.of(complaint);
        return ResponseEntity.ofNullable(response);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComplaintResponseDto>> listAll() {
        List<ComplaintResponseDto> response = complaintService.listAllComplaints()
                .stream()
                .map(ComplaintResponseDto::of)
                .toList();
        return ResponseEntity.ofNullable(response);
    }

    private String getIpFromRequest(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getRemoteAddr();
    }

}
