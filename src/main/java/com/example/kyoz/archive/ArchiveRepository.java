package com.example.kyoz.archive;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchiveRepository extends JpaRepository<Archive, Integer>{
   Archive findByTitle(String title);
   Page<Archive> findAll(Pageable pageable);
   Page<Archive> findAll(Specification<Archive> spec, Pageable pageable);

}
