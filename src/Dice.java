import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

//returns 1-6 based on seed
int roll(Random r) {
    return r.nextInt(1, 7);
}

//returns two dice rolls added together
int rollTwo(Random r) {
    return roll(r) + roll(r);
}

//reports a strings of what happens in rollTwo
String report(Random r){
    int first = roll(r);
    int second = roll(r);
    return first + " + " + second + " = " + (first + second);
}


void main() {
    //checks roll
    Check.is(roll(new Random(42)), 3);
    Check.is(roll(new Random(7)), 5);
    Check.is(roll(new Random(0)), 1);

    //checks rollTwo
    Check.is(rollTwo(new Random(42)), 7);
    Check.is(rollTwo(new Random(7)), 8);
    Check.is(rollTwo(new Random(0)), 6);

    //checks report
    Check.is(report(new Random(42)), "3 + 4 = 7");
    Check.is(report(new Random(7)), "5 + 3 = 8");
    Check.is(report(new Random(0)), "1 + 5 = 6");

    Check.done();

    //printed report 3 times
    IO.println(report(ThreadLocalRandom.current()));
    IO.println(report(ThreadLocalRandom.current()));
    IO.println(report(ThreadLocalRandom.current()));
}