package nemo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.function.Executable;

public class NemoTest {
	
	@Test public void test00InitialCoordinates() {
		assertPosition(nemoInitialCordsFacingNorth(), 0, 0, 0, "North");
	}

	@Test public void test01OtherInitialCoordinates() {
		Nemo nemo = new Nemo(5, -4, new East());
		assertPosition(nemo, 5, -4, 0, "East");
	}
	
	@Test public void test02SendNothingDoNothing() {
		Nemo nemo = nemoInitialCordsFacingNorth().doThis("");
		assertPosition(nemo, 0, 0, 0, "North");
	}
	
	@Test public void test03GoDown() {
		Nemo nemo = nemoInitialCordsFacingNorth().doThis("d");
		assertPosition(nemo, 0, 0, 1, "North");
	}
	
	@Test public void test04GoDownDown() {
		Nemo nemo = nemoInitialCordsFacingNorth().doThis("dd");
		assertPosition(nemo, 0, 0, 2, "North");
	}
	
	@Test public void test05GoDownThenUp() {
		Nemo nemo = nemoInitialCordsFacingNorth().doThis("d");
		assertPosition(nemo, 0, 0, 1, "North");
		
		nemo.doThis("u");
		assertPosition(nemo, 0, 0, 0, "North");
	}
	
	@Test public void test06GoUpWhenNemoIsAtTheTop() {
		Nemo nemo = nemoInitialCordsFacingNorth();
		
		assertPosition(nemo, 0, 0, 0, "North");
		assertPosition(nemo.doThis("u"), 0, 0, 0, "North");
	}

	@Test public void test07TurnLeftNorth(){
		Nemo nemo = nemoInitialCordsFacingNorth();
		
		assertPosition(nemo, 0, 0, 0, "North");
		assertPosition(nemo.doThis("l"), 0, 0, 0, "West");
	}
	
	@Test public void test08TurnRightNorth(){
		Nemo nemo = nemoInitialCordsFacingNorth();
		
		assertPosition(nemo, 0, 0, 0, "North");
		assertPosition(nemo.doThis("r"), 0, 0, 0, "East");
	}
	
	@Test public void test09TurnRightAndRightToSouth(){
		Nemo nemo = nemoInitialCordsFacingNorth();
		
		assertPosition(nemo, 0, 0, 0, "North");
		assertPosition(nemo.doThis("rr"), 0, 0, 0, "South");
	}
	
	@Test public void test10TurnRightThenLeftNorth(){
		Nemo nemo = nemoInitialCordsFacingNorth();
		
		assertPosition(nemo.doThis("r"), 0, 0, 0, "East");
		assertPosition(nemo.doThis("l"), 0, 0, 0, "North");
	}

	@Test public void test11MoveForwardToNorth(){
		Nemo nemo = nemoInitialCordsFacingNorth().doThis("f");
		
		assertPosition(nemo, 0, 1, 0, "North");
	}
	
	@Test public void test12MoveForwardToEast(){
		Nemo nemo = nemoInitialCordsFacingNorth().doThis("rf");
		
		assertPosition(nemo, 1, 0, 0, "East");
	}
	
	@Test public void test13MoveForwardToWest(){
		Nemo nemo = nemoInitialCordsFacingNorth().doThis("lf");
		
		assertPosition(nemo, -1, 0, 0, "West");
	}
	
	@Test public void test14MoveForwardToSouth(){
		Nemo nemo = nemoInitialCordsFacingNorth().doThis("rrf");
		
		assertPosition(nemo, 0, -1, 0, "South");
	}
	
	@Test public void test15ReleaseACapsuleOnTheSurface(){
		Nemo nemo = nemoInitialCordsFacingNorth().doThis("m");
		
		assertPosition(nemo, 0, 0, 0, "North");
	}
	
	@Test public void test16ReleaseACapsuleOnDepthOne(){
		Nemo nemo = nemoInitialCordsFacingNorth().doThis("dm");
		
		assertPosition(nemo, 0, 0, 1, "North");
	}
	
	@Test public void test17NemoExplodes() {
		assertThrowsLike (() -> nemoInitialCordsFacingNorth().doThis("ddm"), 
				Depth.errorMessage_Explodes);
	}
	
	@Test public void test18NemoStopsWhenExplodes(){ 
		assertThrowsLike(() -> nemoInitialCordsFacingNorth().doThis("ddmffudfrl"), 
				Depth.errorMessage_Explodes);
	}
	
	@Test public void test19MoveAndReleaseACapsuleOnTheSurface(){
		Nemo nemo = nemoInitialCordsFacingNorth().doThis("fflfrfrm");
		
		assertPosition(nemo, -1, 3, 0, "East");
	}
	
	@Test public void test20MoveAndReleaseACapsuleOnDepthOne(){
		Nemo nemo = nemoInitialCordsFacingNorth().doThis("dfflfrfrm");
		
		assertPosition(nemo, -1, 3, 1, "East");
	}
	
	@Test public void test21MoveAndReleaseACapsuleBelowDepthOne(){
		Nemo nemo = nemoInitialCordsFacingNorth().doThis("ddfflfrfr");
		
		assertPosition(nemo, -1, 3, 2, "East");
		assertThrowsLike (() -> nemo.doThis("m"), Depth.errorMessage_Explodes);
	}

	@Test public void test22ComplexNemoOperations(){ 
		Nemo nemo = nemoInitialCordsFacingNorth().doThis("lrfmrfmlfrrff");
		
		assertPosition(nemo, 1, 0, 0, "South");
	}

	@Test public void test23ComplexNemoOperationsWithOtherInitialCoordinates() {
		Nemo nemo = new Nemo(5, -4, new West()).doThis("dmdfulfrfrrflffudm");
		
		assertPosition(nemo, 4, -3, 1, "North");
	}
	
	
	private Nemo nemoInitialCordsFacingNorth() {
		return new Nemo(0, 0, new North());
	}
	
	private void assertPosition(Nemo nemo, int x, int y, int depth, String facing) {
		assertEquals (x, nemo.getCoordinateX());
		assertEquals (y, nemo.getCoordinateY());
		assertEquals (depth, nemo.getDepth());
		assertEquals (facing, nemo.getDirection());
	}
	
	private void assertThrowsLike( Executable e, String message ) {
		  assertEquals( message, assertThrows( Error.class, e ).getMessage() );
	}

}	