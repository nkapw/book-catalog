package com.tentang.tech.service;

import com.tentang.tech.domain.Publisher;
import com.tentang.tech.dto.PublisherCreateRequestDTO;
import com.tentang.tech.dto.PublisherListResponseDTO;
import com.tentang.tech.dto.PublisherResponseDTO;
import com.tentang.tech.dto.PublisherUpdateRequestDTO;
import com.tentang.tech.dto.ResultPageResponseDTO;

public interface PublisherService {

  public Publisher findPublisher(String publisherId);

  public void createPublisher(PublisherCreateRequestDTO dto);

  public void updatePublisher(String publisherId, PublisherUpdateRequestDTO dto);

  public ResultPageResponseDTO<PublisherListResponseDTO> findPublisherList(Integer pages, Integer limit, String sortBy,
      String direction, String publisherName);

  public PublisherResponseDTO constructDTO(Publisher publisher);

}
