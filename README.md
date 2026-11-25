# SourceByDevFFnightmare DeObfDeCompByGuideX

Source build of the Purxiy client for Minecraft 1.21.11 (Fabric).

## Credits

- **DevFFNightmare** — original client sources
- **Random** — first pass that got the classes out
- **GuideX** — source recovery and the rebuild that makes it compile

## Build

```bash
./gradlew build
```

Jar lands in `build/libs/`.

Two JDKs are involved. Gradle itself wants **JDK 25** (Loom 1.19.4 needs it), and the
sources compile against **JDK 21**. The 21 toolchain is bundled at
`tools/jdk/jdk-21.0.13.1+2` and referenced from `gradle.properties` by a relative path, so
the tree builds from wherever you unpack it. Delete that folder and point
`org.gradle.java.installations.paths` at your own JDK 21 instead.

First build pulls Minecraft 1.21.11, the intermediary mappings, Fabric Loader 0.18.4 and
Fabric API 0.141.6.

## Layout

```
src/                       Java sources, packages under dev.purxiy.*
resources/                 assets, mixin config, module index
resources/purxiy-offline/  the 65 module jars
decompiled/                raw decompiler output used for rebuilds
tools/                     build helpers and the bundled JDK 21
gradle/, gradlew*          Gradle wrapper 9.9.2
build.gradle               Fabric Loom 1.19.4
```

## Notes

- Package names and the asset namespace stay `dev.purxiy.*` / `assets/purxiy/`. The bundled
  module jars find their shaders and data through those paths, so renaming them breaks the
  modules. Only the name shown in game was changed.
- The sources are written in the intermediary namespace (`class_310`, `method_1551`, …) and
  compile against intermediary mappings, which is why no remap step is needed.
- `./gradlew rebuildOfflineModules` regenerates `resources/purxiy-offline/` from `src/`. The
  `jar` task already depends on it; run it on its own after renaming a class.
- `./gradlew validateModuleEntrypoints` loads all 65 module entrypoints through the real
  classloader.
- The authorisation and integrity checks in the loader are inert. They were already stripped
  from the build these sources came from, so those methods return null/0/false and
  `OfflineBootstrap` does the work.
- Locals are still named `var1`, `var2`, … Most of the tree was recovered from bytecode
  without a local variable table, so there was nothing better to name them after.
