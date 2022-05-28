package ru.job4j.chess;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.BishopBlack;

@ExtendWith(MockitoExtension.class)
public class LogicTest {

    @Spy
    private Logic logic = new Logic();

    @Test(expected = OccupiedCellException.class)
    public void whenMoveOccupiedCellException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        logic.add(new BishopBlack(Cell.C1));
        logic.add(new BishopBlack(Cell.H6));
        logic.move(Cell.C1, Cell.H6);
    }

    @Test
    public void whenMoveOccupiedCellExceptionAgaine() {
        logic.add(new BishopBlack(Cell.A3));
        logic.add(new BishopBlack(Cell.B4));
        Assert.assertThrows(OccupiedCellException.class, () -> logic.move(Cell.A3, Cell.C5));
    }

    @Test(expected = FigureNotFoundException.class)
    public void whenMoveFigureNotFoundException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        logic.add(new BishopBlack(Cell.C1));
        logic.add(new BishopBlack(Cell.H6));
        logic.move(Cell.D2, Cell.H6);
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenMoveImpossibleMoveException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        logic.add(new BishopBlack(Cell.C1));
        logic.add(new BishopBlack(Cell.H6));
        logic.move(Cell.C1, Cell.C2);
    }

    @Test
    public void whenMoveImpossibleMoveExceptionAgaine()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        logic.add(new BishopBlack(Cell.C1));
        logic.add(new BishopBlack(Cell.G5));
        Assert.assertThrows(ImpossibleMoveException.class, () -> logic.move(Cell.C1, Cell.C7));
    }

    @Test(expected = OccupiedCellException.class)
    public void whenNotFreeExeption() throws OccupiedCellException {
        logic.add(new BishopBlack(Cell.A1));
        Cell[] steps = new Cell[]{Cell.A1, Cell.B2};
        logic.free(steps);
    }

    @Test
    public void whenNotFreeExeptionAgain() {
        logic.add(new BishopBlack(Cell.A1));
        logic.add(new BishopBlack(Cell.B2));
        Cell[] steps = new Cell[]{Cell.A1, Cell.B2};
        Assert.assertThrows(OccupiedCellException.class, () -> logic.free(steps));
    }

    @Test
    public void whenFree() throws OccupiedCellException {
        logic.add(new BishopBlack(Cell.A1));
        Cell[] steps = new Cell[]{Cell.B2, Cell.C3};
        Assert.assertTrue(logic.free(steps));
    }

    @Test
    public void testWhenAdd() {
        BishopBlack bishopBlack = new BishopBlack(Cell.A1);
        logic.add(bishopBlack);
        Assert.assertEquals(logic.getFigures()[0], bishopBlack);
        Assert.assertEquals(logic.getIndex(), 1);
    }

    @Test
    public void testWhenClean() {
        logic.add(new BishopBlack(Cell.A1));
        logic.add(new BishopBlack(Cell.B2));
        logic.clean();
        Assert.assertEquals(logic.getIndex(), 0);
        for (Figure figure : logic.getFigures()) {
            Assert.assertNull(figure);
        }
    }
}