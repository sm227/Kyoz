package com.example.kyoz.archive;

import com.example.kyoz.archive.Archive;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.domain.Specification;

public interface ArchiveRepository extends JpaRepository<Archive, Integer>{
   Archive findByTitle(String title);
   Page<Archive> findAll(Pageable pageable);
   Page<Archive> findAll(Specification<Archive> spec, Pageable pageable);

}
