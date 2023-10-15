package komputer;


import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
public class DataKomputer implements AppInterface {
    private final Komputer[] komputer;
        
    public DataKomputer() {
        komputer = new Komputer[1000];
    }    
    
    @Override 
    public int pilihanMenu() { 
        String p = JOptionPane.showInputDialog(null,""
                + "<html>"
                + "===== Pilihan =========<br>"
                + "1 &rarr; Tambahan Data Komputer<br>"
                + "2 &rarr; Cari berdasarkan Brand Komputer<br>"
                + "3 &rarr; Cari berdasarkan Model Komputer<br>"
                + "4 &rarr; Keluar Aplikasi<br>"
                + "=======================<br>"
                + "<b>Ketik Pilihan Anda</b>"
                + "</html>",
                "Munu Pilihan",
                JOptionPane.QUESTION_MESSAGE);
        int pilihan = Integer.parseInt(p);//casting
        return pilihan;
    } 
    
    @Override
    public void add() {

        Komputer kom = new Komputer();
        String brand = JOptionPane.showInputDialog(null, "Ketik Brand:", "" + "Brand", JOptionPane.QUESTION_MESSAGE);
        kom.setBrand(brand);
        String model = JOptionPane.showInputDialog(null, "Ketik Model:", "" + "Brand", JOptionPane.QUESTION_MESSAGE);
        kom.setModel(model);
        String disk = JOptionPane.showInputDialog(null, "Tipe Disk (SSD/Harddisk):", "" + "Tipe Disk", JOptionPane.QUESTION_MESSAGE);
        kom.setDisk(disk);
        String size = JOptionPane.showInputDialog(null, "Kapasitas Disk (Angka):", "" + "Kapasitas Disk (dalam GB)", JOptionPane.QUESTION_MESSAGE);
        int diskSize = Integer.parseInt(size);
        kom.setDiskSize(diskSize);
        String ram = JOptionPane.showInputDialog(null, "Kapasitas RAM (angka):", "" + "Ukuran RAM (dalam GB)", JOptionPane.QUESTION_MESSAGE);
        int ramSize = Integer.parseInt(ram);
        kom.setRam(ramSize);
        
        for (int i = 0; i < komputer.length; i++) {
            if(komputer[i] == null) {
                komputer[i] = kom;
                break;
            }
        }
        
        viewData(kom);

    }
    
    @Override
    public String keyword (String type) {
        String key = JOptionPane.showInputDialog(null, "Ketik "+ type + " Komputer",type, JOptionPane.QUESTION_MESSAGE);
        return key;
    }
    
    @Override
    public Komputer searchByBrand (String brand) {
        Komputer komp = null;

        for (Komputer k : komputer) {
            if (k != null && Levenshtein.compare(k.getBrand().toLowerCase(), brand.toLowerCase()) <= 3) {
                komp = k;
                break;
            }
        }
        return komp;
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

    
    @Override
    public Komputer searchByModel(String model) {

        Komputer komp = null;

        for (Komputer k : komputer) {
            if (k != null && Levenshtein.compare(k.getBrand().toLowerCase(), model.toLowerCase()) <= 3) {
                komp = k;
                break;
            }
        }
        return komp;  
    }
    
    @Override
    public void viewData (Komputer k) {
        if(k == null) {
            JOptionPane.showMessageDialog(null,"Data tidak ditemukan!");
        } else {
            JOptionPane.showMessageDialog(null,
                    "Brand\t\t: "+k.getBrand() + 
                    "\nModel\t\t: "+k.getModel() + 
                    "\nDisk Type\t\t: "+k.getDisk() + 
                    "\nRAM size\t\t: "+k.getDiskSize() + 
                    "\nRAM size\t\t: "+k.getRam(),
                    "Data Komputer",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void viewTable() {
        if(this.komputer.length == 0){
            JOptionPane.showMessageDialog (null, "Not found!");
            return;
        }
        
        Object[] cols = {
            "Number", "Brand", "Model", "Disk Type", "Disk Size", "RAM Size"
        };
        Object[][] rows = new Object[this.komputer.length][6];
        for (int i = 0; i < this.komputer.length; i++) {
            Komputer[] computers = null;
            Komputer computer = computers[i];
            if (computer == null) {
                break;
            }            
            rows[i][0] = i + 1;
            rows[i][1] = computer.getBrand();
            rows[i][2] = computer.getModel();
            rows[i][3] = computer.getDiskType();
            rows[i][4] = computer.getDiskSize();
            rows[i][5] = computer.getRamSize();            
        }        
        JTable table = new JTable(rows, cols);
        JOptionPane.showMessageDialog(null, new JScrollPane(table));   
    }
    @Override
    public void exit() {
        System.exit(0);
    }
}