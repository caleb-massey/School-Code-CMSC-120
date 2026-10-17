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



void main() {


    // Check ticket edge cases given
    Check.is(ticket(3), 0);
    Check.is(ticket(4), 6);
    Check.is(ticket(11), 6);
    Check.is(ticket(12), 12);
    Check.is(ticket(64), 12);
    Check.is(ticket(65), 8);

    Check.done();
}