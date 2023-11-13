package fourInARow;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;
import org.junit.jupiter.api.function.Executable;

public class TestLine {

    @Test  public void test00EmptyBoardInitialization() {
        Linea linea = new Linea(7, 6, 'A');
        assertFalse(linea.finished());
    }

    @Test public void test01RedStarts(){
        Linea linea = new Linea(6, 7, 'A');
        linea.playRedAt(0);

        assertFalse(linea.finished());
    }

    @Test public void test02BlueDoesNotStart(){
        Linea linea = new Linea(6, 7, 'A');

        assertThrowsLike(() -> linea.playBlueAt(1), Linea.messageNotBluesTurn);
    }

    @Test public void test03NotRedsTurn(){
        Linea linea = new Linea(6, 7, 'A');
        linea.playRedAt(0);

        assertThrowsLike(() -> linea.playRedAt(1), Linea.messageNotRedsTurn);
    }

    @Test public void test04VictoryVerticalRedAndEndsModeA() {
        Linea linea = new Linea(7, 6, 'A');
        redVertical(linea);

        assertWhenRedWins(linea);
    }

    @Test public void test05VictoryVerticalRedAndEndsModeAWithOtherBoard() {
        Linea linea = new Linea(10, 10, 'A');
        redVertical(linea);

        assertWhenRedWins(linea);
    }

    @Test public void test06VictoryHorizontalRedAndEndsModeA() {
        Linea linea = new Linea(7, 6, 'A');
        redHorizonal(linea);

        assertWhenRedWins(linea);
    }

    @Test public void test07VictoryDiagonal1RedAndEndsModeB(){
        Linea linea = new Linea(7, 6, 'B');
        redDiagonal(linea);

        assertWhenRedWins(linea);
    }

    @Test public void test08VictoryDiagonal2RedAndEndsModeB(){
        Linea linea = new Linea(7, 6, 'B');

        linea.playRedAt(5);
        linea.playBlueAt(4);
        linea.playRedAt(4);
        linea.playBlueAt(3);
        linea.playRedAt(3);
        linea.playBlueAt(2);
        linea.playRedAt(3);
        linea.playBlueAt(2);
        linea.playRedAt(2);
        linea.playBlueAt(5);
        linea.playRedAt(2);

        assertWhenRedWins(linea);
    }

    @Test public void test09VictoryVerticalRedAndEndsModeC() {
        Linea linea = new Linea(7, 6, 'C');
        redVertical(linea);

        assertWhenRedWins(linea);
    }

    @Test public void test10VictoryHorizontalRedAndEndsModeC() {
        Linea linea = new Linea(7, 6, 'C');
        redHorizonal(linea);

        assertWhenRedWins(linea);
    }

    @Test public void test11VictoryDiagonalRedAndEndsModeC(){
        Linea linea = new Linea(7, 6, 'C');
        redDiagonal(linea);

        assertWhenRedWins(linea);
    }

    @Test public void test12VictoryVerticalBlueAndEndsModeA() {
        Linea linea = new Linea(7, 6, 'A');
        blueVertical(linea);

        assertWhenBlueWins(linea);
    }

    @Test public void test13VictoryHorizontalBlueAndEndsModeA() {
        Linea linea = new Linea(7, 6, 'A');
        blueHorizonal(linea);

        assertWhenBlueWins(linea);
    }

    @Test public void test14VictoryDiagonalBlueAndEndsModeB(){
        Linea linea = new Linea(7, 6, 'B');
        blueDiagonal(linea);

        assertWhenBlueWins(linea);
    }

    @Test public void test15VictoryVerticalBlueAndEndsModeC() {
        Linea linea = new Linea(7, 6, 'C');
        blueVertical(linea);

        assertWhenBlueWins(linea);
    }

    @Test public void test16VictoryHorizontalBlueAndEndsModeC() {
        Linea linea = new Linea(7, 6, 'C');
        blueHorizonal(linea);

        assertWhenBlueWins(linea);
    }

    @Test public void test17VictoryDiagonalBlueAndEndsModeC(){
        Linea linea = new Linea(7, 6, 'C');
        blueDiagonal(linea);

        assertWhenBlueWins(linea);
    }

    @Test public void test18GameDoesNotFinishWhenIsDiagonalInModeA(){
        Linea linea = new Linea(7, 6, 'A');
        blueDiagonal(linea);

        assertFalse(linea.finished());
    }

    @Test public void test19GameDoesNotFinishWhenIsVerticalInModeB(){
        Linea linea = new Linea(7, 6, 'B');
        blueVertical(linea);

        assertFalse(linea.finished());
    }

    @Test public void test20GameDoesNotFinishWhenIsHorizontalInModeA(){
        Linea linea = new Linea(7, 6, 'B');
        blueHorizonal(linea);

        assertFalse(linea.finished());
    }

    @Test public void test21DrawAndEnds(){
        Linea linea = new Linea(4, 4, 'C');
        linea.playRedAt(0);
        linea.playBlueAt(0);
        linea.playRedAt(0);
        linea.playBlueAt(0);

        linea.playRedAt(3);

        linea.playBlueAt(1);
        linea.playRedAt(1);
        linea.playBlueAt(1);
        linea.playRedAt(1);

        linea.playBlueAt(2);
        linea.playRedAt(2);
        linea.playBlueAt(2);
        linea.playRedAt(2);

        linea.playBlueAt(3);
        linea.playRedAt(3);
        linea.playBlueAt(3);

        assertTrue(linea.finished());
        assertEquals(linea.getWinner (), Linea.messageDraw);
        assertThrowsLike(() -> linea.playRedAt(4), Linea.messageGameFinished);
    }

