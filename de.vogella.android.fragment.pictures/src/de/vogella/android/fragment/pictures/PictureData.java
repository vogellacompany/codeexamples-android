package de.vogella.android.fragment.pictures;

import java.util.ArrayList;

public class PictureData {

	static public void setupPics(ArrayList<PictureInfo> pics) {
		PictureInfo pic = new PictureInfo();
		pic.name = "Camp";
		pic.resourceId = R.drawable.camp;
		pic.day = "Day 7";
		pic.description = "We were exhausted by the time we reached the camp. Too tired by far to "
				+ "cook dinner or even to set up the tents. It was all I could do to drink the bacon "
				+ "fat left over from that morning's breakfast, then stumble over to the nearest "
				+ "tree root, curl up, and go to sleep.\n\n"
				+ "That's when the werewolves struck.\n\n"
				+ "It wasn't the terror that got to me, or the blood and flying limbs. It was"
				+ "the sound the creatures made as they tore into their victims.\n\n"
				+ "It was all I could do to scurry up the nearest tree and wait out the onslaught.";
		pics.add(pic);

		pic = new PictureInfo();
		pic.name = "Field";
		pic.resourceId = R.drawable.field;
		pic.day = "Day 12";
		pic.description = "The field looked beautiful at first: radiant in the splendor of the "
				+ "sun and the blue sky, waves of grain undulating with the billowing breeze. The "
				+ "wind was, I believe, the main reason that we didn't hear the zombies approaching, "
				+ "even as they fell upon the stragglers in our group.\n\n"
				+ "The valley is not quite as beautiful now, with all the bones and dried blood. "
				+ "It is a place of unspeakable horror and great tragedy.";
		pics.add(pic);

		pic = new PictureInfo();
		pic.name = "River";
		pic.resourceId = R.drawable.river;
		pic.day = "Day 19";
		pic.description = "The cool and almost mystical fog gave the river a haunting and majestic"
				+ "feel. I felt like King Arthur, reaching out toward the Lady of the Lake, "
				+ "ready to bring back Excalibur. This explains why I found myself dipping my hand "
				+ "into the water, which is when the serpents attacked, grabbing my arm and slithering "
				+ "over me to gain entrance to the boat and to rest of the party.\n\n"
				+ "It is fortunate and most surprising that I lost only a hand. Well, a hand "
				+ "and my entire group of travelers.";
		pics.add(pic);
	}

}