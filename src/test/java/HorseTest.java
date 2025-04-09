import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import org.mockito.MockedStatic;

class HorseTest {

    @Test
    @DisplayName("конструктор выбрасывает IllegalArgumentException при передаче первым параметром null")
    public void testConstruct1() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1.0, 2.0));
    }

    @Test
    @DisplayName("при передаче в конструктор первым параметром null, выброшенное исключение будет содержать сообщение \"Name cannot be null.\"")
    public void testConstruct2() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1.0, 2.0));
        String expectedMassage = "Name cannot be null.";
        String actualMassage = exception.getMessage();
        assertEquals(expectedMassage, actualMassage);
    }

    @ParameterizedTest
    @DisplayName("при передаче в конструктор первым параметром пустой строки или строки содержащей только пробельные символы (пробел, табуляция и т.д.), будет выброшено IllegalArgumentException")
    @ValueSource(strings = {"", " ", "\t", "\n", "\r", "\f"})
    public void testTabulation(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1.0, 2.0));
    }

    @ParameterizedTest
    @DisplayName("при передаче в конструктор первым параметром пустой строки или строки содержащей только пробельные символы (пробел, табуляция и т.д.), выброшенное исключение будет содержать сообщение \"Name cannot be blank.\";")
    @ValueSource(strings = {"", " ", "\t", "\n", "\r", "\f"})
    public void testTabulationMassage(String name) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1.0, 2.0));
        String expectedMassage = "Name cannot be blank.";
        String actualMassage = exception.getMessage();
        assertEquals(expectedMassage, actualMassage);
    }

    @Test
    @DisplayName("при передаче в конструктор вторым параметром отрицательного числа, будет выброшено IllegalArgumentException")
    public void testConstruct3() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("TestName", -1.0, 2.0));
    }

    @Test
    @DisplayName("при передаче в конструктор вторым параметром отрицательного числа, выброшенное исключение будет содержать сообщение \"Speed cannot be negative.\"")
    public void testConstruct4() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("TestName", -1.0, 2.0));
        String expectedMassage = "Speed cannot be negative.";
        String actualMassage = exception.getMessage();
        assertEquals(expectedMassage, actualMassage);
    }

    @Test
    @DisplayName("при передаче в конструктор третьим параметром отрицательного числа, будет выброшено IllegalArgumentException")
    public void testConstruct5() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("TestName", 1.0, -2.0));
    }

    @Test
    @DisplayName("при передаче в конструктор третьим параметром отрицательного числа, выброшенное исключение будет содержать сообщение \"Distance cannot be negative.\"")
    public void testConstruct6() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Horse("TestName", 1.0, -2.0));
        String expectedMassage = "Distance cannot be negative.";
        String actualMassage = exception.getMessage();
        assertEquals(expectedMassage, actualMassage);
    }

    @Test
    @DisplayName("метод getName() возвращает строку, которая была передана первым параметром в конструктор")
    public void testGetName() {
        Horse horse = new Horse("TestName", 2.0, 3.0);
        String expectedName = "TestName";
        String nameReturnedByMethod = horse.getName();
        assertEquals(expectedName, nameReturnedByMethod);
    }

    @Test
    @DisplayName("метод getSpeed() возвращает число, которое было передано вторым параметром в конструктор")
    public void testGetSpeed() {
        Horse horse = new Horse("TestName", 2.0, 3.0);
        double expectedSpeed = 2.0;
        double speedReturnedByMethod = horse.getSpeed();
        assertEquals(expectedSpeed, speedReturnedByMethod);
    }

    @Test
    @DisplayName("метод возвращает число, которое было передано третьим параметром в конструктор")
    public void testGetDistance() {
        Horse horse = new Horse("TestName", 2.0, 3.0);
        double expectedDistance = 3.0;
        double distanceReturnedByMethod = horse.getDistance();
        assertEquals(expectedDistance, distanceReturnedByMethod);
    }

    @Test
    @DisplayName("метод возвращает ноль, если объект был создан с помощью конструктора с двумя параметрами")
    public void testGetDistance2() {
        Horse horse = new Horse("TestName", 2.0);
        double expectedDistance = 0.0;
        double distanceReturnedByMethod = horse.getDistance();
        assertEquals(expectedDistance, distanceReturnedByMethod);
    }

    @Test
    @DisplayName("метод вызывает внутри метод getRandomDouble с параметрами 0.2 и 0.9")
    public void moveTest() {
        Horse horse = new Horse("TestName", 2.0, 3.0);
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            horse.move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.5, 0.9})
    @DisplayName("Проверить, что метод присваивает дистанции значение высчитанное по формуле: distance + speed * getRandomDouble(0.2, 0.9)")
    public void moveTest2(double random) {
        Horse horse = new Horse("TestName", 2.0, 1.0);
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);
            horse.move();

            double expectedDistance = 1.0 + 2.0 * random;
            assertEquals(expectedDistance, horse.getDistance(), 0.0001);
        }
    }
}

