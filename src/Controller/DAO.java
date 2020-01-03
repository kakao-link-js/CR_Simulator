package Controller;

import Model.*;
import common.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * DAO Class
 * 서버와 통신을 담당하는 기능을 하는 Class입니다.
 */

public class DAO {
    private JSONArray resultData = null; //결과값을 받기 위한 변수
    private URL url = null;
    HttpURLConnection conn = null;
    BufferedReader br = null;
    OutputStreamWriter wr = null;
    JSONObject jsonObject;

    // 아이디와 패스워드가 일치하는지
    public boolean isCorrectID(String id, String pw) {
        String route = "login";
        JSONObject jsonObject = new JSONObject();
        if(id == null || pw == null) return false;
        jsonObject.put(Constants.ID_TXT,id);
        jsonObject.put(Constants.PASSWORD_TXT,pw);

        jsonObject = sendRequest(Constants.BASE_URL+route,jsonObject.toString(),Constants.POST_TXT);

        // 결과 처리
        if(jsonObject == null)
            return false;
        System.out.println("isCorrectID : " + jsonObject.toString());
        if(jsonObject.get(Constants.SUCCESS_TXT).equals(Constants.TRUE_TXT))
            return true;
        return false;
    }

    // 이름과 이메일을 통해 아이디를 돌려줌
    public String getID(String name, String email){
        String route = "user/find/email";
        JSONObject jsonObject = new JSONObject();
        if(name == null || email == null) return null;
        jsonObject.put(Constants.NAME_TXT,name);
        jsonObject.put(Constants.EMAIL_TXT,email);

        jsonObject = sendRequest(Constants.BASE_URL+route,jsonObject.toString(),Constants.POST_TXT);

        // 결과 처리
        if(jsonObject == null)
            return null;
        if(jsonObject.get(Constants.SUCCESS_TXT).equals(Constants.TRUE_TXT)) {
            jsonObject = (JSONObject) jsonObject.get("user");
            System.out.println(jsonObject.toString());
            return jsonObject.get(Constants.ID_TXT).toString();
        }
        return null;
    }


    //아이디 중복 체크
    public boolean isDuplicateID(String id) {
        String route = "signup/";

        if(id == null) return false;

        jsonObject = sendGet(Constants.BASE_URL+route+id);

        // 결과 처리
        if(jsonObject == null)
            return false;

        System.out.println("isDuplicateID : " + jsonObject.toString());

        if(jsonObject.get(Constants.SUCCESS_TXT).equals(Constants.TRUE_TXT)) //true면 굿.
            return true;
        return false;
    }

    // 회원가입
    public boolean signUp(UserDTO user){
        String router = "signup";
        JSONObject jsonObject = new JSONObject();

        if(user == null) return false;

        jsonObject.put(Constants.ID_TXT,user.getId());
        jsonObject.put(Constants.PASSWORD_TXT,user.getPassword());
        jsonObject.put(Constants.NAME_TXT,user.getName());
        jsonObject.put(Constants.PHONE_TXT,user.getPhone());
        jsonObject.put(Constants.EMAIL_TXT,user.getEmail());

        jsonObject = sendRequest(Constants.BASE_URL+router,jsonObject.toString(),Constants.POST_TXT);

        // 결과 처리
        if(jsonObject == null)
            return false;
        System.out.println("signUp : " + jsonObject.toString());
        if(jsonObject.get(Constants.SUCCESS_TXT).equals(Constants.TRUE_TXT))
            return true;
        return false;
    }

    // 필터링한 결과 과목들 반환
    public ArrayList<LectureDTO> getFilterLecture(JSONObject jsonObject){
        String route = "timetable";
        ArrayList<LectureDTO> lectureData = new ArrayList<LectureDTO>();

        resultData = sendPostAtArray(Constants.BASE_URL+route,jsonObject.toString());

        // 결과 처리
        if(resultData == null || resultData.size() < 1)
            return lectureData;

        System.out.println("getFilterLecture : " + resultData.get(0).toString());

        lectureData = new ArrayList<LectureDTO>();
        for(int i = 0 ; i < resultData.size();i++){
            lectureData.add(new LectureDTO((JSONObject)resultData.get(i)));
        }
        return lectureData;
    }

