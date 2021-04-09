package tv.strohi.stfu.gui;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.nio.file.StandardWatchEventKinds.*;

public class WatcherPrototype {
    WatchService watcher;
    Map<WatchKey, Path> keys;

    public WatcherPrototype() throws IOException, URISyntaxException {
        watcher = FileSystems.getDefault().newWatchService();
        keys = new HashMap<>();

        Path dir = Paths.get("C:\\Users\\marco\\Desktop\\thr");

        try (Stream<Path> walkStream = Files.walk(dir)) {
            walkStream.filter(p -> p.toFile().isFile())
                    .forEach(f -> {
                        if (f.toString().endsWith("mp4")) {
                            System.out.println("MATCH! " + f);
                        } else {
                            System.out.println("NO MATCH! " + f);
                        }
                    });
        }




        try {

            registerRecursive(dir, watcher);

//            WatchKey key = dir.register(watcher,
//                    ENTRY_CREATE,
//                    ENTRY_DELETE,
//                    ENTRY_MODIFY);
        } catch (IOException x) {
            System.err.println(x);
        }


        for (;;) {

            // wait for key to be signaled
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }

            for (WatchEvent<?> event: key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();

                // This key is registered only
                // for ENTRY_CREATE events,
                // but an OVERFLOW event can
                // occur regardless if events
                // are lost or discarded.
                if (kind == OVERFLOW) {
                    continue;
                }

                // The filename is the
                // context of the event.
                WatchEvent<Path> ev = cast(event);

                Path parent = keys.get(key);
                Path filename = ev.context();

                // Verify that the new
                //  file is a text file.
                try {
                    // Resolve the filename against the directory.
                    // If the filename is "test" and the directory is "foo",
                    // the resolved name is "test/foo".
                    Path child = parent.resolve(filename);

                    if (Files.isDirectory(child)) {
                        continue;
                    }

                    if (!Files.probeContentType(child).equals("text/plain")) {
                        System.err.format("New file '%s'" +
                                " is not a plain text file.%n", filename);
                        continue;
                    }
                } catch (IOException x) {
                    System.err.println(x);
                    continue;
                }

                // Email the file to the
                //  specified email alias.
                System.out.format("Emailing file %s%n", filename);
                //Details left to reader....
            }

            // Reset the key -- this step is critical if you want to
            // receive further watch events.  If the key is no longer valid,
            // the directory is inaccessible so exit the loop.
            boolean valid = key.reset();
            if (!valid) {
                break;
            }
        }
    }

    private void registerRecursive(final Path root, WatchService watcher) throws IOException {
        // register all subfolders
        Files.walkFileTree(root, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
                keys.put(key, dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    @SuppressWarnings("unchecked")
    static WatchEvent<Path> cast(WatchEvent<?> event) {
        return (WatchEvent<Path>)event;
    }
}
