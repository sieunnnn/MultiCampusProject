package multi.second.project.domain.board;


import lombok.RequiredArgsConstructor;
import multi.second.project.domain.board.domain.Board;
import multi.second.project.domain.board.dto.request.BoardModifyRequest;
import multi.second.project.domain.board.dto.request.BoardRegistRequest;
import multi.second.project.domain.board.dto.response.BoardDetailResponse;
import multi.second.project.domain.member.UserPrincipal;
import multi.second.project.infra.util.file.dto.FilePathDto;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("board")
public class BoardController {

	private final BoardService boardService;

	@GetMapping("form")
	public String boardForm() {
		return "/board/board-form";
	}

	@PostMapping("upload")
	public String upload(
			@RequestParam List<MultipartFile> files,
			BoardRegistRequest dto,
			Model model
	) {

		dto.setUserId(UserPrincipal.getUserPrincipal().getUserId());
		boardService.createBoard(dto, files);
		model.addAttribute("message", "글 작성이 완료되었습니다.");
		model.addAttribute("searchUrl","/board/list");
		return "board/message";

	}
	//	@GetMapping("list")
//	public String boardList(Model model,
//							@PageableDefault(page = 0, size = 10, sort = "bdIdx", direction = Sort.Direction.DESC)
//							Pageable pageable,
//							String searchKeyword){ // 데이터를 담아 페이지로 보내기 위해 Model 자료형을 인자로 , 검색할 때 (searchKeyword가 있을 떄) 안할 때 구분해 if문 사용
//
//		Page<Board> list = null;
//
//		if(searchKeyword != null){
//			list = boardService.boardSearchList(searchKeyword, pageable);
//
//		}
//
//		int nowPage = list.getPageable().getPageNumber() + 1; // 현재 페이지를 가져옴 , 0에서 시작하기에 처리를 위해 + 1
//		int startPage = Math.max(nowPage - 4, 1); // Math.max(a, b) -- a 와 b 중 큰 값을 반환 --> 그냥 nowPAge - 4만 하면 nowpage가 1인 경우 -3도 가능하기에 이를 방지하기 위함
//		int endPage = Math.min(nowPage + 5, list.getTotalPages()); // totalPage보다 크면 안되기에 두개 중 최소값 반환하는 Math.min을 사용
//
//		model.addAttribute("list", list ); // boardService에서 생성한 boardlist메소드를 통해 list가 반환되는데 해당 list를 "list"라는 이름으로 넘겨주겠다는 것(html에 나올 수 있게)
//		model.addAttribute("nowPage", nowPage);
//		model.addAttribute("startPage", startPage);
//		model.addAttribute("endPage", endPage);
//
//		return "/board/board-list";
//	}
	@GetMapping("list")
	public String boardList(
			@PageableDefault(size=10, sort="bdIdx", direction = Sort.Direction.DESC, page = 0)
			Pageable pageable,
			Model model,
			String searchKeyword

	) {

		//Page<Board> list = null;

		if(searchKeyword != null){
			Map<String, Object> commandMap = boardService.boardSearchList(searchKeyword, pageable);
			model.addAllAttributes(commandMap);
		} else {
			Map<String, Object> commandMap = boardService.findBoardList(pageable);
			model.addAllAttributes(commandMap);
		}

//		int nowPage = list.getPageable().getPageNumber() + 1; // 현재 페이지를 가져옴 , 0에서 시작하기에 처리를 위해 + 1
//		int startPage = Math.max(nowPage - 4, 1); // Math.max(a, b) -- a 와 b 중 큰 값을 반환 --> 그냥 nowPAge - 4만 하면 nowpage가 1인 경우 -3도 가능하기에 이를 방지하기 위함
//		int endPage = Math.min(nowPage + 5, list.getTotalPages()); // totalPage보다 크면 안되기에 두개 중 최소값 반환하는 Math.min을 사용
//
//		model.addAttribute("list", list ); // boardService에서 생성한 boardlist메소드를 통해 list가 반환되는데 해당 list를 "list"라는 이름으로 넘겨주겠다는 것(html에 나올 수 있게)
//		model.addAttribute("nowPage", nowPage);
//		model.addAttribute("startPage", startPage);
//		model.addAttribute("endPage", endPage);

		//Map<String, Object> commandMap = boardService.findBoardList(pageable);



		return "/board/board-list";
	}

	@GetMapping("detail")
	public String boardDetail(Model model, Long bdIdx) {

		BoardDetailResponse dto = boardService.findBoardByBdIdx(bdIdx);
		model.addAttribute("board", boardService.findBoardByBdIdx(bdIdx));
		return "/board/board-contents";
	}

	@GetMapping("download")
	public ResponseEntity<FileSystemResource> downloadFile(Long fpIdx){

		FilePathDto dto = boardService.findFilePathByFpIdx(fpIdx);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDisposition(ContentDisposition.builder("attachment")
				.filename(dto.getOriginFileName(), Charset.forName("utf-8"))
				.build());

		FileSystemResource fsr = new FileSystemResource(dto.getFullPath());
		return ResponseEntity.ok().headers(headers).body(fsr);
	}


	@GetMapping("modify")
	public String boardModify(Long bdIdx, Model model) {
		BoardDetailResponse dto = boardService.findBoardByBdIdx(bdIdx);
		model.addAttribute("board", dto);
		return "/board/board-modify";

	}

	@PostMapping("modify")
	public String modify(Model model,
						 BoardModifyRequest dto,
						 @RequestParam List<MultipartFile> fileList

	) {

		dto.setUserId(UserPrincipal.getUserPrincipal().getUserId());
		boardService.updateBoard(dto, fileList);

		model.addAttribute("message", "글 수정이 완료되었습니다.");
		model.addAttribute("searchUrl","/board/detail?bdIdx="+dto.getBdIdx());
		return "board/message";
	}

	@PostMapping("remove")
	public String remove(Long bdIdx,Model model) {
		boardService.removeBoard(bdIdx, UserPrincipal.getUserPrincipal().getPrincipal());
		model.addAttribute("message", "글 삭제가 완료되었습니다.");
		model.addAttribute("searchUrl","/board/list");
		return "board/message";

	}



























}