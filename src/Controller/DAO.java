package Controller;

import Model.*;
import common.*;
import org.json.simple.*;

import java.util.ArrayList;

public class DAO {
    private JSONArray resultData = null; //결과값을 받기 위한 변수

    // 아이디와 패스워드가 일치하는지
    public boolean isCorrectID(String id, String pw) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constants.ID_TXT,id);
        jsonObject.put(Constants.PASSWORD_TXT,pw);
        jsonObject.put(Constants.URL_TXT,Constants.BASE_URL+"");

        // 네트워크 처리 비동기화
        //resultData = new NetworkProcessor().execute(jsonObject).get();

        // 결과 처리
        if(resultData == null)
            return false;

        jsonObject = (JSONObject)resultData.get(0);
        if(jsonObject.get("success").equals("true"))
            return true;
        return false;
    }

    // 이름과 전화번호를 통해 아이디를 돌려줌
    public String getID(String name, String phone){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constants.NAME_TXT,name);
        jsonObject.put(Constants.PHONE_TXT,phone);
        jsonObject.put(Constants.URL_TXT,Constants.BASE_URL+"");

        // 네트워크 처리 비동기화
        //resultData = new NetworkProcessor().execute(jsonObject).get();

        // 결과 처리
        if(resultData == null)
            return null;

        jsonObject = (JSONObject)resultData.get(0);
        if(jsonObject.get("success").equals("true"))
            return (String) jsonObject.get("id");
        return null;
    }


    //아이디 중복 체크
    public boolean isDuplicateID(String id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constants.ID_TXT, id);
        jsonObject.put(Constants.URL_TXT, Constants.BASE_URL+"");

        // 네트워크 처리 비동기화
        //resultData = new NetworkProcessor().execute(jsonObject).get();

        // 결과 처리
        if(resultData == null)
            return false;
        
        jsonObject = (JSONObject)resultData.get(0); //0번째 값을 받는다.
        if(jsonObject.get("success").equals("true")) //true면 굿.
            return true;
        return false;
    }

    // 회원가입
    public boolean signUp(UserDTO user){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constants.ID_TXT,user.getId());
        jsonObject.put(Constants.PASSWORD_TXT,user.getPassword());
        jsonObject.put(Constants.NAME_TXT,user.getName());
        jsonObject.put(Constants.PHONE_TXT,user.getPhone());
        jsonObject.put(Constants.BIRTH_TXT,user.getBirth());
        jsonObject.put(Constants.URL_TXT,Constants.BASE_URL+"");

        // 네트워크 처리 비동기화
        //resultData = new NetworkProcessor().execute(jsonObject).get();

        // 결과 처리
        if(resultData == null)
            return false;

        jsonObject = (JSONObject)resultData.get(0);
        if(jsonObject.get("success").equals("true"))
            return true;
        return false;
    }

    // 필터링한 결과 과목들 반환
    public ArrayList<LectureDTO> getfilterLecture(JSONObject jsonObject){
        ArrayList<LectureDTO> lectureData = null;

        jsonObject.put(Constants.URL_TXT,Constants.BASE_URL+"");

        // 네트워크 처리 비동기화
        //resultData = new NetworkProcessor().execute(jsonObject).get();

        // 결과 처리
        if(resultData == null)
            return null;
        lectureData = new ArrayList<LectureDTO>();
        for(int i = 0 ; i < resultData.size();i++){
            lectureData.add(new LectureDTO((JSONObject)resultData.get(i)));
        }
        return lectureData;
    }

    // 내가 신청한 과목들을 반환
    public ArrayList<LectureDTO> getMyLecture(UserDTO user){
        ArrayList<LectureDTO> lectureData = null;
        //처리 설정
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constants.ID_TXT,user.getId());
        jsonObject.put(Constants.URL_TXT,Constants.BASE_URL+"");

        // 네트워크 처리 비동기화
        //resultData = new NetworkProcessor().execute(jsonObject).get();

        // 결과 처리
        if(resultData == null)
            return null;
        lectureData = new ArrayList<LectureDTO>();
        for(int i = 0 ; i < resultData.size();i++){
            lectureData.add(new LectureDTO((JSONObject)resultData.get(i)));
        }
        return lectureData;
    }

    // 수강신청
    public boolean applyLecture(UserDTO user, LectureDTO lecture){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constants.ID_TXT,user.getId());
        jsonObject.put(Constants.COURSENUM_TXT,lecture.getCourseNum());
        jsonObject.put(Constants.CLASSNUM_TXT,lecture.getClassNum());
        jsonObject.put(Constants.URL_TXT,Constants.BASE_URL+"");

        // 네트워크 처리 비동기화
        //resultData = new NetworkProcessor().execute(jsonObject).get();

        // 결과 처리
        if(resultData == null)
            return false;

        jsonObject = (JSONObject)resultData.get(0);
        if(jsonObject.get("success").equals("true"))
            return true;
        return false;
    }

    //신청 취소
    public boolean cancelLecture(UserDTO user, LectureDTO lecture){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constants.ID_TXT,user.getId());
        jsonObject.put(Constants.COURSENUM_TXT,lecture.getCourseNum());
        jsonObject.put(Constants.CLASSNUM_TXT,lecture.getClassNum());
        jsonObject.put(Constants.URL_TXT,Constants.BASE_URL+"");

        // 네트워크 처리 비동기화
        //resultData = new NetworkProcessor().execute(jsonObject).get();

        // 결과 처리
        if(resultData == null)
            return false;

        jsonObject = (JSONObject)resultData.get(0);
        if(jsonObject.get("success").equals("true"))
            return true;
        return false;
    }

    // 정보수정
    public boolean modifyState(UserDTO user){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(Constants.ID_TXT,user.getId());
        jsonObject.put(Constants.PASSWORD_TXT,user.getPassword());
        jsonObject.put(Constants.NAME_TXT,user.getName());
        jsonObject.put(Constants.PHONE_TXT,user.getPhone());
        jsonObject.put(Constants.BIRTH_TXT,user.getBirth());
        jsonObject.put(Constants.URL_TXT,Constants.BASE_URL+"");

        // 네트워크 처리 비동기화
        //resultData = new NetworkProcessor().execute(jsonObject).get();

        // 결과 처리
        if(resultData == null)
            return false;

        jsonObject = (JSONObject)resultData.get(0);
        if(jsonObject.get("success").equals("true"))
            return true;
        return false;
    }

}
