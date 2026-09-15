import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class RefactorDaos {
    public static void main(String[] args) throws IOException {
        String directory = "src/main/java/com/xanadutec/coreflex";
        try (Stream<Path> paths = Files.walk(Paths.get(directory))) {
            paths.filter(Files::isRegularFile)
                 .filter(p -> p.toString().endsWith("DaoImpl.java"))
                 .forEach(RefactorDaos::refactorFile);
        }
        System.out.println("Refactoring complete.");
    }

    private static void refactorFile(Path path) {
        try {
            String content = new String(Files.readAllBytes(path), "UTF-8");
            String originalContent = content;

            // 1. Handle try-with-resources
            content = content.replaceAll(
                "try\\s*\\(\\s*Session\\s+session\\s*=\\s*sessionFactory\\.openSession\\(\\)\\s*\\)\\s*\\{",
                "{ Session session = sessionFactory.getCurrentSession();"
            );

            // 2. Replace openSession
            content = content.replace("sessionFactory.openSession()", "sessionFactory.getCurrentSession()");

            // 3. Remove session.close();
            content = content.replaceAll("session\\.close\\(\\)\\s*;\\s*", "");

            // 4. Remove session.beginTransaction()
            content = content.replaceAll(
                "(?:org\\.hibernate\\.)?Transaction\\s+(?:tx|transaction|t)\\s*=\\s*session\\.beginTransaction\\(\\)\\s*;\\s*",
                ""
            );

            // 5. Remove tx.commit()
            content = content.replaceAll("(?:tx|transaction|t)\\.commit\\(\\)\\s*;\\s*", "");

            // 6. Add @Transactional
            if (content.contains("sessionFactory.getCurrentSession()")) {
                if (!Pattern.compile("@Transactional\\s+public\\s+class").matcher(content).find()) {
                    content = content.replaceAll("(public\\s+class\\s+\\w+DaoImpl)", "@Transactional\n$1");
                }
                
                if (!content.contains("import org.springframework.transaction.annotation.Transactional;")) {
                    content = content.replaceFirst(
                        "(import .*?;)",
                        "$1\nimport org.springframework.transaction.annotation.Transactional;"
                    );
                }
            }

            if (!content.equals(originalContent)) {
                Files.write(path, content.getBytes("UTF-8"));
                System.out.println("Refactored " + path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
