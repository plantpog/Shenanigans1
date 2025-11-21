# Shenanigans1

Small sample app that demonstrates a simple "focus mode" using Java Swing.

## Files

- `src/FocusModeApp.java` — small GUI: a launcher that opens a fullscreen overlay and a small exit window.

## Build & run

From the repository root run:

```bash
javac -d out src/FocusModeApp.java
java -cp out FocusModeApp
# Shenanigans1 / LoveLetter

Small sample app demonstrating a simple fullscreen overlay and a small always-on-top control window, implemented with Java Swing.

## Files

- `src/FocusModeApp.java` — main source: a launcher that opens a fullscreen overlay and a small exit window with an input box.
- `loveletter.jar` / `loveletter-java17.jar` — runnable JARs built from the compiled classes (Java 17 target).

## Quick build & run (native)

From the repository root:

```bash
javac -d out src/FocusModeApp.java
java -cp out FocusModeApp
```

Or run the provided runnable JAR (recommended if you don't want to compile):

```bash
java -jar loveletter-java17.jar   # works on Java 17+
java -jar loveletter.jar          # same contents (rebuilt)
```

## Rebuild the runnable JAR

If you modify `src/FocusModeApp.java`, rebuild and recreate the jars:

```bash
# compile for Java 17
javac --release 17 -d out17 src/FocusModeApp.java
# create jars (Main-Class set in manifest.txt)
jar cfm loveletter-java17.jar manifest.txt -C out17 .
jar cfm loveletter.jar manifest.txt -C out17 .
```


## Notes & safety

- The overlay displays configurable text from the source. Review `src/FocusModeApp.java` before running — the current sample contains placeholder/demo messages which may be offensive; change or remove them as needed.
- The overlay uses `setAlwaysOnTop(true)` and an undecorated fullscreen window; z-order behaviour may vary by OS and window manager.
- If `java -jar` reports an UnsupportedClassVersionError, check your Java runtime version with `java -version` and either install Java 17+ or recompile using `--release` for your target version.

If you want, I can create a native bundle for your OS using `jpackage`, or add an installer script.

Enjoy!