// this is the orignal fare that doesn't check for 0
// int fare(int km){
// if (km <= 2) {
// return 5;
//
// } else {
// return 5 + km * 2;
// }
// }
//

// this is the orignal nightFare that doesn't check for 0
// int nightFare(int km, boolean night){
//     if (km <= 2){
//         return 5;

//     } else if (night){
//         return 5 + km * 3;

//     } else {
//         return 5 + km * 2;
//     }
// }

// the fare for a ride of km kilometres:
// 5 for two kilometres or less, and 5 plus 2 * kilometre beyond that
int fare(int km) {
  if (km < 1) {
    return 0;

  } else if (km <= 2) {
    return 5;

  } else {
    return 5 + km * 2;
  }
}

// the same fare, but 3 * kilometre at night
int nightFare(int km, boolean night) {
  if (km < 1) {
    return 0;

  } else if (km <= 2) {
    return 5;

  } else if (night) {
    return 5 + km * 3;

  } else {
    return 5 + km * 2;
  }
}

// returns the bigger number
int bigger(int a, int b) {
  return a >= b ? a : b;
}

// returns true if it's the weekend
boolean isWeekend(String day) {
  return day.equals("Sat") || day.equals("Sun");
}

// checks if you can run or not
// if it's not raining and temp is greater than 50 return true
boolean canRun(boolean raining, int temp) {
  return !raining && temp >= 50;
}

// checks if s1 and s2 pass, one has to be greater than 90, the lower score greater than 70
boolean passes(int s1, int s2) {
  // stores the lowest grade
  int lowestScore = (s1 < s2) ? s1 : s2;

  // checks if one of the grades is greater than 90
  boolean highGrade = (s1 > 90) || (s2 > 90);
  boolean lowGrade = lowestScore > 70;
  return highGrade && lowGrade;
}

// OPTIONAL WORK BELOW, instead of in a seperate file per usual

// moves the selected characters to the end, rotates them around
// checks if s longer than 0, else it has nothing to do and returns as is
String rotate(String s, int k) {
  int length = s.length();
  if (length > 0) {
    StringBuilder sb = new StringBuilder(s);
    String lm = sb.substring(0, k % length);
    sb.append(lm);
    sb.delete(0, k % length);
    return sb.toString();
  } else {
    return s;
  }
}

// swaps first and last characters
// checks that string is long enough
// text with fewer than 2 characters has no ends to swap and comes back as it is
String swapEnds(String s) {
  StringBuilder sb = new StringBuilder(s);
  int length = s.length();
  // the line below is where it checks the strings length is longer or equal to 2
  if (length >= 2) {
    String firstCharacter = sb.substring(0, 1);
    String lastCharacter = sb.substring(length - 1, length);
    sb.append(firstCharacter);
    sb.delete(0, 1);
    sb.insert(0, lastCharacter);
    sb.delete(length - 1, length);
    return sb.toString();
  } else {
    return s;
  }
}

// A year is a leap year when it is divisible by 4,
// except when it is divisible by 100,
// except when it is divisible by 400.
// returns true if above checks pass
boolean isLeap(int year) {
  boolean byFour = year % 4 == 0;
  boolean byHundred = year % 100 == 0;
  boolean byFourHundred = year % 400 == 0;
  return (byFour && !byHundred || byFourHundred);

  // leaving my former try here with why it didn't work, I flipped them in my head
  // it would always return true because byFour was true,
  // needed to check that byHundred was false for byFour to be true
  // return byFour || (byHundred && byFourHundred);
}

// returns how many days are in month of year, checks for the leap year too
int daysIn(int month, int year) {
  boolean isThirty = (month == 4 || month == 6 || month == 9 || month == 11);
  int days;
  if (month == 2) {
    if (isLeap(year)) {
      days = 29;
    } else {
      days = 28;
    }
  } else if (isThirty) {
    days = 30;
  } else {
    days = 31;
  }
  return days;
}

// return true if date is valid, checks if month is 1-12 and days are 1 - daysIn(month, year)
boolean isValidDate(int month, int day, int year) {
  return (month >= 1 && month <= 12) && (day >= 1 && day <= daysIn(month, year));
}

// returns weekday number using Zeller’s rule. 0 is Saturday -> 6 is Friday
int weekdayNumber(int month, int day, int year) {
  int m = month < 3 ? month + 12 : month;
  int y = month < 3 ? year - 1 : year;
  int k = y % 100;
  int j = y / 100;
  return (day + 13 * (m + 1) / 5 + k + k / 4 + j / 4 + 5 * j) % 7;
}

// returns the name of day using 0 - 6, 0 being Saturday and 6 being Friday
String dayName(int h) {
  String day;
  if (h < 0 || h > 6) {
    return "Error 1: Not an accepted number";
  } else if (h == 0) {
    day = "Saturday";
  } else if (h == 1) {
    day = "Sunday";
  } else if (h == 2) {
    day = "Monday";
  } else if (h == 3) {
    day = "Tuesday";
  } else if (h == 4) {
    day = "Wednesday";
  } else if (h == 5) {
    day = "Thursday";
  } else {
    day = "Friday";
  }
  return day;
}

