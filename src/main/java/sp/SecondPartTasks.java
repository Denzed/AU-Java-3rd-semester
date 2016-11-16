package sp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class SecondPartTasks {
	
    private SecondPartTasks() {}

    // Найти строки из переданных файлов, в которых встречается указанная подстрока.
    public static List<String> findQuotes(List<String> paths, CharSequence sequence) {
    	return paths.stream()
    				.map(Paths::get)
    				.flatMap(file -> {
    					Stream.Builder<String> tmp = Stream.builder();
    					Stream<String> res = tmp.build();
    					try {
							res = Files.lines(file);
						} catch (IOException e) {
							System.err.println(String.format(
									"An error occured while reading %s -- skipped", 
									file.toString()));
							e.printStackTrace();
						}
						return res;
					})
    				.filter(line -> line.contains(sequence))
    			    .collect(Collectors.toList());
    }

    // В квадрат с длиной стороны 1 вписана мишень.
    // Стрелок атакует мишень и каждый раз попадает в произвольную точку квадрата.
    // Надо промоделировать этот процесс с помощью класса java.util.Random и посчитать, какова вероятность попасть в мишень.
    public static double piDividedBy4() {
        final Random gen = new java.util.Random();
        final long ITERATIONS = 1000000;
        return Stream.generate(() -> {
        	Double x = gen.nextDouble() - 0.5,
        		   y = gen.nextDouble() - 0.5;
        	return x * x + y * y;
        })
        			 .limit(ITERATIONS)
        			 .mapToInt(dist -> dist <= 0.25 ? 1 : 0)
        		     .average()
        		     .orElse(0);
    }

    // Дано отображение из имени автора в список с содержанием его произведений.
    // Надо вычислить, чья общая длина произведений наибольшая.
    public static String findPrinter(Map<String, List<String>> compositions) {
        return compositions.entrySet()
        				   .stream()
        				   .collect(Collectors.toMap(
         						  Map.Entry::getKey,
        						  (entry) -> entry.getValue()
        						  				  .stream()
        						   				  .collect(Collectors.summingInt(
        						   				  		   String::length))))
        				   .entrySet()
        				   .stream()
        				   .max((e1, e2) -> e1.getValue().compareTo(e2.getValue()))
        				   .get()
        				   .getKey();
    }

    // Вы крупный поставщик продуктов. Каждая торговая сеть делает вам заказ в виде Map<Товар, Количество>.
    // Необходимо вычислить, какой товар и в каком количестве надо поставить.
    public static Map<String, Integer> calculateGlobalOrder(List<Map<String, Integer>> orders) {
        return orders.stream()
        			 .reduce(Collections.emptyMap(), 
        					 (m1, m2) -> Stream.of(m1, m2)
        						 	   		   .map(Map::entrySet)
        						 	   		   .flatMap(Collection::stream)
        						 	   		   .collect(Collectors.toMap(Map.Entry::getKey, 
        						 			   					 		 Map.Entry::getValue,
        						 			   					 		 Integer::sum)));
    }
}