package multi.second.project.domain.message.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import multi.second.project.domain.member.domain.Member;
import multi.second.project.domain.message.domain.Message;

import java.time.LocalDateTime;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageListResponse {

    private Long msIdx;

    private String userId;

    private String guest;

    private String content;

    private LocalDateTime regDate;

    public MessageListResponse(Message message) {
        this.msIdx = message.getMsIdx();
        this.userId = message.getMember().getUserId();
        this.guest = message.getGuest();
        this.content = message.getContent();
        this.regDate = message.getRegDate();
    }

    public static List<MessageListResponse> toDtoList(List<Message> entityList) {
        return entityList.stream().map(e -> new MessageListResponse(e)).collect(toList());

    }
}