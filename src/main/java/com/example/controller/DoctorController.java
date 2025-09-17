@RestController
@RequestMapping("/api/users")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // Endpoint: /api/users/{user}/doctors/{doctorId}/availability/{date}/{token}
    @GetMapping("/{user}/doctors/{doctorId}/availability/{date}/{token}")
    public ResponseEntity<?> getDoctorAvailability(
            @PathVariable String user,
            @PathVariable Long doctorId,
            @PathVariable String date,
            @PathVariable String token) {

        // TODO: validate token here (giả sử có TokenService)
        boolean valid = TokenValidator.isValid(user, token);
        if (!valid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid or expired token"));
        }

        // Gọi service để lấy lịch trống của bác sĩ
        List<String> availableSlots = doctorService.getAvailability(doctorId, date);

        if (availableSlots.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No availability found for doctor " + doctorId + " on " + date));
        }

        // Trả về dữ liệu dạng JSON có cấu trúc
        return ResponseEntity.ok(Map.of(
                "user", user,
                "doctorId", doctorId,
                "date", date,
                "availableSlots", availableSlots
        ));
    }
}
