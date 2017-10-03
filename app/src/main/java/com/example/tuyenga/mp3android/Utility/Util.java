package com.example.tuyenga.mp3android.Utility;

/**
 * Created by tuyenga on 03/10/2017.
 */

public class Util {
    /**
     *
     * @param milliseconds
     * @return finalTimerString
     */
    public static String milliSecondsToTime(long milliseconds)
    {
        String finalTimerString = "";
        String secondsString = "";

        // chuyen tong thoi gian bai hat ra giay

        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);

        // them gio neu co

        if(hours > 0 )
        {
            finalTimerString = hours + ":";
        }

        //  them 0 truoc giay neu no chi co 1 so

        if(seconds < 10 )
        {
            secondsString = "0"+seconds;
        }
        else {
            secondsString = ""+seconds;
        }

        finalTimerString = finalTimerString + minutes + ":" + secondsString;

        return  finalTimerString;
    }
    /**
      * Chuyển thời gian đang chạy thành % để progress sang thanh seekbar
      *
      * @param currentDuration
      * @param totalDuration
     * return percentage.intValue()
      *
     */
    public static int getProgressPercentage(long currentDuration , long totalDuration)
    {
        Double percentage = (double) 0;


        long currentSeconds = (int) (currentDuration / 1000);
        long totalSeconds = (int) (totalDuration / 1000);

        // tinh phan tram

        percentage = (((double) currentSeconds) / totalDuration)* 100;

        // return percentage

        return  percentage.intValue();
    }

    /**
     *
     * @param progress
     * @param totalDuration
     * @return currentDuration * 1000
     */
    public static int progressToTimer(int progress,int totalDuration)
    {
        int currentDuration = 0;
        totalDuration = (int) (totalDuration / 1000);
        currentDuration = (int) ((((double)progress)/100) * totalDuration);

        return currentDuration *1000;
    }
}
