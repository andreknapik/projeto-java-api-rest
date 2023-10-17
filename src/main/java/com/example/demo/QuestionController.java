package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    @Autowired
    private QuestionRepository questionRepository;

    // Endpoint para criar uma nova pergunta
    @PostMapping
    public Question createQuestion(@RequestBody Question question) {
        // Valide a resposta correta aqui

        return questionRepository.save(question);
    }

    // Endpoint para buscar perguntas ativas por categoria
    @GetMapping("/{category}")
    public List<Question> getActiveQuestionsByCategory(@PathVariable String category) {
        return questionRepository.findByCategoryAndActive(category, true);
    }

    // Endpoint para ativar/desativar uma pergunta
    @PatchMapping("/{id}")
    public Question toggleQuestionActivation(@PathVariable Long id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);

        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();
            question.setActive(!question.isActive());
            return questionRepository.save(question);
        }

        return null; // Trate o caso em que a pergunta não é encontrada
    }
}