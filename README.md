# Quilt Crasher

A Quilt mod intentionally crashing under configurable circumstances. Intended for debugging crash-fixing mods.

## Usage

Requires Quilted Fabric API.
There is a [Config File](TODO) which allows configuring
- When the crash should occur (Title screen, mod init, world load (client side), server join (client side))
- Which other mod ids are required for the crash to occur (For simulating n-way mod incompatibility)
- Which mods are required to fix the incompatibility (All are required, and it is ignored if empty)
- The message in the thrown error

Additionally, there is (planned) functionality to generate dummy mods.
