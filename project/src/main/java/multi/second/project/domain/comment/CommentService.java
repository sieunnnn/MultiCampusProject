package multi.second.project.domain.comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import multi.second.project.domain.comment.domain.Comment;
import multi.second.project.domain.comment.dto.request.CommentModifyRequest;
import multi.second.project.domain.comment.dto.request.CommentRegistRequest;
import multi.second.project.domain.comment.dto.response.CommentResponse;
import multi.second.project.domain.comment.repository.CommentRepository;
import multi.second.project.domain.gallery.domain.Gallery;
import multi.second.project.domain.gallery.dto.request.GalleryModifyRequest;
import multi.second.project.domain.gallery.dto.request.GalleryRegistRequest;
import multi.second.project.domain.gallery.dto.response.GalleryDetailResponse;
import multi.second.project.domain.gallery.dto.response.GalleryListResponse;
import multi.second.project.domain.gallery.repository.GalleryRepository;
import multi.second.project.domain.member.domain.Member;
import multi.second.project.domain.member.dto.Principal;
import multi.second.project.domain.member.repository.MemberRepository;
import multi.second.project.infra.code.ErrorCode;
import multi.second.project.infra.exception.AuthException;
import multi.second.project.infra.exception.HandlableException;
import multi.second.project.infra.util.file.FilePath;
import multi.second.project.infra.util.file.FileRepository;
import multi.second.project.infra.util.file.FileUtil;
import multi.second.project.infra.util.file.dto.FilePathDto;
import multi.second.project.infra.util.file.dto.FileUploadDto;
import multi.second.project.infra.util.paging.Paging;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class CommentService {

	private final GalleryRepository galleryRepository;
	private final MemberRepository memberRepository;
	private final CommentRepository commentRepository;
	
	//포스트의 댓글 가져오는 코드

//	public List<CommentListResponse> findCommentListByPostIdx(Long postIdx) {
//		
//		System.out.println();
//		//return CommentListResponse.toDtoList(galleryRepository.findCommentByPostIdx(postIdx));
//		return null;
//	}

	
	@Transactional
	public void deleteComment(Long cmIdx, Principal principal, Long postIdx) {
		// TODO Auto-generated method stub
		Comment comment = commentRepository.findById(cmIdx)
					.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		Gallery gallery = galleryRepository.findById(postIdx)
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		
		if(!comment.getMember().getUserId().equals(principal.getUserId())) throw new AuthException(ErrorCode.UNAUTHORIZED_REQUEST);
		
		gallery.removeComment(comment);
		commentRepository.delete(comment);
		
	}
	
	
	@Transactional
	public void createComment(CommentRegistRequest dto, Long postIdx) {
		// TODO Auto-generated method stub
		Member member = memberRepository.findById(dto.getUserId()).get();
		Comment comment = Comment.createComment(dto, member);
		
		Gallery gallery = galleryRepository.findById(postIdx) //인데스값만 담긴 갤러리 엔티티
				.orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
//		Gallery gallery = galleryRepository.findById(null).get();
//				addComment(comment);
		
		// JPA가 변경된 내용을 데이터베이스에 반영
		gallery.addComment(comment);
		commentRepository.saveAndFlush(comment);
		
		
		
	}
	
	@Transactional
	public void updateComment(CommentModifyRequest dto) {
		// TODO Auto-generated method stub
		System.out.println("dto.getCmIdx() :  "+dto.getCmIdx());
		Comment comment = commentRepository.findById(dto.getCmIdx()).orElseThrow(() -> new HandlableException(ErrorCode.NOT_EXISTS));
		System.out.println("comment.getMember().getUserId() :  " +comment.getMember().getUserId());
		System.out.println("dto.getUserId() :  " +dto.getUserId());
		if(!comment.getMember().getUserId().equals(dto.getUserId())) throw new AuthException(ErrorCode.UNAUTHORIZED_REQUEST);
		//if(!comment.getGallery().getPostIdx().equals(dto.getPostIdx())) throw new AuthException(ErrorCode.UNAUTHORIZED_REQUEST);//(피드백)필요없음 UserId만 검사
		System.out.println("dto :  "+dto);
		comment.updateComment(dto);
		
		commentRepository.flush();
		
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
