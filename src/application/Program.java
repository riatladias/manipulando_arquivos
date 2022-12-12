package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		List<Product> products = new ArrayList<>();

		System.out.println("Enter a file path: ");
		File filePath = new File(sc.nextLine());

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line = br.readLine();
			while (line != null) {
				String[] vect = line.split(",");
				String name = vect[0];
				Double price = Double.parseDouble(vect[1]);
				int quantity = Integer.parseInt(vect[2]);
				products.add(new Product(name, price, quantity));
				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		File Path = new File(filePath.getParent());

		new File(Path + "\\out").mkdir();

		String summaryFilePath = Path + "\\out\\summary.csv";

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(summaryFilePath))) {
			for (Product p : products) {
				bw.write(p.toString());
				bw.newLine();
			}
			System.out.println("summary.csv Created!");
		} catch (IOException e) {
			System.out.println("ERROR: " + e.getMessage());
		}

		sc.close();

	}

}
