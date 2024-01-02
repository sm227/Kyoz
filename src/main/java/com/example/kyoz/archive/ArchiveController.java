package com.example.kyoz.archive;

//import com.example.kyoz.answer.AnswerForm;
import com.example.kyoz.archive.Archive;
import com.example.kyoz.archive.ArchiveForm;
import com.example.kyoz.archive.ArchiveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import com.example.kyoz.user.SiteUser;
import com.example.kyoz.user.UserService;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.web.server.ResponseStatusException;


@RequestMapping("/archive")
@RequiredArgsConstructor
@Controller
public class ArchiveController {
    private final ArchiveService archiveService;
    private final UserService userService;

    @GetMapping("/list")
    public String list(Model model,  @RequestParam(value="page", defaultValue="0") int page, @RequestParam(value = "kw", defaultValue = "") String kw) {
        Page<Archive> paging = this.archiveService.getList(page, kw);
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
        return "archive_list";

    }

    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Archive archive = this.archiveService.getArchive(id);
        model.addAttribute("archive", archive);

        return "archive_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String archiveCreate(ArchiveForm archiveForm) {
        return "archive_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String archiveCreate(@Valid ArchiveForm archiveForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "archive_form";
        }
        SiteUser siteUser = this.userService.getUser(principal.getName());
        this.archiveService.create(archiveForm.getTitle(), archiveForm.getDescription(), archiveForm.getLink(),siteUser, archiveForm.getGrade());
        return "redirect:/archive/list"; // 질문 저장후 질문목록으로 이동
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String archiveDelete(Principal principal, @PathVariable("id") Integer id) {
        Archive archive = this.archiveService.getArchive(id);
        if (!archive.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.archiveService.delete(archive);
        return "redirect:/archive/list";
    }


}