    @Test public void test22ColumnFull(){
        Linea linea = new Linea(7, 6, 'B');

        linea.playRedAt(3);
        linea.playBlueAt(3);
        linea.playRedAt(3);
        linea.playBlueAt(3);
        linea.playRedAt(3);
        linea.playBlueAt(3);

        assertThrowsLike(() -> linea.playRedAt(3), Linea.messageFullColumn);
    }

    @Test public void test23ColumnOutOfBounds(){
        Linea linea = new Linea(7, 6, 'B');

        assertThrowsLike(() -> linea.playRedAt(9), Linea.messageColumnOutOfBounds);
    }

    @Test  public void test24NotAValidWinVariant() {
        assertThrowsLike(() -> new Linea(7, 6, 'k'), Linea.messageNotAValidVariant);
    }

    @Test public void test25InterruptedVertical(){
        Linea linea = new Linea(6, 7, 'A');

        linea.playRedAt(1);
        linea.playBlueAt(2);
        linea.playRedAt(1);
        linea.playBlueAt(2);
        linea.playRedAt(1);
        linea.playBlueAt(2);
        linea.playRedAt(3);
        linea.playBlueAt(1);
        linea.playRedAt(1);

        assertFalse(linea.finished());
    }

    @Test public void test26InterruptedHorizonal(){
        Linea linea = new Linea(6, 7, 'A');

        linea.playRedAt(0);
        linea.playBlueAt(0);
        linea.playRedAt(1);
        linea.playBlueAt(1);
        linea.playRedAt(2);
        linea.playBlueAt(3);
        linea.playRedAt(4);

        assertFalse(linea.finished());
    }

    @Test public void test27InterruptedDiagonal(){
        Linea linea = new Linea(6, 7, 'B');

        linea.playRedAt(0);
        linea.playBlueAt(1);
        linea.playRedAt(1);
        linea.playBlueAt(2);
        linea.playRedAt(2);
        linea.playBlueAt(2);
        linea.playRedAt(3);
        linea.playBlueAt(3);
        linea.playRedAt(3);
        linea.playBlueAt(0);
        linea.playRedAt(3);
        linea.playBlueAt(4);
        linea.playRedAt(4);
        linea.playBlueAt(4);
        linea.playRedAt(4);
        linea.playBlueAt(0);
        linea.playRedAt(4);

        assertFalse(linea.finished());
    }

    @Test public void test28show(){
        Linea linea = new Linea(4, 4, 'A');

        assertEquals(linea.show(), "_ _ _ _ \n_ _ _ _ \n_ _ _ _ \n_ _ _ _ \n0 1 2 3 \n\n");
    }

    @Test public void test29show(){
        Linea linea = new Linea(4, 4, 'A');

        blueVertical(linea);

        assertEquals(linea.show(), "_ B _ _ \nR B _ _ \nR B _ _ \nR B R _ \n0 1 2 3 \n\nWinner: Blue");
    }




    private void assertThrowsLike( Executable e, String message ) {
        assertEquals( message, assertThrows( Exception.class, e ).getMessage() );
    }

    private void assertWhenRedWins(Linea linea) {
        assertTrue(linea.finished());
        assertEquals(linea.getWinner (), Linea.messageWinnerRed);
        assertThrowsLike(() -> linea.playBlueAt(0), Linea.messageGameFinished);
    }

    private void assertWhenBlueWins(Linea linea) {
        assertTrue(linea.finished());
        assertEquals(linea.getWinner (), Linea.messageWinnerBlue);
        assertThrowsLike(() -> linea.playRedAt(4), Linea.messageGameFinished);
    }

    private void blueDiagonal(Linea linea) {
        linea.playRedAt(4);
        linea.playBlueAt(4);
        linea.playRedAt(3);
        linea.playBlueAt(3);
        linea.playRedAt(2);
        linea.playBlueAt(3);
        linea.playRedAt(2);
        linea.playBlueAt(1);
        linea.playRedAt(2);
        linea.playBlueAt(2);
        linea.playRedAt(1);
        linea.playBlueAt(1);
        linea.playRedAt(1);
        linea.playBlueAt(1);
    }

    private void blueVertical(Linea linea) {
        linea.playRedAt(0);
        linea.playBlueAt(1);
        linea.playRedAt(0);
        linea.playBlueAt(1);
        linea.playRedAt(0);
        linea.playBlueAt(1);
        linea.playRedAt(2);
        linea.playBlueAt(1);
    }

    private void blueHorizonal(Linea linea) {
        linea.playRedAt(0);
        linea.playBlueAt(1);
        linea.playRedAt(1);
        linea.playBlueAt(2);
        linea.playRedAt(2);
        linea.playBlueAt(3);
        linea.playRedAt(3);
        linea.playBlueAt(4);
    }

    private void redDiagonal(Linea linea) {
        linea.playRedAt(1);
        linea.playBlueAt(2);
        linea.playRedAt(2);
        linea.playBlueAt(3);
        linea.playRedAt(3);
        linea.playBlueAt(4);
        linea.playRedAt(3);
        linea.playBlueAt(4);
        linea.playRedAt(4);
        linea.playBlueAt(1);
        linea.playRedAt(4);
    }

    private void redVertical(Linea linea) {
        linea.playRedAt(0);
        linea.playBlueAt(1);
        linea.playRedAt(0);
        linea.playBlueAt(1);
        linea.playRedAt(0);
        linea.playBlueAt(1);
        linea.playRedAt(0);
    }

    private void redHorizonal(Linea linea) {
        linea.playRedAt(0);
        linea.playBlueAt(0);
        linea.playRedAt(1);
        linea.playBlueAt(1);
        linea.playRedAt(2);
        linea.playBlueAt(2);
        linea.playRedAt(3);
    }

}
