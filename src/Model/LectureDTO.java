package Model;

import common.Constants;
import org.json.simple.JSONObject;

import java.util.ArrayList;

/**
 * LectureDTO Class
 * 강의의 정보를 갖고 전달하기 위해 만들어진 Class
 */

public class LectureDTO {

	private String openUniv;		//개설대학
	private String major;		//개설학과전공
	private String courseNum;	//학수번호
	private String classNum;		//분반
	private String className;	//교과목명
	private String completion;	//이수구분
	private float grade;			//학년
	private float score; 		//학점
	private String time; 		// 요일 및 강의시간
	private String classRoom; 	//강의실
	private String professor; 	//교수
	private int numberOfPeople; //신청인원

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
		if(obj[9] != null) this.time = obj[9].toString();
		else this.time = null;
		if(obj[10] != null) this.classRoom = obj[10].toString();
		else this.classRoom  = null;
		if(obj[11] != null) this.professor 	= obj[11].toString();
		else this.professor = null;
		this.numberOfPeople = 0;
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
		this.numberOfPeople = Integer.parseInt(lectureDataArrayList.get(11));
	} // LectureVO(ArrayList<String>)

	public LectureDTO(JSONObject jsonObject){
		this.openUniv = (String) jsonObject.get(Constants.OPENUNIV_TXT);
		this.major = (String) jsonObject.get(Constants.MAJOR_TXT);
		this.courseNum	= (String) jsonObject.get(Constants.COURSENUM_TXT);
		this.classNum 	= (String) jsonObject.get(Constants.CLASSNUM_TXT);
		this.className 	= (String) jsonObject.get(Constants.CLASSNAME_TXT);
		this.completion = (String) jsonObject.get(Constants.COMPLETION_TXT);
		this.grade		= Float.parseFloat(jsonObject.get(Constants.GRADE_TXT).toString());
		this.score		= Float.parseFloat(jsonObject.get(Constants.SCORE_TXT).toString());
		this.time		= (String) jsonObject.get(Constants.TIME_TXT);
		this.classRoom  = (String) jsonObject.get(Constants.CLASSROOM_TXT);
		this.professor	= (String) jsonObject.get(Constants.PROFESSOR_TXT);
		if(jsonObject.get(Constants.NUMBER_TXT) != null)
			this.numberOfPeople = Integer.parseInt(jsonObject.get(Constants.NUMBER_TXT).toString());
		else
			this.numberOfPeople = 0;
	}

	// 문장 배열로 반환하는 메소드
	public String[] makeStringArray() {
		String output[] = new String[13];
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
		output[12] = String.valueOf(numberOfPeople);
	return output;
	} // public String[] makeStringArray()

	public String getOpenUniv(){ return openUniv; } //개설대학
	public String getMajor(){ return major; } //개설전공
	public String getCourseNum(){return courseNum;} //학수번호
	public String getClassNum(){return classNum;} //분반
	public String getClassName(){return className;}	//교과목명
	public String getCompletion(){return completion;} //이수구분
	public float getGrade(){return grade;} //학년
	public float getScore(){return score;} //학점
	public String getTime(){return time;} // 요일 및 강의시간
	public String getClassRoom(){return classRoom;}	//강의실
	public String getProfessor(){return professor;}	//교수
	public int getNumberOfPeople(){return numberOfPeople;} //신청인원
}
