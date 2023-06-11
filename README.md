# Minecraft-Path-AI
A simple number prediction AI in combination with tracking player movements

Start a Minecraft server with the plugin loaded.

Run `server.py` to start Flask server

Use `/setserver <serverURL>` ingame to allow the Minecraft server to send requests to the Flask server

You can now use `/setlocation <name>` to create locations for the plugin to track. You can also use `/removelocation <name>` to delete locations. Doing this command sends a request to the Flask server, and assigning it an ID. The name, ID, and location of points are saved in a `mappings.json` file.

After creating all your points, you can use `/togglecollecting` to start or stop detecting players on points. When a player is detected at one of the points, it will send a request to the server, letting it know. 

// INFO: THE SERVER IS DESIGNED FOR A SPAWN -> CHECKPOINT -> DESTINATION SYSTEM, SO CHANGE IT IF YOU WANT MORE CHECKPOINTS.

After detecting a full route (SPAWN -> CHECKPOINT -> DESTINATION), it will save it in a `dataset.csv` file. I suggest doing at least 30 runs for best results.

Run `main.py` and it will start training using `dataset.csv`

// INFO: THE TRAINING FILE IS ALSO DESIGNED FOR A SPAWN -> CHECKPOINT -> DESTINATION SYSTEM, SO CHANGE IT IF YOU WANT MORE CHECKPOINTS.
