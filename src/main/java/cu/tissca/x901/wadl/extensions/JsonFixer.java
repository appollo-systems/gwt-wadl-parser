package cu.tissca.x901.wadl.extensions;

/**
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public class JsonFixer {

    private enum LexerState {
        DEFAULT {
            @Override
            public LexerState process(char c) {
                switch (c){
                    case '"': return STRING_LITERAL;
                    default: return this;
                }
            }
        },
        ESCAPING {
            @Override
            public LexerState process(char c) {
                return STRING_LITERAL;
            }
        },
        STRING_LITERAL {
            @Override
            public LexerState process(char c) {
                switch(c){
                    case '\\': return ESCAPING;
                    case '"': return DEFAULT;
                    default: return this;
                }
            }
        };
        public abstract LexerState process (char c);
    };


    /**
     * This method eliminates all new line characters inside json string literals.
     *
     * This line characters were present in the example from jira-rest-plugin.wadl. It suggests that it's not uncommon
     * for newline chars to be present in string literals in json. To add robustness to the library we deal with this
     * special case.
     *
     * @param data
     * @return
     */
    public static String fixJson(String data) {
        char[] chars = data.toCharArray();
        StringBuilder sb = new StringBuilder();
        LexerState lexerState = LexerState.DEFAULT;
        for(char c:chars) {
            boolean isParsingALiteral = lexerState == LexerState.STRING_LITERAL;
            if(isParsingALiteral && c=='\n') { // skip the newline
                continue;
            }
            sb.append(c);
            lexerState = lexerState.process(c);
        }
        return sb.toString();
    }

}
