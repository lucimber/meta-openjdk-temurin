# README (branch: main)
This layer provides support for the JVMs of the Eclipse Temurin Project for use with
OpenEmbedded and/or Yocto Project build systems.

> You are reading the `main` branch. It targets the OE-Core **6.x** release series (wrynose and any later 6.x codenames). For earlier series, switch to the matching release branch (`scarthgap` for 5.x, `kirkstone` for 4.x, `dunfell` for 3.x).

## Why prebuilt binaries?
Building a JVM from source takes a long time and maintaining patches/tests is a lot of effort. Eclipse Temurin binaries are JCK-certified and proven compatible with the Java specification.

## Dependencies
* git://git.openembedded.org/openembedded-core
* git://git.openembedded.org/bitbake

If you're using Poky, you're already set up—just clone the branch matching your Yocto codename.

## Adding the layer
```bash
git clone -b <yocto-codename> https://github.com/lucimber/meta-openjdk-temurin.git
bitbake-layers add-layer meta-openjdk-temurin
```

## Supported Yocto Releases
The layer uses one git branch per OE-Core **major version**. Each branch officially supports the LTS (`.0`) release in its series and additionally declares compatibility with every other codename in the same major line, grouped by shared BitBake/OE-Core generation.

| Branch      | Major | Primary (LTS) | Additional compatible codenames               |
| ----------- | ----- | ------------- | ---------------------------------------------- |
| `dunfell`   | 3.x   | dunfell       | zeus, gatesgarth, hardknott, honister          |
| `kirkstone` | 4.x   | kirkstone     | langdale, mickledore, nanbield                 |
| `scarthgap` | 5.x   | scarthgap     | styhead, walnascar, whinlatter                 |
| `main`      | 6.x   | —             | wrynose (only 6.x codename released so far)    |

> Non-LTS codename entries in `LAYERSERIES_COMPAT_meta-openjdk-temurin` are **informal and untested**, offered for user convenience. *Primary support is focused on the LTS release in each major line.*

### Benefits of this grouping:

* **Minimal maintenance burden**: Close alignment in BitBake/core component versions enables common patch reuse.
* **Greater patch stability**: Fewer divergent code paths across grouped releases.
* **Clear support boundaries**: Users of this layer understand which combinations are recommended vs. “use at your own risk.”
* **Coherent branch structure**: Each Git branch aligns with an LTS release and its compatible ecosystem.

> See https://wiki.yoctoproject.org/wiki/Releases for a comprehensive list of Codenames.

## Supported Java Versions
| JVM / Architecture | arm | aarch64 | riscv64 | x64 | x86 |
|--------------------|-----|---------|---------|-----|-----|
| JRE 8              | ✅  | ✅      | ❌      | ✅  | ❌  |
| JRE 11             | ✅  | ✅      | ❌      | ✅  | ❌  |
| JRE 17             | ✅  | ✅      | ✅      | ✅  | ❌  |
| JRE 21             | ❌  | ✅      | ✅      | ✅  | ❌  |

## Participation & Contribution

Contributions are welcome under the included GPL - 2.0 license.
By submitting a PR, you agree to the [Developer Certificate of Origin](https://developercertificate.org):

```
By making a contribution to this project, I certify that:
a. The contribution was made by me under GPL-2.0 (or compatible).
b. If based on prior work, I have the right to submit it under GPL-2.0.
…
Signed-off-by: Your Name <you@example.com>
```
> Either place `Signed-off-by: ...` into the last line of your commit message, or use the `-s` flag togther with the `git commit` command.

## Trademarks
Eclipse Temurin is a trademark of the Eclipse Foundation. Eclipse, and the
Eclipse Logo are registered trademarks of the Eclipse Foundation.

Java and all Java-based trademarks are trademarks of Oracle Corporation in
the United States, other countries, or both.

## License
meta-openjdk-temurin - A software layer as defined by the Yocto Project Layer Model.

Copyright (C) 2023  Lucimber UG

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License along
with this program; if not, write to the Free Software Foundation, Inc.,
51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.

---

*meta - openjdk - temurin is maintained by Lucimber UG, based on work from Eclipse Temurin and the Yocto/OpenEmbedded community.*

