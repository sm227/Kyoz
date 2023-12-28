package com.example.kyoz.archive;

//import com.example.kyoz.answer.AnswerForm;
import com.example.kyoz.archive.Archive;
import com.example.kyoz.archive.ArchiveForm;
import com.example.kyoz.archive.ArchiveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;

@RequestMapping("/archive")
@RequiredArgsConstructor
@Controller
public class ArchiveController {
    private final ArchiveService archiveService;


    @GetMapping("/list")
    public String list(Model model,  @RequestParam(value="page", defaultValue="0") int page) {
        Page<Archive> paging = this.archiveService.getList(page);
        model.addAttribute("paging", paging);
        return "archive_list";

    }

    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Archive archive = this.archiveService.getArchive(id);
        model.addAttribute("archive", archive);

        return "archive_detail";
    }

    @GetMapping("/create")
    public String archiveCreate(ArchiveForm archiveForm) {
        return "archive_form";
    }

    @PostMapping("/create")
    public String archiveCreate(@Valid ArchiveForm archiveForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "archive_form";
        }

        this.archiveService.create(archiveForm.getTitle(), archiveForm.getDescription(), archiveForm.getLink());
        return "redirect:/archive/list"; // 질문 저장후 질문목록으로 이동
    }
}
