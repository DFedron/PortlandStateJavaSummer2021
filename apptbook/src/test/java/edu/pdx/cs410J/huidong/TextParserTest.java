package edu.pdx.cs410J.huidong;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class TextParserTest {
    @Test
    void parseReturnNull(){
        TextParser textParser = new TextParser();
        assertThat(textParser.parse(),is(nullValue()));
    }

}
