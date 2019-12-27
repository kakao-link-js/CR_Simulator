package Controller;

import Model.*;
import common.*;
import org.apache.xmlbeans.impl.jam.JSourcePosition;
import org.json.simple.*;

import java.util.ArrayList;

public class DAO {
    private JSONArray resultData = null; //결과값을 받기 위한 변수

    // 아이디와 패스워드가 일치하는지
    public boolean isCorrectID(String id, String pw) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",id);
        jsonObject.put("password",pw);
        jsonObject.put("url",Constants.BASE_URL+"");

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
    public String checkIdDuplication(String name,String phone){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name",name);
        jsonObject.put("phone",phone);
        jsonObject.put("url",Constants.BASE_URL+"");

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
    public boolean canGetID(String id) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("url", Constants.BASE_URL+"");

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
        jsonObject.put("id",user.getId());
        jsonObject.put("password",user.getPassword());
        jsonObject.put("name",user.getName());
        jsonObject.put("phone",user.getPhone());
        jsonObject.put("birth",user.getBirth());
        jsonObject.put("url",Constants.BASE_URL+"");

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
    public JSONArray getfilterLecture(ArrayList<String> filter){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("개 설 학 과 전 공",filter.get(0));
        jsonObject.put("학 수 번 호",filter.get(1));
        jsonObject.put("교 과 목 명",filter.get(2));
        jsonObject.put("교 수 명",filter.get(3));
        jsonObject.put("학 년",filter.get(4));
        jsonObject.put("분 반",filter.get(5));
        jsonObject.put("이 수 구 분",filter.get(6));

        jsonObject.put("url",Constants.BASE_URL+"");

        // 네트워크 처리 비동기화
        //resultData = new NetworkProcessor().execute(jsonObject).get();

        // 결과 처리
        if(resultData == null)
            return null;
        jsonObject = (JSONObject)resultData.get(0);
        if(jsonObject.get("success").equals("false"))
            return null;
        return resultData;
    }

    // 내가 신청한 과목들을 반환
    public JSONArray getMyLecture(UserDTO user){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",user.getId());
        jsonObject.put("url",Constants.BASE_URL+"");

        // 네트워크 처리 비동기화
        //resultData = new NetworkProcessor().execute(jsonObject).get();

        // 결과 처리
        if(resultData == null)
            return null;
        jsonObject = (JSONObject)resultData.get(0);
        if(jsonObject.get("success").equals("false"))
            return null;
        return resultData;
    }

    // 수강신청
    public boolean applyLecture(UserDTO user, LectureDTO lecture){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",user.getId());
        jsonObject.put("학 수 번 호",lecture.courseNum);
        jsonObject.put("분반",lecture.classNum);
        jsonObject.put("url",Constants.BASE_URL+"");

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
        jsonObject.put("id",user.getId());
        jsonObject.put("학 수 번 호",lecture.courseNum);
        jsonObject.put("분반",lecture.classNum);
        jsonObject.put("url",Constants.BASE_URL+"");

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
        jsonObject.put("id",user.getId());
        jsonObject.put("password",user.getPassword());
        jsonObject.put("name",user.getName());
        jsonObject.put("phone",user.getPhone());
        jsonObject.put("birth",user.getBirth());
        jsonObject.put("url",Constants.BASE_URL+"");

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
