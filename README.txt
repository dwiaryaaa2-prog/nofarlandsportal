Build:
  1. Install JDK 21.
  2. Open this folder in IntelliJ IDEA (it will import Gradle), or run: gradle wrapper && ./gradlew build
  3. Jar is in build/libs/nofarlandsportal-1.0.0.jar (not the -sources one)
  4. Put it in mods/ with Fabric Loader + Fabric API.

If Gradle fails on versions, generate a fresh project at https://fabricmc.net/develop/template/
and copy only the src/ folder into it (set the mod id to nofarlandsportal).
