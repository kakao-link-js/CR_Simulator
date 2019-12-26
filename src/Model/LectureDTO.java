package Model;

import java.util.ArrayList;

public class LectureDTO {

	public String openUniv;		//개설대학
	public String major;		//개설학과전공
	public String courseNum;	//학수번호
	public String classNum;		//분반
	public String className;	//교과목명
	public String completion;	//이수구분
	public float grade;			//학년
	public float score; 		//학점
	public String time; 		// 요일 및 강의시간
	public String classRoom; 	//강의실
	public String professor; 	//교수

	// 1.constructor
	public LectureDTO(Object[] obj) {
		this.openUniv	= obj[1].toString();
		this.major		= obj[2].toString();
		this.courseNum	= obj[3].toString();
		this.classNum 	= obj[4].toString();
		this.className  = obj[5].toString();
		this.completion = obj[6].toString();
		this.grade  	= Float.parseFloat(obj[7].toString());
		this.score  	= Float.parseFloat(obj[8].toString());
		this.time  		= obj[9].toString();
		this.classRoom  = obj[10].toString();
		this.professor 	= obj[11].toString();
	} // public LectureVO(Object[])

	public LectureDTO(ArrayList<String> lectureDataArrayList) {
		this.openUniv	= lectureDataArrayList.get(0);
		this.major		= lectureDataArrayList.get(1);
		this.courseNum	= lectureDataArrayList.get(2);
		this.classNum 	= lectureDataArrayList.get(3);
		this.className 	= lectureDataArrayList.get(4);
		this.completion = lectureDataArrayList.get(5);
		this.grade		= Float.parseFloat(lectureDataArrayList.get(6));
		this.score		= Float.parseFloat(lectureDataArrayList.get(7));
		this.time		= lectureDataArrayList.get(8);
		this.classRoom  = lectureDataArrayList.get(9);
		this.professor	= lectureDataArrayList.get(10);
	} // LectureVO(ArrayList<String>)

	public LectureDTO(String openUniv, String major, String courseNum, String classNum, String className,
					  String completion, String grade, String score, String time, String classRoom, String professor) {
		this.openUniv	= openUniv;
		this.major		= major;
		this.courseNum	= courseNum;
		this.classNum	= classNum;
		this.className	= className;
		this.completion = completion;
		this.grade 		= Float.parseFloat(grade);
		this.score 		= Float.parseFloat(score);
		this.time 		= time;
		this.classRoom 	= classRoom;
		this.professor 	= professor;
	} // public LectureVO(String)

	// 문장 배열로 반환하는 메소드
	public String[] makeStringArray() {
		String output[] = new String[12];
		output[0] = "";
		output[1] = openUniv;
		output[2] = major;
		output[3] = courseNum;
		output[4] = classNum;
		output[5] = className;
		output[6] = completion;
		output[7] = Float.toString(grade);
		output[8] = Float.toString(score);
		output[9] = time;
		output[10] = classRoom;
		output[11] = professor;
		return output;
	} // public String[] makeStringArray()
}
