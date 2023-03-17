package multi.second.project.infra.util.paging;

import org.springframework.data.domain.Page;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Paging {
	
	private int currentPage; //현재 페이지
	private int total;		//전체 게시물
	private int cntPerPage; //페이지당 게시물 수
	private int blockCnt;  //하단에 표시될 페이지 블록 개수
	
	private int prevPage;
	private int nextPage;
	private int lastPage;
	
	private int lastBlock;
	private int blockStart;
	private int blockEnd;

	private Paging(Builder builder) {
		this.currentPage = builder.currentPage;
		this.total = builder.total;
		this.cntPerPage = builder.cntPerPage;
		this.blockCnt = builder.blockCnt;
		this.lastBlock = builder.lastBlock;
		
		this.lastPage = total/cntPerPage;
		this.prevPage = currentPage <= 0 ? 0 : currentPage - 1;
		this.nextPage = currentPage >= lastPage ? currentPage:currentPage + 1;

		this.blockStart = (currentPage/blockCnt) * blockCnt + 1 ;
		this.blockEnd = blockStart + blockCnt > lastBlock ? lastBlock : blockStart + blockCnt -1;
	}

	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		private int currentPage; //현재 페이지
		private int total;		//전체 게시물
		private int cntPerPage; //페이지당 게시물 수
		private int blockCnt;  //하단에 표시될 페이지 블록 개수
		private int lastBlock;
		
		public Builder page(Page page) {
			this.currentPage = page.getNumber();
			this.total = (int) page.getTotalElements();
			this.lastBlock = page.getTotalPages();
			this.cntPerPage = page.getSize();
			return this;
		}
		
		public Builder blockCnt(int blockCnt) {
			this.blockCnt = blockCnt;
			return this;
		}
		
		public Paging build() {
			return new Paging(this);
		}
		
	}
	
	
	
	
	

}
