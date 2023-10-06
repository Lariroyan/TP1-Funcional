package sumergible;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class SumergibleTest {
	
	@Test public void test00CoordenadasIniciales() {
		assertEquals (0, new Sumergible().getCoordenadaX());
		assertEquals (0, new Sumergible().getCoordenadaY());
		assertEquals (0, new Sumergible().getProfundidad());
		assertEquals (0, new Sumergible().getAngulo());
	}
	
	@Test public void test01() { //quedarse en el lugar
		Sumergible sumergible = new Sumergible().doThis("");
		assertEquals (0, sumergible.getCoordenadaX());
		assertEquals (0, sumergible.getCoordenadaY());
		assertEquals (0, sumergible.getProfundidad());
		assertEquals (0, sumergible.getAngulo());
	}
	
	@Test public void test02() {
		Sumergible sumergible = new Sumergible().doThis("d"); //usar caracteres
		assertEquals (0, sumergible.getCoordenadaX());
		assertEquals (0, sumergible.getCoordenadaY());
		assertEquals (-1, sumergible.getProfundidad());
		assertEquals (0, sumergible.getAngulo());
	}
	
	//test 03 "u"
	@Test public void test03() {
		Sumergible sumergible = new Sumergible().doThis("d");
		assertEquals (-1, sumergible.getProfundidad());
		
		sumergible.doThis("u"); 
		assertEquals (0, sumergible.getProfundidad());
	}

	//test 04 "l" "r"
	@Test public void test04(){
		Sumergible sumergible = new Sumergible().doThis("l");
		assertEquals (90, sumergible.getAngulo());
		
		sumergible.doThis("r");
		assertEquals (0, sumergible.getAngulo());
			
	}
    //caso de test con r
	@Test public void test05(){
		
		Sumergible sumergible = new Sumergible();
		assertEquals (0, sumergible.getAngulo());
		
		sumergible.doThis("r");
		assertEquals (270, sumergible.getAngulo());
			
	}

	//caso de test con f 
	@Test public void test06(){ 
		Sumergible sumergible = new Sumergible().doThis("f");
		assertEquals (1, sumergible.getCoordenadaX());
		assertEquals (0, sumergible.getAngulo());
	}
	
	//caso de test con m - no hay que hacerlo todavía pero hay que ir viendo y probando 
	@Test public void test07(){
		Sumergible sumergible = new Sumergible().doThis("m d");
		assertEquals (-1, sumergible.getProfundidad()); 
		assertEquals (true, sumergible.getEstadoCapsulaLiberada()); 

		sumergible.doThis("d");
		assertEquals (-2, sumergible.getProfundidad()); 
	}

	@Test public void test08(){
		Sumergible sumergible = new Sumergible().doThis("d d");
		assertThrowsLike (() -> sumergible.doThis("m"), Sumergible.errorMessage_Explota);
	}
	
	
	@Test public void test09(){
		Sumergible sumergible = new Sumergible().doThis("d d u f l f l f r f l l f f");
		assertEquals (-1, sumergible.getProfundidad());
		assertEquals (0, sumergible.getCoordenadaX());
		assertEquals (0, sumergible.getCoordenadaY());
		assertEquals (270, sumergible.getAngulo());
		assertEquals (false, sumergible.getEstadoCapsulaLiberada());
		
	}
	
	
	
	
	private void assertThrowsLike( Executable e, String message ) {
		  assertEquals( message, assertThrows( Error.class, e ).getMessage() );
	  }

}	
