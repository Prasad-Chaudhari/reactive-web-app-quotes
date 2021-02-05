package com.example.demo.repository;

import java.util.List;

import com.example.demo.models.Quote;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuoteMongoBlockingRepository extends PagingAndSortingRepository<Quote, String> {

    List<Quote> findAllByIdNotNullOrderByIdAsc(final Pageable page);
}