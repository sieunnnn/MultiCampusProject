package multi.second.project.domain.message.repository;


import multi.second.project.domain.member.domain.Member;
import multi.second.project.domain.message.domain.Message;

import java.util.List;

public interface MessageRepositoryExtension {

    List<Message> testQueryDSL(String content, boolean isDel);
}
