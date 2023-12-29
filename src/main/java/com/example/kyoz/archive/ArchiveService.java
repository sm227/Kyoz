package com.example.kyoz.archive;

import com.example.kyoz.DataNotFoundException;
import com.example.kyoz.archive.Archive;
import com.example.kyoz.archive.ArchiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import org.springframework.data.domain.Sort;

import com.example.kyoz.user.SiteUser;

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

    public void create(String title, String description, String link, SiteUser user) {
        Archive q = new Archive();
        q.setTitle(title);
        q.setDescription(description);
        q.setLink(link);
        q.setCreateDate(LocalDateTime.now());
        q.setAuthor(user);
        this.archiveRepository.save(q);
    }

    public Page<Archive> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.archiveRepository.findAll(pageable);
    }

    public void delete(Archive archive) {
        this.archiveRepository.delete(archive);
    }
}
