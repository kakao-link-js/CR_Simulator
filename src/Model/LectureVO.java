package Model;

public class LectureVO {
	public String openUniv = null; //개설대학
	public String major = null; //개설학과전공
	public String courseNum = null; //학수번호
	public String classNum = null; //분반
	public String className = null; //교과목명
	public String completion = null; //이수구분
	public int grade; //학년
	public int score; //학점
	public String time = null; // 요일 및 강의시간
	public String classRoom = null; //강의실
	public String professor = null; //교수

	public LectureVO() {

	}

	public LectureVO(String openUniv,String major,String courseNum,String classNum, String className, String completion,String grade,String score,String time, String classRoom, String professor) {
		this.openUniv = openUniv;
		this.major = major;
		this.courseNum = courseNum;
		this.classNum = classNum;
		this.className = className;
		this.completion = completion;
		this.grade = Integer.parseInt(grade);
		this.score = Integer.parseInt(score);
		this.time = time;
		this.classRoom = classRoom;
		this.professor = professor;
	}
}