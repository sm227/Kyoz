package com.example.kyoz.archive;

import com.example.kyoz.DataNotFoundException;
import com.example.kyoz.user.SiteUser;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ArchiveService {

    private final ArchiveRepository archiveRepository;

    public List<Archive> getList() {
        return this.archiveRepository.findAll();
    }

    public Archive getArchive(Integer id) {
        Optional<Archive> archive = this.archiveRepository.findById(id);
        if (archive.isPresent()) {
            return archive.get();
        } else {
            throw new DataNotFoundException("archive not found");
        }
    }

    public void create(String title, String description, String link, SiteUser user, String grade) {
        Archive q = new Archive();
        q.setGrade(grade);
        q.setTitle(title);
        q.setDescription(description);
        q.setLink(link);
        q.setCreateDate(LocalDateTime.now());
        q.setAuthor(user);
        this.archiveRepository.save(q);
    }

    public Page<Archive> getList(int page, String kw) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        Specification<Archive> spec = search(kw);
        return this.archiveRepository.findAll(spec, pageable);
    }

    public void delete(Archive archive) {
        this.archiveRepository.delete(archive);
    }

    private Specification<Archive> search(String kw) {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Archive> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);  // 중복을 제거
                Join<Archive, SiteUser> u1 = q.join("author", JoinType.LEFT);
                return cb.or(cb.like(q.get("title"), "%" + kw + "%"), // 제목
                        cb.like(q.get("description"), "%" + kw + "%"),      // 내용
                        cb.like(u1.get("username"), "%" + kw + "%"));   // 질문 작성자
            }
        };
    }
}