String dayOfWeek(int month, int day, int year) {
  int d = weekdayNumber(month, day, year);
  return dayName(d);
}

// THINGS TO TRY OPTIONAL NIGHTFARE

// this is the orignal nightFare that doesn't check for 0 in one line
int nightFareOneLine(int km, boolean night) {
  // I'd rather read if else if else statements, they're easier to read, the ? : is for very simple
  // checks
  return (km <= 2) ? 5 : (night) ? 5 + km * 3 : 5 + km * 2;
}

void main() {
  // Check fare givens
  Check.is(fare(2), 5);
  Check.is(fare(3), 11);

  // Check fare
  Check.is(fare(0), 0);
  Check.is(fare(1), 5);

  // Check nightfare givens
  Check.is(nightFare(3, true), 14);
  Check.is(nightFare(3, false), 11);

  // Check nightfare
  Check.is(nightFare(0, true), 0);
  Check.is(nightFare(0, false), 0);
  Check.is(nightFare(1, true), 5);
  Check.is(nightFare(2, true), 5);

  // Check bigger
  Check.is(bigger(3, 8), 8);
  Check.is(bigger(10, 5), 10);
  Check.is(bigger(1, 0), 1);

  // Check isWeekend
  Check.is(isWeekend("Sat"), true);
  Check.is(isWeekend("Sun"), true);
  Check.is(isWeekend("Mon"), false);
  Check.is(isWeekend("Cat"), false);

  // Check canRun
  Check.is(canRun(false, 50), true);
  Check.is(canRun(true, 70), false);
  Check.is(canRun(false, 49), false);
  Check.is(canRun(false, 51), true);

  // Check passes
  Check.is(passes(95, 65), false);
  Check.is(passes(95, 70), false);
  Check.is(passes(70, 90), false);
  Check.is(passes(71, 91), true);
  Check.is(passes(71, 90), false);
  Check.is(passes(71, 89), false);

  // OPTIONAL CHECKS FOR OPTIONAL CODE BELOW

  // Check rotate
  Check.is(rotate("abcde", 7), "cdeab");
  Check.is(rotate("", 3), "");

  // Check swapEnds
  Check.is(swapEnds("a"), "a");
  Check.is(swapEnds(""), "");

  // Check isLeap
  Check.is(isLeap(1900), false);
  Check.is(isLeap(2024), true);
  Check.is(isLeap(2000), true);
  Check.is(isLeap(2028), true);
  Check.is(isLeap(2017), false);
  Check.is(isLeap(2026), false);

  // Checks daysIn
  Check.is(daysIn(4, 2026), 30);
  Check.is(daysIn(2, 2024), 29);
  Check.is(daysIn(2, 2026), 28);
  Check.is(daysIn(10, 2026), 31);

  // Checks isValidDate
  Check.is(isValidDate(2, 29, 2024), true);
  Check.is(isValidDate(2, 29, 2026), false);
  Check.is(isValidDate(13, 1, 2026), false);
  Check.is(isValidDate(4, 30, 2024), true);

  // Check weekdayNumber
  Check.is(weekdayNumber(10, 7, 2026), 4);
  Check.is(weekdayNumber(1, 1, 2000), 0);
  Check.is(weekdayNumber(2, 29, 2024), 5);

  // Check dayName
  Check.is(dayName(3), "Tuesday");
  Check.is(dayName(0), "Saturday");
  Check.is(dayName(5), "Thursday");
  Check.is(dayName(6), "Friday");
  Check.is(dayName(7), "Error 1: Not an accepted number");
  Check.is(dayName(-1), "Error 1: Not an accepted number");

  // Check dayOfWeek
  Check.is(dayOfWeek(10, 7, 2026), "Wednesday");
  Check.is(dayOfWeek(7, 20, 1969), "Sunday");
  Check.is(dayOfWeek(6, 8, 2032), "Tuesday");
  Check.is(dayOfWeek(5, 12, 2007), "Saturday");

  // THINGS TO TRY CHECK
  // Check nightFareOneLine
  Check.is(nightFareOneLine(3, true), 14);
  Check.is(nightFareOneLine(3, false), 11);
  Check.is(nightFareOneLine(1, true), 5);
  Check.is(nightFareOneLine(2, true), 5);

  // END OF OPTIONAL CODE CHECKS

  Check.done();

  // THINGS TO TRY SEGMENT

  // int age = 30;
  // if (age < 12)
  // IO.println("child");
  // IO.println("half price");
  // it's gonna print half price, because java doesn't read indentation for structure, just braces
  // so it prints the last thing underneath the if statement

  // String day = "Saturday".substring(0, 3);
  // IO.println(day == "Sat");
  // it's gonna print false, because we didn't use .equals for the text

  // IO.println(day.equals("Sat"));
  // this will print true
}