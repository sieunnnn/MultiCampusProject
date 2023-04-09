package multi.second.project.domain.board.domain;

public enum BoardType {
    notice("공지사항"),
    advertise("광고"),
    course("여행코스"),
    free("자유게시판");



    private String value;

    BoardType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}