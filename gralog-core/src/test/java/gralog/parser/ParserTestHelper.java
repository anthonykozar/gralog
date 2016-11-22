/*
 * This file is part of GrALoG FX, Copyright (c) 2016 LaS group, TU Berlin.
 * License: https://www.gnu.org/licenses/gpl.html GPL version 3 or later.
 */
package gralog.parser;

import gralog.algorithm.ParseError;
import static org.junit.Assert.assertEquals;

/**
 * Helper functions for testing parsers.
 */
public class ParserTestHelper {

    @FunctionalInterface
    public interface Parser {

        Object parse(String s) throws Exception;
    }

    private final Parser p;

    public ParserTestHelper(Parser p) {
        this.p = p;
    }

    public void parseAndCompare(String toParse) throws Exception {
        parseAndCompare(toParse, toParse);
    }

    public void parseAndCompare(String toParse, String result) throws Exception {
        assertEquals(result, p.parse(toParse).toString());
    }

    public void parseAndFail(String toParse) throws Exception {
        try {
            p.parse(toParse);
        } catch (ParseError e) {
            return; // Everything is fine, this is what we expect.
        }
        throw new Exception("Parsing should have failed on: " + toParse);
    }
}
