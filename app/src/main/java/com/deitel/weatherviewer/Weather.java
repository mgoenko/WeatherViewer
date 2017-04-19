// Weather.java
// Maintains one day's weather information
package com.deitel.weatherviewer;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

class Weather {
   public final String dayOfWeek;
   public final Date dataDay;
   public final String minTemp;
   public final String maxTemp;
   public final String humidity;
   public final String description;
   public final String iconURL;

   // constructor
   public Weather(long timeStamp, double minTemp, double maxTemp,
      double humidity, String description, String iconName) {
      // NumberFormat to format double temperatures rounded to integers
      NumberFormat numberFormat = NumberFormat.getInstance();
      numberFormat.setMaximumFractionDigits(0);

      this.dayOfWeek = convertTimeStampToDay(timeStamp);
      this.dataDay = convertTimeStampTodataDay(timeStamp);
      this.minTemp = numberFormat.format(minTemp) + "\u00B0C";
      this.maxTemp = numberFormat.format(maxTemp) + "\u00B0C";
      this.humidity =
         NumberFormat.getPercentInstance().format(humidity / 100.0);
      this.description = description;
      this.iconURL =
         "http://openweathermap.org/img/w/" + iconName + ".png";
   }

   // convert timestamp to a day's name (e.g., Monday, Tuesday, ...)
   private static String convertTimeStampToDay(long timeStamp) {
      Calendar calendar = Calendar.getInstance(); // create Calendar
      calendar.setTimeInMillis(timeStamp * 1000); // set time
      TimeZone tz = TimeZone.getDefault(); // get device's time zone

      // adjust time for device's time zone
      calendar.add(Calendar.MILLISECOND,
         tz.getOffset(calendar.getTimeInMillis()));

      // SimpleDateFormat that returns the day's name
      SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE");
      return dateFormatter.format(calendar.getTime());
   }

   // convert timestamp to a day data(e.g., 28/01/2017,  ...)
   private static Date convertTimeStampTodataDay(long timeStamp) {
      Calendar calendar = Calendar.getInstance(); // create Calendar
      calendar.setTimeInMillis(timeStamp * 1000); // set time
      TimeZone tz = TimeZone.getDefault(); // get device's time zone

      // adjust time for device's time zone
      calendar.add(Calendar.MILLISECOND,
              tz.getOffset(calendar.getTimeInMillis()));

      // SimpleDateFormat that returns the day's name
      SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE");
      return calendar.getTime();
   }
}
