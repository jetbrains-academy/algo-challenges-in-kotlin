import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResolveTemplate {
    private final File currentDirectory;
    private final File rootDirectory;
    private final File currentFile;
    private final HashSet<String> includedPathsSoFar;

    public ResolveTemplate(File currentFile, File rootDirectory, HashSet<String> includedPathsSoFar) throws IOException {
        this.currentFile = currentFile;
        this.currentDirectory = currentFile.getParentFile();
        this.rootDirectory = rootDirectory;
        this.includedPathsSoFar = new HashSet<>(includedPathsSoFar);
        this.includedPathsSoFar.add(currentFile.getCanonicalPath());
    }

    public ResolveTemplate(File currentFile, File rootDirectory) throws IOException {
        this(currentFile, rootDirectory, new HashSet<>());
    }


    private static String readFileContent(File file) throws IOException {
        return String.join("\n", Files.readAllLines(file.toPath(), StandardCharsets.UTF_8));
    }

    private File resolvePath(String filePath) {
        boolean absolute = false;
        while (filePath.startsWith("/") || filePath.startsWith(File.separator)) {
            filePath = filePath.substring(1);
            absolute = true;
        }
        if (absolute) {
            return new File(rootDirectory, filePath);
        } else {
            return new File(currentDirectory, filePath);
        }
    }

    private static Matcher findSubstitute(String name, String content) {
        Pattern pattern = Pattern.compile("<%" + name + "\\s+\"([^\"]+)\"\\s+%>");
        return pattern.matcher(content);
    }

    private String substitute(String name, String content, Function<String, String> transformer) throws IOException {
        while (true) {
            Matcher matcher = findSubstitute(name, content);
            if (!matcher.find()) {
                break;
            }
            String cssFileName = matcher.group(1);
            File cssFile = resolvePath(cssFileName);
            String cssContent;
            if (includedPathsSoFar.contains(cssFile.getCanonicalPath())) {
                cssContent = readFileContent(cssFile);
            } else {
                ResolveTemplate resolver = new ResolveTemplate(cssFile, rootDirectory, includedPathsSoFar);
                cssContent = resolver.resolve();
            }
            content = content.substring(0, matcher.start()) +
                    transformer.apply(cssContent) +
                    content.substring(matcher.end());
        }
        return content;
    }

    private String includeCss(String content) throws IOException {
        return substitute("css", content, cssContent -> String.format("<style>\n%s\n</style>", cssContent));
    }

    private String include(String content) throws IOException {
        return substitute("include", content, Function.identity());
    }

    private String resolve() throws IOException {
        String content = readFileContent(currentFile);
        content = includeCss(content);
        content = include(content);
        return content;
    }

    public static void resolveTemplate(File template, File target, File rootDirectory) throws IOException {
        ResolveTemplate resolver = new ResolveTemplate(template, rootDirectory);
        Files.writeString(target.toPath(), resolver.resolve(), StandardCharsets.UTF_8);
    }
}
