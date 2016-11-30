package sp;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import static org.junit.Assert.*;
import static sp.SecondPartTasks.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SecondPartTasksTest {

    @Test
    public void testFindQuotes() {
    	String file = Paths.get("src", "main", "java", "sp", "SecondPartTasks.java")
    			  		   .toString();
        assertEquals(Collections.singletonList("package sp;"),
   			 		 findQuotes(Collections.singletonList(file), "pack"));
        assertEquals(Arrays.asList(),
			 		 findQuotes(Collections.emptyList(), "pack"));
        assertEquals(Arrays.asList(),
        			 findQuotes(Collections.singletonList(file), "peck"));
    }

    @Test
    public void testPiDividedBy4() {
        assertEquals(Math.PI / 4, piDividedBy4(), 1e-3);
    }

    @Test
    public void testFindPrinter() {
        assertEquals("lol", findPrinter(ImmutableMap.of("lol", Arrays.asList())));
        assertEquals("kek", findPrinter(ImmutableMap.of("lol", Arrays.asList(), 
														"kek", Arrays.asList("kek"))));
        assertEquals("keks", findPrinter(ImmutableMap.of("lol", Arrays.asList(), 
														 "kek", Arrays.asList("kek"),
        												 "keks", Arrays.asList("k", 
        														 			   "e", 
        														 			   "k", 
        														 			   "s"))));
    }

    @Test
    public void testCalculateGlobalOrder() {
        assertEquals(Collections.emptyMap(), 
        			 calculateGlobalOrder(Collections.emptyList()));
        assertEquals(ImmutableMap.of("a", 3), 
   			 		 calculateGlobalOrder(Arrays.asList(ImmutableMap.of("a", 1),
   					 								    ImmutableMap.of("a", 2))));
        assertEquals(ImmutableMap.of("a", 1, "b", 2), 
        			 calculateGlobalOrder(Arrays.asList(ImmutableMap.of("a", 1),
        					 							ImmutableMap.of("b", 2))));
    }
}