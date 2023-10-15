package komputer;

import java.util.ArrayList;
import java.util.List;

public class Komputer {
    
    private String brand;
    private String model;
    private String disk;
    private int diskSize;
    private int ram;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDisk() {
        return disk;
    }

    public void setDisk(String disk) {
        this.disk = disk;
    }

    public int getDiskSize() {
        return diskSize;
    }

    public void setDiskSize(int diskSize) {
        this.diskSize = diskSize;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }
    public static void main(String[] args) {
        List<Komputer> komputerList = new ArrayList<>();
        komputerList.add(new Komputer());
        komputerList.add(new Komputer());
        komputerList.add(new Komputer());
        komputerList.add(new Komputer());

        List<Komputer> matchingComputers = searchByBrand(komputerList, "hp");
        for (Komputer komputer : matchingComputers) {
            System.out.println("Merek: " + komputer.getBrand());
        }
    }

        public static List<Komputer> searchByBrand(List<Komputer> komputerList, String brand) {
        List<Komputer> matchingComputers = new ArrayList<>();

        for (Komputer k : komputerList) {
            if (k != null && Levenshtein.compare(k.getBrand().toLowerCase(), brand.toLowerCase()) <= 3) {
                matchingComputers.add(k);
            }
        }

        return matchingComputers;
    }

    Object getDiskType() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    Object getRamSize() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    public static class Levenshtein {
        public static int compare(String str1, String str2) {
            int len1 = str1.length();
            int len2 = str2.length();

            int[][] matrix = new int[len1 + 1][len2 + 1];

            for (int i = 0; i <= len1; i++) {
                for (int j = 0; j <= len2; j++) {
                    if (i == 0) {
                        matrix[i][j] = j;
                    } else if (j == 0) {
                        matrix[i][j] = i;
                    } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                        matrix[i][j] = matrix[i - 1][j - 1];
                    } else {
                        matrix[i][j] = Math.min(matrix[i - 1][j] + 1, Math.min(matrix[i][j - 1] + 1, matrix[i - 1][j - 1] + 1));
                    }
                }
            }

            return matrix[len1][len2];
        }
    }

}