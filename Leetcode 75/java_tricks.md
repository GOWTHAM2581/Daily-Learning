# to convert string to char array

char[] words = s.toCharArray();


# to convert char array to string

String s = new String(words);   // here words is the char array
// words is a String[], but new String() only accepts char[] or byte[].

# to print a array

System.out.println(Arrays.toString(words));

# to split a string of sentence into array

String[] words = s.split(" ");

String[] words = sentence.split("\\s+");  //remove one or more spaces




//convert Array List into Array

List<Integer> list = new ArrayList<>();

int[] arr = list.stream().mapToInt(i -> i).toArray();