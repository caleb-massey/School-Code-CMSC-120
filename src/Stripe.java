// the width stripe draws for unit u
int stripeWidth(int u) {
    return u * 6;
}

// the flag width is two stripes wide
int flagWidth(int u) {
    return stripeWidth(u) * 2;
}

// a stripe drawn as a row of characters as wide as the stripe
String stripeRow(int u) {
    return "#".repeat(stripeWidth(u));
}

// one row of the flag: two stripes side by side, in two characters
String flagRow(int u) {
    return stripeRow(u) + ".".repeat(stripeWidth(u));
}

// a stripe is u tall, so the flag is u rows
String flag(int u) {
    return (flagRow(u) + "\n").repeat(u);
}


void main() {

    // checks for stripeWidth
    Check.is(stripeWidth(10), 60);
    Check.is(stripeWidth(5), 30);

    // checks for flagWidth
    Check.is(flagWidth(0), 0);
    Check.is(flagWidth(12), 144);
    Check.is(flagWidth(6), 72);

    // checks for stripeRow
    Check.is(stripeRow(0).length(), 0);
    Check.is(stripeRow(10).length(), 60);
    Check.is(stripeRow(6).length(), 36);
    Check.is(stripeRow(2).length(), 12);

    // checks for flagRow
    Check.is(flagRow(0).length(), 0);
    Check.is(flagRow(10).length(), 120);
    Check.is(flagRow(6).length(), 72);

    // is flagRow equal to flagWidth: they should be
    Check.is(flagRow(6).length(), flagWidth(6));
    Check.is(flagRow(10).length(), flagWidth(10));

    // print final report at the very end:
    Check.done();
    
    // print the flag
    IO.println(flag(4));
}

/*
The worked out by hand section

flag(3).length() is 111

    I run flagrow(3) which is 3 rows of 12 characters which is 36 characters, then I add the /n turns it to 37
    then repeat(3) runs to repeat is 3 times, making the character count 37 * 3, which 111 characters
*/