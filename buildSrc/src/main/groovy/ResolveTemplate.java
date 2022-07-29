import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResolveTemplate {

    private static String readFileContent(File file) throws IOException {
        return String.join("\n", Files.readAllLines(file.toPath(), StandardCharsets.UTF_8));
    }

    private static String includeCss(String content, File projectDirectory) throws IOException {
        while (true) {
            Pattern pattern = Pattern.compile("<%css\\s+\"([^\"]+)\"\\s+%>");
            Matcher matcher = pattern.matcher(content);
            if (!matcher.find()) {
                break;
            }
            String cssFileName = matcher.group(1);
            String cssContent = readFileContent(new File(projectDirectory, cssFileName));
            content = content.substring(0, matcher.start()) +
                    String.format("<style>\n%s\n</style>", cssContent) +
                    content.substring(matcher.end());
        }
        return content;
    }

    static void resolveTemplate(File template, File target, File projectDirectory) throws IOException {
        String content = readFileContent(template);
        content = includeCss(content, projectDirectory);
        Files.writeString(target.toPath(), content, StandardCharsets.UTF_8);
    }
}
