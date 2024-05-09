package W03_StructuralSpecificationBasedTesting;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class CaseUtilsTest {
    @Test
    public void testBaseCase(){
        assertEquals("",CaseUtils.toCamelCase("",true,null));
        assertEquals(null,CaseUtils.toCamelCase(null,true,null));
    }
    @Test
    public void testWithoutDelimiters(){
        assertEquals("HelloBye",CaseUtils.toCamelCase("heLlO Bye",true));
        assertEquals("h",CaseUtils.toCamelCase("h",false));
        assertEquals("H",CaseUtils.toCamelCase("h",true,null));
        assertEquals("\n",CaseUtils.toCamelCase("\n",false));
    }
    @Test
    public void testWithDelimiters(){
        assertEquals("HelloBye",CaseUtils.toCamelCase("heLlO   BYE",true,' '));
        assertEquals("helloBye",CaseUtils.toCamelCase("heLlO   BYE",false,' '));
        assertEquals("helloBye",CaseUtils.toCamelCase("heLlO BYE",false,'\t'));
        assertEquals("thisistest",CaseUtils.toCamelCase(" \t ThisIsTest\t",false,'\t',' '));
        assertEquals(" \t \n \t \n",CaseUtils.toCamelCase(" \t \n \t \n",true,'\t','\n',' '));
        assertEquals(" ",CaseUtils.toCamelCase(" ",false,' '));
    }

}