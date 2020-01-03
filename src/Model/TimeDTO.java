package Model;

/**
 * TimeDTO Class
 * 시간을 비교하기위해 만들어진 클래스
 */

public class TimeDTO {
	
	public String Day;
    public int startHour;
    public int startMinute;
    public int endHour;
    public int endMinute;
    
    public TimeDTO() {
    } //public TimeDTO()

	public TimeDTO(int startHour, int startMinute, int endHour, int endMinute){
        this.startHour = startHour;         // 시간
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
    } //public TimeDTO(int startHour, int startMinute, int endHour, int endMinute)

    public TimeDTO(String day, int startHour, int startMinute, int endHour, int endMinute){
        this.Day = day;     // 요일
        this.startHour = startHour;         // 시간
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
    } //public TimeDTO(String day, int startHour, int startMinute, int endHour, int endMinute)

    // time 객체를 분으로 환산해 반환
    public int ToMinute(boolean isStartTime){
        if (isStartTime)
            return startHour * 60 + startMinute;

        return endHour * 60 + endMinute;
    } //public int ToMinute(boolean isStartTime)

    // 현재 시간이 수업시간 안에 포함되는 시간인지 확인
    public boolean isInTime(TimeDTO time){
        // 분으로 환산해서 계산
        int timeStart = 60 * time.startHour + time.startMinute;
        int timeEnd = 60 * time.endHour + time.endMinute;

        int periodStart = 60 * startHour + startMinute;
        int periodEnd = 60 * endHour + endMinute;

        return (timeStart <= periodStart) && (timeEnd >= periodEnd);
    } //public boolean isInTime(TimeDTO time)
}
