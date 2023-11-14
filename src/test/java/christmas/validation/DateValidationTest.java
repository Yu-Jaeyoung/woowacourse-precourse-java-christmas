package christmas.validation;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DateValidationTest {
    static final String INVALID_DAY = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    @ParameterizedTest
    @ValueSource(strings = {"", " ", " 1", "1 ", " 1 ", "a", "ㄱ", "ㄷ", "l"})
    @DisplayName("빈 공백 및 숫자가 아닌 입력 예외 처리")
    void blankAndNotANumberException(String input) {
        assertThatThrownBy(() -> DateValidation.validate(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", " 1", "1 ", " 1", "a", "ㄱ", "ㄷ", "l"})
    @DisplayName("빈 공백 및 숫자가 아닌 입력 예외 처리 시 출력 메시지")
    void blankAndNotANumberExceptionMessage(String input) {
        assertThatThrownBy(() -> DateValidation.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_DAY);
    }

    @ParameterizedTest
    @ValueSource(strings = {"32", "40", "00", "-5", "47", "00", "0", "-111"})
    @DisplayName("날짜 범위에 맞지 않은 입력 예외 처리")
    void notInRangeException(String input) {
        assertThatThrownBy(() -> DateValidation.validate(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"32", "40", "00", "-5", "47", "00", "0", "-111"})
    @DisplayName("날짜 범위에 맞지 않은 입력 예외 처리 시 출력 메시지")
    void notInRangeExceptionMessage(String input) {
        assertThatThrownBy(() -> DateValidation.validate(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(INVALID_DAY);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "13", "25", "31"})
    @DisplayName("유효한 날짜 입력 테스트")
    void validDateException(String input) {
        assertThatCode(() -> DateValidation.validate(input)).doesNotThrowAnyException();
    }
}