package common;

/**
 * ExceptionHandling Class
 * 예외처리를 위한 클래스 입니다.
 */

public class ExceptionHandling {

    public static boolean isOnlyKorean(String name){
        if(name.matches(Constants.KOREAN_REGEX))
            return true;
        return false;
    }

    public static boolean isOnlyNumber(String id){
        if(id.matches(Constants.NUMBER_REGEX))
            return true;
        return false;
    }

    public static boolean isPhoneNumber(String phone){
        if(phone.matches(Constants.PHONE_REGEX))
            return true;
        return false;
    }
}
