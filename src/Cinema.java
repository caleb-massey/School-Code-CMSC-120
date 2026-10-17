enum Day { MON, TUE, WED, THU, FRI, SAT, SUN }
enum Rating { G, PG, PG13, R, NC17 }
enum Show { MATINEE, EVENING, LATE }

// gives back correct pricing according to age
int ticket(int age){
    if (age < 4) {
        return 0;
    } else if (age < 12) {
        return 6;
    } else if (age >= 65) {
        return 8;
    } else {
        return 12;
    }
}

// whether d is Saturday or Sunday
boolean isWeekend(Day d) {
    return switch (d) {
        case SAT, SUN -> true;
        case MON, TUE, WED, THU, FRI -> false;
    };
}

// returns cinema hours that it opens at 
int openHour(Day d) {
    return switch(d){
        case SAT, SUN -> 10;
        case FRI -> 14;
        case MON, TUE, WED, THU -> 16;
    };
}

// checks to see if the hour is open for the cinema 
boolean isOpenAt(Day d, int hour) {
    return switch(d) {
        case SAT, SUN -> (hour >= 10) && (hour <= 23);
        case FRI -> (hour >= 14) && (hour <= 23);
        case MON, TUE, WED, THU -> (hour >= 16) && (hour <= 23);
    };
}


void main() {
    Check.is(isWeekend(Day.SAT), true);
    Check.is(isWeekend(Day.MON), false);

    // Check openhour
    Check.is(openHour(Day.FRI), 14);
    Check.is(openHour(Day.TUE), 16);
    Check.is(openHour(Day.SUN), 10);

    // Check isOpenAt
    Check.is(isOpenAt(Day.SAT, 9), false);
    Check.is(isOpenAt(Day.FRI, 11), false);
    Check.is(isOpenAt(Day.MON, 16), true);
    Check.is(isOpenAt(Day.TUE, 20), true);

    // Check ticket edge cases given
    Check.is(ticket(3), 0);
    Check.is(ticket(4), 6);
    Check.is(ticket(11), 6);
    Check.is(ticket(12), 12);
    Check.is(ticket(64), 12);
    Check.is(ticket(65), 8);

    Check.done();
}