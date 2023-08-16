# Use cases of BHS

---

- Create new Bodies
- Add/Remove Bodies to different entities
- Create a new Part
- Add/Remove Parts from Bodies
- Add/Remove Parts Modifiers
- Add/Remove the Damage Handlers 
- Add/Remove the Debufs Handlers 
- Add healing items

---

## Vampire Player

There is a new type of player, VampirePlayer, this new player has the same body as a normal player but **has two wings** to fly and burns with the sun light.

### Working:

VampirePlayers have a special atack, blood sucking, this deals a new **type of damage that should be registered, and a default damage handler should be set**.

A new body must be created for this new type of player, the VampirePlayerBody extends PlayerBody, the change is that **two parts are added to this body**, VampireWings.

VampireWing is a new part that extends the Part class.

A VampirePlayer will be damaged when is exposed to the sun light, VampirePlayerBody **has a handler for that type of damage** that executes special logic.

VampirePlayers are inmune to fall damage so the **handler to that type of damage should be removed from the VampirePlayer.**

When a VampirePlayer gets a VampireWing health to a critical level (threshold) a **debuf will be aplied to the VampirePlayer** not allowing him to fly correctly or fly at all.

**When an entity is created, various conditions must be applied to it to know if one body or another is applied to it**, in this case if the entity is a player with the Vampire Origin, the body of the VampirePlayer will be applied

---

When an `LivingEntity` is created, the `BodyManager` is used to assign to it, if necessary, a `Body`. For this purpose,
multiple `BodyAssignement` are used, which are composed of the `Body` to assign, a list of entities to which to apply 
this assignment, a priority to handle overwrites and a `shouldUse` method where additional logic can be specified. 
These `BodyAssignement` can be created by other mods and will also be generated automatically when parsing the configuration.