    // 내가 신청한 과목들을 반환
    public ArrayList<LectureDTO> getMyLecture(UserDTO user){
        String route = "reg/"+user.getId();
        ArrayList<LectureDTO> lectureData = new ArrayList<LectureDTO>();
        if(user == null) return lectureData;

        resultData = sendGetAtArray(Constants.BASE_URL+route);

        // 결과 처리
        if(resultData == null || resultData.size() < 1)
            return lectureData;

        System.out.println("getMyLecture : "+resultData.get(0).toString());

        lectureData = new ArrayList<LectureDTO>();
        for(int i = 0 ; i < resultData.size();i++)
            lectureData.add(new LectureDTO((JSONObject)resultData.get(i)));
        return lectureData;
    }

    // 수강신청
    public boolean applyLecture(UserDTO user, LectureDTO lecture){
        String route ="reg";
        JSONObject jsonObject = new JSONObject();
        if(user == null || lecture == null) return false;
        jsonObject.put(Constants.ID_TXT,user.getId());
        jsonObject.put(Constants.MAJOR_TXT,lecture.getMajor());
        jsonObject.put(Constants.COURSENUM_TXT,lecture.getCourseNum());
        jsonObject.put(Constants.CLASSNUM_TXT,lecture.getClassNum());

        jsonObject = sendRequest(Constants.BASE_URL+route,jsonObject.toString(),Constants.PUT_TXT);

        // 결과 처리
        if(jsonObject == null)
            return false;

        if(jsonObject.get(Constants.SUCCESS_TXT).equals(Constants.TRUE_TXT))
            return true;
        return false;
    }

    //신청 취소
    public boolean cancelLecture(UserDTO user, LectureDTO lecture){
        String route = "reg";
        JSONObject jsonObject = new JSONObject();
        if(user == null || lecture == null) return false;
        jsonObject.put(Constants.ID_TXT,user.getId());
        jsonObject.put(Constants.COURSENUM_TXT,lecture.getCourseNum());
        jsonObject.put(Constants.CLASSNUM_TXT,lecture.getClassNum());
        jsonObject.put(Constants.MAJOR_TXT,lecture.getMajor());

        jsonObject = sendRequest(Constants.BASE_URL+route,jsonObject.toString(),Constants.DELETE_TXT);

        // 결과 처리
        if(jsonObject == null)
            return false;

        if(jsonObject.get(Constants.SUCCESS_TXT).equals(Constants.TRUE_TXT))
            return true;
        return false;
    }

    // 정보수정
    public boolean modifyState(UserDTO user){
        String route = "user";
        JSONObject jsonObject = new JSONObject();
        if(user == null) return false;
        jsonObject.put(Constants.ID_TXT,user.getId());
        jsonObject.put(Constants.PASSWORD_TXT,user.getPassword());
        jsonObject.put(Constants.NAME_TXT,user.getName());
        jsonObject.put(Constants.PHONE_TXT,user.getPhone());
        jsonObject.put(Constants.EMAIL_TXT,user.getEmail());

        jsonObject = sendRequest(Constants.BASE_URL+route,jsonObject.toString(), Constants.PUT_TXT);

        // 결과 처리
        if(jsonObject == null)
            return false;
        System.out.println("modify : " + jsonObject.toString());
        if(jsonObject.get(Constants.SUCCESS_TXT).equals(Constants.TRUE_TXT))
            return true;
        return false;
    }

