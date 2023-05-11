# README

This layer provides support for the JVMs of the Eclipse Temurin Project for use with
OpenEmbedded and/or Yocto Project build systems. 


## Why prebuilt binaries?

The process of building a JVM binary takes a long time and maintaining it
(e.g. applying patches and testing it) is a lot of work.
The Eclipse Temurin Project makes sure that the produced binaries pass
the relevant Oracle Java Compatibility Kit (JCK), to demonstrate
that the binaries are compatible implementations of the Java specification.


## Dependencies

  URI: https://git.yoctoproject.org/poky/
  branch: kirkstone


## Supported Versions

| JVM / Architecture | arm | aarch64 | x64 |
|--------------------|-----|---------|-----|
| JRE 8              | x   | x       | x   |
| JRE 11             | x   | x       | x   |
| JRE 17             | x   | x       | x   |


## Patches

Please submit any patches against the meta-openjdk-temurin layer via pull requests
on [the project page on GitHub](https://github.com/lucimber/meta-openjdk-temurin).


## Adding the meta-openjdk-temurin layer to your build

Run 'bitbake-layers add-layer meta-openjdk-temurin'


## Participation
Participation is welcome and endorsed by the chosen license
and a simplified contributor agreement.

### Contributor Agreement
As the chosen open source license implicitly serves
as both the inbound (from contributors) and
outbound (to other contributors and users) license,
there's no need for an additional contributor agreement.

But to be super safe, this project requires developers
to state that each commit they make is authorized.
A Developer Certificate of Origin requirement is how many
projects achieve this.

> By making a contribution to this project, I certify that:
> 
> a. The contribution was created in whole or in part by me and I have the right to submit it under the open source license indicated in the file; or
>
> b. The contribution is based upon previous work that, to the best of my knowledge, is covered under an appropriate open source license and I have the right under that license to submit that work with modifications, whether created in whole or in part by me, under the same open source license (unless I am permitted to submit under a different license), as indicated in the file; or
>
> c. The contribution was provided directly to me by some other person who certified (a), (b) or (c) and I have not modified it.
>
> d. I understand and agree that this project and the contribution are public and that a record of the contribution (including all personal information I submit with it, including my sign-off) is maintained indefinitely and may be redistributed consistent with this project or the open source license(s) involved.

Therefore the contributors to this project sign-off that
they adhere to these requirements by adding
a Signed-off-by line to commit messages.

    This is an example commit message.
    
    Signed-off-by: Peter Peterson <pp@example.org>


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