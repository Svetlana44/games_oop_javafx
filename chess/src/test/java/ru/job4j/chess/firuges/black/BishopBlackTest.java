package ru.job4j.chess.firuges.black;

import org.junit.Test;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;

import static org.junit.Assert.*;
import static ru.job4j.chess.firuges.Cell.*;

public class BishopBlackTest {
    private BishopBlack bishopBlack = new BishopBlack(A1);

    @Test
    public void whenCreate() {
        BishopBlack expected = new BishopBlack(Cell.findBy(0, 7));
        assertEquals(expected, bishopBlack);
    }

    @Test
    public void whenCopy() {
        BishopBlack actual = (BishopBlack) bishopBlack.copy(H8);
        BishopBlack expected = new BishopBlack(Cell.findBy(7, 0));
        assertEquals(expected, actual);
    }

    @Test
    public void whenWayTrows() {
        assertThrows(ImpossibleMoveException.class, () -> bishopBlack.way(A2));
    }

    @Test
    public void whenWay() {
        Cell[] way = new Cell[]{B2, C3};
        Cell[] expected = bishopBlack.way(C3);
        assertArrayEquals(expected, way);
    }
}