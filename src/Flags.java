record Stripe(String color, int height) {}

// returns area of the flag
int area(Stripe s, int width) {
    return s.height * width;
}

// returns if it is thinner than 5
boolean isThin(Stripe s) {
    return s.height < 5;
}

// describes the flag in text
String describe(Stripe s) {
    return s.color + ", " + s.height + " tall";
}

// returns if it s the same color between two flags
boolean sameColor(Stripe a, Stripe b) {
    return a.color.equals(b.color);
}


void main() {
    // Checks isThin
    Check.is(isThin(new Stripe("red", 4)), true);
    Check.is(isThin(new Stripe("blue", 5)), false);

    // Checks area
    Check.is(area(new Stripe("red", 10), 60), 600);
    Check.is(area(new Stripe("red", 10), 10), 100);

    // Checks describe
    Check.is(describe(new Stripe("red", 10)), "red, 10 tall");
    Check.is(describe(new Stripe("green", 40)), "green, 40 tall");

    // Checks sameColor
    Check.is(sameColor(new Stripe("red", 0), new Stripe("red", 5)), true);
    Check.is(sameColor(new Stripe("red", 5), new Stripe("white", 5)), false);

    Check.done();

}