package ru.practicum.comments.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.comments.dto.CommentCreateOrUpdateDto;
import ru.practicum.comments.dto.CommentFullDto;
import ru.practicum.comments.services.interfaces.PrivateCommentService;
import ru.practicum.common.exception.ConflictException;
import ru.practicum.common.exception.NotFoundException;

/**
 * Закрытый API для работы с комментариями
 */
@RestController
@RequestMapping(" /users/{userId}/events/{eventId}/comments")
@RequiredArgsConstructor
public class PrivateCommentController {

    private final PrivateCommentService privateCommentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentFullDto createComment(@PathVariable(name = "userId") Long userId,
                                        @PathVariable(name = "eventId") Long eventId,
                                        @RequestBody @Valid CommentCreateOrUpdateDto dto) throws ConflictException,
                                                                                                 NotFoundException {
        return privateCommentService.createComment(userId, eventId, dto);
    }

    @PatchMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public CommentFullDto updateComment(@PathVariable(name = "userId") Long userId,
                                        @PathVariable(name = "eventId") Long eventId,
                                        @PathVariable(name = "commentId") Long commentId,
                                        @RequestBody @Valid CommentCreateOrUpdateDto dto) throws ConflictException,
                                                                                                 NotFoundException {
        return privateCommentService.updateComment(userId, eventId, commentId, dto);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@PathVariable(name = "userId") Long userId,
                              @PathVariable(name = "eventId") Long eventId,
                              @PathVariable(name = "commentId") Long commentId) throws ConflictException,
                                                                                       NotFoundException {
        privateCommentService.deleteComment(userId, eventId, commentId);
    }
}
