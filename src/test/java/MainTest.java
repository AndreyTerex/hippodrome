import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    @DisplayName("Main выполняется не дольше 22 секунд")
    @Timeout(22)
    @Disabled
    void TimeTestMain()throws Exception {
        Main.main(new String[] {});
    }
}