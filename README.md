# README
This layer provides support for the JVMs of the Eclipse Temurin Project for use with
OpenEmbedded and/or Yocto Project build systems. 

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
This layer supports **LTS branches**, and offers **optional compatibility** for certain adjacent releases grouped based on **technical similarity**, not just release sequence. The following table illustrates this using two examples:

| LTS Branch    | Non‑LTS Compatible Releases          |
| ------------- | ------------------------------------ |
| **Kirkstone** | `langdale`, `mickledore`, `nanbield` |
| **Scarthgap** | `styhead`, `walnascar`               |

> These non-LTS entries in `LAYERSERIES_COMPAT_meta-openjdk-temurin` are **informal and untested**, offered for user convenience. *Support remains focused solely on LTS branches.*

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

