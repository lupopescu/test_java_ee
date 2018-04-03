package mag;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class WeightAnimalAction extends RecursiveAction {
	private int start;
	private int end;
	private Double[] weigths;

	public WeightAnimalAction(int start, int end, Double[] weigths) {

		this.start = start;
		this.end = end;
		this.weigths = weigths;
	}

	public static void main(String[] args) {
		/*Double[] w = new Double[10];
		ForkJoinTask<?> task = new WeightAnimalAction(0, w.length, w);
		ForkJoinPool p = new ForkJoinPool();
		p.invoke(task);
		System.out.println();
		System.out.println(" weights ");*/
		// System.out.println(Arrays.deepToString(w));

		File file = new File("C:\\Users\\Liuda Popescu"); //\\hsqlprefs.dat");
		Path p=Paths.get("C:", "Users","Liuda Popescu");
		String nP=p.toString();
		File file2= new File(nP);
		System.out.println(nP+".........");
		
		
		System.out.println("File Exists: " + file.exists());
	if (file.exists()) {
			System.out.println("Absolute Path: " + file.getAbsolutePath());
			System.out.println("Is Directory: " + file.isDirectory());
			System.out.println("Parent Path: " + file.getParent());
			if (file.isFile()) {
				System.out.println("File size: " + file.length());
				System.out.println("File LastModified: " + file.lastModified());
			} else {
				for (File subfile : file.listFiles()) {
					System.out.println("\t" + subfile.getName());
				}
			}
		}

	}

	@Override
	protected void compute() {
		if ((end - start) <= 3) {
			for (int i = start; i < end; i++) {
				weigths[i] = (double) new Random().nextInt(100);
				System.out.println("animal " + i + " weighted");
			}
		} else {
			int middle = start + ((end - start) / 2);
			System.out.println("start " + start + " middle " + middle + " end "
					+ end);
			invokeAll(new WeightAnimalAction(start, middle, weigths),
					new WeightAnimalAction(middle, end, weigths));
		}

	}

}
