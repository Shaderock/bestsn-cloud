package utm.pad.cloud.datawarehouse.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/instance", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@RequiredArgsConstructor
public class InstanceController {
    @Value("${app.instance-id}")
    private String instanceId;

    @GetMapping
    public ResponseEntity<String> getInstance() {
        return ResponseEntity.ok("Instance-id: " + instanceId);
    }
}
