package org.elixir_lang.elixir_flex_lexer.group.quote;

import com.intellij.psi.tree.IElementType;
import org.elixir_lang.psi.ElixirTypes;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by luke.imhoff on 9/2/14.
 */
@RunWith(Parameterized.class)
public class SingleQuoteTest extends Test {
    /*
     * Constants
     */

    public static final IElementType FRAGMENT_TYPE = ElixirTypes.CHAR_LIST_FRAGMENT;

    /*
     * Constructors
     */

    public SingleQuoteTest(CharSequence charSequence, IElementType tokenType, int lexicalState) {
        super(charSequence, tokenType, lexicalState);
    }

    /*
     * Methods
     */

   @Parameterized.Parameters(
            name = "\"{0}\" parses as {1} token and advances to state {2}"
    )
    public static Collection<Object[]> generateData() {
       return Test.generateData(
               FRAGMENT_TYPE,
               Arrays.asList(
                       new Object[][] {
                               { "'", ElixirTypes.CHAR_LIST_TERMINATOR, INITIAL_STATE },
                               { "\"", FRAGMENT_TYPE, LEXICAL_STATE }
                       }
               )
       );
    }

    @Override
    protected void reset(CharSequence charSequence) throws IOException {
        // start of single quote to trigger GROUP state with terminator being single quote
        CharSequence fullCharSequence = "'" + charSequence;
        super.reset(fullCharSequence);
    }
}
