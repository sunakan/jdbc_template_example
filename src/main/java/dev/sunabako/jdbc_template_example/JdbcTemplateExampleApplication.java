package dev.sunabako.jdbc_template_example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class JdbcTemplateExampleApplication implements ApplicationRunner {

    final JdbcTemplate jdbcTemplate;

    // ここでの@Autowiredは省略可能だけど、していない
    public JdbcTemplateExampleApplication(@Autowired JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(JdbcTemplateExampleApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 1. customersテーブルがあるならDROPする
        System.out.println("------------------[ 1 ]");
        //jdbcTemplate.execute("DROP TABLE customers IF EXISTS");

        // 2. customersテーブルをCREATEする
        System.out.println("------------------[ 2 ]");
        //jdbcTemplate.execute("CREATE TABLE customers(id SERIAL, last_name VARCHAR(255), first_name VARCHAR(255))");

        // 3. 名字と名前でsplitする
        System.out.println("------------------[ 3 ]");
        List<Object[]> splitUpNames = Arrays.asList("田中 太郎", "鈴木 一郎", "高橋 次郎", "田中 一郎")
                .stream()
                .map(name -> name.split(" "))
                .collect(Collectors.toList());

        // 4. Java 8 の stream を使って Print する
        System.out.println("------------------[ 4 ]");
        splitUpNames.forEach(name -> System.out.println(String.format("INSERT 顧客 レコード for %s %s", name[0], name[1])));

        // 5. JdbcTemplateのbatchUpdateを使って バルクインサート
        // 複数のINSERTの場合、batchUpdateが良いらしい
        // 利用：batchUpdate(String sql, List<Object[]> batchArgs)
        System.out.println("------------------[ 5 ]");
        jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);

        // 6. JdbcTemplateを使って クエリ 実行(クエリ内容：first_nameが一郎)
        // 利用：queryForStream(StringSE sql, RowMapper<T> rowMapper, ObjectSE... args)
        System.out.println("------------------[ 6 ]");
        jdbcTemplate.queryForStream(
                "SELECT id, first_name, last_name FROM customers WHERE first_name = ?",
                new CustomerRowMapper(),
                new Object[]{"田中"}
        ).forEach(customer -> System.out.println(customer.toString()));
        System.out.println("------------------田中だけ出たはず");
    }
}
