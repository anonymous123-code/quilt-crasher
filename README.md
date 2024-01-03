# Quilt Crasher

Utility to debug mods handling crashes. Can intentionally crash Quilt under configurable circumstances.

## Usage

Requires Quilted Fabric API.

There is a [Config File](./src/main/java/io/github/anonymous123_code/quilt_crasher/QuiltCrasherConfig.java) which allows configuring
- When the crash should occur (Title screen, mod init, world load (client side), server join (client side))
- Which other mod ids are required for the crash to occur (For simulating n-way mod incompatibility)
- Which mods are required to fix the incompatibility (All are required, and it is ignored if empty)
- The message in the thrown error

Additionally, there is functionality to generate dummy mods. It is configurable
- How many mods should be generated
- If the button to trigger generation should be shown at all

---

[View Quilt Crasher on Modrinth](https://modrinth.com/mod/quilt-crasher)
