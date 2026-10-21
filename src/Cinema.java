enum Day { MON, TUE, WED, THU, FRI, SAT, SUN }
enum Rating { G, PG, PG13, R, NC17 }
enum Show { MATINEE, EVENING, LATE }
enum Move { ROCK, PAPER, SCISSORS, LIZARD, SPOCK }
record Visit(int age, Rating rating, boolean withAdult) {}
record Ticket(int age, Show show) {}
record Round(Move mine, Move theirs) {}

// PART A

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

// returns what a ticket costs
int cost(Ticket t){
    return price(t.age, t.show);
}

// returns whether you're a child, adult, or senior; returns it with the show type
// age checks work like ticket(), child under 12, senior of 65 over, everyone else adult
String label(Ticket t){
    String age; 
    if (t.age < 12){  
        age = "child";
    } else if(t.age >= 65){
        age = "senior";
    } else{
        age = "adult";
    }
    String show = switch(t.show){
        case MATINEE -> "matinee";
        case EVENING -> "evening";
        case LATE -> "late";
    };
    return age + ", " + show;
}

// OPTIONAL CODE

// original before LIZARD and SPOCK
// returns true if a beats b in rock paper scissors
// boolean beats(Move a, Move b){
//     return switch(a){
//         case ROCK -> b == Move.SCISSORS;
//         case SCISSORS -> b == Move.PAPER;
//         case PAPER -> b == Move.ROCK;
//     };
// }

// returns true if a beats b in rock paper scissors
boolean beats(Move a, Move b){
    return switch(a){
        case ROCK -> b == Move.SCISSORS  || b == Move.LIZARD;
        case PAPER -> b == Move.ROCK || b == Move.SPOCK;
        case SCISSORS -> b == Move.PAPER || b == Move.LIZARD;
        case LIZARD -> b == Move.SPOCK || b == Move.PAPER;
        case SPOCK -> b == Move.SCISSORS || b== Move.ROCK;
    };
}




// returns the result of who won the match, if I won, if  I lost, if we draw
String result(Move mine, Move theirs){
    if (beats(mine, theirs)){
        return "win";
    } else if (beats(theirs, mine)){
        return "lose";
    } else {
        return "draw";
    }
}

// returns 1 point if I won 0 if I lost
int points(Round r){
    return result(r.mine, r.theirs).equals("win") ? 1 : 0;
}

// returns 1 point if they won 0 if they lost
int theirPoints(Round r){
    return result(r.theirs, r.mine).equals("win") ? 1 : 0;
}

// I had to change nothing about result, points, or match.
// result uses beats to determine who wins, then points uses result,
// and match uses points, they all relly on beats being correct.
// returns who won in the best of three: "you", "them" or "tie"
String match(Round r1, Round r2, Round r3){
    int mine = (points(r1) + points(r2) + points(r3));
    int theirs = (theirPoints(r1) + theirPoints(r2) + theirPoints(r3));
    if (mine > theirs){
        return "you";
    } else if (theirs > mine){
        return "them";
    } else {
        return "tie";
    }
}

// A6's daysIn & isLeap

// A year is a leap year when it is divisible by 4,
// except when it is divisible by 100,
// except when it is divisible by 400.
// returns true if above checks pass
boolean isLeap(int year) {
  boolean byFour = year % 4 == 0;
  boolean byHundred = year % 100 == 0;
  boolean byFourHundred = year % 400 == 0;
  return (byFour && !byHundred || byFourHundred);
}

// returns how many days are in month of year, checks for the leap year too
int daysIn(int month, int year) {
    return switch(month){
        case 4, 6, 9, 11 -> 30;
        case 2 -> isLeap(year) ? 29 : 28;
        default -> 31;
    };
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

    // Check admitted
    Check.is(admitted(new Visit(16, Rating.R, true)), true);
    Check.is(admitted(new Visit(18, Rating.NC17, false)), true);
    Check.is(admitted(new Visit(2, Rating.PG, false)), true);
    Check.is(admitted(new Visit(16, Rating.NC17, true)), false);
    Check.is(admitted(new Visit(16, Rating.R, false)), false);

    // Check cost
    Check.is(cost(new Ticket(8, Show.MATINEE)), 3);
    Check.is(cost(new Ticket(30, Show.MATINEE)), 9);
    Check.is(cost(new Ticket(3, Show.MATINEE)), 0);

    // Check label
    Check.is(label(new Ticket(8, Show.MATINEE)), "child, matinee");
    Check.is(label(new Ticket(11, Show.EVENING)), "child, evening");
    Check.is(label(new Ticket(65, Show.LATE)), "senior, late");
    Check.is(label(new Ticket(12, Show.EVENING)), "adult, evening");
    Check.is(label(new Ticket(64, Show.MATINEE)), "adult, matinee");

    // OPTIONAL CHECKS
    
    // Check beats
    Check.is(beats(Move.ROCK, Move.SCISSORS), true);
    Check.is(beats(Move.SCISSORS, Move.ROCK), false);
    Check.is(beats(Move.ROCK, Move.ROCK), false);
    Check.is(beats(Move.ROCK, Move.LIZARD), true);

    // Check result
    Check.is(result(Move.ROCK, Move.SCISSORS), "win");
    Check.is(result(Move.SCISSORS, Move.ROCK), "lose");
    Check.is(result(Move.ROCK, Move.ROCK), "draw");

    // Check match without LIZARD or SPOCK
    Check.is(match(
        new Round(Move.ROCK, Move.SCISSORS), 
        new Round(Move.PAPER, Move.SCISSORS), 
        new Round(Move.ROCK, Move.ROCK)), "tie");

    Check.is(match(
        new Round(Move.ROCK, Move.SCISSORS), 
        new Round(Move.PAPER, Move.SCISSORS), 
        new Round(Move.PAPER, Move.ROCK)), "you");
        
    Check.is(match(
        new Round(Move.ROCK, Move.SCISSORS), 
        new Round(Move.PAPER, Move.SCISSORS), 
        new Round(Move.ROCK, Move.PAPER)), "them");
    
    // Check match with LIZARD and SPOCK
    Check.is(match(
        new Round(Move.ROCK, Move.LIZARD), 
        new Round(Move.PAPER, Move.SCISSORS), 
        new Round(Move.ROCK, Move.ROCK)), "tie");

    Check.is(match(
        new Round(Move.ROCK, Move.SCISSORS), 
        new Round(Move.PAPER, Move.SCISSORS), 
        new Round(Move.SPOCK, Move.ROCK)), "you");
        
    Check.is(match(
        new Round(Move.ROCK, Move.SCISSORS), 
        new Round(Move.PAPER, Move.LIZARD), 
        new Round(Move.ROCK, Move.SPOCK)), "them");

    // Checks daysIn
    Check.is(daysIn(4, 2026), 30);
    Check.is(daysIn(2, 2024), 29);
    Check.is(daysIn(2, 2026), 28);
    Check.is(daysIn(10, 2026), 31);
    
    Check.done();

    // THINGS TO TRY
    IO.println(new Ticket(8, Show.MATINEE)); // this print Ticket as like a list or a dictionary
    IO.println(Show.LATE); // this just prints LATE, shows that enums can be made to strings

    // I think this'll print false as they're 2 different objects(compares the address)
    IO.println(new Ticket(8, Show.MATINEE) == new Ticket(8, Show.MATINEE)); // yup, prints false

    // this one actually compares what's inside the records
    IO.println(new Ticket(8, Show.MATINEE).equals(new Ticket(8, Show.MATINEE))); // prints true
}

// THINGS TO TRY ANSWERS
// for the isWeekend Day.SUN is false, since it defaulted to it.