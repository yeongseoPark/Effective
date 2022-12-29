package Chapter2.item10Equals;

import java.util.Objects;

final class CaseInsensitiveString {
    private final String str;

    public CaseInsensitiveString(String s) {
        this.str = Objects.requireNonNull(s);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof CaseInsensitiveString)
            return str.equalsIgnoreCase(((CaseInsensitiveString) o).str);
        if (o instanceof String) // 대칭성 위배, Polish와 polish라 가정하면
            return str.equalsIgnoreCase((String) o); // - true 여도 ((String) o).equals(str)이 false 이다
        return false;
    }
}
