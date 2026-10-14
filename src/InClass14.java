enum Day { MON, TUE, WED, THU, FRI, SAT, SUN }

// greeting for an hour of the day, 0 to 23
String greeting(int hour) {
    if (hour < 18) {
        return "Good afternoon";
    } else if (hour < 12) {
        return "Good morning";
    } else {
        return "Good evening";
    }
}

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


void main() {
    Check.is(ticket(3), 0);
    Check.is(ticket(4), 6);
    Check.is(ticket(11), 6);
    Check.is(ticket(12), 12);
    Check.is(ticket(64), 12);
    Check.is(ticket(65), 8);

    Check.done();
}