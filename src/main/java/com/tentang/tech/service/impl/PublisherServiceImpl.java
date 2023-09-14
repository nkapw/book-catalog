package com.tentang.tech.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tentang.tech.domain.Publisher;
import com.tentang.tech.dto.PublisherCreateRequestDTO;
import com.tentang.tech.dto.PublisherListResponseDTO;
import com.tentang.tech.dto.PublisherResponseDTO;
import com.tentang.tech.dto.PublisherUpdateRequestDTO;
import com.tentang.tech.dto.ResultPageResponseDTO;
import com.tentang.tech.exception.BadRequestException;
import com.tentang.tech.repository.PublisherRepository;
import com.tentang.tech.service.PublisherService;
import com.tentang.tech.util.PaginationUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PublisherServiceImpl implements PublisherService {

  private PublisherRepository publisherRepository;

  @Override
  public Publisher findPublisher(String publisherId) {
    return publisherRepository.findBySecureId(publisherId)
        .orElseThrow(() -> new BadRequestException("invalid.publisher_id"));

  }

  @Override
  public void createPublisher(PublisherCreateRequestDTO dto) {
    Publisher publisher = new Publisher();
    publisher.setName(dto.getPublisherName());
    publisher.setCompanyName(dto.getCompanyName());
    publisher.setAddress(dto.getAddress());

    publisherRepository.save(publisher);
  }

  @Override
  public void updatePublisher(String publisherId, PublisherUpdateRequestDTO dto) {

    Publisher publisher = publisherRepository.findBySecureId(publisherId)
        .orElseThrow(() -> new BadRequestException("invalid.publisher_id"));
    publisher.setName(dto.getPublisherName() == null || dto.getPublisherName().isBlank() ? publisher.getName()
        : dto.getPublisherName());
    publisher.setName(
        dto.getCompanyName() == null || dto.getCompanyName().isBlank() ? publisher.getName() : dto.getCompanyName());
    publisher.setAddress(dto.getAddress());

    publisherRepository.save(publisher);
  }

  @Override
  public ResultPageResponseDTO<PublisherListResponseDTO> findPublisherList(Integer pages, Integer limit, String sortBy,
      String direction, String publisherName) {
    publisherName = StringUtils.isBlank(publisherName) ? "%" : publisherName + "%";
    Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction), sortBy));
    Pageable pageable = PageRequest.of(pages, limit, sort);
    Page<Publisher> pageResult = publisherRepository.findByNameLikeIgnoreCase(publisherName, pageable);
    List<PublisherListResponseDTO> responseDTOS = pageResult.stream().map(publisher -> {
      PublisherListResponseDTO dto = new PublisherListResponseDTO();
      dto.setPublisherId(publisher.getSecureId());
      dto.setCompanyName(publisher.getCompanyName());
      dto.setPublisherName(publisher.getName());
      return dto;
    }).collect(Collectors.toList());
    System.out.println(responseDTOS);
    return PaginationUtil.createResultPageDTO(responseDTOS, pageResult.getTotalElements(), pageResult.getTotalPages());

  }

  @Override
  public PublisherResponseDTO constructDTO(Publisher publisher) {
    PublisherResponseDTO dto = new PublisherResponseDTO();
    dto.setPublisherId(publisher.getSecureId());
    dto.setPublisherName(publisher.getName());
    return dto;
  }
}
