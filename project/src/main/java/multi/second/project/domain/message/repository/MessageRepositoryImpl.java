//package multi.second.project.domain.message.repository;
//
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import multi.second.project.domain.gallery.domain.Gallery;
//import multi.second.project.domain.message.domain.Message;
//
//import java.util.List;
//
//public class MessageRepositoryImpl implements MessageRepositoryExtension {
//
//    private final JPAQueryFactory query;
//    private QMessage message = QMessage.message;
//
//    public MessageRepositoryImpl(JPAQueryFactory query) {
//        this.query = query;
//    }
//
//    @Override
//    public List<Message> testQueryDSL(String content, boolean isDel) {
//
//        List<Message> messages = query.select(message)
//                .from(message)
//                .where(message.conetnt.contains(content).and(message.isDel.eq(isDel)))
//                .fetch();
//
//        return messages;
//    }
//
//
//}
