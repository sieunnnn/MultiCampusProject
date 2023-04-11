package multi.second.project.domain.message;

import lombok.AllArgsConstructor;
import multi.second.project.domain.member.domain.Member;
import multi.second.project.domain.message.domain.Message;
import multi.second.project.domain.message.dto.request.MessageRegistRequest;
import multi.second.project.domain.message.dto.response.MessageListResponse;
import multi.second.project.domain.message.repository.MessageRepository;
import multi.second.project.domain.note.domain.Note;
import multi.second.project.infra.util.paging.Paging;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

    @Service
    @Transactional
    @AllArgsConstructor
    public class MessageService {

        private final MessageRepository messageRepository;

        public Map<String, Object> findMessageListByNoteNtIdx(Long ntIdx, Pageable pageable) {
            Page<Message> page = messageRepository.findAllByNoteNtIdx(ntIdx, pageable);
            Paging paging = Paging.builder()
                    .page(page)
                    .blockCnt(5)
                    .build();
            return Map.of("messageList", MessageListResponse.toDtoList(page.getContent()), "paging", paging);
        }

        public void createMessage(MessageRegistRequest dto, Member member, Long ntIdx) {
            Message message = Message.createMessage(dto, member);
            message.setNote(Note.builder().ntIdx(ntIdx).build());
            messageRepository.save(message);
        }


        public void updateMessage(Long msIdx, MessageRegistRequest dto) {
            Message message = messageRepository.findById(msIdx).orElseThrow(() -> new IllegalArgumentException("Invalid message id"));
            message.updateMessage(dto);
        }

        public void deleteMessage(Long msIdx) {
            Message message = messageRepository.findById(msIdx).orElseThrow(() -> new IllegalArgumentException("Invalid message id"));
            message.deleteMessage();
        }
    }


