enum Day { MON, TUE, WED, THU, FRI, SAT, SUN }

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

    Check.done();
}