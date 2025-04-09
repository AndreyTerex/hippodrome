
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;




public class HippodromeTest {

    @Test
    @DisplayName("конструктор выбрасывает IllegalArgumentException при передаче первым параметром null")
    public void testConstruct1() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    @DisplayName("при передаче в конструктор первым параметром null, выброшенное исключение будет содержать сообщение \"Horses cannot be null.\"")
    public void testConstruct2() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        String expectedMassage = "Horses cannot be null.";
        String actualMassage = exception.getMessage();
        assertEquals(expectedMassage, actualMassage);
    }

    @Test
    @DisplayName("при передаче в конструктор пустого списка, будет выброшено IllegalArgumentException")
    public void testConstruct3() {
        List<Horse> TestList = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(TestList));

    }

    @Test
    @DisplayName("при передаче в конструктор пустого списка, выброшенное исключение будет содержать сообщение \"Horses cannot be empty.\"")
    public void testConstruct4() {
        List<Horse> TestList = new ArrayList<>();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(TestList));
        String expectedMassage = "Horses cannot be empty.";
        String actualMassage = exception.getMessage();
        assertEquals(expectedMassage, actualMassage);
    }

    @Test
    @DisplayName("метод возвращает список, который содержит те же объекты и в той же последовательности, что и список который был передан в конструктор.")
    public void testGetHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("horse" + i, 1.0 + i, 0.0));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        List<Horse> returnHorses = hippodrome.getHorses();
        assertEquals(horses.size(), returnHorses.size());
        for (int i = 0; i < horses.size(); i++) {
            assertSame(horses.get(i), returnHorses.get(i));
        }
    }
    @Test
    @DisplayName("метод вызывает метод move у всех лошадей")
    public void moveTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (Horse horse : horses) {
            Mockito.verify(horse).move();
        }
    }
    @Test
    @DisplayName("метод возвращает лошадь с самым большим значением distance")
    public void winnerTest(){
    List<Horse> horses = new ArrayList<>();
        Horse horse1 = new Horse("TestName1",1.0,20);
        Horse horse2 =new Horse("TestName2",1.0,100);
        Horse horse3 =new Horse("TestName3",1.0,40);
        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);
        Hippodrome hippodrome = new Hippodrome(horses);
        Horse winner = hippodrome.getWinner();

        assertSame(horse2, winner);
        }
    }

