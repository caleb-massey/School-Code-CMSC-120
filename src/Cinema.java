// PART A
enum Day { MON, TUE, WED, THU, FRI, SAT, SUN }
enum Rating { G, PG, PG13, R, NC17 }
enum Show { MATINEE, EVENING, LATE }
record Visit(int age, Rating rating, boolean withAdult) {}
record Ticket(int age, Show show) {}

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

// checks whether someone can see a film with r Rating, returns true if they can
// R is only for 17 over or withAdult, NC17 is only 18 and over
boolean allowed(Rating r, int age, boolean withAdult){
    return switch (r) {
        case G, PG, PG13 -> true;
        case R -> (age > 16) || withAdult;
        case NC17 -> age > 17;
    };
}

// returns how much less a ticket costs at that show: 3 at a matinee, 
// 2 at a late show, nothing in the evening
int discount(Show s){
    return switch(s) {
        case MATINEE -> 3;
        case LATE -> 2;
        case EVENING -> 0;
    };
}

// returns the price of a ticket for that age at that show minus the dicount;
// won't return less then 0
int price(int age, Show s){
    int price = ticket(age) - discount(s);
    return price > 0 ? price : 0;
}

// an evening show on a Friday or a Saturday is the cinemas busiest time
// So those will return true all else is false
boolean isPeak(Day d, Show s){
    boolean day = (d == Day.FRI) || (d == Day.SAT);
    boolean evening = (s == Show.EVENING);
    return day && evening;
}

// PART B

// returns true if the person can be admitted
boolean admitted(Visit v){
    return allowed(v.rating, v.age, v.withAdult);
}

void main() {
    // PART A CHECKS

    // Check ticket edge cases given
    Check.is(ticket(3), 0);
    Check.is(ticket(4), 6);
    Check.is(ticket(11), 6);
    Check.is(ticket(12), 12);
    Check.is(ticket(64), 12);
    Check.is(ticket(65), 8);

    // Check allowed
    Check.is(allowed(Rating.R, 16, true), true);
    Check.is(allowed(Rating.NC17, 17, true), false);
    Check.is(allowed(Rating.R, 17, false), true);
    Check.is(allowed(Rating.NC17, 18, false), true);
    Check.is(allowed(Rating.NC17, 18, true), true);
    Check.is(allowed(Rating.R, 16, false), false);

    // Check discount
    Check.is(discount(Show.EVENING), 0);
    Check.is(discount(Show.MATINEE), 3);
    Check.is(discount(Show.LATE), 2);
    
    // Check price
    Check.is(price(2, Show.MATINEE), 0);
    Check.is(price(30, Show.MATINEE), 9);
    Check.is(price(65, Show.LATE), 6);

    // Check isPeak
    Check.is(isPeak(Day.FRI, Show.EVENING), true);
    Check.is(isPeak(Day.FRI, Show.MATINEE), false);
    Check.is(isPeak(Day.SAT, Show.EVENING), true);
    Check.is(isPeak(Day.SAT, Show.LATE), false);

    // PART B CHECKS
    Check.is(admitted(new Visit(16, Rating.R, true)), true);
    Check.is(admitted(new Visit(18, Rating.NC17, false)), true);
    Check.is(admitted(new Visit(2, Rating.PG, false)), true);
    Check.is(admitted(new Visit(16, Rating.NC17, true)), false);
    Check.is(admitted(new Visit(16, Rating.R, false)), false);
    
    

    Check.done();
}