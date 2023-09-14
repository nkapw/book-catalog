package com.tentang.tech.web;

import com.tentang.tech.dto.PublisherCreateRequestDTO;
import com.tentang.tech.dto.PublisherListResponseDTO;
import com.tentang.tech.dto.PublisherUpdateRequestDTO;
import com.tentang.tech.dto.ResultPageResponseDTO;
import com.tentang.tech.service.PublisherService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@AllArgsConstructor
public class PublisherResource {

  private PublisherService publisherService;

  @PostMapping("/v1/publisher")
  public ResponseEntity<Void> createNewPublisher(@RequestBody @Valid PublisherCreateRequestDTO dto) {
    publisherService.createPublisher(dto);
    return ResponseEntity.created(URI.create("/v1/publisher")).build();
  }

  @PutMapping("/v1/publisher/{publisherId}")
  public ResponseEntity<Void> createNewPublisher(
      @PathVariable String publisherId, @RequestBody @Valid PublisherUpdateRequestDTO dto) {
    publisherService.updatePublisher(publisherId, dto);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/v1/publisher")
  public ResponseEntity<ResultPageResponseDTO<PublisherListResponseDTO>> findPublisherList(
      @RequestParam(name = "pages", required = true, defaultValue = "0") Integer pages,
      @RequestParam(name = "limit", required = true, defaultValue = "10") Integer limit,
      @RequestParam(name = "sortBy", required = true, defaultValue = "name") String sortBy,
      @RequestParam(name = "direction", required = true, defaultValue = "asc") String direction,
      @RequestParam(name = "publisherName", required = false) String publisherName
  ) {
    return ResponseEntity.ok().body(publisherService.findPublisherList(pages, limit, sortBy, direction, publisherName));
  }
}
