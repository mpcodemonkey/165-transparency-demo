# 165-transparency-demo

This is an attempt to display all possible combinations of blending source, destination, test, and equation enumerable cominations currently present in SAGE(The Simple Adaptable Game Engine). This project is based off of a special project undertaken by Jonathan Tinney for Dr. Scott Gordon, in relationship to implementing transparency in SAGE.

This project is currently around 50-60% done.

Features include:
- A simple world that displays a set of hierarchical scene node groups with group controllers.
- varying object types, including both hand-created trimesh objects and obj models
- a menu(press space to access) that displays a blend menu for modifying blend functions on the fly

Missing:
- Multiple SceneNode groups with different blend functions applied to each

Current Objectives:
- Clean up assignment specific code(remove and/or obfuscate)
- Replace object model with one that has a proper vertex mapping
