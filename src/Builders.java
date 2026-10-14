
//returns text plus reversed text
String mirror(String s){
    StringBuilder mirrored = new StringBuilder(s);
    mirrored.reverse();
    return s + mirrored;
}


//frames the text
String frame(String s){
    StringBuilder frame = new StringBuilder(s);
    frame.insert(0, "[");
    frame.append("]");
    return frame.toString();
}

//moves the selected characters to the end, rotates them around
//checks if int given is longer than given string and returns readable error
//w3schools.com was my reference for if else.
String rotate(String s, int k){
    if (s.length() >= k){
        StringBuilder sb = new StringBuilder(s);
        String lm = sb.substring(0, k);
        sb.append(lm);
        sb.delete(0, k);
        return sb.toString();
    }
    else{
        return "String is shorter than given number";
    }
}

//turns the builder around
void flip(StringBuilder b){
    b.reverse();
}

//doubles the builder
void twice(StringBuilder b){
    b.append(b);
}

//swaps first and last characters
//checks that string is long enough
String swapEnds(String s){
    StringBuilder sb = new StringBuilder(s);
    int length =  s.length();
    if (length >= 2){
        String firstCharacter = sb.substring(0, 1);
        String lastCharacter = sb.substring(length - 1, length);
        sb.append(firstCharacter);
        sb.delete(0, 1);
        sb.insert(0, lastCharacter);
        sb.delete(length - 1, length);
        return sb.toString();
    }
    else{
        return "String is not long enough";
    }
}



void main() {
    // check if it's mirrored
    Check.is(mirror("abc"), "abccba");
    Check.is(mirror(""), "");
    Check.is(mirror(" "), "  ");
    //for fun :)
    Check.is(mirror("tacocat "), "tacocat  tacocat");

    //Check if it bracketed it
    Check.is(frame("abc"), "[abc]");
    Check.is(frame("Hello World"), "[Hello World]");
    Check.is(frame(""), "[]");

    // check if it moved the letters
    Check.is(rotate("abcde", 2), "cdeab");
    Check.is(rotate("abcde", 0), "abcde");
    Check.is(rotate("", 2), "String is shorter than given number");

    //Checks flip
    StringBuilder x = new StringBuilder("abc");
    flip(x);
    Check.is(x.toString(), "cba");

    //Checks twice
    StringBuilder y = new StringBuilder("abc");
    twice(y);
    Check.is(y.toString(), "abcabc");

    //Checks swapEnds
    Check.is(swapEnds("quokka"), "auokkq"); 
    Check.is(swapEnds("ab"), "ba");
    Check.is(swapEnds(""), "String is not long enough");
    Check.is(swapEnds(" "), "String is not long enough");


    //Predictions needed to be solved
    StringBuilder a = new StringBuilder("ab");
    StringBuilder b = a;
    b.append("c");
    Check.is(a.toString(), "abc");

    String s = "ab";
    String t = s;
    t = t + "c";
    Check.is(s, "ab");

    StringBuilder c = new StringBuilder("ab");
    StringBuilder d = new StringBuilder("ab");
    Check.is(c == d, false);
    Check.is(c.toString().equals(d.toString()), true);

    StringBuilder e = new StringBuilder("ab");
    flip(e);
    flip(e);
    Check.is(e.toString(), "ab");
    //End predictions

    Check.done();
}