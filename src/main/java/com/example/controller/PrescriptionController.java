@RestController
@RequestMapping("/api/users")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    @Autowired
    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    // Endpoint: /api/users/{user}/prescriptions/{token}
    @PostMapping("/{user}/prescriptions/{token}")
    public ResponseEntity<?> savePrescription(
            @PathVariable String user,
            @PathVariable String token,
            @RequestBody PrescriptionDTO prescriptionDTO) {

        // TODO: validate token (giả lập)
        boolean valid = TokenValidator.isValid(user, token);
        if (!valid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid or expired token"));
        }

        // Validate dữ liệu cơ bản
        if (prescriptionDTO == null || prescriptionDTO.getDoctorId() == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Missing required fields: doctorId"));
        }

        try {
            Prescription saved = prescriptionService.savePrescription(prescriptionDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "user", user,
                    "message", "Prescription saved successfully",
                    "prescriptionId", saved.getId()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Failed to save prescription: " + e.getMessage()));
        }
    }
}
