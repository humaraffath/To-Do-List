package com.example.checklist.controller;

import com.example.checklist.model.Checklist;
import com.example.checklist.repository.ChecklistRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checklists")
public class ChecklistController {

    private final ChecklistRepository checklistRepository;

    public ChecklistController(ChecklistRepository checklistRepository) {
        this.checklistRepository = checklistRepository;
    }

    @GetMapping
    public List<Checklist> getAllChecklists() {
        return checklistRepository.findAll();
    }

    @PostMapping
    public Checklist createChecklist(@RequestBody Checklist checklist) {
        return checklistRepository.save(checklist);
    }

    @PutMapping("/{id}")
    public Checklist updateChecklist(@PathVariable Long id, @RequestBody Checklist checklistDetails) {
        Checklist checklist = checklistRepository.findById(id).orElseThrow();
        checklist.setTitle(checklistDetails.getTitle());
        checklist.setCompleted(checklistDetails.isCompleted());
        checklist.setDueDate(checklistDetails.getDueDate());
        return checklistRepository.save(checklist);
    }

    @DeleteMapping("/{id}")
    public void deleteChecklist(@PathVariable Long id) {
        checklistRepository.deleteById(id);
    }
}