    //유저 정보 받기
    public UserDTO getUserData(String userId){
        String route = "user/";
        JSONObject jsonObject;
        if(userId == null) return null;

        jsonObject = sendGet(Constants.BASE_URL+route+userId);

        // 결과 처리
        if(jsonObject == null)
            return null;
        System.out.println("getUserData : " + jsonObject.toString());
        jsonObject = (JSONObject)jsonObject.get("user");
        System.out.println("getUserData : " + jsonObject.toString());

        UserDTO user = new UserDTO();
        user.setId(jsonObject.get(Constants.ID_TXT).toString());
        user.setPassword(jsonObject.get(Constants.PASSWORD_TXT).toString());
        user.setName(jsonObject.get(Constants.NAME_TXT).toString());
        user.setPhone(jsonObject.get(Constants.PHONE_TXT).toString());
        user.setEmail(jsonObject.get(Constants.EMAIL_TXT).toString());
        return user;
    }

    //서버에 리퀘스트를 보낸 후 받는 메소드
    public JSONObject sendRequest(String strUrl, String jsonMessage, String quary){
        try {
            JSONObject output = new JSONObject();
            JSONParser parser = new JSONParser();
            url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000); //서버에 연결되는 Timeout 시간 설정
            conn.setReadTimeout(5000); // InputStream 읽어 오는 Timeout 시간 설정
            conn.setRequestMethod(quary);

            //json으로 message를 전달하고자 할 때
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoInput(true);
            conn.setDoOutput(true); //POST 데이터를 OutputStream으로 넘겨 주겠다는 설정
            conn.setUseCaches(false);
            conn.setDefaultUseCaches(false);

            wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(jsonMessage); //json 형식의 message 전달
            wr.flush();

            //Stream을 처리해줘야 하는 귀찮음이 있음.
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                output = (JSONObject) parser.parse(line);
            }

            br.close();
            return output;
        } catch (Exception e){
            System.err.println(e.toString());
            return null;
        }
    }

    //POST 메소드 리퀘스트 후 Array로 받는 메소드
    public JSONArray sendPostAtArray(String strUrl, String jsonMessage){
        try {
            JSONArray output = new JSONArray();
            JSONParser parser = new JSONParser();
            url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000); //서버에 연결되는 Timeout 시간 설정
            conn.setReadTimeout(5000); // InputStream 읽어 오는 Timeout 시간 설정
            conn.setRequestMethod("POST");

            //json으로 message를 전달하고자 할 때
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoInput(true);
            conn.setDoOutput(true); //POST 데이터를 OutputStream으로 넘겨 주겠다는 설정
            conn.setUseCaches(false);
            conn.setDefaultUseCaches(false);

            wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(jsonMessage); //json 형식의 message 전달
            wr.flush();

            //Stream을 처리해줘야 하는 귀찮음이 있음.
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                output = (JSONArray) parser.parse(line);
            }

            br.close();
            return output;
        } catch (Exception e){
            System.err.println(e.toString());
            return null;
        }
    }

    //GET메소드 리퀘스트
    private JSONObject sendGet(String targetUrl){
        try {
            JSONObject output = new JSONObject();
            JSONParser parser = new JSONParser();
            url = new URL(targetUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET"); // optional default is GET
            conn.setConnectTimeout(3000); //서버에 연결되는 Timeout 시간 설정
            conn.setReadTimeout(3000); // InputStream 읽어 오는 Timeout 시간 설정
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                output = (JSONObject) parser.parse(inputLine);
            }
            br.close(); // print result
            return output;
        } catch (Exception e) {
            System.err.println(e.toString());
            return null;
        }
    }

    //GET메소드 Array 반환 리퀘스트
    private JSONArray sendGetAtArray(String targetUrl){
        try {
            JSONArray output = new JSONArray();
            JSONParser parser = new JSONParser();
            url = new URL(targetUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET"); // optional default is GET
            conn.setConnectTimeout(3000); //서버에 연결되는 Timeout 시간 설정
            conn.setReadTimeout(3000); // InputStream 읽어 오는 Timeout 시간 설정
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                jsonObject = (JSONObject) parser.parse(inputLine);
            }
            jsonObject = (JSONObject) jsonObject.get("reg");
            output = (JSONArray) jsonObject.get("timetable");
            br.close(); // print result
            return output;
        } catch (Exception e) {
            System.err.println(e.toString());
            return null;
        }
    }


}
