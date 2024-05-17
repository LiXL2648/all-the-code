package com.li.decorator;

/**
 * @author LiXL
 * @date 2024/2/28
 */
public class Demo {

    public static void main(String[] args) {
        String salaryRecords = "Name,Salary\nJohn Smith,100000\nSteven Jobs,912000";
        String fileName = "C:\\Users\\16344\\Desktop\\a.txt";
        DataSource dataSource = new FileDataSource(fileName);

        DataSourceDecorator dataSourceDecorator = new CompressionDecorator(new EncryptionDecorator(dataSource));
        dataSourceDecorator.writeData(salaryRecords);
        System.out.println(salaryRecords + "\n");
        System.out.println(dataSource.readData() + "\n");
        System.out.println(dataSourceDecorator.readData());
    }
}
