import java.io.*;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.Map;

/**
 * Created by taner on 06.04.2017.
 */
public class Main
{
	public static class ListOwner
	{
		public String name;
		public List<Integer> points;

		public ListOwner(String name, List<Integer> points)
		{
			this.name = name;
			this.points = points;
		}

		public String getName()
		{
			return name;
		}

		public List<Integer> getPoints(){
			return points;
		}
	}
	public static void main(String[] args) throws IOException
	{
		Stream.iterate(0, i -> i < 10, i -> i+1).forEach(System.out::println);

		ListOwner nullOwner = null;
		ListOwner notNullOwner = new ListOwner("Taner", List.of(1,2,3,4));

		// JAVA 8
		Stream.of(nullOwner).map(o -> o==null?Stream.empty():o.getPoints()).forEach(System.out::println);
		Stream.of(notNullOwner).map(o -> o==null?Stream.empty():o.getPoints()).forEach(System.out::println);

		// JAVA 9
		Stream.ofNullable(nullOwner).map(ListOwner::getPoints).forEach(System.out::println);
		Stream.ofNullable(notNullOwner).map(ListOwner::getPoints).forEach(System.out::println);


		List<Integer> sortedNumbers = List.of(0,1,2,3,4,5,6,7,8,9,10);

		System.out.println("***** takeWhile *****");
		sortedNumbers.stream().takeWhile(n -> n < 5).forEach(System.out::println);

		System.out.println("***** dropWhile *****");
		sortedNumbers.stream().dropWhile(n -> n < 5).forEach(System.out::println);



		List<ListOwner> owners = List.of(new ListOwner("Taner", List.of(1,2,3,4)),
				new ListOwner("Altug", List.of(21,22,23,24)),
				new ListOwner("Taner", List.of(31,32,33,34)));

		Map<String, List<List<Integer>>> ownerPoints =
				owners.stream().collect(
						Collectors.groupingBy(
								ListOwner::getName,
								Collectors.mapping(
										ListOwner::getPoints,
										Collectors.toList())));
		System.out.println(ownerPoints);

		Map<String, List<Integer>> ownerPoints2 =
				owners.stream().collect(
						Collectors.groupingBy(
								ListOwner::getName,
								Collectors.flatMapping(
										owner -> owner.getPoints().stream(),
										Collectors.toList())));
		System.out.println(ownerPoints2);

		List<Integer> numbers = List.of(1, 2, 3, 5, 5);

		Map<Integer, Long> result = numbers.stream()
				.filter(val -> val > 3)
				.collect(Collectors.groupingBy(i -> i, Collectors.counting()));
		System.out.println(result.toString());

		result = numbers.stream()
				.collect(Collectors.groupingBy(i -> i,
						Collectors.filtering(val -> val > 3, Collectors.counting())));
		System.out.println(result.toString());

	}

}


